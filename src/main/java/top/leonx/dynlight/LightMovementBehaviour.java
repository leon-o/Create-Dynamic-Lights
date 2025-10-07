package top.leonx.dynlight;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.Blocks;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;

public class LightMovementBehaviour implements MovementBehaviour {

    private final int luminance;

    private static final String LightBlockPrevPosKey = "LightBlockPrevPos";

    public LightMovementBehaviour(int luminance) {
        this.luminance = luminance;
    }

    @Override
    public void stopMoving(MovementContext context) {
        if (context.world.isClientSide()) return;
        cleanPreviousLightBlock(context);
    }

    @Override
    public void visitNewPosition(MovementContext context, BlockPos pos) {
        if (context.world.isClientSide()) return;

        cleanPreviousLightBlock(context);
        createNewLightBlock(context, pos);
    }

    private void cleanPreviousLightBlock(MovementContext context) {
        if (context.data.contains(LightBlockPrevPosKey)) {
            var prevBlockPos = NbtUtils.readBlockPos(context.data, LightBlockPrevPosKey);
            if(prevBlockPos.isPresent())
            {
                context.world.setBlock(prevBlockPos.get(), Blocks.AIR.defaultBlockState(), 2);
                context.data.remove(LightBlockPrevPosKey);
            }
        }
    }

    private void createNewLightBlock(MovementContext context, BlockPos pos) {
        if (!CreateDynLightAllConfigs.server().enableLightBlock.get()
                || CreateDynLightAllConfigs.server().lightBlockEmissionLowerLimit.get() > luminance) {
            return;
        }
        var originBlockState = context.world.getBlockState(pos);
        if (originBlockState.is(Blocks.AIR)) {
            context.world.setBlock(pos, Blocks.LIGHT.defaultBlockState(), 2);
            context.data.put(LightBlockPrevPosKey, NbtUtils.writeBlockPos(pos));
        }
    }
}
