package top.leonx.dynlight.config.fabric;

import com.simibubi.create.foundation.config.ConfigBase;
import com.tterrag.registrate.fabric.EnvExecutor;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import net.fabricmc.api.EnvType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import top.leonx.dynlight.CreateDynLight;
import top.leonx.dynlight.config.CreateDynLightClient;
import top.leonx.dynlight.config.CreateDynLightCommon;
import top.leonx.dynlight.config.CreateDynLightServer;
import top.leonx.dynlight.lamb.ContraptionEntityEventHandler;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreateDynLightAllConfigsImpl {
    public static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);
    private static CreateDynLightCommon common;
    private static CreateDynLightClient client;
    private static CreateDynLightServer server;

    public static CreateDynLightCommon common() {
        return common;
    }

    public static CreateDynLightClient client() {
        return client;
    }

    public static CreateDynLightServer server() {
        return server;
    }

    public static ConfigBase byType(ModConfig.Type type) {
        return CONFIGS.get(type);
    }

    private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
        Pair<T, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(builder -> {
            T config = factory.get();
            config.registerAll(builder);
            return config;
        });

        T config = specPair.getLeft();
        config.specification = specPair.getRight();
        CONFIGS.put(side, config);
        return config;
    }

    public static void register() {
        server = register(CreateDynLightServer::new, ModConfig.Type.SERVER);
        client = register(CreateDynLightClient::new, ModConfig.Type.CLIENT);
//        common = register(CreateDynLightCommon::new, ModConfig.Type.COMMON);

        for (Map.Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet())
            ForgeConfigRegistry.INSTANCE.register(CreateDynLight.MOD_ID, pair.getKey(), pair.getValue().specification);

        ModConfigEvents.loading(CreateDynLight.MOD_ID).register(CreateDynLightAllConfigsImpl::onLoad);
        ModConfigEvents.reloading(CreateDynLight.MOD_ID).register(CreateDynLightAllConfigsImpl::onReload);
    }


    public static void onLoad(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig.getSpec())
                config.onLoad();
    }

    public static void onReload(ModConfig modConfig) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == modConfig.getSpec())
                config.onReload();
    }
}
