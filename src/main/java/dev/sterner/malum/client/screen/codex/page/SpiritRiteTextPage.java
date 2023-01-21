package dev.sterner.malum.client.screen.codex.page;


import dev.sterner.malum.Malum;
import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import dev.sterner.malum.common.rite.MalumRiteType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SpiritRiteTextPage extends BookPage {
	public final MalumRiteType riteType;
	private final String translationKey;

	public SpiritRiteTextPage(MalumRiteType riteType, String translationKey) {
		super(Malum.id("textures/gui/book/pages/spirit_rite_page.png"));
		this.riteType = riteType;
		this.translationKey = translationKey;
	}

	public String headlineTranslationKey() {
		return riteType.translationIdentifier(isCorrupted());
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
		ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft + 14, guiTop + 76, 126);
		ProgressionBookScreen.renderRiteIcon(riteType, poseStack, isCorrupted(), guiLeft + 67, guiTop + 44);
	}

	@Override
	public void renderRight(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks) {
		int guiLeft = guiLeft();
		int guiTop = guiTop();
		Text component = Text.translatable(headlineTranslationKey());
		ProgressionBookScreen.renderText(poseStack, component, guiLeft + 218 - minecraft.textRenderer.getWidth(component.getString()) / 2, guiTop + 10);
		ProgressionBookScreen.renderWrappingText(poseStack, translationKey(), guiLeft + 156, guiTop + 76, 126);
		ProgressionBookScreen.renderRiteIcon(riteType, poseStack, isCorrupted(), guiLeft + 209, guiTop + 44);
	}

	public boolean isCorrupted() {
		return translationKey.contains("corrupt");
	}
}
