package top.leonx.dynlight.fabric;

import net.fabricmc.api.ModInitializer;
import top.leonx.dynlight.CreateDynLight;

public final class CreateDynLightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        CreateDynLight.init();
    }
}
