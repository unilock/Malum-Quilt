package dev.sterner.malum.common.block.spirit_altar;

import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.spirit_altar.SpiritAltarBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("deprecation")
public class SpiritAltarBlock<T extends SpiritAltarBlockEntity> extends WaterLoggedEntityBlock<T>{
    public static final VoxelShape SHAPE = VoxelShapes.union(
        Block.createCuboidShape(1.0d, 0d, 1.0d, 15.0d, 4.0d, 15.0d),
        Block.createCuboidShape(3.0d, 4.0d, 3.0d, 13.0d, 10.0d, 13.0d),
        Block.createCuboidShape(0d, 10.0d, 0d, 16.0d, 16.0d, 16.0d),
        Block.createCuboidShape(-2.0d, 9.0d, -2.0d, 3.0d, 17.0d, 3.0d),
        Block.createCuboidShape(-2.0d, 9.0d, 13.0d, 3.0d, 17.0d, 18.0d),
        Block.createCuboidShape(13.0d, 9.0d, -2.0d, 18.0d, 17.0d, 3.0d),
        Block.createCuboidShape(13.0d, 9.0d, 13.0d, 18.0d, 17.0d, 18.0d),
        Block.createCuboidShape(13.0d, 0d, 5.0d, 16.0d, 6.0d, 11.0d),
        Block.createCuboidShape(5.0d, 0d, 0d, 11.0d, 6.0d, 3.0d),
        Block.createCuboidShape(5.0d, 0d, 13.0d, 11.0d, 6.0d, 16.0d),
        Block.createCuboidShape(0d, 0d, 5.0d, 3.0d, 6.0d, 11.0d)
    );

    public SpiritAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

	@Override
	public void prepare(BlockState state, WorldAccess world, BlockPos pos, int flags, int maxUpdateDepth) {

		super.prepare(state, world, pos, flags, maxUpdateDepth);
	}

	@Override
	public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		setBlockEntity((BlockEntityType<T>) MalumBlockEntityRegistry.SPIRIT_ALTAR);
		return super.createBlockEntity(pos, state);
	}

	@NotNull
	@Override
	public ActionResult onUse(@NotNull BlockState state, @NotNull World level, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull Hand hand, @NotNull BlockHitResult ray) {
		System.out.println("Print: " + this.blockEntityType);
		return super.onUse(state, level, pos, player, hand, ray);
	}
}
