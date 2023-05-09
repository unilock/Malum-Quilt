package dev.sterner.malum.compat.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.spiritrite.MalumRiteType;
import dev.sterner.malum.compat.emi.EMIPlugin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class SpiritRiteEmiRecipe implements EmiRecipe
{
	private static final Identifier BACKGROUND_LOCATION = Malum.id("textures/gui/spirit_rite_jei.png");

	private final TextRenderer font;

	private final MalumRiteType rite;
	private final List<EmiIngredient> spirits;

	public SpiritRiteEmiRecipe(MalumRiteType rite)
	{
		this.rite = rite;
		this.spirits = rite.spirits.stream().map((spirit) -> (EmiIngredient) EmiStack.of(spirit.getSplinterItem())).toList();

		this.font = MinecraftClient.getInstance().textRenderer;
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return EMIPlugin.SPIRIT_RITE;
	}

	@Override
	public Identifier getId()
	{
		return null;
	}

	@Override
	public List<EmiIngredient> getInputs()
	{
		return spirits;
	}

	@Override
	public List<EmiStack> getOutputs()
	{
		return List.of();
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
			Text text = Text.translatable(rite.translationIdentifier(false));
			ProgressionBookScreen.renderText(matrices, text, 71 - font.getWidth(text) / 2, 160);
		});

		for(int i = 0; i < rite.spirits.size(); i++)
		{
			widgets.addSlot(EmiStack.of(rite.spirits.get(i).getSplinterItem()), 62, 120 - 20 * i).catalyst(true).drawBack(false);
		}
	}
}
