package top.leonx.dynlight.lamb;

import dev.architectury.injectables.annotations.ExpectPlatform;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;

public class LambDynLightsDelegate {
    @ExpectPlatform
    public static void scheduleChunkRebuild(LevelRenderer levelRenderer, long pos){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void updateTrackedChunks(BlockPos.MutableBlockPos chunkPos, LongOpenHashSet lambdynlights$trackedLitChunkPos, LongOpenHashSet newPos) {
        throw new AssertionError();
    }

    public static void scheduleChunkRebuild(LevelRenderer renderer, BlockPos.MutableBlockPos chunkPos) {
        scheduleChunkRebuild(renderer, chunkPos.asLong());
    }

    @ExpectPlatform
    public static void addLightSource(CreateDynLightSource lightSource) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void removeLightSource(CreateDynLightSource lightSource) {
    }

    @ExpectPlatform
    public static boolean getDynamicLightsModeEnabled() {
        return false;
    }

    @ExpectPlatform
    public static int getDynamicLightsModeDelay(){
        return 0;
    }
}
