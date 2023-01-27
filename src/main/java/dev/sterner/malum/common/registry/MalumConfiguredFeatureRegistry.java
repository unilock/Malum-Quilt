package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.LinkedHashMap;
import java.util.Map;

public interface MalumConfiguredFeatureRegistry {
	Map<Identifier, ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = new LinkedHashMap<>();
	Map<ConfiguredFeature<?, ?>, RegistryKey<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURE_KEYS = new LinkedHashMap<>();

	ConfiguredFeature<?, ?> CONFIGURED_RUNEWOOD_TREE_FEATURE = register("runewood_tree", new ConfiguredFeature<>(MalumFeatureRegistry.RUNEWOOD_TREE_FEATURE, DefaultFeatureConfig.INSTANCE));
	ConfiguredFeature<?, ?> CONFIGURED_SOULWOOD_TREE_FEATURE = register("soulwood_tree", new ConfiguredFeature<>(MalumFeatureRegistry.SOULWOOD_TREE_FEATURE, DefaultFeatureConfig.INSTANCE));

	static <C extends FeatureConfig, E extends Feature<C>, F extends ConfiguredFeature<C, E>> F register(String id, F feature) {
		CONFIGURED_FEATURES.put(Malum.id(id), feature);
		return feature;
	}
	static void init(Registry<ConfiguredFeature<?, ?>> configured) {
		CONFIGURED_FEATURES.forEach((id, feature) -> {Registry.register(configured, id, feature);
			CONFIGURED_FEATURE_KEYS.put(feature, configured.getKey(feature).orElseThrow());
		});
	}
}
