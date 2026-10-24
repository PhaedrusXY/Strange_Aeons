package com.phaedrusxy.thaumicstoragescanning.network;

import com.phaedrusxy.thaumicstoragescanning.ThaumicStorageScanning;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ThaumicStorageScanning.MOD_ID)
public final class ModNetworking {
    private static final String PROTOCOL_VERSION = "1";

    private ModNetworking() {}

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);
        registrar.playToServer(TerminalScanPayload.TYPE, TerminalScanPayload.STREAM_CODEC, TerminalScanPayload::handle);
    }
}
