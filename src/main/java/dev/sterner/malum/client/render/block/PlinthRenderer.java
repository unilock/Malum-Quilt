package dev.sterner.malum.client.render.block;

import dev.sterner.malum.client.render.entity.SoulEntityRenderer;
import dev.sterner.malum.common.blockentity.storage.PlinthCoreBlockEntity;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PlinthRenderer implements BlockEntityRenderer<PlinthCoreBlockEntity> {
	public PlinthRenderer(BlockEntityRendererFactory.Context context) {
	}

	@Override
	public void render(PlinthCoreBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		World level = MinecraftClient.getInstance().world;
		if (entity.data != null) {
			matrices.push();
			Vec3d offset = entity.itemOffset();
			double y = offset.y + Math.sin(((level.getTime() % 360) + tickDelta) / 20f) * 0.08f;
			matrices.translate(offset.x, y, offset.z);
			SoulEntityRenderer.renderSoul(matrices, entity.data.primaryType.getColor().darker());
			matrices.pop();
			return;
		}
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		ItemStack stack = entity.inventory.getStack(0);
		if (!stack.isEmpty()) {
			matrices.push();
			Vec3d offset = entity.itemOffset();
			matrices.translate(offset.x, offset.y, offset.z);
			if (stack.getItem() instanceof MalumSpiritItem) {
				double y = Math.sin(((level.getTime() % 360) + tickDelta) / 20f) * 0.05f;
				matrices.translate(0, y, 0);
			}
			matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(((level.getTime() % 360) + tickDelta) * 3));
			matrices.scale(0.45f, 0.45f, 0.45f);
			itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
			matrices.pop();
		}
	}
}
