package dev.sterner.malum.common.block.obelisk;

import dev.sterner.malum.common.blockentity.obelisk.RunewoodObeliskBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class RunewoodObeliskCoreBlock extends ObeliskCoreBlock<RunewoodObeliskBlockEntity> {
	public RunewoodObeliskCoreBlock(AbstractBlock.Settings properties) {
		super(properties);
	}

	@Override
	public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new RunewoodObeliskBlockEntity(pos, state);
	}
}
