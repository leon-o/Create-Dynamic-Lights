package top.leonx.dynlight.forge.lamb;

import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.forge.CreateDynLightAllConfigsImpl;
import top.leonx.dynlight.forge.CreateDynLightAllPackets;
import top.leonx.dynlight.forge.MovementBehavioursRegister;

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
        evt.enqueueWork(MovementBehavioursRegister::RegisterAll);
    }

    private void modInit(FMLLoadCompleteEvent evt){
        CreateDynLight.LOGGER.info("CreateDynLight Loaded");
    }
}
