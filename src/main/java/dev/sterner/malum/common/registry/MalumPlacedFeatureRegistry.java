package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModification;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;

import static dev.sterner.malum.Malum.MODID;

public interface MalumPlacedFeatureRegistry {
	//ORES
	RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));
	RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));
	RegistryKey<PlacedFeature> ORE_NATURAL_QUARTZ = placedFeature(Malum.id("ore_natural_quartz"));

	//GEODES
	RegistryKey<PlacedFeature> GEODE_NATURAL_QUARTZ_UPPER = placedFeature(Malum.id("geode_quartz_upper"));
	RegistryKey<PlacedFeature> GEODE_NATURAL_QUARTZ_LOWER = placedFeature(Malum.id("geode_quartz_lower"));


	Holder<PlacedFeature> RUNEWOOD_CHECKED_WITH_CHANCE = PlacedFeatureUtil.register("malum:runewood_tree", MalumConfiguredFeatureRegistry.CONFIGURED_RUNEWOOD_TREE_FEATURE, VegetationPlacedFeatures.treePlacementModifiers(RarityFilterPlacementModifier.create(40), MalumObjects.RUNEWOOD_SAPLING));


	static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(Registry.PLACED_FEATURE_KEY, id);
	}

	static void init(){
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LOCAL_MODIFICATIONS, GEODE_NATURAL_QUARTZ_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.LOCAL_MODIFICATIONS, GEODE_NATURAL_QUARTZ_LOWER);

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_NATURAL_QUARTZ);

		BiomeModification worldgen = BiomeModifications.create(new Identifier(MODID, "world_features"));
		worldgen.add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(ConventionalBiomeTags.FOREST).or(BiomeSelectors.isIn(ConventionalBiomeTags.PLAINS)), (biomeSelectionContext, biomeModificationContext) -> {
			biomeModificationContext.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, RUNEWOOD_CHECKED_WITH_CHANCE.value());
		});

	}
}
