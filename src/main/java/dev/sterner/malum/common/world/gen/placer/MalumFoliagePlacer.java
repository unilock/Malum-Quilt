package dev.sterner.malum.common.world.gen.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.sterner.malum.common.block.MalumLeavesBlock;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumWorldRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.BiConsumer;

public class MalumFoliagePlacer extends FoliagePlacer {
	public static final Codec<MalumFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> fillFoliagePlacerFields(instance).apply(instance, MalumFoliagePlacer::new));


	public MalumFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return null;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator random, TreeFeatureConfig config, int trunkHeight, TreeNode node, int foliageHeight, int radius, int offset) {
		var pos = node.getCenter();
		if (node instanceof MalumTreeNode dirNode) {
			var dir = dirNode.dir;
			var length = dirNode.branchLength;
			//makeLeafBlob();
		}
	}

	protected void generateBetween(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator random, TreeFeatureConfig config, BlockPos pos1, BlockPos pos2, float placementChance) {
		for (BlockPos mutable : BlockPos.Mutable.iterate(pos1, pos2)) {
			if (random.nextFloat() <= placementChance) {
				placeFoliageBlock(world, replacer, random, config, mutable);
			}
		}
	}

	public static void makeLeafBlob(RandomGenerator rand, BlockPos pos, int leavesColor) {
		makeLeafSlice(pos, 1, 0);
		makeLeafSlice(pos.up(1), 2, 1);
		makeLeafSlice(pos.up(2), 2, 2);
		makeLeafSlice(pos.up(3), 2, 3);
		makeLeafSlice(pos.up(4), 1, 4);
	}

	public static void makeLeafSlice(BlockPos pos, int leavesSize, int leavesColor) {
		for (int x = -leavesSize; x <= leavesSize; x++) {
			for (int z = -leavesSize; z <= leavesSize; z++) {
				if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
					continue;
				}
				BlockPos leavesPos = new BlockPos(pos).add(x, 0, z);
				//placeFoliageBlock(world, replacer, random, config, mutable);
			}
		}
	}


	@Override
	public int getRandomHeight(RandomGenerator random, int trunkHeight, TreeFeatureConfig config) {
		return 3;
	}

	@Override
	protected boolean isInvalidForLeaves(RandomGenerator random, int dx, int y, int dz, int radius, boolean giantTrunk) {
		return dx == radius && dz == radius;
	}

	public static class MalumTreeNode extends DirectionalTreeNode {

		public final int branchLength;

		public MalumTreeNode(BlockPos center, int foliageRadius, boolean giantTrunk, Direction dir, int branchlength) {
			super(center, foliageRadius, giantTrunk, dir);
			this.branchLength = branchlength;
		}
	}
}
