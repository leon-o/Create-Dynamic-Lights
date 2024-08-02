package top.leonx.dynlight.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import top.leonx.dynlight.CreateDynLight;

public class ModEventHandler {
    public static void register(){
        ClientEntityEvents.ENTITY_LOAD.register(ModEventHandler::onClientEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(ModEventHandler::onClientEntityUnload);
        ClientTickEvents.END_WORLD_TICK.register(ModEventHandler::onTick);
    }

    private static void onClientEntityLoad(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            CreateDynLight.scheduleAddLightSourcesOfContraptionEntity(contraptionEntity);
        }
    }

    private static void onClientEntityUnload(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            CreateDynLight.removeLightSourcesOfContraptionEntity(contraptionEntity);
        }
    }

    private static void onTick(ClientLevel clientLevel) {
        CreateDynLight.onTick();
    }
}
