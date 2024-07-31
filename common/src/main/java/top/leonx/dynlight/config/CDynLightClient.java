package top.leonx.dynlight.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class CDynLightClient extends ConfigBase {

    @Override
    public String getName() {
        return "dynamic light client";
    }

    public final ConfigBool enableEmbeddiumDynamicLight = b(false, "enable embeddium dynamic light", Comments.enableEmbeddiumDynamicLight);


    public static class Comments {

        public static final String enableEmbeddiumDynamicLight = "Enable dynamic light for embeddium.";
    }
}
