package top.leonx.dynlight.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.fabric.CreateDynLightAllConfigsImpl;

import java.util.ArrayList;

public final class CreateDynLightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateDynLightAllConfigsImpl.register();
        CreateDynLight.registerGlobalBehaviourProvider();
    }
}
