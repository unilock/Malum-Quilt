package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import org.quiltmc.qsl.registry.api.event.DynamicRegistryManagerSetupContext;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import java.util.List;

import static dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry.*;

public interface MalumPlacedFeatureRegistry {
	//ORES
	RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));
	RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));

	//TREES
	RegistryKey<PlacedFeature> RUNEWOOD_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Malum.id("runewood_tree"));

	//OTHER
	RegistryKey<PlacedFeature> WEEPING_WELL = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Malum.id("weeping_well"));

	static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	static void init(Registry<ConfiguredFeature<?, ?>> configured, DynamicRegistryManagerSetupContext.RegistryMap registryMap) {
		Holder<ConfiguredFeature<?, ?>> runewood = configured.getHolder(CONFIGURED_FEATURE_KEYS.get(CONFIGURED_RUNEWOOD_TREE_FEATURE)).orElseThrow();
		Holder<ConfiguredFeature<?, ?>> weeping = configured.getHolder(CONFIGURED_FEATURE_KEYS.get(CONFIGURED_WEEPING_WELL_FEATURE)).orElseThrow();

		registryMap.register(RegistryKeys.PLACED_FEATURE, Malum.id("runewood_tree"),
				new PlacedFeature(runewood, VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(40), MalumObjects.RUNEWOOD_SAPLING)));
		registryMap.register(RegistryKeys.PLACED_FEATURE, Malum.id("weeping_well"),
				new PlacedFeature(weeping, List.of(
						RarityFilterPlacementModifier.create(1),
						InSquarePlacementModifier.getInstance(),
						HeightRangePlacementModifier.create(ConstantHeightProvider.create(YOffset.fixed(0))),
						BiomePlacementModifier.getInstance())
				));


		BiomeModifications.create(Malum.id("worldgen"))
				.add(ModificationPhase.ADDITIONS, (b) -> b.isIn(BiomeTags.FOREST), context -> context.getGenerationSettings()
						.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MalumPlacedFeatureRegistry.RUNEWOOD_CHECKED))
				.add(ModificationPhase.ADDITIONS, (b) -> b.isIn(BiomeTags.OVERWORLD), context -> context.getGenerationSettings()
						.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, MalumPlacedFeatureRegistry.WEEPING_WELL)
				);


		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);
	}
}
