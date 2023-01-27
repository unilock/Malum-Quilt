package dev.sterner.malum.common.block.obelisk;

import dev.sterner.malum.common.blockentity.obelisk.BrilliantObeliskBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class BrillianceObeliskCoreBlock extends ObeliskCoreBlock<BrilliantObeliskBlockEntity> {
	public BrillianceObeliskCoreBlock(AbstractBlock.Settings properties) {
		super(properties);
	}

	@Override
	public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		setBlockEntity(MalumBlockEntityRegistry.BRILLIANT_OBELISK);
		return super.createBlockEntity(pos, state);
	}
}
