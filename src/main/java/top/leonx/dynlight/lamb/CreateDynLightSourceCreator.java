package top.leonx.dynlight.lamb;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;

public class CreateDynLightSourceCreator {
    public static CreateDynLightSource createDynLightSource(int id, AbstractContraptionEntity entity, BlockPos blockPos, int luminance){
        return new CreateDynLightSourceForge(id, entity, blockPos, luminance);
    }
}
