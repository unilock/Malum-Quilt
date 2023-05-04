package dev.sterner.malum.client.render.block;

import dev.sterner.malum.common.blockentity.storage.ItemStandBlockEntity;
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
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class ItemStandBlockEntityRenderer implements BlockEntityRenderer<ItemStandBlockEntity> {
	public ItemStandBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
	}


	@Override
	public void render(ItemStandBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		World world = MinecraftClient.getInstance().world;
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		ItemStack stack = entity.inventory.getStack(0);
		if (!stack.isEmpty()) {
			matrices.push();
			Vec3f offset = new Vec3f(entity.itemOffset());
			if (stack.getItem() instanceof MalumSpiritItem) {
				double y = Math.sin(((world.getTime() + tickDelta)) / 20f) * 0.05f;
				matrices.translate(0, y, 0);
			}
			matrices.translate(offset.getX(), offset.getY(), offset.getZ());
			matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(((world.getTime() % 360) + tickDelta) * 3));
			matrices.scale(0.6f, 0.6f, 0.6f);
			itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
			matrices.pop();
		}
	}
}
