package dev.sterner.malum.common.world.gen.placer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.lodestone.systems.worldgen.LodestoneBlockFiller;
import dev.sterner.malum.common.block.MalumSaplingBlock;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumWorldRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.predicate.block.BlockPredicate;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MalumTrunkPlacer extends StraightTrunkPlacer {
	public static final Codec<MalumTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> fillTrunkPlacerFields(instance).apply(instance, MalumTrunkPlacer::new));

	private static final int minimumSapBlockCount = 2;
	private static final int extraSapBlockCount = 1;

	private static final int minimumTrunkHeight = 7;
	private static final int extraTrunkHeight = 3;
	private static final int minimumSideTrunkHeight = 0;
	private static final int extraSideTrunkHeight = 2;

	private static final int minimumDownwardsBranchOffset = 2;
	private static final int extraDownwardsBranchOffset = 2;
	private static final int minimumBranchCoreOffset = 2;
	private static final int branchCoreOffsetExtra = 1;
	private static final int minimumBranchHeight = 3;
	private static final int branchHeightExtra = 2;

	public MalumTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return null;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator random, int height, BlockPos startPos, TreeFeatureConfig config) {
		ImmutableList.Builder<FoliagePlacer.TreeNode> builder = ImmutableList.builder();
		builder.addAll(super.generate(world, replacer, random, height, startPos, config));
		BlockState defaultLog = MalumObjects.RUNEWOOD_LOG.getDefaultState();

		var fifthHeight = height / 5;
		var mutable = startPos.mutableCopy();
		for (int i = 0; i < fifthHeight - 1; i++) {
			placeOnCardinals(mutable, blockPos -> placeTrunkBlock(world, replacer, random, blockPos, config));
			mutable.move(Direction.UP);
		}
		placeOnCardinals(mutable, blockPos -> placeTrunkBlock(world, replacer, random, mutable, config, (state) -> defaultLog));


		var branches = fifthHeight + ((random.nextBoolean() ? 1 : -1) * random.nextInt(2));
		Set<BlockPos> branchLocations = Sets.newHashSet();
		for (int i = 0; i < branches; i++) {
			var blockHeight = MathHelper.clamp(random.nextInt(height), fifthHeight + random.nextInt(3) + 2, height - random.nextInt(3) - 5);
			var dir = Direction.Type.HORIZONTAL.random(random);
			var pos = startPos.up(blockHeight).offset(dir);
			if (pos.getY() <= startPos.up(fifthHeight + 2).getY() || setHasWithin(branchLocations, pos, 2)) {
				continue;
			}
			branchLocations.add(pos);
			var branchLength = random.nextInt(3) + 1;
			var lastPos = pos.offset(dir, branchLength);
			for (BlockPos branchPos : BlockPos.iterate(pos, lastPos)) {
				placeTrunkBlock(world, replacer, random, branchPos, config, state -> {
					if (state.contains(Properties.AXIS)) {
						return state.with(Properties.AXIS, dir.getAxis());
					}
					return state;
				});
			}
			builder.add(new MalumFoliagePlacer.MalumTreeNode(lastPos, 1, false, dir, branchLength + 1));

		}
		return builder.build();
	}

	private void placeOnCardinals(BlockPos blockPos, Consumer<BlockPos> setFunction) {
		setFunction.accept(blockPos.north());
		setFunction.accept(blockPos.south());
		setFunction.accept(blockPos.east());
		setFunction.accept(blockPos.west());
	}

	private boolean setHasWithin(Set<BlockPos> posSet, BlockPos pos, int length) {
		for (int i = -length; i <= length; i++) {
			if (posSet.contains(pos.add(0, i, 0))) {
				return true;
			}
		}
		return false;
	}

}
