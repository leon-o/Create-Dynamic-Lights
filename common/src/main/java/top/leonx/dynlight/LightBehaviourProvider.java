package top.leonx.dynlight;

import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LightBehaviourProvider implements AllMovementBehaviours.BehaviourProvider {

    List<LightMovementBehaviour> lightMovementBehaviours = new ArrayList<>(15);

    public LightBehaviourProvider() {
        for (int i = 0; i < 15; i++) {
            lightMovementBehaviours.add(new LightMovementBehaviour(i + 1));
        }
    }

    @Override
    public @Nullable MovementBehaviour getBehaviour(BlockState blockState) {
        int lightEmission = blockState.getLightEmission();
        if (lightEmission <= 0) return null;
        var index = Math.min(15, lightEmission) - 1;
        return lightMovementBehaviours.get(index);
    }
}
