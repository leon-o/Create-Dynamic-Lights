package top.leonx.dynlight.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.fabric.CreateDynLightAllConfigsImpl;

import java.util.ArrayList;

public final class CreateDynLightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateDynLightAllConfigsImpl.register();
        var list = new ArrayList<Block>();
        for (var block : BuiltInRegistries.BLOCK) {
            var defaultBlockState = block.defaultBlockState();
            if (defaultBlockState.getLightEmission() > 0) {
                list.add(block);
            }
        }
        CreateDynLight.registerBehaviours(list);
        ModEventHandler.register();
    }
}
