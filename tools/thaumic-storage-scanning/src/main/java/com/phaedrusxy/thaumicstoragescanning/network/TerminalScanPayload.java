package com.phaedrusxy.thaumicstoragescanning.network;

import com.phaedrusxy.thaumicstoragescanning.ThaumicStorageScanning;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanTarget;
import com.leclowndu93150.thaumaturge.api.research.scan.ScanningManager;
import com.leclowndu93150.thaumaturge.content.item.ThaumometerItem;
import com.tom.storagemod.block.entity.StorageTerminalBlockEntity;
import com.tom.storagemod.inventory.TerminalItemStack;
import com.tom.storagemod.menu.StorageTerminalMenu;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record TerminalScanPayload(ItemStack stack) implements CustomPacketPayload {
    public static final Type<TerminalScanPayload> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(ThaumicStorageScanning.MOD_ID, "scan_terminal_item"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TerminalScanPayload> STREAM_CODEC =
            StreamCodec.composite(ItemStack.STREAM_CODEC, TerminalScanPayload::stack, TerminalScanPayload::new);

    private static final int MIN_SCAN_INTERVAL_TICKS = 20;
    private static final Map<UUID, Long> LAST_SCAN_TICK = new ConcurrentHashMap<>();

    public static void handle(TerminalScanPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }
            if (!(player.containerMenu instanceof StorageTerminalMenu menu)) {
                return;
            }
            if (!(menu.getCarried().getItem() instanceof ThaumometerItem)) {
                return;
            }
            if (menu.storageSlotList.isEmpty()) {
                return;
            }

            StorageTerminalBlockEntity terminal = menu.storageSlotList.get(0).inventory;
            if (terminal == null || !terminal.canInteractWith(player, true)) {
                return;
            }

            ItemStack requested = payload.stack();
            if (requested == null || requested.isEmpty()) {
                return;
            }

            ItemStack verified = ItemStack.EMPTY;
            for (TerminalItemStack stored : terminal.getStacks().values()) {
                if (stored != null && stored.getQuantity() > 0
                        && ItemStack.isSameItemSameComponents(stored.getStack(), requested)) {
                    verified = stored.getStack().copyWithCount(1);
                    break;
                }
            }
            if (verified.isEmpty()) {
                return;
            }

            long now = player.level().getGameTime();
            long last = LAST_SCAN_TICK.getOrDefault(player.getUUID(), Long.MIN_VALUE / 2);
            if (now - last < MIN_SCAN_INTERVAL_TICKS) {
                return;
            }
            LAST_SCAN_TICK.put(player.getUUID(), now);

            ScanningManager.scan(player, ScanTarget.stack(verified));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
