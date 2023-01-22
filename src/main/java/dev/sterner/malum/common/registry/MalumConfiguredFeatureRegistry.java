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

import static dev.sterner.malum.common.registry.MalumBlockRegistry.*;
import static dev.sterner.malum.common.registry.MalumFeatureRegistry.RUNEWOOD_TREE_FEATURE;
import static dev.sterner.malum.common.registry.MalumFeatureRegistry.SOULWOOD_TREE_FEATURE;
import static net.minecraft.registry.tag.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.registry.tag.BlockTags.STONE_ORE_REPLACEABLES;

public class MalumConfiguredFeatureRegistry {
	public static List<OreFeatureConfig.Target> BLAZING_QUARTZ_TARGETS = List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER), BLAZING_QUARTZ_ORE.getDefaultState()));
	public static List<OreFeatureConfig.Target> SOULSTONE_ORE_TARGETS  = List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(STONE_ORE_REPLACEABLES), SOULSTONE_ORE.getDefaultState()), OreFeatureConfig.createTarget(new TagMatchRuleTest(DEEPSLATE_ORE_REPLACEABLES), DEEPSLATE_SOULSTONE_ORE.getDefaultState()));
	public static List<OreFeatureConfig.Target> BRILLIANCE_ORE_TARGETS = List.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(STONE_ORE_REPLACEABLES), BRILLIANT_STONE.getDefaultState()), OreFeatureConfig.createTarget(new TagMatchRuleTest(DEEPSLATE_ORE_REPLACEABLES), BRILLIANT_DEEPSLATE.getDefaultState()));

	public static RegistryKey<ConfiguredFeature<?, ?>> UNDERGROUND_SOULSTONE_CONFIGURED = ConfiguredFeatureUtil.m_qoarwirv("underground_soulstone");
	public static RegistryKey<ConfiguredFeature<?, ?>>     SURFACE_SOULSTONE_CONFIGURED     = ConfiguredFeatureUtil.m_qoarwirv("surface_soulstone");
	public static RegistryKey<ConfiguredFeature<?, ?>>     BRILLIANT_STONE_VEIN_CONFIGURED  = ConfiguredFeatureUtil.m_qoarwirv("brilliant_stone");
	public static RegistryKey<ConfiguredFeature<?, ?>>     BLAZING_QUARTZ_VEIN_CONFIGURED   = ConfiguredFeatureUtil.m_qoarwirv("blazing_quartz");
	public static RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_RUNEWOOD_TREE_FEATURE = ConfiguredFeatureUtil.m_qoarwirv("runewood_tree");
	public static RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_SOULWOOD_TREE_FEATURE = ConfiguredFeatureUtil.m_qoarwirv("soulwood_tree");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
		ConfiguredFeatureUtil.m_rajyrrbd(bootstrapContext, SURFACE_SOULSTONE_CONFIGURED, Feature.ORE, new OreFeatureConfig(SOULSTONE_ORE_TARGETS, 6));
		ConfiguredFeatureUtil.m_rajyrrbd(bootstrapContext, UNDERGROUND_SOULSTONE_CONFIGURED, Feature.ORE, new OreFeatureConfig(SOULSTONE_ORE_TARGETS, 12));
		ConfiguredFeatureUtil.m_rajyrrbd(bootstrapContext, BRILLIANT_STONE_VEIN_CONFIGURED, Feature.ORE, new OreFeatureConfig(BRILLIANCE_ORE_TARGETS, 4));
		ConfiguredFeatureUtil.m_rajyrrbd(bootstrapContext, BLAZING_QUARTZ_VEIN_CONFIGURED, Feature.ORE, new OreFeatureConfig(BLAZING_QUARTZ_TARGETS, 14));
		ConfiguredFeatureUtil.m_fpwwfrjz(bootstrapContext, CONFIGURED_RUNEWOOD_TREE_FEATURE, RUNEWOOD_TREE_FEATURE);
		ConfiguredFeatureUtil.m_fpwwfrjz(bootstrapContext, CONFIGURED_SOULWOOD_TREE_FEATURE, SOULWOOD_TREE_FEATURE);
	}

}
