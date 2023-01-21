package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.recipe.*;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;


@SuppressWarnings("unused")
public class MalumRecipeSerializerRegistry {
	public static Map<Identifier, RecipeSerializer<? extends Recipe<?>>> RECIPE_SERIALIZER = new LinkedHashMap<>();

	public static RecipeSerializer<SpiritInfusionRecipe> SPIRIT_INFUSION_SERIALIZER                   = register("spirit_infusion",     new SpiritInfusionRecipe.Serializer());
   // RecipeSerializer<SavedNbtRecipe> SAVED_NBT_RECIPE_SERIALIZER                        = register("nbt_carry",           new SavedNbtRecipe.Serializer());
    public static RecipeSerializer<BlockTransmutationRecipe> BLOCK_TRANSMUTATION_SERIALIZER           = register("block_transmutation", new BlockTransmutationRecipe.Serializer<>(BlockTransmutationRecipe::new));
	public static RecipeSerializer<SpiritFocusingRecipe> SPIRIT_FOCUSING_SERIALIZER                   = register("spirit_focusing", new SpiritFocusingRecipe.Serializer());
	public static RecipeSerializer<SpiritRepairRecipe> SPIRIT_REPAIR_SERIALIZER                       = register("spirit_repair", new SpiritRepairRecipe.Serializer());

	public static RecipeSerializer<AugmentingRecipe> AUGMENTING_SERIALIZER                            = register(AugmentingRecipe.NAME, new AugmentingRecipe.Serializer());
	public static RecipeSerializer<SpiritTransmutationRecipe> SPIRIT_TRANSMUTATION_RECIPE_SERIALIZER  = register(SpiritTransmutationRecipe.NAME, new SpiritTransmutationRecipe.Serializer());


    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        RECIPE_SERIALIZER.put(new Identifier(MODID, id), serializer);
        return serializer;
    }

    public static void init() {
        RECIPE_SERIALIZER.forEach((id, serializer) -> Registry.register(Registries.RECIPE_SERIALIZER, id, serializer));
    }
}
