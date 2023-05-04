package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.world.gen.feature.RunewoodTreeFeature;
import dev.sterner.malum.common.world.gen.feature.SoulwoodTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public interface MalumFeatureRegistry {
	Feature<DefaultFeatureConfig> RUNEWOOD_TREE_FEATURE = register("runewood_tree", new RunewoodTreeFeature());
	Feature<DefaultFeatureConfig> SOULWOOD_TREE_FEATURE = register("soulwood_tree", new SoulwoodTreeFeature());

	static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
		return Registry.register(Registry.FEATURE, id, feature);
	}

	static void init(){

	}
}
