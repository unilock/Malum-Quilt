package dev.sterner.malum.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.recipe.SpiritInfusionRecipe;
import dev.sterner.malum.compat.emi.EMIPlugin;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class SpiritInfusionEmiRecipe implements EmiRecipe
{
	private static final Identifier BACKGROUND_LOCATION = Malum.id("textures/gui/spirit_infusion_jei.png");

	private final SpiritInfusionRecipe recipe;

	private final List<EmiIngredient> inputs;
	private final List<EmiIngredient> extraItems;
	private final List<EmiIngredient> spirits;
	private final List<EmiStack> result;

	public SpiritInfusionEmiRecipe(SpiritInfusionRecipe recipe)
	{
		this.recipe = recipe;

		this.inputs = Lists.newArrayList();
		this.inputs.add(EMIPlugin.convertIngredientWithCount(recipe.input));
		this.inputs.addAll(this.extraItems = EMIPlugin.convertIngredientWithCounts(recipe.extraItems));
		this.inputs.addAll(this.spirits = EMIPlugin.convertSpiritWithCounts(recipe.spirits));

		this.result = List.of(EmiStack.of(recipe.output));
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return EMIPlugin.SPIRIT_INFUSION;
	}

	@Override
	public Identifier getId()
	{
		return recipe.getId();
	}

	@Override
	public List<EmiIngredient> getInputs()
	{
		return inputs;
	}

	@Override
	public List<EmiStack> getOutputs()
	{
		return result;
	}

	@Override
	public int getDisplayWidth()
	{
		return 142;
	}

	@Override
	public int getDisplayHeight()
	{
		return 185;
	}

	@Override
	public void addWidgets(WidgetHolder widgets)
	{
		widgets.addTexture(BACKGROUND_LOCATION, 0, 0, this.getDisplayWidth(), this.getDisplayHeight(), 0, 0);

		widgets.addDrawable(0, 0, 0, 0, (matrices, mx, my, d) ->
		{
			ProgressionBookScreen.renderItemFrames(matrices, spirits.size(), 19, 48, true);
			if(!extraItems.isEmpty())
			{
				ProgressionBookScreen.renderItemFrames(matrices, extraItems.size(), 103, 48, true);
			}
		});

		EMIPlugin.addItems(widgets, 19, 48, true, spirits);
		EMIPlugin.addItems(widgets, 103, 48, true, extraItems);

		widgets.addSlot(inputs.get(0), 62, 56).drawBack(false);

		widgets.addSlot(result.get(0), 62, 123).recipeContext(this).drawBack(false);
	}
}
