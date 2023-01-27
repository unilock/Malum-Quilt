package dev.sterner.malum.common.block.fusion_plate;

import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.FusionPlateBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.NotNull;

public class FusionPlateCoreBlock<T extends FusionPlateBlockEntity> extends WaterLoggedEntityBlock<T> {
    public static final VoxelShape SHAPE = makeShape();

    public FusionPlateCoreBlock(Settings settings) {
        super(settings);
    }

	@Override
	public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		setBlockEntity((BlockEntityType<T>) MalumBlockEntityRegistry.FUSION_PLATE);
		return super.createBlockEntity(pos, state);
	}

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public static VoxelShape makeShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combineAndSimplify(shape, VoxelShapes.cuboid(0, 0, 0, 1, 0.75, 1), BooleanBiFunction.OR);

        return shape;
    }
}
