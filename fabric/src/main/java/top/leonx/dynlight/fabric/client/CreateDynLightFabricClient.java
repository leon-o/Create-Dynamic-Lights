package top.leonx.dynlight.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import top.leonx.dynlight.fabric.LambModEventHandler;

public final class CreateDynLightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // If there is LambDynamicLights, register the event handler
        if (FabricLoader.getInstance().isModLoaded("lambdynlights")) {
            LambModEventHandler.register();
        }
    }
}
