package dev.sterner.malum.common.block.storage;

import dev.sterner.malum.common.blockentity.storage.ItemPedestalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.stream.Stream;

public class WoodItemPedestalBlock <T extends ItemPedestalBlockEntity> extends ItemPedestalBlock<T> {
    public static final VoxelShape SHAPE = Stream.of(
        Block.createCuboidShape(4, 0, 4, 12, 3, 12),
        Block.createCuboidShape(5, 3, 5, 11, 11, 11),
        Block.createCuboidShape(4, 11, 4, 12, 13, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    public WoodItemPedestalBlock(Settings settings) {
        super(settings);
    }
}
