package dev.sterner.malum.common.registry;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.BootstrapContext;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;

import java.util.List;

import static dev.sterner.malum.common.registry.MalumFeatureRegistry.*;
import static dev.sterner.malum.common.registry.MalumObjects.*;
import static net.minecraft.registry.tag.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.registry.tag.BlockTags.STONE_ORE_REPLACEABLES;

public interface MalumConfiguredFeatureRegistry {
	RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_RUNEWOOD_TREE_FEATURE = ConfiguredFeatureUtil.m_qoarwirv("runewood_tree");
	RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_SOULWOOD_TREE_FEATURE = ConfiguredFeatureUtil.m_qoarwirv("soulwood_tree");
	RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_WEEPING_WELL_FEATURE = ConfiguredFeatureUtil.m_qoarwirv("weeping_well");

	static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
		ConfiguredFeatureUtil.m_fpwwfrjz(bootstrapContext, CONFIGURED_RUNEWOOD_TREE_FEATURE, RUNEWOOD_TREE_FEATURE);
		ConfiguredFeatureUtil.m_fpwwfrjz(bootstrapContext, CONFIGURED_SOULWOOD_TREE_FEATURE, SOULWOOD_TREE_FEATURE);
		ConfiguredFeatureUtil.m_fpwwfrjz(bootstrapContext, CONFIGURED_WEEPING_WELL_FEATURE, WEEPING_WELL_FEATURE);
	}

	static void init(){

	}

}
