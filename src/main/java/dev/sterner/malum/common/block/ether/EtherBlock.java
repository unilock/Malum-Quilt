package dev.sterner.malum.common.block.ether;

import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EtherBlock<T extends EtherBlockEntity> extends WaterLoggedEntityBlock<T> {
    public static final VoxelShape SHAPE = Block.createCuboidShape(6.0d, 6.0d, 6.0d, 10.0d, 10.0d, 10.0d);

    public EtherBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
