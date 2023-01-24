package dev.sterner.malum.common.registry;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Holder;
import net.minecraft.registry.HolderProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.BootstrapContext;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;

import static dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry.*;

public interface MalumPlacedFeatureRegistry {
	RegistryKey<PlacedFeature> BRILLIANT_STONE_PLACED       = PlacedFeatureUtil.m_ssakkmfw("brilliant_stone");
	RegistryKey<PlacedFeature> SURFACE_SOULSTONE_PLACED     = PlacedFeatureUtil.m_ssakkmfw("surface_soulstone");
	RegistryKey<PlacedFeature> UNDERGROUND_SOULSTONE_PLACED = PlacedFeatureUtil.m_ssakkmfw("underground_soulstone");
	RegistryKey<PlacedFeature> BLAZING_QUARTZ_PLACED        = PlacedFeatureUtil.m_ssakkmfw("blazing_quartz");
	RegistryKey<PlacedFeature> RUNEWOOD_CHECKED        = PlacedFeatureUtil.m_ssakkmfw("runewood_tree");

	static void init() {
		BiomeModifications.addFeature((b) -> b.isIn(BiomeTags.FOREST), GenerationStep.Feature.VEGETAL_DECORATION, RUNEWOOD_CHECKED);
	}

	static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
		HolderProvider<ConfiguredFeature<?, ?>> holderProvider = bootstrapContext.lookup(RegistryKeys.CONFIGURED_FEATURE);
		Holder<ConfiguredFeature<?, ?>> soulstoneSurface = holderProvider.getHolderOrThrow(SURFACE_SOULSTONE_CONFIGURED);
		Holder<ConfiguredFeature<?, ?>> soulstoneUnderground = holderProvider.getHolderOrThrow(UNDERGROUND_SOULSTONE_CONFIGURED);
		Holder<ConfiguredFeature<?, ?>> bq = holderProvider.getHolderOrThrow(BLAZING_QUARTZ_VEIN_CONFIGURED);
		Holder<ConfiguredFeature<?, ?>> bs = holderProvider.getHolderOrThrow(BRILLIANT_STONE_VEIN_CONFIGURED);
		Holder<ConfiguredFeature<?, ?>> runewood = holderProvider.getHolderOrThrow(CONFIGURED_RUNEWOOD_TREE_FEATURE);
		PlacedFeatureUtil.m_wsgklyng(bootstrapContext, RUNEWOOD_CHECKED, runewood, PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.OAK_SAPLING));
		PlacedFeatureUtil.m_sdevhksy(bootstrapContext, BRILLIANT_STONE_PLACED, bs, OrePlacedFeatures.commonOrePlacementModifiers(3, HeightRangePlacementModifier.createUniform(YOffset.fixed(-64), YOffset.fixed(30))));
		PlacedFeatureUtil.m_sdevhksy(bootstrapContext, SURFACE_SOULSTONE_PLACED, soulstoneSurface, OrePlacedFeatures.commonOrePlacementModifiers(8, HeightRangePlacementModifier.createUniform(YOffset.fixed(60), YOffset.fixed(100))));
		PlacedFeatureUtil.m_sdevhksy(bootstrapContext, UNDERGROUND_SOULSTONE_PLACED, soulstoneUnderground, OrePlacedFeatures.commonOrePlacementModifiers(8, HeightRangePlacementModifier.createUniform(YOffset.fixed(-64), YOffset.fixed(30))));
		PlacedFeatureUtil.m_sdevhksy(bootstrapContext, BLAZING_QUARTZ_PLACED, bq, OrePlacedFeatures.commonOrePlacementModifiers(16, PlacedFeatureUtil.EIGHT_ABOVE_AND_BELOW_RANGE));
	}
}
