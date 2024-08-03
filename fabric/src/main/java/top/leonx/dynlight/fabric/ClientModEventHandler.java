package top.leonx.dynlight.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import top.leonx.dynlight.lamb.ContraptionEntityEventHandler;

public class ClientModEventHandler {
    public static void register(){
        ClientEntityEvents.ENTITY_LOAD.register(ClientModEventHandler::onClientEntityLoad);
        ClientEntityEvents.ENTITY_UNLOAD.register(ClientModEventHandler::onClientEntityUnload);
        ClientTickEvents.END_WORLD_TICK.register(ClientModEventHandler::onTick);
    }

    private static void onClientEntityLoad(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityJoin(contraptionEntity);
        }
    }

    private static void onClientEntityUnload(Entity entity, ClientLevel clientLevel) {
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityLeave(contraptionEntity);
        }
    }

    private static void onTick(ClientLevel clientLevel) {
        ContraptionEntityEventHandler.onTick(clientLevel);
    }
}
