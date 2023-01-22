package dev.sterner.malum.client.render.item;

import dev.sterner.malum.common.blockentity.storage.SpiritJarBlockEntity;
import dev.sterner.malum.common.item.spirit.SpiritJarItem;
import dev.sterner.malum.common.registry.MalumBlockRegistry;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class SpiritJarItemRenderer extends BuiltinModelItemRenderer {
	private final SpiritJarBlockEntity jar = new SpiritJarBlockEntity(BlockPos.ORIGIN, MalumBlockRegistry.SPIRIT_JAR.getDefaultState());
	private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

	public SpiritJarItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelLoader entityModelLoader) {
		super(blockEntityRenderDispatcher, entityModelLoader);
		this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
	}

	@Override
	public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (stack.getItem() instanceof SpiritJarItem) {
			if (stack.hasNbt() && stack.getNbt().contains("spirit")) {
				MalumSpiritType spirit = SpiritHelper.getSpiritType(stack.getNbt().getString("spirit"));
				int count = stack.getNbt().getInt("count");
				jar.type = spirit;
				jar.count = count;

				this.blockEntityRenderDispatcher.renderEntity(jar, matrices, vertexConsumers, light, overlay);

			}
		}
	}
}
