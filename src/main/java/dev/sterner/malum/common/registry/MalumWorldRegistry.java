package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

public class MalumWorldRegistry {

	public static final RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	public static final RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));

	public static final RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	public static final RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));

	public static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	public static void init(){
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);

		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);
	}
}
