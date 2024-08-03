package top.leonx.dynlight.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.lamb.ContraptionEntityEventHandler;

@Mod.EventBusSubscriber(modid = CreateDynLight.MOD_ID)
public class ClientModEventHandler {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityJoin(contraptionEntity);
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveWorld(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityLeave(contraptionEntity);
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.LevelTickEvent event) {
        if (!event.level.isClientSide()) {
            return;
        }
        if (event.phase == TickEvent.Phase.END) {
            ContraptionEntityEventHandler.onTick(event.level);
        }
    }
}
