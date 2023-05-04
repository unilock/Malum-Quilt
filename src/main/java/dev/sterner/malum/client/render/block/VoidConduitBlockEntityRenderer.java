package dev.sterner.malum.client.render.block;

import com.sammy.lodestone.handlers.RenderHandler;
import com.sammy.lodestone.setup.LodestoneRenderLayerRegistry;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.blockentity.VoidConduitBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

public class VoidConduitBlockEntityRenderer implements BlockEntityRenderer<VoidConduitBlockEntity> {

	public static final Identifier VIGNETTE = Malum.id("textures/block/weeping_well/primordial_soup_vignette.png");

	public VoidConduitBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
	}

	@Override
	public void render(VoidConduitBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		renderQuad(matrices);
	}

	public void renderQuad(MatrixStack matrices) {
		float height = 0.75f;
		float width = 1.5f;
		var textureConsumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderLayerRegistry.TRANSPARENT_TEXTURE.applyAndCache(VIGNETTE));
		Vec3f[] positions = new Vec3f[]{new Vec3f(-width, height, width), new Vec3f(width, height, width), new Vec3f(width, height, -width), new Vec3f(-width, height, -width)};
		VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setPosColorTexLightmapDefaultFormat();

		matrices.push();
		matrices.translate(0.5f, 0.001f, 0.5f);
		builder.renderQuad(textureConsumer, matrices, positions, 1f);
		builder.setPosColorLightmapDefaultFormat();
		matrices.pop();
	}


}
