package dev.sterner.malum.common.block.ether;

import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EtherSconceBlock<T extends EtherBlockEntity> extends EtherTorchBlock<T> {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 11.0D, 10.0D);

    public EtherSconceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
