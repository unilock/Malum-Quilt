package dev.sterner.malum.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class MalumDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(MalumTagProviders.MalumBlockTags::new);
		pack.addProvider(MalumTagProviders.MalumItemTags::new);
		pack.addProvider(MalumTagProviders.MalumEntityTypeTags::new);

		//TODO pack.addProvider(MalumLanguageProvider::new);
		pack.addProvider(MalumLootTableProviders.BlockLoot::new);
		pack.addProvider(MalumLootTableProviders.EntityLoot::new);
		pack.addProvider(MalumModelProvider::new);
		pack.addProvider(MalumRecipeProvider::new);
	}
}
