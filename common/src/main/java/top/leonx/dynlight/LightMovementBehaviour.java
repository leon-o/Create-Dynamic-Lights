package top.leonx.dynlight;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.Blocks;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;
import top.leonx.dynlight.lamb.CreateDynLightSource;
import top.leonx.dynlight.lamb.CreateDynLightSourceHolder;

public class LightMovementBehaviour implements MovementBehaviour {

    private final int luminance;

    public LightMovementBehaviour(int luminance) {
        this.luminance = luminance;
    }

//    @Override
//    public void tick(MovementContext context) {
//        var entity = context.contraption.entity;
//        if(entity == null) return;
//        if (context.world.isClientSide()) {
//            if(context.temporaryData == null){
//                context.temporaryData = CreateDynLightSourceHolder.INSTANCE.getOrCreate(entity.getId(), context.localPos, context.world, context.position, luminance);
//            }
//
//            if(context.temporaryData instanceof CreateDynLightSource lightSource){
//                lightSource.setPosition(context.position);
//            }
//        }
//    }

    @Override
    public void stopMoving(MovementContext context) {
        if (context.world.isClientSide()) {
            return;
        }
        cleanPreviousLightBlock(context);

        var entity = context.contraption.entity;
        // StopMoving won't be called on client side, so we need to send packet on server side.
//        CreateDynLightAllPackets.getChannel().send(PacketDistributor.TRACKING_ENTITY.with(() -> entity),
//                new LightSourceRemovePacket(entity.getId(), context.localPos));

    }

    @Override
    public void visitNewPosition(MovementContext context, BlockPos pos) {
        if(context.world.isClientSide()) return;

        cleanPreviousLightBlock(context);
        createNewLightBlock(context, pos);
    }

    private void cleanPreviousLightBlock(MovementContext context) {
        if (context.data.contains("PrevPos")) {
            var tag = context.data.getCompound("PrevPos");
            var prevBlockPos = NbtUtils.readBlockPos(tag);
            context.world.setBlock(prevBlockPos, Blocks.AIR.defaultBlockState(), 2);
            context.data.remove("PrevPos");
        }
    }

    private void createNewLightBlock(MovementContext context, BlockPos pos) {
        if(!CreateDynLightAllConfigs.server().enableLightBlock.get()){
            return;
        }
        var originBlockState = context.world.getBlockState(pos);
        if(originBlockState.is(Blocks.AIR)){
            context.world.setBlock(pos, Blocks.LIGHT.defaultBlockState(), 2);
            context.data.put("PrevPos", NbtUtils.writeBlockPos(pos));
        }
    }
}
