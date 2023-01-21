package dev.sterner.malum.client.screen.codex.page;

import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.recipe.SpiritRepairRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpiritRepairPage extends BookPage {
	private final SpiritRepairRecipe recipe;

	public SpiritRepairPage(Predicate<SpiritRepairRecipe> predicate) {
		super(Malum.id("textures/gui/book/pages/spirit_repair_page.png"));
		if (MinecraftClient.getInstance() == null) //this is null during datagen
		{
			this.recipe = null;
			return;
		}
		this.recipe = SpiritRepairRecipe.getRecipe(MinecraftClient.getInstance().world, predicate);
	}

	public SpiritRepairPage(SpiritRepairRecipe recipe) {
		super(Malum.id("textures/gui/book/pages/spirit_repair_page.png"));
		this.recipe = recipe;
	}

	@Override
	public boolean isValid() {
		return recipe != null;
	}

	public static SpiritRepairPage fromInput(Item inputItem) {
		return new SpiritRepairPage(s -> s.doesInputMatch(inputItem.getDefaultStack()));
	}

	@Override
	public void renderLeft(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		ProgressionBookScreen.renderComponent(poseStack, recipe.repairMaterial, guiLeft + 48, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.inputs.stream().map(s -> s.getDefaultStack()).peek(s -> s.setDamage((int) (s.getMaxDamage() * recipe.durabilityPercentage))).collect(Collectors.toList()), guiLeft + 86, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.inputs.stream().map(s -> SpiritRepairRecipe.getRepairRecipeOutput(s.getDefaultStack())).collect(Collectors.toList()), guiLeft + 67, guiTop + 126, mouseX, mouseY);
		if (!recipe.spirits.isEmpty()) {
			ProgressionBookScreen.renderComponents(poseStack, recipe.spirits, guiLeft + 65, guiTop + 16, mouseX, mouseY, false);
		}
	}

	@Override
	public void renderRight(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		ProgressionBookScreen.renderComponent(poseStack, recipe.repairMaterial, guiLeft + 190, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.inputs.stream().map(s -> s.getDefaultStack()).peek(s -> s.setDamage((int) (s.getMaxDamage() * recipe.durabilityPercentage))).collect(Collectors.toList()), guiLeft + 228, guiTop + 59, mouseX, mouseY);
		ProgressionBookScreen.renderItem(poseStack, recipe.inputs.stream().map(s -> SpiritRepairRecipe.getRepairRecipeOutput(s.getDefaultStack())).collect(Collectors.toList()), guiLeft + 209, guiTop + 126, mouseX, mouseY);
		if (!recipe.spirits.isEmpty()) {
			ProgressionBookScreen.renderComponents(poseStack, recipe.spirits, guiLeft + 207, guiTop + 16, mouseX, mouseY, false);
		}
	}
}
