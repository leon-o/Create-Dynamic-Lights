package top.leonx.dynlight.config;

import net.createmod.catnip.config.ConfigBase;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class CreateDynLightAllConfigs {

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
        Pair<T, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(builder -> {
            T config = factory.get();
            config.registerAll(builder);
            return config;
        });

        T config = specPair.getLeft();
        config.specification = specPair.getRight();
        CONFIGS.put(side, config);
        return config;
    }

    public static void register(ModLoadingContext context, ModContainer container) {
        server = register(CreateDynLightServer::new, ModConfig.Type.SERVER);
        client = register(CreateDynLightClient::new, ModConfig.Type.CLIENT);
//        common = register(CreateDynLightCommon::new, ModConfig.Type.COMMON);

        for (Map.Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet())
            container.registerConfig(pair.getKey(), pair.getValue().specification);
    }

    @SubscribeEvent
    public static void onLoad(ModConfigEvent.Loading event) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == event.getConfig()
                    .getSpec())
                config.onLoad();
    }

    @SubscribeEvent
    public static void onReload(ModConfigEvent.Reloading event) {
        for (ConfigBase config : CONFIGS.values())
            if (config.specification == event.getConfig()
                    .getSpec())
                config.onReload();
    }
}
