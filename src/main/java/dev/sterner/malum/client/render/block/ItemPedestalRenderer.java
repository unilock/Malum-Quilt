package dev.sterner.malum.client.render.block;

import dev.sterner.malum.common.blockentity.storage.ItemPedestalBlockEntity;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class ItemPedestalRenderer implements BlockEntityRenderer<ItemPedestalBlockEntity> {
	public ItemPedestalRenderer(BlockEntityRendererFactory.Context context) {
	}

	@Override
	public void render(ItemPedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		World level = MinecraftClient.getInstance().world;
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		ItemStack stack = entity.inventory.getStack(0);
		if (!stack.isEmpty()) {
			matrices.push();
			Vector3f offset = new Vector3f(entity.itemOffset().m_sruzucpd());
			if (stack.getItem() instanceof MalumSpiritItem) {
				double y = Math.sin(((level.getTime() + tickDelta)) / 20f) * 0.1f;
				matrices.translate(0, y, 0);
			}
			matrices.translate(offset.x(), offset.y(), offset.z());
			matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(((level.getTime() % 360) + tickDelta) * 3));
			matrices.scale(0.6f, 0.6f, 0.6f);
			itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
			matrices.pop();
		}
	}
}
