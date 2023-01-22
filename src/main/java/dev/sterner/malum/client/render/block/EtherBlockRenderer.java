package dev.sterner.malum.client.render.block;

import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EtherBlockRenderer implements BlockEntityRenderer<EtherBlockEntity> {
	@Override
	public void render(EtherBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

	}
}
