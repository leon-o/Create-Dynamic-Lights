package top.leonx.dynlight;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import top.leonx.dynlight.lamb.ContraptionEntityEventHandler;

@EventBusSubscriber
public class LambModEventHandler {

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
    public static void onTick(LevelTickEvent.Post event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }
        ContraptionEntityEventHandler.onTick(event.getLevel());
    }
}
