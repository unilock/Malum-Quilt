package dev.sterner.malum.client.screen.codex.page;

import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class HeadlineTextPage extends BookPage{
	private final String headlineTranslationKey;
	private final String translationKey;

	public HeadlineTextPage(String headlineTranslationKey, String translationKey) {
		super(Malum.id("textures/gui/book/pages/headline_page.png"));
		this.headlineTranslationKey = headlineTranslationKey;
		this.translationKey = translationKey;
	}

	public String headlineTranslationKey() {
		return "malum.gui.book.entry.page.headline." + headlineTranslationKey;
	}

	public String translationKey() {
		return "malum.gui.book.entry.page.text." + translationKey;
	}

	@Override
	public void renderLeft(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		Text component = Text.translatable(headlineTranslationKey());
		ProgressionBookScreen.renderText(poseStack, component, guiLeft + 75 - minecraft.textRenderer.getWidth(component.getString()) / 2, guiTop + 10);
		ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft + 14, guiTop + 31, 125);
	}

	@Override
	public void renderRight(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		Text component = Text.translatable(headlineTranslationKey());
		ProgressionBookScreen.renderText(poseStack, component, guiLeft + 218 - minecraft.textRenderer.getWidth(component.getString()) / 2, guiTop + 10);
		ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft + 156, guiTop + 31, 125);
	}
}
