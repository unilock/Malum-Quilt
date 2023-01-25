package dev.sterner.malum.common.registry;

import com.mojang.serialization.Codec;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.world.gen.feature.RunewoodTreeFeature;
import dev.sterner.malum.common.world.gen.feature.SoulwoodTreeFeature;
import dev.sterner.malum.common.world.gen.placer.MalumFoliagePlacer;
import dev.sterner.malum.common.world.gen.placer.MalumTrunkPlacer;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.BootstrapContext;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountOnEveryLayerPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;


public interface MalumWorldRegistry {

	RegistryKey<PlacedFeature> ORE_SOULSTONE_UPPER = placedFeature(Malum.id("ore_soulstone_upper"));
	RegistryKey<PlacedFeature> ORE_SOULSTONE_LOWER = placedFeature(Malum.id("ore_soulstone_lower"));

	RegistryKey<PlacedFeature> ORE_BRILLIANT = placedFeature(Malum.id("ore_brilliant"));
	RegistryKey<PlacedFeature> ORE_BLAZING_QUARTZ = placedFeature(Malum.id("ore_blazing_quartz"));

	TrunkPlacerType<MalumTrunkPlacer> MALUM_TRUNK_PLACER = registerTrunk(MODID + ":malum_trunk_placer", MalumTrunkPlacer.CODEC);
	FoliagePlacerType<MalumFoliagePlacer> MALUM_FOLIAGE_PLACER = registerFoliage(MODID + ":malum_foliage_placer", MalumFoliagePlacer.CODEC);

	RegistryKey<PlacedFeature> RUNEWOOD_TREE_PLACED = placedFeature(Malum.id("tree_runewood"));
	RunewoodTreeFeature RUNEWOOD_TREE = register("runewood_tree", new RunewoodTreeFeature());
	SoulwoodTreeFeature SOULWOOD_TREE = register("soulwood_tree", new SoulwoodTreeFeature());

	static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
		return Registry.register(Registries.FEATURE_WORLDGEN, id, feature);
	}

	static RegistryKey<PlacedFeature> placedFeature(Identifier id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
	}

	static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunk(String id, Codec<P> codec) {
		return Registry.register(Registries.TRUNK_PLACER_TYPE, id, new TrunkPlacerType<>(codec));
	}

	static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(String id, Codec<P> codec) {
		return Registry.register(Registries.FOLIAGE_PLACER_TYPE, id, new FoliagePlacerType<>(codec));
	}

	static void init(){
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_UPPER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_SOULSTONE_LOWER);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BRILLIANT);
		BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLAZING_QUARTZ);

		BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.VEGETAL_DECORATION, RUNEWOOD_TREE_PLACED);
	}


}
