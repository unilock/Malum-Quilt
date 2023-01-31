package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.quiltmc.qsl.registry.api.event.DynamicRegistryManagerSetupContext;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import static dev.sterner.malum.common.registry.MalumConfiguredFeatureRegistry.*;

public interface MalumPlacedFeatureRegistry {
	//ORES
	RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));
	RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));
	RegistryKey<PlacedFeature> ORE_NATURAL_QUARTZ = placedFeature(Malum.id("ore_natural_quartz"));

	//TREES
	RegistryKey<PlacedFeature> RUNEWOOD_CHECKED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Malum.id("runewood_tree"));

	//GEODES
	RegistryKey<PlacedFeature> GEODE_NATURAL_QUARTZ_UPPER = placedFeature(Malum.id("geode_quartz_upper"));
	RegistryKey<PlacedFeature> GEODE_NATURAL_QUARTZ_LOWER = placedFeature(Malum.id("geode_quartz_lower"));

	static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	static void init(Registry<ConfiguredFeature<?, ?>> configured, DynamicRegistryManagerSetupContext.RegistryMap registryMap) {
		Holder<ConfiguredFeature<?, ?>> runewood = configured.getHolder(CONFIGURED_FEATURE_KEYS.get(CONFIGURED_RUNEWOOD_TREE_FEATURE)).orElseThrow();

		registryMap.register(RegistryKeys.PLACED_FEATURE, Malum.id("runewood_tree"),
				new PlacedFeature(runewood,
						VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(40), MalumObjects.RUNEWOOD_SAPLING)));


		BiomeModifications.create(Malum.id("worldgen"))
				.add(ModificationPhase.ADDITIONS, (b) -> b.isIn(BiomeTags.FOREST), context -> context.getGenerationSettings()
						.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MalumPlacedFeatureRegistry.RUNEWOOD_CHECKED));



		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LOCAL_MODIFICATIONS, GEODE_NATURAL_QUARTZ_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LOCAL_MODIFICATIONS, GEODE_NATURAL_QUARTZ_LOWER);

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NATURAL_QUARTZ);
	}
}
