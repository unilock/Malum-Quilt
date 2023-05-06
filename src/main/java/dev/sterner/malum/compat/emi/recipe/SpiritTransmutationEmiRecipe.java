package dev.sterner.malum.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.sterner.malum.Malum;
import dev.sterner.malum.compat.emi.EMIPlugin;
import dev.sterner.malum.compat.emi.wrapper.SpiritTransmuationRecipeWrapper;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.List;

public class SpiritTransmutationEmiRecipe implements EmiRecipe
{
	private static final Identifier BACKGROUND_LOCATION = Malum.id("textures/gui/spirit_transmutation_jei.png");

	private final SpiritTransmuationRecipeWrapper recipe;

	private final List<EmiIngredient> inputs;
	private final List<EmiIngredient> result;
	private final List<EmiStack> outputs;

	public SpiritTransmutationEmiRecipe(SpiritTransmuationRecipeWrapper recipe)
	{
		this.recipe = recipe;

		this.inputs = List.of(EmiIngredient.of(recipe.subRecipes().stream().map((r) -> EmiIngredient.of(r.ingredient)).toList()));

		this.result = List.of(EmiIngredient.of(recipe.subRecipes().stream().map((r) -> EmiIngredient.of(Ingredient.ofStacks(r.output))).toList()));
		this.outputs = this.result.get(0).getEmiStacks();
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return EMIPlugin.SPIRIT_TRANSMUTATION;
	}

	@Override
	public Identifier getId()
	{
		return null;
	}

	@Override
	public List<EmiIngredient> getInputs()
	{
		return inputs;
	}

	@Override
	public List<EmiStack> getOutputs()
	{
		return outputs;
	}

	@Override
	public int getDisplayWidth()
	{
		return 142;
	}

	@Override
	public int getDisplayHeight()
	{
		return 83;
	}

	@Override
	public void addWidgets(WidgetHolder widgets)
	{
		widgets.addTexture(BACKGROUND_LOCATION, 1, 0, this.getDisplayWidth(), this.getDisplayHeight(), 0, 0);

		widgets.addSlot(inputs.get(0), 28, 26).drawBack(false);

		widgets.addSlot(result.get(0), 93, 26).recipeContext(this).drawBack(false);
	}
}
