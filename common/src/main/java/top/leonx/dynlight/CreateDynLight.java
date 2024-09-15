package top.leonx.dynlight;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllMovementBehaviours;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;

import java.util.Collection;


public class CreateDynLight {
    public static final String MOD_ID = "createdynlight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @Deprecated
    public static void registerBehaviours(Collection<Block> blocks){
        CreateDynLight.LOGGER.info("Registering DynLightMovementBehaviours");
        blocks.forEach(block -> {
            var lightEmission = block.defaultBlockState().getLightEmission();
            AllMovementBehaviours.registerBehaviour(block, new LightMovementBehaviour(lightEmission));
        });
        CreateDynLight.LOGGER.info("Registered LightMovementBehaviour for [{}]", String.join(", ", blocks.stream().map(Block::getDescriptionId).toList()));
    }

    public static void registerGlobalBehaviourProvider(){
        var provider = new LightBehaviourProvider();
        AllMovementBehaviours.registerBehaviourProvider(provider);
        CreateDynLight.LOGGER.info("Registered LightBehaviourProvider");
    }
}
