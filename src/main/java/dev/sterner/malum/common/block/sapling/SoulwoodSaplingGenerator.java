package dev.sterner.malum.common.block.sapling;

import dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SoulwoodSaplingGenerator extends SaplingGenerator {

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
		return MalumConfiguredFeatureRegistry.CONFIGURED_SOULWOOD_TREE_FEATURE;
	}
}
