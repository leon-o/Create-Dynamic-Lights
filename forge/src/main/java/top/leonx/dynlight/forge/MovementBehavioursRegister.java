package top.leonx.dynlight.forge;

import com.simibubi.create.AllMovementBehaviours;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.LightMovementBehaviour;

public class MovementBehavioursRegister {
    public static void RegisterAll(){
        CreateDynLight.LOGGER.info("Registering DynLightMovementBehaviours");
        for (ResourceLocation blockRegistryName : ForgeRegistries.BLOCKS.getKeys()) {
            Block block = ForgeRegistries.BLOCKS.getValue(blockRegistryName);
            if (block != null) {
                //noinspection deprecation
                var lightEmission = block.defaultBlockState().getLightEmission();
                AllMovementBehaviours.registerBehaviour(block, new LightMovementBehaviour(lightEmission));
            }
        }
    }
}
