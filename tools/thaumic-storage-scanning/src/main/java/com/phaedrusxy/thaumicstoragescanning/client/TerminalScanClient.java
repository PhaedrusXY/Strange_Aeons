package com.phaedrusxy.thaumicstoragescanning.client;

import com.phaedrusxy.thaumicstoragescanning.ThaumicStorageScanning;
import com.phaedrusxy.thaumicstoragescanning.network.TerminalScanPayload;
import com.leclowndu93150.thaumaturge.api.aspect.AspectIndexAccess;
import com.leclowndu93150.thaumaturge.api.aspect.AspectInstance;
import com.leclowndu93150.thaumaturge.api.aspect.AspectList;
import com.leclowndu93150.thaumaturge.api.capability.KnowledgeAccess;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanKeys;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanTarget;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanningManager;
import com.leclowndu93150.thaumaturge.client.render.aspect.AspectTagRenderer;
import com.leclowndu93150.thaumaturge.client.screen.TTTooltips;
import com.leclowndu93150.thaumaturge.content.item.ThaumometerItem;
import com.leclowndu93150.thaumaturge.content.research.pool.AspectPools;
import com.leclowndu93150.thaumaturge.network.ServerboundScanSlotPayload;
import com.leclowndu93150.thaumaturge.registry.TTSounds;
import com.tom.storagemod.screen.AbstractStorageTerminalScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.jspecify.annotations.Nullable;

@EventBusSubscriber(modid = ThaumicStorageScanning.MOD_ID, value = Dist.CLIENT)
public final class TerminalScanClient {
    private static final int SOUND_INTERVAL = 2;
    private static final float SOUND_VOLUME = 0.2F;
    private static final float SOUND_PITCH = 0.45F;
    private static final float SOUND_PITCH_SPREAD = 0.1F;
    private static final int CURSOR_TOP_OFFSET = -20;
    private static final int TAG_TOP_OFFSET = -28;
    private static final int TAG_SPACING = 18;
    private static final int PROGRESS_COLOR = 0xFFFFAA00;
    private static final int HINT_COLOR = 0xFFAA00AA;

    private static ItemStack target = ItemStack.EMPTY;
    private static int ticks;
    private static boolean attempted;

    private TerminalScanClient() {}

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        ItemStack hovered = hoveredNetworkStack(minecraft);

        if (player == null || hovered.isEmpty() || !hasThaumometerOnCursor(player)) {
            reset();
            return;
        }

        if (!sameTarget(target, hovered)) {
            target = hovered.copyWithCount(1);
            ticks = 0;
            attempted = false;
        }

        ScanTarget scanTarget = ScanTarget.stack(target);
        if (attempted || !ScanningManager.isStillScannable(player, scanTarget)
                || studyHint(player, aspectsOf(target)) != null) {
            ticks = 0;
            return;
        }

        ticks++;
        if (ticks % SOUND_INTERVAL == 0) {
            minecraft.getSoundManager().play(SimpleSoundInstance.forUI(
                    TTSounds.CAMERA_TICKS.get(),
                    SOUND_PITCH + player.getRandom().nextFloat() * SOUND_PITCH_SPREAD,
                    SOUND_VOLUME));
        }

        if (ticks >= ServerboundScanSlotPayload.CHANNEL_TICKS) {
            ClientPacketDistributor.sendToServer(new TerminalScanPayload(target.copyWithCount(1)));
            ticks = 0;
            attempted = true;
        }
    }

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || !(event.getScreen() instanceof AbstractStorageTerminalScreen<?>)
                || !hasThaumometerOnCursor(player)) {
            return;
        }

        ItemStack hovered = hoveredNetworkStack(minecraft);
        if (hovered.isEmpty()) {
            return;
        }

        GuiGraphicsExtractor graphics = event.getGuiGraphics();
        int top = event.getMouseY() + CURSOR_TOP_OFFSET;

        if (ticks > 0 && !attempted) {
            Component progress = TTTooltips.scanning(
                    ticks / (float) ServerboundScanSlotPayload.CHANNEL_TICKS);
            graphics.text(
                    minecraft.font,
                    progress,
                    event.getMouseX() - minecraft.font.width(progress) / 2,
                    top,
                    PROGRESS_COLOR,
                    true);
            return;
        }

        renderKnownAspects(
                graphics,
                minecraft,
                player,
                hovered,
                event.getMouseX(),
                event.getMouseY() + TAG_TOP_OFFSET);

        Component hint = studyHint(player, aspectsOf(hovered));
        if (hint != null) {
            graphics.text(
                    minecraft.font,
                    hint,
                    event.getMouseX() - minecraft.font.width(hint) / 2,
                    top,
                    HINT_COLOR,
                    true);
        }
    }

    private static void renderKnownAspects(
            GuiGraphicsExtractor graphics,
            Minecraft minecraft,
            LocalPlayer player,
            ItemStack stack,
            int centreX,
            int y) {
        if (!KnowledgeAccess.of(player).isResearchKnown(ScanKeys.item(stack.getItem()))) {
            return;
        }

        AspectList aspects = aspectsOf(stack);
        if (aspects.isEmpty()) {
            return;
        }

        int x = centreX
                - (aspects.entries().size() * TAG_SPACING
                - (TAG_SPACING - AspectTagRenderer.TAG_SIZE)) / 2;
        for (AspectInstance entry : aspects.entries()) {
            AspectTagRenderer.render(
                    graphics,
                    minecraft.font,
                    x,
                    y,
                    entry.aspect(),
                    entry.amount());
            x += TAG_SPACING;
        }
    }

    private static @Nullable Component studyHint(LocalPlayer player, AspectList aspects) {
        for (AspectInstance entry : aspects.entries()) {
            if (!AspectPools.hasDiscoveredComponents(player, entry.aspect())) {
                return AspectPools.missingComponentHint(player, entry.aspect());
            }
        }
        return null;
    }

    private static AspectList aspectsOf(ItemStack stack) {
        return AspectIndexAccess.index().of(stack);
    }

    private static boolean hasThaumometerOnCursor(LocalPlayer player) {
        return player.containerMenu.getCarried().getItem() instanceof ThaumometerItem;
    }

    private static ItemStack hoveredNetworkStack(Minecraft minecraft) {
        if (!(minecraft.screen instanceof AbstractStorageTerminalScreen<?> screen)) {
            return ItemStack.EMPTY;
        }
        Slot slot = screen.getHoveredSlot();
        if (slot == null || !slot.hasItem()
                || slot.x != Integer.MIN_VALUE || slot.y != Integer.MIN_VALUE) {
            return ItemStack.EMPTY;
        }
        return slot.getItem();
    }

    private static boolean sameTarget(ItemStack a, ItemStack b) {
        return !a.isEmpty() && !b.isEmpty()
                && ItemStack.isSameItemSameComponents(a, b);
    }

    private static void reset() {
        target = ItemStack.EMPTY;
        ticks = 0;
        attempted = false;
    }
}
