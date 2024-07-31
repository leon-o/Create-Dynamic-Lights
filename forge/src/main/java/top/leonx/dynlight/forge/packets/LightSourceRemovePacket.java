package top.leonx.dynlight.forge.packets;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import top.leonx.dynlight.lamb.CreateDynLightSourceHolder;

public class LightSourceRemovePacket extends LightSourcePacketBase {

    public LightSourceRemovePacket(int entityID, BlockPos localPos) {
        super(entityID, localPos);
    }

    public LightSourceRemovePacket(FriendlyByteBuf buffer) {
        super(buffer);
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
            () -> () -> {
                if(Minecraft.getInstance().level == null)
                    return;
                Entity entityByID = Minecraft.getInstance().level.getEntity(entityID);
                if (!(entityByID instanceof AbstractContraptionEntity))
                    return;

                CreateDynLightSourceHolder.INSTANCE.remove(entityID, localPos);
            })
        );
        return true;
    }
}
