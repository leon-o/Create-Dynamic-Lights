package top.leonx.dynlight.fabric;

import net.fabricmc.api.ModInitializer;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.fabric.CreateDynLightAllConfigsImpl;

public final class CreateDynLightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateDynLightAllConfigsImpl.register();
        CreateDynLight.registerGlobalBehaviourProvider();
    }
}
