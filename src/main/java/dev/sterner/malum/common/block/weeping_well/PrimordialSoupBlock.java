package dev.sterner.malum.common.block.weeping_well;

import dev.sterner.malum.common.component.MalumComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PrimordialSoupBlock extends Block {

	public static final BooleanProperty TOP = BooleanProperty.of("top");
	protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

	public PrimordialSoupBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(TOP, true));
	}

	@Override
	public boolean isSideInvisible(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
		return (pAdjacentBlockState.getBlock() instanceof VoidConduitBlock || pAdjacentBlockState.isOf(this)) || super.isSideInvisible(pState, pAdjacentBlockState, pDirection);
	}

	@Override
	public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}


	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(TOP);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
				.with(TOP, !(world.getBlockState(pos.up()).getBlock() instanceof PrimordialSoupBlock));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (state.get(TOP)) {
			return TOP_SHAPE;
		}
		return super.getOutlineShape(state, world, pos, context);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.get(livingEntity).touchedByGoop(state, livingEntity);
		}
	}
}
