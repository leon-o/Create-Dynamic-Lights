package top.leonx.dynlight.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CreateDynLightServer extends ConfigBase {

    @Override
    public String getName() {
        return "dynamic light server";
    }
    public final ConfigBool enableLightBlock = b(false, "enableLightBlock", Comments.enableLightBlock);

    public static class Comments {

        public static final String enableLightBlock = "Place a light source block to light up the area.";
    }
}
