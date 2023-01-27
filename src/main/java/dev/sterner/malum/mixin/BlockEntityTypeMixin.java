package dev.sterner.malum.mixin;

import dev.sterner.malum.common.registry.MalumObjects;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockEntityType.class)
abstract class BlockEntityTypeMixin {
	@SuppressWarnings("InvalidInjectorMethodSignature")
	@ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntityType$Builder;create(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;[Lnet/minecraft/block/Block;)Lnet/minecraft/block/entity/BlockEntityType$Builder;"), index = 1)
	private static Block[] malum$signAdditions(Block[] blocks) {
		Block[] newBlocks = new Block[blocks.length + 4];
		System.arraycopy(blocks, 0, newBlocks, 0, blocks.length);
		newBlocks[newBlocks.length - 1] = MalumObjects.RUNEWOOD_SIGN;
		newBlocks[newBlocks.length - 2] = MalumObjects.RUNEWOOD_WALL_SIGN;
		newBlocks[newBlocks.length - 3] = MalumObjects.SOULWOOD_SIGN;
		newBlocks[newBlocks.length - 4] = MalumObjects.SOULWOOD_WALL_SIGN;
		return newBlocks;
	}
}
