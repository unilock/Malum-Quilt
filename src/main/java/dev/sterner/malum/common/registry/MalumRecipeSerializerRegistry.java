package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.recipe.*;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;



public interface MalumRecipeSerializerRegistry {
	Map<Identifier, RecipeSerializer<? extends Recipe<?>>> RECIPE_SERIALIZER = new LinkedHashMap<>();

	RecipeSerializer<SpiritInfusionRecipe> SPIRIT_INFUSION_SERIALIZER = register(SpiritInfusionRecipe.NAME, new SpiritInfusionRecipe.Serializer());
    RecipeSerializer<SpiritFocusingRecipe> SPIRIT_FOCUSING_SERIALIZER = register(SpiritFocusingRecipe.NAME, new SpiritFocusingRecipe.Serializer());
	RecipeSerializer<SpiritRepairRecipe> SPIRIT_REPAIR_SERIALIZER = register(SpiritRepairRecipe.NAME, new SpiritRepairRecipe.Serializer());
	RecipeSerializer<AugmentingRecipe> AUGMENTING_SERIALIZER = register(AugmentingRecipe.NAME, new AugmentingRecipe.Serializer());
	RecipeSerializer<SpiritTransmutationRecipe> SPIRIT_TRANSMUTATION_RECIPE_SERIALIZER = register(SpiritTransmutationRecipe.NAME, new SpiritTransmutationRecipe.Serializer());
	RecipeSerializer<FavorOfTheVoidRecipe> VOID_FAVOR_RECIPE_SERIALIZER = register(FavorOfTheVoidRecipe.NAME, new FavorOfTheVoidRecipe.Serializer());


    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        RECIPE_SERIALIZER.put(new Identifier(MODID, id), serializer);
        return serializer;
    }

    static void init() {
        RECIPE_SERIALIZER.forEach((id, serializer) -> Registry.register(Registry.RECIPE_SERIALIZER, id, serializer));
    }
}
