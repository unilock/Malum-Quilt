package dev.sterner.malum.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class MalumSaplingBlock extends SaplingBlock {
    private final SaplingGenerator generator;
    /**
     * Access widened by quilt_block_extensions to accessible
     *
     * @param generator
     * @param settings
     */
    public MalumSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
        this.generator = generator;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (world.getBlockState(pos.down()).isIn(BlockTagRegistry.BLIGHTED_BLOCKS)) {
            return true;
        }
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycle(STAGE), 4);
        } else {
            generator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
        }
    }
}
