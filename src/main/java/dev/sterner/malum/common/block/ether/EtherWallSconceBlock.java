package dev.sterner.malum.common.block.ether;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.Map;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class EtherWallSconceBlock<T extends EtherBlockEntity> extends EtherWallTorchBlock<EtherBlockEntity> {
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
        Direction.NORTH, Block.createCuboidShape(6.0D, 2.0D, 10.0D, 10.0D, 13.0D, 16.0D),
        Direction.SOUTH, Block.createCuboidShape(6.0D, 2.0D, 0.0D, 10.0D, 13.0D, 6.0D),
        Direction.WEST, Block.createCuboidShape(10.0D, 2.0D, 6.0D, 16.0D, 13.0D, 10.0D),
        Direction.EAST, Block.createCuboidShape(0.0D, 2.0D, 6.0D, 6.0D, 13.0D, 10.0D)));

    public EtherWallSconceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return  SHAPES.get(state.get(HORIZONTAL_FACING));
    }
}
