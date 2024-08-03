package top.leonx.dynlight.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import top.leonx.dynlight.fabric.ClientModEventHandler;

public final class CreateDynLightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientModEventHandler.register();
    }
}
