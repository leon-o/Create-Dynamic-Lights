package top.leonx.dynlight;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.api.contraption.dispenser.MountedDispenseBehavior;
import com.simibubi.create.api.registry.SimpleRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightBehaviourProvider implements SimpleRegistry.Provider<Block, MovementBehaviour>
{

    Map<Integer, LightMovementBehaviour> lightMovementBehaviours = new HashMap(15);


    @Override
    public @Nullable MovementBehaviour get(Block block) {
        int lightEmission = block.defaultBlockState().getLightEmission();
        if (lightEmission <= 0) return null;
        var index = Math.min(15, lightEmission);
        return lightMovementBehaviours.get(index);
    }

    public LightBehaviourProvider() {
        for (int i = 1; i <= 15; i++) {
            lightMovementBehaviours.put(i, new LightMovementBehaviour(i + 1));
        }
    }
}
