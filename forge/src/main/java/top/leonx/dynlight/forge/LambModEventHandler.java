package top.leonx.dynlight.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import top.leonx.dynlight.lamb.ContraptionEntityEventHandler;


public class LambModEventHandler {

    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityJoin(contraptionEntity);
        }
    }


    public static void onEntityLeaveWorld(EntityLeaveLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            return;
        }

        var entity = event.getEntity();
        if (entity instanceof AbstractContraptionEntity contraptionEntity) {
            ContraptionEntityEventHandler.onContraptionEntityLeave(contraptionEntity);
        }
    }


    public static void onTick(TickEvent.LevelTickEvent event) {
        if (!event.level.isClientSide()) {
            return;
        }
        if (event.phase == TickEvent.Phase.END) {
            ContraptionEntityEventHandler.onTick(event.level);
        }
    }
}
