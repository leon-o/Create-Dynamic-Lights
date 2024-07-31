package top.leonx.dynlight.config;

import dev.architectury.injectables.annotations.ExpectPlatform;
public class CreateDynLightAllConfigs {
    @ExpectPlatform
    public static CDynLightServer server(){
        throw new AssertionError();
    }
    @ExpectPlatform
    public static CDynLightCommon common(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static CDynLightClient client(){
        throw new AssertionError();
    }
}
