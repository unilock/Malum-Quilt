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


@SuppressWarnings("unused")
public class MalumRecipeTypeRegistry {
	public static Map<Identifier, RecipeType<? extends Recipe<?>>> RECIPE_TYPES = new LinkedHashMap<>();

	public static RecipeType<SpiritInfusionRecipe> SPIRIT_INFUSION           = register(SpiritInfusionRecipe.NAME);
    //RecipeType<SavedNbtRecipe> SAVED_NBT                       = register("nbt_carry");
	//public static RecipeType<BlockTransmutationRecipe> BLOCK_TRANSMUTATION   = register("block_transmutation");
	public static RecipeType<SpiritFocusingRecipe> SPIRIT_FOCUSING           = register(SpiritFocusingRecipe.NAME);
	public static RecipeType<SpiritRepairRecipe> SPIRIT_REPAIR               = register(SpiritRepairRecipe.NAME);
	public static RecipeType<AugmentingRecipe> AUGMENTING                    = register(AugmentingRecipe.NAME);
	public static RecipeType<SpiritTransmutationRecipe> SPIRIT_TRANSMUTATION = register(SpiritTransmutationRecipe.NAME);

	public static <T extends Recipe<?>> RecipeType<T> register(String id) {
        RecipeType<T> type = new RecipeType<>(){ public String toString() { return id; }};
        RECIPE_TYPES.put(new Identifier(MODID, id), type);
        return type;
    }

	public static void init() {
        RECIPE_TYPES.forEach((id, type) -> Registry.register(Registries.RECIPE_TYPE, id, type));
    }
}
