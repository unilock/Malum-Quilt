package dev.sterner.malum.common.world.gen.feature;

import com.sammy.lodestone.systems.worldgen.LodestoneBlockFiller;
import dev.sterner.malum.common.block.weeping_well.PrimordialSoupBlock;
import dev.sterner.malum.common.block.weeping_well.WeepingWellBlock;
import dev.sterner.malum.common.registry.MalumObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.block.enums.WallShape;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class WeepingWellFeature extends Feature<DefaultFeatureConfig> {
	public WeepingWellFeature() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public boolean place(FeatureContext<DefaultFeatureConfig> context) {
		var level = context.getWorld();
		BlockPos pos = context.getOrigin();
		BlockPos.Mutable mutableBlockPos = pos.mutableCopy();
		while (true) {
			if (level.isOutOfHeightLimit(mutableBlockPos)) {
				return false;
			}
			final boolean isSpaceEmpty = level.isAir(mutableBlockPos) && level.testFluidState(mutableBlockPos, FluidState::isEmpty);
			final boolean isSpaceBelowSolid = !level.isAir(mutableBlockPos.move(0, -1, 0));
			if ((isSpaceEmpty && isSpaceBelowSolid)) {
				pos = mutableBlockPos.down();
				break;
			}
		}

		if (!isSufficientlyFlat(level, pos)) {
			return false;
		}


		var rand = context.getRandom();
		LodestoneBlockFiller filler = new LodestoneBlockFiller(false);
		Direction[] directions = new Direction[]{Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST};

		mutableBlockPos = new BlockPos.Mutable();
		int failedSolidChecks = 0;
		int failedNonSolidChecks = 0;

		for (int x = -2; x <= 2; x++) {
			for (int y = -5; y <= 4; y++) {
				for (int z = -2; z <= 2; z++) {
					mutableBlockPos.set(pos.getX()+x, pos.getY()+y, pos.getZ()+z);
					if (y <= 0) {
						if (level.isAir(mutableBlockPos) || !level.testFluidState(mutableBlockPos, FluidState::isEmpty)) {
							failedSolidChecks++;
						}
					}
					else {
						if (!canPlace(level, mutableBlockPos)) {
							failedNonSolidChecks++;
						}
					}
				}
			}
		}
		if (failedSolidChecks >= 25) {
			return false;
		}
		if (failedNonSolidChecks >= 50) {
			return false;
		}
		pos = pos.down();
		int wellDepth = 4;
		int airPocketHeight = 2;
		for (int i = 0; i < 9; i++) {
			int xOffset = (i / 3) - 1;
			int zOffset = i % 3 - 1;
			for (int j = 0; j < wellDepth; j++) {
				BlockPos primordialGoopPos = pos.add(xOffset, -j, zOffset);
				filler.getEntries().put(primordialGoopPos, new LodestoneBlockFiller.BlockStateEntry(MalumObjects.PRIMORDIAL_SOUP.getDefaultState().with(PrimordialSoupBlock.TOP, j == 0)));
			}
			for (int j = 1; j <= airPocketHeight; j++) {
				BlockPos airPocketPos = pos.add(xOffset, j, zOffset);
				filler.getEntries().put(airPocketPos, new LodestoneBlockFiller.BlockStateEntry(Blocks.CAVE_AIR.getDefaultState()));
			}
		}
		filler.getEntries().replace(pos, new LodestoneBlockFiller.BlockStateEntry(MalumObjects.VOID_CONDUIT.getDefaultState()));

		BlockPos above = pos.up();
		for (Direction direction : directions) {
			BlockPos.Mutable start = above.mutableCopy().move(direction, 2).move(direction.rotateYCounterclockwise(), 2);
			for (int i = 0; i < 4; i++) {
				Block block = MalumObjects.WEEPING_WELL_SIDE;
				BlockPos.Mutable segmentPosition = start.move(direction.rotateYClockwise());
				if (i == 1) {
					block = MalumObjects.WEEPING_WELL_CORE;
				}
				if (i == 3) {
					block = MalumObjects.WEEPING_WELL_CORNER;
				}
				BlockState state = block.getDefaultState().with(WeepingWellBlock.FACING, direction);
				BlockPos immutable = segmentPosition.toImmutable();
				filler.getEntries().put(immutable, new LodestoneBlockFiller.BlockStateEntry(state));
				filler.getEntries().put(immutable.down(), new LodestoneBlockFiller.BlockStateEntry(Blocks.DEEPSLATE.getDefaultState()));
				filler.getEntries().put(immutable.up(), new LodestoneBlockFiller.BlockStateEntry(Blocks.CAVE_AIR.getDefaultState()));
				filler.getEntries().put(immutable.up(2), new LodestoneBlockFiller.BlockStateEntry(Blocks.CAVE_AIR.getDefaultState()));
			}
		}

		int startingIndex = rand.nextInt(2);
		Direction cachedChosenDirection = null;
		for (int i = 0; i < 2; i++) {
			Direction columnDirection = directions[startingIndex + i * 2];
			BlockPos.Mutable columnPosition = pos.mutableCopy().move(0, 2, 0).move(columnDirection, 3);
			if (rand.nextBoolean()) {
				Direction chosenDirection;
				if (cachedChosenDirection == null) {
					chosenDirection = rand.nextBoolean() ? columnDirection.rotateYCounterclockwise() : columnDirection.rotateYClockwise();
				} else {
					chosenDirection = rand.nextBoolean() ? cachedChosenDirection.getOpposite() : null;
				}
				if (chosenDirection != null) {
					columnPosition.move(chosenDirection);
				}
				cachedChosenDirection = chosenDirection;
			}
			int columnHeight = 3 + rand.nextInt(4);
			int wallHeightDifference = 1 + rand.nextInt(3);
			int wallHeight = columnHeight - wallHeightDifference;
			for (int j = 0; j < columnHeight; j++) {
				BlockState state;
				if (j == 0 || j == columnHeight - 1) {
					state = MalumObjects.TAINTED_ROCK_COLUMN_CAP.getDefaultState().with(Properties.FACING, j == 0 ? Direction.DOWN : Direction.UP);
				} else {
					state = MalumObjects.TAINTED_ROCK_COLUMN.getDefaultState().with(Properties.AXIS, Direction.Axis.Y);
				}
				filler.getEntries().put(columnPosition.toImmutable(), new LodestoneBlockFiller.BlockStateEntry(state));
				if (j < wallHeight) {
					BlockState wallState = MalumObjects.TAINTED_ROCK_BRICKS_WALL.getDefaultState();
					final WallShape wallSide = j == wallHeight-1 ? WallShape.LOW : WallShape.TALL;
					switch (columnDirection) {
						case SOUTH -> wallState = wallState.with(WallBlock.SOUTH_SHAPE, wallSide);
						case NORTH -> wallState = wallState.with(WallBlock.NORTH_SHAPE, wallSide);
						case WEST -> wallState = wallState.with(WallBlock.WEST_SHAPE, wallSide);
						case EAST -> wallState = wallState.with(WallBlock.EAST_SHAPE, wallSide);
					}
					filler.getEntries().put(columnPosition.move(columnDirection.getOpposite()).toImmutable(), new LodestoneBlockFiller.BlockStateEntry(wallState));
					columnPosition.move(columnDirection);
				}
				columnPosition.move(0, 1, 0);
			}
		}

		filler.fill(level);
		return true;
	}

	private boolean isSufficientlyFlat(StructureWorldAccess level, BlockPos origin) {
		final long count = BlockPos.stream(origin.add(-3, 0, -3), origin.add(3, 0, 3))
				.filter(pos -> level.getBlockState(pos).isSideSolidFullSquare(level, pos.down(), Direction.UP))
				.filter(pos -> level.getBlockState(pos.up()).isAir())
				.count();
		return count >= 20; //maximum is 49
	}

	public static boolean canPlace(StructureWorldAccess level, BlockPos pos) {
		if (level.isOutOfHeightLimit(pos)) {
			return false;
		}
		BlockState state = level.getBlockState(pos);
		return level.isAir(pos) || state.getMaterial().isReplaceable();
	}


}
