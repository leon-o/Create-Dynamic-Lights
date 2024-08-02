package top.leonx.dynlight.lamb.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import dev.lambdaurora.lambdynlights.DynamicLightSource;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceFabric extends CreateDynLightSource implements DynamicLightSource {
    public CreateDynLightSourceFabric(int id, AbstractContraptionEntity entity, BlockPos localPos, int luminance) {
        super(id, entity, localPos, luminance);
    }

    @Override
    public double getDynamicLightX() {
        return super.getDynamicLightX();
    }

    @Override
    public double getDynamicLightY() {
        return super.getDynamicLightY();
    }

    @Override
    public double getDynamicLightZ() {
        return super.getDynamicLightZ();
    }

    @Override
    public Level getDynamicLightWorld() {
        return super.getDynamicLightWorld();
    }

    @Override
    public void resetDynamicLight() {
        super.resetDynamicLight();
    }

    @Override
    public int getLuminance() {
        return super.getLuminance();
    }

    @Override
    public void dynamicLightTick() {
        super.dynamicLightTick();
    }

    @Override
    public boolean shouldUpdateDynamicLight() {
        return super.shouldUpdateDynamicLight();
    }

    @Override
    public boolean lambdynlights$updateDynamicLight(@NotNull LevelRenderer levelRenderer) {
        return super.lambdynlights$updateDynamicLight(levelRenderer);
    }

    @Override
    public void lambdynlights$scheduleTrackedChunksRebuild(@NotNull LevelRenderer levelRenderer) {
        super.lambdynlights$scheduleTrackedChunksRebuild(levelRenderer);
    }
}
