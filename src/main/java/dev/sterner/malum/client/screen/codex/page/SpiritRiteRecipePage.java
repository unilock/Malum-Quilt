package dev.sterner.malum.client.screen.codex.page;

import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.spiritrite.MalumRiteType;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SpiritRiteRecipePage extends BookPage {
	private final MalumRiteType riteType;

	public SpiritRiteRecipePage(MalumRiteType riteType) {
		super(Malum.id("textures/gui/book/pages/spirit_rite_recipe_page.png"));
		this.riteType = riteType;
	}

	@Override
	public void renderLeft(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		renderRite(poseStack, guiLeft + 67, guiTop + 123, mouseX, mouseY, riteType.spirits);
	}

	@Override
	public void renderRight(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		renderRite(poseStack, guiLeft + 209, guiTop + 123, mouseX, mouseY, riteType.spirits);
	}

	public void renderRite(MatrixStack poseStack, int left, int top, int mouseX, int mouseY, List<MalumSpiritType> spirits) {
		for (int i = 0; i < spirits.size(); i++) {
			ItemStack stack = spirits.get(i).getSplinterItem().getDefaultStack();
			ProgressionBookScreen.renderItem(poseStack, stack, left, top - 20 * i, mouseX, mouseY);
		}
	}
}
