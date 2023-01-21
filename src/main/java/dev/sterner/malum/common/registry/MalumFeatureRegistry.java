package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.world.gen.feature.RunewoodTreeFeature;
import dev.sterner.malum.common.world.gen.feature.SoulwoodTreeFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;


@SuppressWarnings("unused")
public class MalumFeatureRegistry {
    public static Map<Identifier, Feature<? extends FeatureConfig>> FEATURES = new LinkedHashMap<>();

	public static Feature<DefaultFeatureConfig> RUNEWOOD_TREE_FEATURE = register("runewood_tree", new RunewoodTreeFeature());
	public static Feature<DefaultFeatureConfig> SOULWOOD_TREE_FEATURE = register("soulwood_tree", new SoulwoodTreeFeature());

	public static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        FEATURES.put(new Identifier(MODID, id), feature);
        return feature;
    }

	public static void init() {
        FEATURES.forEach((id, feature) -> Registry.register(Registries.FEATURE_WORLDGEN, id, feature));
    }
}
