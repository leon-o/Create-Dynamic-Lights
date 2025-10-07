package top.leonx.dynlight.config;


import net.createmod.catnip.config.ConfigBase;

public class CreateDynLightServer extends ConfigBase {

    @Override
    public String getName() {
        return "dynamic light server";
    }
    public final ConfigBase.ConfigBool enableLightBlock = b(false, "enableLightBlock", Comments.enableLightBlock);

    public final ConfigInt lightBlockEmissionLowerLimit = i(1, 0, 15, "lightBlockEmissionLowerLimit", Comments.lightBlockEmissionLowerLimit);

    public static class Comments {

        public static final String enableLightBlock = "Place a light source block to light up the area.";
        public static final String lightBlockEmissionLowerLimit = "Blocks with light emission value greater than this value will be considered as a light source. " +
                "If a block's light emission value is less than this value, no light block will be placed.";
    }
}
