package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.recipe.*;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;


public interface MalumRecipeTypeRegistry {
	Map<Identifier, RecipeType<? extends Recipe<?>>> RECIPE_TYPES = new LinkedHashMap<>();

	RecipeType<SpiritInfusionRecipe> SPIRIT_INFUSION = register(SpiritInfusionRecipe.NAME);
	RecipeType<SpiritFocusingRecipe> SPIRIT_FOCUSING = register(SpiritFocusingRecipe.NAME);
	RecipeType<SpiritRepairRecipe> SPIRIT_REPAIR = register(SpiritRepairRecipe.NAME);
	RecipeType<AugmentingRecipe> AUGMENTING = register(AugmentingRecipe.NAME);
	RecipeType<SpiritTransmutationRecipe> SPIRIT_TRANSMUTATION = register(SpiritTransmutationRecipe.NAME);

	static <T extends Recipe<?>> RecipeType<T> register(String id) {
        RecipeType<T> type = new RecipeType<>(){ public String toString() { return id; }};
        RECIPE_TYPES.put(new Identifier(MODID, id), type);
        return type;
    }

	static void init() {
        RECIPE_TYPES.forEach((id, type) -> Registry.register(Registries.RECIPE_TYPE, id, type));
    }
}
