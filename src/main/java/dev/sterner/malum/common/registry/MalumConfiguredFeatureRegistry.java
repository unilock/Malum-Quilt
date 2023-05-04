package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import dev.sterner.malum.MalumConfig;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModification;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;
import static dev.sterner.malum.common.registry.MalumFeatureRegistry.RUNEWOOD_TREE_FEATURE;
import static dev.sterner.malum.common.registry.MalumFeatureRegistry.SOULWOOD_TREE_FEATURE;
import static dev.sterner.malum.common.registry.MalumObjects.*;
import static net.minecraft.world.gen.feature.OreConfiguredFeatures.*;

public interface MalumConfiguredFeatureRegistry {
	List<OreFeatureConfig.Target> SOULSTONE_ORE_TARGETS  = List.of(OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, SOULSTONE_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, DEEPSLATE_SOULSTONE_ORE.getDefaultState()));
	List<OreFeatureConfig.Target> BRILLIANCE_ORE_TARGETS = List.of(OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, BRILLIANT_STONE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, BRILLIANT_DEEPSLATE.getDefaultState()));
	List<OreFeatureConfig.Target> BLAZING_QUARTZ_TARGETS = List.of(OreFeatureConfig.createTarget(NETHERRACK, BLAZING_QUARTZ_ORE.getDefaultState()));

	Holder<ConfiguredFeature<OreFeatureConfig, ?>>     LOWER_ORE_SOULSTONE_CONFIGURED       = register("lower_ore_soulstone", Feature.ORE, new OreFeatureConfig(SOULSTONE_ORE_TARGETS, MalumConfig.SOULSTONE_AMOUNT));
	Holder<ConfiguredFeature<OreFeatureConfig, ?>> 	   UPPER_ORE_SOULSTONE_CONFIGURED       = register("upper_ore_soulstone", Feature.ORE, new OreFeatureConfig(SOULSTONE_ORE_TARGETS, MalumConfig.SURFACE_SOULSTONE_AMOUNT));
	Holder<ConfiguredFeature<OreFeatureConfig, ?>>     ORE_BRILLIANCE_CONFIGURED            = register("ore_brilliance",      Feature.ORE, new OreFeatureConfig(BRILLIANCE_ORE_TARGETS, MalumConfig.BRILLIANT_STONE_AMOUNT));
	Holder<ConfiguredFeature<OreFeatureConfig, ?>>     BLAZING_QUARTZ_CONFIGURED            = register("blazing_quartz",      Feature.ORE, new OreFeatureConfig(BLAZING_QUARTZ_TARGETS, MalumConfig.BLAZE_QUARTZ_AMOUNT));
	Holder<ConfiguredFeature<DefaultFeatureConfig, ?>> CONFIGURED_RUNEWOOD_TREE_FEATURE     = register("runewood_tree",       RUNEWOOD_TREE_FEATURE);
	Holder<ConfiguredFeature<DefaultFeatureConfig, ?>> CONFIGURED_SOULWOOD_TREE_FEATURE     = register("soulwood_tree",       SOULWOOD_TREE_FEATURE);

	static Holder<ConfiguredFeature<DefaultFeatureConfig, ?>> register(String id, Feature<DefaultFeatureConfig> feature) {
		return ConfiguredFeatureUtil.register(new Identifier(MODID, id).toString(), feature);
	}

	static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfig) {
		return ConfiguredFeatureUtil.register(new Identifier(MODID, id).toString(), feature, featureConfig);
	}

	static void init() {
		BiomeModification biomeModification = BiomeModifications.create(new Identifier(MODID, "world_features"));


	}
}
