package top.leonx.dynlight.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.leonx.dynlight.CreateDynLight;

@Mod.EventBusSubscriber(modid = CreateDynLight.MOD_ID)
public class ClientModEventHandler {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            CreateDynLight.scheduleAddLightSourcesOfContraptionEntity(contraptionEntity);
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveWorld(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            CreateDynLight.removeLightSourcesOfContraptionEntity(contraptionEntity);
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            CreateDynLight.onTick();
        }
    }
}
