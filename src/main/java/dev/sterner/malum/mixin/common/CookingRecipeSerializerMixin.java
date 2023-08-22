package dev.sterner.malum.mixin.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CookingRecipeSerializer.class)
abstract class CookingRecipeSerializerMixin {

	@Redirect(method = "read(Lnet/minecraft/util/Identifier;Lcom/google/gson/JsonObject;)Lnet/minecraft/recipe/AbstractCookingRecipe;", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/JsonHelper;getString(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", ordinal = 0))
	private String getString(JsonObject json, String key) {
		if (JsonHelper.hasPrimitive(json, key)) {
			return JsonHelper.getString(json, key);
		}

		if (JsonHelper.hasJsonObject(json, key)) {
			return JsonHelper.getString(JsonHelper.getObject(json, key), "item");
		}

		throw new JsonSyntaxException("Missing " + key + ", expected to find a string or object");
	}

	@ModifyArgs(method = "read(Lnet/minecraft/util/Identifier;Lcom/google/gson/JsonObject;)Lnet/minecraft/recipe/AbstractCookingRecipe;", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CookingRecipeSerializer$RecipeFactory;create(Lnet/minecraft/util/Identifier;Ljava/lang/String;Lnet/minecraft/recipe/Ingredient;Lnet/minecraft/item/ItemStack;FI)Lnet/minecraft/recipe/AbstractCookingRecipe;", ordinal = 0))
	private void create(Args args, Identifier identifier, JsonObject jsonObject) {
		if (jsonObject.get("result").isJsonObject()) {
			ItemStack stack = args.get(3);
			stack.setCount(jsonObject.getAsJsonObject("result").get("count").getAsInt());
		}
	}
}
