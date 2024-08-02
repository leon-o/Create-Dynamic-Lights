package top.leonx.dynlight;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.slf4j.Logger;
import top.leonx.dynlight.lamb.CreateDynLightSourceHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class CreateDynLight {
    public static final String MOD_ID = "createdynlight";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final List<AbstractContraptionEntity> scheduledToAddContraptionEntities = new ArrayList<>();

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static void registerBehaviours(Collection<Block> blocks){
        CreateDynLight.LOGGER.info("Registering DynLightMovementBehaviours");
        blocks.forEach(block -> {
            var lightEmission = block.defaultBlockState().getLightEmission();
            AllMovementBehaviours.registerBehaviour(block, new LightMovementBehaviour(lightEmission));
        });
        CreateDynLight.LOGGER.info("Registered LightMovementBehaviour for [" + String.join(", ", blocks.stream().map(Block::getDescriptionId).toList()) + "]");
    }

    public static void scheduleAddLightSourcesOfContraptionEntity(AbstractContraptionEntity contraptionEntity) {
        Contraption contraption = contraptionEntity.getContraption();
        if(contraption!=null) {
            addLightSourcesOfContraption(contraption);
            return;
        }

        scheduledToAddContraptionEntities.add(contraptionEntity);
    }

    private static void addLightSourcesOfContraption(Contraption contraption) {
        Map<BlockPos, StructureTemplate.StructureBlockInfo> blocks = contraption.getBlocks();
        blocks.forEach((pos, blockInfo) -> {
            if (blockInfo.state().getLightEmission() > 0) {
                CreateDynLightSourceHolder.INSTANCE.getOrCreate(contraption.entity, blockInfo.pos(), blockInfo.state().getLightEmission());
            }
        });
    }

    public static void removeLightSourcesOfContraptionEntity(AbstractContraptionEntity contraptionEntity) {
        CreateDynLightSourceHolder.INSTANCE.removeAll(contraptionEntity);
    }

    public static void onTick() {
        var toRemove = new ArrayList<AbstractContraptionEntity>();
        for (AbstractContraptionEntity entity : scheduledToAddContraptionEntities) {
            Contraption contraption = entity.getContraption();
            if (contraption != null) {
                addLightSourcesOfContraption(contraption);
                toRemove.add(entity);
            }
        }
        scheduledToAddContraptionEntities.removeAll(toRemove);
    }
}
