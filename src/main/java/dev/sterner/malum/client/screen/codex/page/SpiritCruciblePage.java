package dev.sterner.malum.client.screen.codex.page;

import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.recipe.SpiritFocusingRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;

import java.util.function.Predicate;

public class SpiritCruciblePage extends BookPage {
	private final SpiritFocusingRecipe recipe;

	public SpiritCruciblePage(Predicate<SpiritFocusingRecipe> predicate) {
		super(Malum.id("textures/gui/book/pages/spirit_crucible_page.png"));
		if (MinecraftClient.getInstance() == null) //this is null during datagen
		{
			this.recipe = null;
			return;
		}
		this.recipe = SpiritFocusingRecipe.getRecipe(MinecraftClient.getInstance().world, predicate);
	}

	public SpiritCruciblePage(SpiritFocusingRecipe recipe) {
		super(Malum.id("textures/gui/book/pages/spirit_crucible_page.png"));
		this.recipe = recipe;
	}

	@Override
	public boolean isValid() {
		return recipe != null;
	}

	public static SpiritCruciblePage fromInput(Item inputItem) {
		return new SpiritCruciblePage(s -> s.doesInputMatch(inputItem.getDefaultStack()));
	}

	public static SpiritCruciblePage fromOutput(Item outputItem) {
		return new SpiritCruciblePage(s -> s.doesOutputMatch(outputItem.getDefaultStack()));
	}

	@Override
	public void renderLeft(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		ProgressionBookScreen.renderItem(poseStack, recipe.input, guiLeft + 67, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.output, guiLeft + 67, guiTop + 126, mouseX, mouseY);
		ProgressionBookScreen.renderComponents(poseStack, recipe.spirits, guiLeft + 65, guiTop + 16, mouseX, mouseY, false);
	}

	@Override
	public void renderRight(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		ProgressionBookScreen.renderItem(poseStack, recipe.input, guiLeft + 209, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.output, guiLeft + 209, guiTop + 126, mouseX, mouseY);
		ProgressionBookScreen.renderComponents(poseStack, recipe.spirits, guiLeft + 207, guiTop + 16, mouseX, mouseY, false);
	}
}
