package dev.sterner.malum.common.recipe;

import com.google.gson.JsonObject;
import com.sammy.lodestone.forge.CraftingHelper;
import com.sammy.lodestone.systems.recipe.ILodestoneRecipe;
import dev.sterner.malum.common.registry.MalumRecipeSerializerRegistry;
import dev.sterner.malum.common.registry.MalumRecipeTypeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class AugmentingRecipe extends ILodestoneRecipe {
    public static final String NAME = "augmenting";

    private final Identifier id;

    public final Ingredient targetItem;
    public final Ingredient augment;
    public final NbtCompound tagAugment;

    public AugmentingRecipe(Identifier id, Ingredient targetItem, Ingredient augment, NbtCompound tagAugment) {
        this.id = id;
        this.targetItem = targetItem;
        this.augment = augment;
        this.tagAugment = tagAugment;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MalumRecipeSerializerRegistry.AUGMENTING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return MalumRecipeTypeRegistry.AUGMENTING;
    }

    public boolean doesInputMatch(ItemStack input) {
        return this.targetItem.test(input);
    }

    public boolean doesAugmentMatch(ItemStack input) {
        return this.augment.test(input);
    }

    public static AugmentingRecipe getRecipe(World world, ItemStack stack, ItemStack augment) {
        return getRecipe(world, c -> c.doesInputMatch(stack) && c.doesAugmentMatch(augment));
    }

    public static AugmentingRecipe getRecipe(World world, Predicate<AugmentingRecipe> predicate) {
        List<AugmentingRecipe> recipes = getRecipes(world);
        for (AugmentingRecipe recipe : recipes) {
            if (predicate.test(recipe)) {
                return recipe;
            }
        }
        return null;
    }

    public static List<AugmentingRecipe> getRecipes(World world) {
        return world.getRecipeManager().listAllOfType(MalumRecipeTypeRegistry.AUGMENTING);
    }

    public static class Serializer implements RecipeSerializer<AugmentingRecipe> {

        @Override
        public AugmentingRecipe read(Identifier recipeId, JsonObject json) {
            Ingredient targetItem = Ingredient.fromJson(json.get("targetItem"));
            Ingredient input = Ingredient.fromJson(json.get("augment"));

            NbtCompound tagAugment = CraftingHelper.getNBT(json.get("tagAugment"));
            return new AugmentingRecipe(recipeId, targetItem, input, tagAugment);
        }

        @Nullable
        @Override
        public AugmentingRecipe read(Identifier recipeId, PacketByteBuf buffer) {
            Ingredient targetItem = Ingredient.fromPacket(buffer);
            Ingredient input = Ingredient.fromPacket(buffer);
            NbtCompound tagAugment = buffer.readNbt();
            return new AugmentingRecipe(recipeId, targetItem, input, tagAugment);
        }

        @Override
        public void write(PacketByteBuf buffer, AugmentingRecipe recipe) {
            recipe.targetItem.write(buffer);
            recipe.augment.write(buffer);
            buffer.writeNbt(recipe.tagAugment);
        }
    }
}
