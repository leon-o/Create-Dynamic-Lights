package top.leonx.dynlight.lamb.forge;

import dev.lambdaurora.lambdynlights.DynamicLightSource;
import dev.lambdaurora.lambdynlights.LambDynLights;
import dev.lambdaurora.lambdynlights.config.DynamicLightsConfig;
import dev.lambdaurora.lambdynlights.config.QualityMode;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.lamb.CreateDynLightSource;

import java.util.Objects;

public class LambDynLightsDelegateImpl {

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        LambDynLights.scheduleChunkRebuild(levelRenderer, pos);
    }


    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet lambdynlights$trackedLitChunkPos, LongOpenHashSet newPos) {
        LambDynLights.updateTrackedChunks(chunkPos, lambdynlights$trackedLitChunkPos, newPos);
    }

    public static void scheduleChunkRebuild(LevelRenderer renderer, BlockPos.MutableBlockPos chunkPos) {
        LambDynLights.scheduleChunkRebuild(renderer, chunkPos);
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource forgeLightSource) {
            LambDynLights.get().addLightSource(forgeLightSource);
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource forgeLightSource) {
            LambDynLights.get().removeLightSource(forgeLightSource);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        QualityMode mode = DynamicLightsConfig.Quality.get();
        return !Objects.equals(mode, QualityMode.OFF);
    }

    public static int getDynamicLightsModeDelay(){
        QualityMode mode = DynamicLightsConfig.Quality.get();
        if (Objects.equals(mode, QualityMode.SLOW)) {
            return 500;
        } else if (Objects.equals(mode, QualityMode.FAST)) {
            return 200;
        } else {
            return 0;
        }
    }
}
