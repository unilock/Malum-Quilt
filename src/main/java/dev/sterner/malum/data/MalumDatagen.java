package dev.sterner.malum.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class MalumDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {

		dataGenerator.addProvider(MalumTagProviders.MalumBlockTags::new);
		//dataGenerator.addProvider(MalumTagProviders.MalumItemTags::new);
		dataGenerator.addProvider(MalumTagProviders.MalumEntityTypeTags::new);

		//TODO pack.addProvider(MalumLanguageProvider::new);
		dataGenerator.addProvider(MalumLootTableProviders.BlockLoot::new);
		//dataGenerator.addProvider(MalumLootTableProviders.EntityLoot::new);
		dataGenerator.addProvider(MalumModelProvider::new);
		dataGenerator.addProvider(MalumRecipeProvider::new);
	}
}
