package top.leonx.dynlight.lamb.fabric;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import top.leonx.dynlight.lamb.CreateDynLightSource;

public class CreateDynLightSourceCreatorImpl {
    public static CreateDynLightSource createDynLightSource(int id, AbstractContraptionEntity entity, BlockPos blockPos, int luminance){
        return new CreateDynLightSourceFabric(id, entity, blockPos, luminance);
    }
}
