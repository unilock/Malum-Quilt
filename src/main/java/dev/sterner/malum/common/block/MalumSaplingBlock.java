package dev.sterner.malum.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.function.Supplier;

public class MalumSaplingBlock extends SaplingBlock {
	public final Supplier<? extends Feature<DefaultFeatureConfig>> tree;

    public MalumSaplingBlock(Supplier<? extends Feature<DefaultFeatureConfig>> tree, Settings settings) {
        super(null, settings);
        this.tree = tree;
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
		System.out.println("grow");
        if (state.get(STAGE) == 0) {
			System.out.println("setBlockState");
			world.setBlockState(pos, state.cycle(STAGE), 4);
        } else {
			System.out.println("Generate: ");
            tree.get().placeIfValid(DefaultFeatureConfig.INSTANCE, world, world.getChunkManager().getChunkGenerator(), random, pos);
        }
    }
}
