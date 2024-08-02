package top.leonx.dynlight.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.forge.CreateDynLightAllConfigsImpl;

import java.util.ArrayList;

@Mod(CreateDynLight.MOD_ID)
public final class CreateDynLightForge {

    public CreateDynLightForge()
    {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        CreateDynLightAllConfigsImpl.register(modLoadingContext);
        CreateDynLightAllPackets.registerPackets();

        // Register the setup method for modloading
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(EventPriority.LOWEST, this::commonSetup);

        modEventBus.addListener(this::modInit);
    }

    private void setup(FMLClientSetupEvent t) {
        CreateDynLight.LOGGER.info("CreateDynLight Initialized");
    }

    private void commonSetup(FMLCommonSetupEvent evt){
        evt.enqueueWork(()->{
            var list = new ArrayList<Block>();
            for (ResourceLocation blockRegistryName : ForgeRegistries.BLOCKS.getKeys()) {
                Block block = ForgeRegistries.BLOCKS.getValue(blockRegistryName);
                if (block != null) {
                    @SuppressWarnings("deprecation")
                    var lightEmission = block.defaultBlockState().getLightEmission();
                    if (lightEmission > 0)
                    {
                        list.add(block);
                    }
                }
            }
            CreateDynLight.registerBehaviours(list);
        });
    }

    private void modInit(FMLLoadCompleteEvent evt){
        CreateDynLight.LOGGER.info("CreateDynLight Loaded");
    }
}
