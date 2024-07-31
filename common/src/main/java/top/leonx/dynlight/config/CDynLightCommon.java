package top.leonx.dynlight.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CDynLightCommon extends ConfigBase {

    @Override
    public String getName() {
        return "dynamic light common";
    }

    public final ConfigBool enable = b(false, "enable", Comments.enable);

    public static class Comments {
        public static final String enable = "Enable dynamic light.";
    }
}