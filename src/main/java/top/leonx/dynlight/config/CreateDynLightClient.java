package top.leonx.dynlight.config;

import net.createmod.catnip.config.ConfigBase;
import top.leonx.dynlight.lamb.LambDynLightsDelegate;

public class CreateDynLightClient extends ConfigBase {

    @Override
    public String getName() {
        return "dynamic light client";
    }

    public final ConfigBool enableLambDynamicLight = b(true, "enable Lamb dynamic light", Comments.enableLambDynamicLight);

    public final ConfigFloat luminanceMultiplier = f(1.0f, 0.0f, 2.0f, "luminance multiplier", Comments.luminanceMultiplier);

    public final ConfigBool ignoreLambModeSetting = b(true, "ignore Lamb mode setting", Comments.ignoreLambModeSetting);

    public final ConfigInt updateInterval = i(0, 0, 500, "update interval", Comments.updateInterval);

    public int getUpdateInterval(){
        if(ignoreLambModeSetting.get())
            return updateInterval.get();
        return updateInterval.get() + LambDynLightsDelegate.getDynamicLightsModeDelay();
    }

    public static class Comments {

        public static final String enableLambDynamicLight = "Enable dynamic light for LambDynamicLight.";

        public static final String luminanceMultiplier = "The multiplier of the luminance of the light.";

        public static final String ignoreLambModeSetting = "Whether to ignore the Lamb mode setting. If true, the lamb's update interval will be ignored.";

        public static final String updateInterval = "The interval of updating the light. 0 means update every tick. " +
                "If ignore Lamb mode setting is true, this value will completely replace the Lamb's update interval, otherwise, it will be added to the Lamb's update interval.";
    }
}
