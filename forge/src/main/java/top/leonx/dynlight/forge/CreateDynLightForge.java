package top.leonx.dynlight.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.forge.CreateDynLightAllConfigsImpl;

@Mod(CreateDynLight.MOD_ID)
public final class CreateDynLightForge {

    public CreateDynLightForge()
    {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        CreateDynLightAllConfigsImpl.register(modLoadingContext);

        // Register the setup method for modloading
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(EventPriority.LOWEST, this::commonSetup);

        modEventBus.addListener(this::modInit);
    }

    private void setup(FMLClientSetupEvent t) {
        CreateDynLight.LOGGER.info("CreateDynLight Initialized");

        if (ModList.get().isLoaded("dynamiclightsreforged")) {
            t.enqueueWork(()->{
                var forgeEventBus = MinecraftForge.EVENT_BUS;
                forgeEventBus.addListener(LambModEventHandler::onEntityJoinWorld);
                forgeEventBus.addListener(LambModEventHandler::onEntityLeaveWorld);
                forgeEventBus.addListener(LambModEventHandler::onTick);
            });
        }
    }

    private void commonSetup(FMLCommonSetupEvent evt){
        evt.enqueueWork(CreateDynLight::registerGlobalBehaviourProvider);
    }

    private void modInit(FMLLoadCompleteEvent evt){
        CreateDynLight.LOGGER.info("CreateDynLight Loaded");
    }
}
