package dev.sterner.malum.client.render.entity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneRenderLayers;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.entity.spirit.SoulEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;

import java.awt.*;

import static com.sammy.lodestone.handlers.RenderHandler.DELAYED_RENDER;
import static com.sammy.lodestone.helpers.RenderHelper.FULL_BRIGHT;
import static com.sammy.lodestone.setup.LodestoneRenderLayers.queueUniformChanges;

public class SoulEntityRenderer extends EntityRenderer<SoulEntity> {
	public final ItemRenderer itemRenderer;

	private static final Identifier SOUL_NOISE = Malum.id("textures/vfx/noise/soul_noise.png");
	private static final RenderLayer SOUL_NOISE_TYPE = LodestoneRenderLayers.RADIAL_NOISE.apply(SOUL_NOISE);
	private static final Identifier SECONDARY_SOUL_NOISE = Malum.id("textures/vfx/noise/soul_noise_secondary.png");
	private static final RenderLayer SECONDARY_SOUL_NOISE_TYPE = LodestoneRenderLayers.RADIAL_SCATTER_NOISE.apply(SECONDARY_SOUL_NOISE);
	private static final Identifier TRINARY_SOUL_NOISE = Malum.id("textures/vfx/noise/soul_noise_trinary.png");
	private static final RenderLayer TRINARY_SOUL_NOISE_TYPE = LodestoneRenderLayers.RADIAL_SCATTER_NOISE.apply(TRINARY_SOUL_NOISE);


	public SoulEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
		this.itemRenderer = ctx.getItemRenderer();
		this.shadowRadius = 0;
		this.shadowOpacity = 0;
	}

	@Override
	public void render(SoulEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		matrices.translate(0, 0.25 + entity.getYOffset(tickDelta), 0);
		renderSoul(matrices, entity.color.darker());
		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

	public static void renderSoul(MatrixStack matrices, Color color) {
		matrices.multiply(MinecraftClient.getInstance().getEntityRenderDispatcher().getRotation());
		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(180f));

		VertexConsumer soulNoise = DELAYED_RENDER.getBuffer(queueUniformChanges(SOUL_NOISE_TYPE,
				(instance) -> {
					instance.getUniformOrDefault("Speed").setFloat(2500f);
					instance.getUniformOrDefault("Intensity").setFloat(25f);
				}));

		VertexConsumer secondarySoulNoise = DELAYED_RENDER.getBuffer(queueUniformChanges(SECONDARY_SOUL_NOISE_TYPE,
				(instance -> {
					instance.getUniformOrDefault("Speed").setFloat(-1500f);
					instance.getUniformOrDefault("ScatterPower").setFloat(-20f);
					instance.getUniformOrDefault("Intensity").setFloat(35f);
				})));
		VertexConsumer trinarySoulNoise = DELAYED_RENDER.getBuffer(queueUniformChanges(TRINARY_SOUL_NOISE_TYPE,
				(instance -> {
					instance.getUniformOrDefault("Speed").setFloat(-2000f);
					instance.getUniformOrDefault("ScatterPower").setFloat(30f);
					instance.getUniformOrDefault("Intensity").setFloat(55f);
				})));

		VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat()
				.setColor(color.brighter())
				.setAlpha(1)
				.setLight(FULL_BRIGHT)
				.renderQuad(soulNoise, matrices, 0.6f)
				.setColor(ColorHelper.brighter(color, 2))
				.renderQuad(secondarySoulNoise, matrices, 0.7f)
				.renderQuad(trinarySoulNoise, matrices, 0.8f);
	}

	@Override
	public Identifier getTexture(SoulEntity entity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}
}
