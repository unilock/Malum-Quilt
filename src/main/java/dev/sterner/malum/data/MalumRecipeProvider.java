package dev.sterner.malum.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class MalumRecipeProvider extends FabricRecipeProvider {


	public MalumRecipeProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

	}
}
