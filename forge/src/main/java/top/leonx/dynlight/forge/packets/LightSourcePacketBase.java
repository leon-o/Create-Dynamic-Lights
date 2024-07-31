package top.leonx.dynlight.forge.packets;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public abstract class LightSourcePacketBase extends SimplePacketBase {
    protected final int entityID;
    protected final BlockPos localPos;
    public LightSourcePacketBase(int entityID, BlockPos localPos) {
        this.entityID = entityID;
        this.localPos = localPos;
    }

    public LightSourcePacketBase(FriendlyByteBuf buffer) {
        entityID = buffer.readInt();
        localPos = buffer.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(entityID);
        buffer.writeBlockPos(localPos);
    }

    @Override
    public abstract boolean handle(NetworkEvent.Context context);
}
