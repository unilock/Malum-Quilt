package dev.sterner.malum.common.block.mirror;

import dev.sterner.malum.common.blockentity.mirror.EmitterMirrorBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class EmitterMirrorBlock<T extends EmitterMirrorBlockEntity> extends WallMirrorBlock<T> {
    public EmitterMirrorBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

}
