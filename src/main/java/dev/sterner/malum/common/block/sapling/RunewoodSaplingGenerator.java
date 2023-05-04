package dev.sterner.malum.common.block.sapling;

import dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RunewoodSaplingGenerator extends SaplingGenerator {
	protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
		return MalumConfiguredFeatureRegistry.CONFIGURED_RUNEWOOD_TREE_FEATURE;
	}
}
