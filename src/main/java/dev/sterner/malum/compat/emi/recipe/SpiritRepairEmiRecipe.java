package dev.sterner.malum.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.recipe.SpiritRepairRecipe;
import dev.sterner.malum.compat.emi.EMIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class SpiritRepairEmiRecipe implements EmiRecipe
{
	private static final Identifier BACKGROUND_LOCATION = Malum.id("textures/gui/spirit_repair_jei.png");

	private final SpiritRepairRecipe recipe;

	private final List<EmiIngredient> inputs;
	private final EmiIngredient damaged;
	private final EmiIngredient repairIngredient;
	private final List<EmiIngredient> spirits;
	private final List<EmiStack> outputs;
	private final EmiIngredient repaired;

	public SpiritRepairEmiRecipe(SpiritRepairRecipe recipe)
	{
		this.recipe = recipe;

		this.inputs = Lists.newArrayList();
		this.outputs = recipe.inputs.stream().map((r) -> EmiStack.of(r.getDefaultStack())).toList();
		this.damaged = EmiIngredient.of(this.outputs.stream()
				.map(EmiStack::getItemStack)
				.map(ItemStack::copy)
				.peek((s) -> s.setDamage((int) (s.getMaxDamage() * recipe.durabilityPercentage)))
				.map((s) -> EmiIngredient.of(Ingredient.ofStacks(s)))
				.toList());
		this.inputs.add(this.damaged);
		this.repairIngredient = EMIPlugin.convertIngredientWithCount(recipe.repairMaterial);
		this.inputs.add(this.repairIngredient);
		this.inputs.addAll(this.spirits = EMIPlugin.convertSpiritWithCounts(recipe.spirits));

		this.repaired = EmiIngredient.of(this.outputs.stream()
				.map(EmiStack::getItemStack)
				.map(SpiritRepairRecipe::getRepairRecipeOutput)
				.map(EmiStack::of)
				.toList());
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return EMIPlugin.SPIRIT_REPAIR;
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
		return 185;
	}

	@Override
	public void addWidgets(WidgetHolder widgets)
	{
		widgets.addTexture(BACKGROUND_LOCATION, 0, 0, this.getDisplayWidth(), this.getDisplayHeight(), 0, 0);

		widgets.addDrawable(0, 0, 0, 0, (matrices, mx, my, d) ->
		{
			if(!spirits.isEmpty())
			{
				ProgressionBookScreen.renderItemFrames(matrices, spirits.size(), 61, 12, false);
			}
		});

		EMIPlugin.addItems(widgets, 61, 12, false, spirits);

		widgets.addSlot(damaged, 81, 56).drawBack(false);
		widgets.addSlot(repairIngredient, 43, 56).drawBack(false);

		widgets.addSlot(repaired, 62, 123).recipeContext(this).drawBack(false);
	}
}
