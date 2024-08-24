package top.leonx.dynlight;

import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import top.leonx.dynlight.config.CreateDynLightAllConfigs;

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
        var lowerLimit = CreateDynLightAllConfigs.server().lightBlockEmissionLowerLimit.get();
        int lightEmission = blockState.getLightEmission();
        if (lightEmission >= lowerLimit) {
            var index = Math.max(Math.min(15, lightEmission), 1) - 1;
            return lightMovementBehaviours.get(index);
        }
        return null;
    }
}
