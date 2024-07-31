package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceCreator {
    @ExpectPlatform
    public static CreateDynLightSource createDynLightSource(int id, AbstractContraptionEntity entity, BlockPos blockPos, int luminance){
        throw new AssertionError();
    }
}
