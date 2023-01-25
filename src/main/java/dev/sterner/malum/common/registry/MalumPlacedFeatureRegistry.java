package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Holder;
import net.minecraft.registry.HolderProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.BootstrapContext;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModification;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import static dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry.*;

public interface MalumPlacedFeatureRegistry {
	RegistryKey<PlacedFeature> RUNEWOOD_CHECKED        = PlacedFeatureUtil.m_ssakkmfw("malum:runewood_tree");
	RegistryKey<PlacedFeature> WEEPING_WELL = PlacedFeatureUtil.m_ssakkmfw("weeping_well");

	static void init() {
		BiomeModifications.addFeature((b) -> b.isIn(BiomeTags.FOREST), GenerationStep.Feature.VEGETAL_DECORATION, RUNEWOOD_CHECKED);
	}

	static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {

		HolderProvider<ConfiguredFeature<?, ?>> holderProvider = bootstrapContext.lookup(RegistryKeys.CONFIGURED_FEATURE);
		Holder<ConfiguredFeature<?, ?>> runewood = holderProvider.getHolderOrThrow(CONFIGURED_RUNEWOOD_TREE_FEATURE);
		Holder<ConfiguredFeature<?, ?>> weep = holderProvider.getHolderOrThrow(CONFIGURED_WEEPING_WELL_FEATURE);

		PlacedFeatureUtil.m_wsgklyng(bootstrapContext, RUNEWOOD_CHECKED, runewood, PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.OAK_SAPLING));

		PlacedFeatureUtil.m_wsgklyng(bootstrapContext, WEEPING_WELL, weep, RarityFilterPlacementModifier.create(30), InSquarePlacementModifier.getInstance(),
				HeightRangePlacementModifier.createUniform(YOffset.aboveBottom(6), YOffset.fixed(0)), BiomePlacementModifier.getInstance());


	}
}
