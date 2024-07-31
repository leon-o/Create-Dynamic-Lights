package top.leonx.dynlight.lamb.forge;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import dev.lambdaurora.lambdynlights.DynamicLightSource;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceForge extends CreateDynLightSource implements DynamicLightSource {
    public CreateDynLightSourceForge(int id, AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        super(id, entity, localPos, luminance);
    }

    @Override
    public double tdv$getDynamicLightX() {
        return super.getDynamicLightX();
    }

    @Override
    public double tdv$getDynamicLightY() {
        return super.getDynamicLightY();
    }

    @Override
    public double tdv$getDynamicLightZ() {
        return super.getDynamicLightZ();
    }

    @Override
    public Level tdv$getDynamicLightWorld() {
        return super.getDynamicLightWorld();
    }

    @Override
    public void tdv$resetDynamicLight() {
        super.resetDynamicLight();
    }

    @Override
    public int tdv$getLuminance() {
        return super.getLuminance();
    }

    @Override
    public void tdv$dynamicLightTick() {
        super.dynamicLightTick();
    }

    @Override
    public boolean tdv$shouldUpdateDynamicLight() {
        return super.shouldUpdateDynamicLight();
    }

    @Override
    public boolean tdv$lambdynlights$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {
        return super.lambdynlights$updateDynamicLight(levelRenderer);
    }

    @Override
    public void tdv$lambdynlights$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {
        super.lambdynlights$scheduleTrackedChunksRebuild(levelRenderer);
    }
}
