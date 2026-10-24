package com.phaedrusxy.thaumicstoragescanning.client;

import com.phaedrusxy.thaumicstoragescanning.ThaumicStorageScanning;
import com.phaedrusxy.thaumicstoragescanning.network.TerminalScanPayload;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanTarget;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanningManager;
import com.leclowndu93150.thaumaturge.content.item.ThaumometerItem;
import com.tom.storagemod.screen.AbstractStorageTerminalScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

@EventBusSubscriber(modid = ThaumicStorageScanning.MOD_ID, value = Dist.CLIENT)
public final class TerminalScanClient {
    private static final int SCAN_TICKS = 25;
    private static final int PROGRESS_COLOR = 0xFFFFAA00;
    private static final int CURSOR_TOP_OFFSET = -20;

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

        if (attempted || !ScanningManager.isStillScannable(player, ScanTarget.stack(target))) {
            ticks = 0;
            return;
        }

        ticks++;
        if (ticks >= SCAN_TICKS) {
            ClientPacketDistributor.sendToServer(new TerminalScanPayload(target.copyWithCount(1)));
            ticks = 0;
            attempted = true;
        }
    }

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null || !(event.getScreen() instanceof AbstractStorageTerminalScreen<?>)) {
            return;
        }

        ItemStack hovered = hoveredNetworkStack(minecraft);
        if (hovered.isEmpty() || !hasThaumometerOnCursor(player) || attempted || ticks <= 0) {
            return;
        }

        int dots = Math.max(1, Math.min(3, (ticks * 3 / SCAN_TICKS) + 1));
        Component progress = Component.translatable("gui.thaumaturge.scan.scanning")
                .append(".".repeat(dots))
                .withStyle(ChatFormatting.GOLD);

        GuiGraphicsExtractor graphics = event.getGuiGraphics();
        int x = event.getMouseX() - minecraft.font.width(progress) / 2;
        int y = event.getMouseY() + CURSOR_TOP_OFFSET;
        graphics.text(minecraft.font, progress, x, y, PROGRESS_COLOR, true);
    }

    private static boolean hasThaumometerOnCursor(LocalPlayer player) {
        return player.containerMenu.getCarried().getItem() instanceof ThaumometerItem;
    }

    private static ItemStack hoveredNetworkStack(Minecraft minecraft) {
        if (!(minecraft.screen instanceof AbstractStorageTerminalScreen<?> screen)) {
            return ItemStack.EMPTY;
        }
        Slot slot = screen.getHoveredSlot();
        if (slot == null || !slot.hasItem() || slot.x != Integer.MIN_VALUE || slot.y != Integer.MIN_VALUE) {
            return ItemStack.EMPTY;
        }
        return slot.getItem();
    }

    private static boolean sameTarget(ItemStack a, ItemStack b) {
        return !a.isEmpty() && !b.isEmpty() && ItemStack.isSameItemSameComponents(a, b);
    }

    private static void reset() {
        target = ItemStack.EMPTY;
        ticks = 0;
        attempted = false;
    }
}
