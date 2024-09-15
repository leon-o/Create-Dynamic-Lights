package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.world.level.Level;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;

import java.util.ArrayList;
import java.util.List;

public class ContraptionEntityEventHandler {
    private static final List<AbstractContraptionEntity> scheduledToAddContraptionEntities = new ArrayList<>();
    private static boolean lastDynamicLightEnabled = CreateDynLightAllConfigs.client().enableLambDynamicLight.get();

    private static final List<AbstractContraptionEntity> contraptionEntities = new ArrayList<>();

    public static void onContraptionEntityJoin(AbstractContraptionEntity contraptionEntity) {
        contraptionEntities.add(contraptionEntity);
        if(!CreateDynLightAllConfigs.client().enableLambDynamicLight.get())
            return;
        Contraption contraption = contraptionEntity.getContraption();
        if(contraption!=null) {
            addLightSourcesOfContraption(contraption);
            return;
        }

        scheduledToAddContraptionEntities.add(contraptionEntity);
    }

    public static void onContraptionEntityLeave(AbstractContraptionEntity contraptionEntity) {
        contraptionEntities.remove(contraptionEntity);
        CreateDynLightSourceHolder.INSTANCE.removeAll(contraptionEntity);
    }

    private static void addLightSourcesOfContraption(Contraption contraption) {
        var blocks = contraption.getBlocks();
        blocks.forEach((pos, blockInfo) -> {
            if (blockInfo.state().getLightEmission() > 0) {
                CreateDynLightSourceHolder.INSTANCE.getOrCreate(contraption.entity, blockInfo.pos(), blockInfo.state().getLightEmission());
            }
        });
    }

    public static void onTick(Level level) {
        var toRemove = new ArrayList<AbstractContraptionEntity>();
        for (AbstractContraptionEntity entity : scheduledToAddContraptionEntities) {
            Contraption contraption = entity.getContraption();
            if (contraption != null) {
                addLightSourcesOfContraption(contraption);
                toRemove.add(entity);
            }
        }
        scheduledToAddContraptionEntities.removeAll(toRemove);

        var curEnabled = CreateDynLightAllConfigs.client().enableLambDynamicLight.get();
        if(curEnabled != lastDynamicLightEnabled) {
            onDynamicLightEnabledChanged();
            lastDynamicLightEnabled = curEnabled;
        }
    }

    public static void onDynamicLightEnabledChanged() {
        var curEnabled = CreateDynLightAllConfigs.client().enableLambDynamicLight.get();
        if(curEnabled) {
            for (AbstractContraptionEntity entity : contraptionEntities) {
                Contraption contraption = entity.getContraption();
                if (contraption != null) {
                    addLightSourcesOfContraption(contraption);
                }
            }
        }else {
            scheduledToAddContraptionEntities.clear();
            for (AbstractContraptionEntity entity : contraptionEntities) {
                CreateDynLightSourceHolder.INSTANCE.removeAll(entity);
            }
        }
    }
}
