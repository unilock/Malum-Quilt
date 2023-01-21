package dev.sterner.malum.common.block.blight;

import dev.sterner.malum.common.block.BlockTagRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class BlightedGrassBlock extends FernBlock {
    public BlightedGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.isIn(BlockTagRegistry.BLIGHTED_BLOCKS)) {
            return true;
        }
        return super.canPlaceAt(state, world, pos);
    }
}
