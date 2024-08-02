package top.leonx.dynlight.config;

import dev.architectury.injectables.annotations.ExpectPlatform;
public class CreateDynLightAllConfigs {
    @ExpectPlatform
    public static CreateDynLightServer server(){
        throw new AssertionError();
    }
    @ExpectPlatform
    public static CreateDynLightCommon common(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static CreateDynLightClient client(){
        throw new AssertionError();
    }
}
