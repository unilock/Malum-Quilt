package dev.sterner.malum.client.screen.codex.objects;

import dev.sterner.malum.client.screen.codex.ProgressionBookScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class BookObject {
	public boolean isHovering;
	public float hover;
	public int posX;
	public int posY;
	public int width;
	public int height;

	public BookObject(int posX, int posY, int width, int height)
	{
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	public int hoverCap()
	{
		return 20;
	}

	public void render(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks)
	{

	}
	public void lateRender(MinecraftClient minecraft, MatrixStack poseStack, float xOffset, float yOffset, int mouseX, int mouseY, float partialTicks)
	{

	}
	public void click(float xOffset, float yOffset, double mouseX, double mouseY)
	{

	}
	public void exit()
	{

	}
	public boolean isHovering(float xOffset, float yOffset, double mouseX, double mouseY)
	{
		return ProgressionBookScreen.isHovering(mouseX,mouseY, offsetPosX(xOffset), offsetPosY(yOffset), width, height);
	}
	public int offsetPosX(float xOffset)
	{
		int guiLeft = (width - ProgressionBookScreen.screen.bookWidth) / 2;
		return (int) (guiLeft+ this.posX + xOffset);
	}
	public int offsetPosY(float yOffset)
	{
		int guiTop = (height - ProgressionBookScreen.screen.bookHeight) / 2;
		return (int) (guiTop + this.posY + yOffset);
	}
}
