package dev.sterner.malum.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class MalumDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		MalumTagProviders.MalumBlockTags blockTagsProvider = new MalumTagProviders.MalumBlockTags(dataGenerator);
		dataGenerator.addProvider(blockTagsProvider);
		dataGenerator.addProvider((p) -> new MalumTagProviders.MalumItemTags(p, blockTagsProvider));
		dataGenerator.addProvider(MalumTagProviders.MalumEntityTypeTags::new);

		//TODO pack.addProvider(MalumLanguageProvider::new);
		dataGenerator.addProvider(MalumLootTableProviders.BlockLoot::new);
		//dataGenerator.addProvider(MalumLootTableProviders.EntityLoot::new);
		dataGenerator.addProvider(MalumModelProvider::new);
		dataGenerator.addProvider(MalumRecipeProvider::new);
	}
}
