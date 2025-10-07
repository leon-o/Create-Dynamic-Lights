package top.leonx.dynlight;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.api.registry.SimpleRegistry;
import com.simibubi.create.content.contraptions.behaviour.DoorMovingInteraction;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.ModLoadingContext;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;

import java.util.ArrayList;
import java.util.Collection;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateDynLight.MODID)
public class CreateDynLight {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "createdynlight";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CreateDynLight(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        CreateDynLightAllConfigs.register(modLoadingContext, modContainer);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
//        event.enqueueWork(()->{
//            var list = new ArrayList<Block>();
//            for (Block block : BuiltInRegistries.BLOCK.stream().toList()) {
//                if (block != null) {
//                    @SuppressWarnings("deprecation")
//                    var lightEmission = block.defaultBlockState().getLightEmission();
//                    if (lightEmission > 0)
//                    {
//                        list.add(block);
//                    }
//                }
//            }
//            registerBehaviours(list);
//        });

        event.enqueueWork(()->{
            MovementBehaviour.REGISTRY.registerProvider(new LightBehaviourProvider());
        });
    }

    public static void registerBehaviours(Collection<Block> blocks){
        CreateDynLight.LOGGER.info("Registering DynLightMovementBehaviours");
        blocks.forEach(block -> {
            var lightEmission = block.defaultBlockState().getLightEmission();
            if (MovementBehaviour.REGISTRY.get(block) == null) {
                MovementBehaviour.REGISTRY.register(block, new LightMovementBehaviour(lightEmission));
            }
        });
        CreateDynLight.LOGGER.info("Registered LightMovementBehaviour for [{}]", String.join(", ", blocks.stream().map(Block::getDescriptionId).toList()));
    }
}
