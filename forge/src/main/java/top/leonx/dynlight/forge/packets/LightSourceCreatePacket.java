package top.leonx.dynlight.forge.packets;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import top.leonx.dynlight.lamb.CreateDynLightSourceHolder;
import top.leonx.dynlight.forge.packets.LightSourcePacketBase;

public class LightSourceCreatePacket extends LightSourcePacketBase {


    public LightSourceCreatePacket(int entityID, BlockPos localPos) {
        super(entityID, localPos);
    }

    public LightSourceCreatePacket(FriendlyByteBuf buffer) {
        super(buffer);
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
//        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
//            () -> () -> {
//                if(Minecraft.getInstance().level == null)
//                    return;
//                Entity entityByID = Minecraft.getInstance().level.getEntity(entityID);
//                if (!(entityByID instanceof AbstractContraptionEntity))
//                    return;
//
//                CreateDynLightSourceHolder.INSTANCE.create(entityID, localPos, Minecraft.getInstance().level, VecHelper.getCenterOf(localPos), 15);
//            })
//        );
        return true;
    }
}
