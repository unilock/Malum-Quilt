package dev.sterner.malum.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class MalumSaplingBlock extends SaplingBlock {
	public MalumSaplingBlock(SaplingGenerator generator, Settings settings) {
		super(generator, settings);
	}

	@Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (world.getBlockState(pos.down()).isIn(BlockTagRegistry.BLIGHTED_BLOCKS)) {
            return true;
        }
        return super.canPlaceAt(state, world, pos);
    }
}
