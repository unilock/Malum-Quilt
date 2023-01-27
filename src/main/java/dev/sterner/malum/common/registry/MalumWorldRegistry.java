package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;


public interface MalumWorldRegistry {

	RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));

	RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));



	static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
		return Registry.register(Registries.FEATURE_WORLDGEN, id, feature);
	}

	static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	static void init(){
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);

	}



}
