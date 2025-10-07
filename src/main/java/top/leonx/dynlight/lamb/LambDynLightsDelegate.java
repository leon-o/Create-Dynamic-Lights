package top.leonx.dynlight.lamb;


import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import toni.sodiumdynamiclights.DynamicLightSource;
import toni.sodiumdynamiclights.DynamicLightsConfig;
import toni.sodiumdynamiclights.DynamicLightsMode;
import toni.sodiumdynamiclights.SodiumDynamicLights;

import java.util.Objects;

public class LambDynLightsDelegate {

    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        SodiumDynamicLights.scheduleChunkRebuild(levelRenderer, pos);
    }


    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet lambdynlights$trackedLitChunkPos, LongOpenHashSet newPos) {
        SodiumDynamicLights.updateTrackedChunks(chunkPos, lambdynlights$trackedLitChunkPos, newPos);
    }

    public static void scheduleChunkRebuild(LevelRenderer renderer, BlockPos.MutableBlockPos chunkPos) {
        SodiumDynamicLights.scheduleChunkRebuild(renderer, chunkPos);
    }

    public static void addLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource forgeLightSource && SodiumDynamicLights.get() != null) {
            SodiumDynamicLights.get().addLightSource(forgeLightSource);
        }
    }

    public static void removeLightSource(CreateDynLightSource lightSource) {
        if (lightSource instanceof DynamicLightSource forgeLightSource && SodiumDynamicLights.get() != null) {
            SodiumDynamicLights.get().removeLightSource(forgeLightSource);
        }
    }

    public static boolean getDynamicLightsModeEnabled() {
        DynamicLightsMode mode = DynamicLightsConfig.DYNAMIC_LIGHTS_MODE.get();
        return !Objects.equals(mode, DynamicLightsMode.OFF);
    }

    public static int getDynamicLightsModeDelay(){
        DynamicLightsMode mode = DynamicLightsConfig.DYNAMIC_LIGHTS_MODE.get();
        if (Objects.equals(mode, DynamicLightsMode.SLOW)) {
            return 500;
        } else if (Objects.equals(mode, DynamicLightsMode.FAST)) {
            return 200;
        } else {
            return 0;
        }
    }
}
