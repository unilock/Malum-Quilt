package dev.sterner.malum.common.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.helpers.DataHelper;
import com.sammy.lodestone.systems.worldgen.LodestoneBlockFiller;
import dev.sterner.malum.common.block.BlockTagRegistry;
import dev.sterner.malum.common.block.MalumLeavesBlock;
import dev.sterner.malum.common.registry.MalumBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.util.random.LegacySimpleRandom;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.sterner.malum.common.world.gen.feature.RunewoodTreeFeature.canPlace;
import static dev.sterner.malum.common.world.gen.feature.RunewoodTreeFeature.updateLeaves;
import static net.minecraft.registry.tag.BlockTags.*;

public class SoulwoodTreeFeature extends Feature<DefaultFeatureConfig> {
    private static final OctaveSimplexNoiseSampler BLIGHT_NOISE = new OctaveSimplexNoiseSampler(new ChunkRandom(new LegacySimpleRandom(1234L)), ImmutableList.of(0));

    private static final int minimumSapBlockCount = 3;
    private static final int extraSapBlockCount = 5;

    private static final int minimumTrunkHeight = 12;
    private static final int extraTrunkHeight = 6;
    private static final int minimumTwistCooldown = 3;
    private static final int extraTwistCooldown = 1;
    private static final int minimumTrunkTwists = 2;
    private static final int extraTrunkTwists = 4;
    private static final int minimumSideTrunkHeight = 1;
    private static final int extraSideTrunkHeight = 2;

    private static final int minimumDownwardsBranchOffset = 2;
    private static final int extraDownwardsBranchOffset = 3;
    private static final int minimumBranchCoreOffset = 2;
    private static final int branchCoreOffsetExtra = 3;
    private static final int minimumBranchTwists = 0;
    private static final int extraBranchTwists = 2;
    private static final int minimumBranchHeight = 5;
    private static final int branchHeightExtra = 2;

    public SoulwoodTreeFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean place(FeatureContext<DefaultFeatureConfig> ctx) {
        var world = ctx.getWorld();
        BlockPos pos = ctx.getOrigin();
        RandomGenerator rand = ctx.getRandom();
        if (world.isAir(pos.down()) || !MalumBlockRegistry.SOULWOOD_GROWTH.getDefaultState().canPlaceAt(world, pos)) {
            return false;
        }
        BlockState defaultLog = MalumBlockRegistry.SOULWOOD_LOG.getDefaultState();
        BlockState blightedLog = MalumBlockRegistry.BLIGHTED_SOULWOOD.getDefaultState();

        LodestoneBlockFiller treeFiller = new LodestoneBlockFiller(false);
        LodestoneBlockFiller blightFiller = new LodestoneBlockFiller(false);
        LodestoneBlockFiller leavesFiller = new LodestoneBlockFiller(true);

        int trunkHeight = minimumTrunkHeight + rand.nextInt(extraTrunkHeight + 1);
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST};
        int twistDirection = rand.nextInt(directions.length);
        int twistCooldown = 3;
        int twists = minimumTrunkTwists + rand.nextInt(extraTrunkTwists + 1);
        int lowestPossibleBranch = minimumDownwardsBranchOffset + extraDownwardsBranchOffset;
        BlockPos twistedPos = pos;
        for (int i = 0; i <= trunkHeight; i++) //trunk placement
        {
            if (i < trunkHeight - lowestPossibleBranch) {
                if (twistCooldown == 0 && twists != 0) {
                    twistCooldown = minimumTwistCooldown + rand.nextInt(extraTwistCooldown + 1);
                    BlockPos trunkPos = twistedPos.up(i);
                    if (canPlace(world, trunkPos)) {
                        treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(defaultLog, trunkPos));
                        twistCooldown--;
                    } else {
                        return false;
                    }
                    twistedPos = twistedPos.offset(directions[twistDirection]);
                    if (rand.nextFloat() < 0.85f) {
                        twistDirection++;
                        if (twistDirection == 4) {
                            twistDirection = 0;
                        }
                    }
                    twists--;
                }
            }
            BlockPos trunkPos = twistedPos.up(i);
            if (canPlace(world, trunkPos)) {
                treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(i == 0 ? blightedLog : defaultLog, trunkPos));
                twistCooldown--;
            } else {
                return false;
            }
        }
        BlockPos trunkTop = twistedPos.up(trunkHeight);

        makeLeafBlob(leavesFiller, rand, trunkTop);
        for (Direction direction : directions) //side trunk placement
        {
            int blightedIndex = 0;
            int sideTrunkHeight = minimumSideTrunkHeight + rand.nextInt(extraSideTrunkHeight + 1);
            for (int i = 0; i < sideTrunkHeight; i++) {
                BlockPos sideTrunkPos = pos.offset(direction).up(i);
                if (canPlace(world, sideTrunkPos)) {
                    if (i == 0) {
                        blightedIndex = treeFiller.entries.size();
                    }
                    treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(i == 0 ? blightedLog : defaultLog, sideTrunkPos));
                } else {
                    return false;
                }
            }
            boolean success = downwardsTrunk(world, treeFiller, pos.offset(direction));
            if (success) {
                treeFiller.replace(blightedIndex, e -> e.replaceState(defaultLog));
            }
        }
        for (Direction direction : directions) //tree top placement
        {
            int branchCoreOffset = minimumDownwardsBranchOffset + rand.nextInt(extraDownwardsBranchOffset + 1);
            int branchOffset = minimumBranchCoreOffset + rand.nextInt(branchCoreOffsetExtra + 1);
            BlockPos branchStartPos = trunkTop.down(branchCoreOffset);
            twistCooldown = 1;
            twists = minimumBranchTwists + rand.nextInt(extraBranchTwists + 1);
            for (int i = 1; i < branchOffset; i++) //branch connection placement
            {
                BlockPos branchConnectionPos = branchStartPos.offset(direction, i);
                if (twistCooldown == 0 && twists != 0) {
                    twistCooldown = minimumTwistCooldown + rand.nextInt(extraTwistCooldown + 1);
                    BlockPos offsetPos = branchConnectionPos.up();
                    if (canPlace(world, offsetPos)) {
                        treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(defaultLog.with(PillarBlock.AXIS, direction.getAxis()), offsetPos));
                    } else {
                        return false;
                    }
                    branchStartPos = branchStartPos.up();
                    twists--;
                }
                if (canPlace(world, branchConnectionPos)) {
                    treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(defaultLog.with(PillarBlock.AXIS, direction.getAxis()), branchConnectionPos));
                    twistCooldown--;
                } else {
                    return false;
                }
            }
            BlockPos branchEndPos = branchStartPos.offset(direction, branchOffset);
            int branchHeight = minimumBranchHeight + rand.nextInt(branchHeightExtra + 1);
            for (int i = 0; i < branchHeight; i++) //branch placement
            {
                BlockPos branchPos = branchEndPos.up(i);
                if (canPlace(world, branchPos)) {
                    treeFiller.entries.add(new LodestoneBlockFiller.BlockStateEntry(defaultLog, branchPos));
                } else {
                    return false;
                }
            }
            makeLeafBlob(leavesFiller, rand, branchEndPos.up(1));
        }
        generateBlight(world, blightFiller, pos.down(), 6);

        int sapBlockCount = minimumSapBlockCount + rand.nextInt(extraSapBlockCount + 1);
        int[] sapBlockIndexes = DataHelper.nextInts(sapBlockCount, treeFiller.entries.size());
        for (Integer index : sapBlockIndexes) {
            treeFiller.replace(index, e -> e.replaceState(BlockHelper.getBlockStateWithExistingProperties(e.state, MalumBlockRegistry.EXPOSED_SOULWOOD_LOG.getDefaultState())));
        }

        blightFiller.fill(world);
        treeFiller.fill(world);
        leavesFiller.fill(world);
        updateLeaves(world, treeFiller.entries.stream().map(e -> e.pos).collect(Collectors.toSet()));
        return true;
    }

    public static boolean downwardsTrunk(StructureWorldAccess worldAccess, LodestoneBlockFiller filler, BlockPos pos) {
        BlockState defaultLog = MalumBlockRegistry.SOULWOOD_LOG.getDefaultState();
        BlockState blightedLog = MalumBlockRegistry.BLIGHTED_SOULWOOD.getDefaultState();
        int i = 0;
        do {
            i++;
            BlockPos trunkPos = pos.down(i);
            if (canPlace(worldAccess, trunkPos)) {
                boolean blighted = !canPlace(worldAccess, trunkPos.down());
                filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(blighted ? blightedLog : defaultLog, trunkPos));
            } else {
                break;
            }
            if (i > worldAccess.getTopY()) {
                break;
            }
        }
        while (true);
        return i > 1;
    }

    public static void makeLeafBlob(LodestoneBlockFiller filler, RandomGenerator rand, BlockPos pos) {
        makeLeafSlice(filler, rand, pos, 1, 0);
        makeLeafSlice(filler, rand, pos.up(1), 2, 1);
        makeLeafSlice(filler, rand, pos.up(2), 3, 2);
        makeLeafSlice(filler, rand, pos.up(3), 3, 3);
        makeLeafSlice(filler, rand, pos.up(3), 3, 3 - rand.nextInt(1));
        makeLeafSlice(filler, rand, pos.up(4), 3, 3 - rand.nextInt(1));
        makeLeafSlice(filler, rand, pos.up(5), 2, 3 + rand.nextInt(1));
        makeLeafSlice(filler, rand, pos.up(6), 1, 4);
    }

    public static void makeLeafSlice(LodestoneBlockFiller filler, RandomGenerator rand, BlockPos pos, int leavesSize, int leavesColor) {
        for (int x = -leavesSize; x <= leavesSize; x++) {
            for (int z = -leavesSize; z <= leavesSize; z++) {
                if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize) {
                    continue;
                }
                BlockPos leavesPos = new BlockPos(pos).add(x, 0, z);
                int offsetColor = leavesColor + MathHelper.nextInt(rand, leavesColor == 0 ? 0 : -1, leavesColor == 4 ? 0 : 1);
                filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(MalumBlockRegistry.SOULWOOD_LEAVES.getDefaultState().with(MalumLeavesBlock.COLOR, offsetColor), leavesPos));
            }
        }
    }

    public static Map<Integer, Double> generateBlight(StructureWorldAccess worldAccess, LodestoneBlockFiller filler, BlockPos pos, int radius) {
        Map<Integer, Double> noiseValues = new HashMap<>();
        for (int i = 0; i <= 360; i++) {
            noiseValues.put(i, BLIGHT_NOISE.sample(pos.getX() + pos.getZ() + i * 0.02f, pos.getY() / 0.05f, true) * 2.5f);
        }
        generateBlight(worldAccess, filler, noiseValues, pos, radius);
        return noiseValues;
    }

    public static void generateBlight(StructureWorldAccess worldAccess, LodestoneBlockFiller filler, Map<Integer, Double> noiseValues, BlockPos pos, int radius) {
        generateBlight(worldAccess, filler, pos, radius*2, radius, noiseValues);
        filler.fill(worldAccess);
    }

    public static void generateBlight(StructureWorldAccess worldAccess, LodestoneBlockFiller filler, BlockPos center, int radius, int effectiveRadius, Map<Integer, Double> noiseValues) {
        int x = center.getX();
        int z = center.getZ();
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        int saplingsPlaced = 0;
        Vec3d lastSaplingPos = null;
        for (int i = 0; i < radius * 2 + 1; i++) {
            for (int j = 0; j < radius * 2 + 1; j++) {
                int xp = x + i - radius;
                int zp = z + j - radius;
                blockPos.set(xp, center.getY(), zp);
                double theta = 180 + 180 / Math.PI * Math.atan2(x - xp, z - zp);
                double naturalNoiseValue = noiseValues.get(MathHelper.floor(theta));
                if (naturalNoiseValue > 1f) {
                    naturalNoiseValue *= naturalNoiseValue;
                }
                int floor = (int) Math.floor(pointDistancePlane(xp, zp, x, z));
                if (floor <= (effectiveRadius + Math.floor(naturalNoiseValue) - 1)) {
                    int verticalRange = 4;
                    for (int i1 = 0; worldAccess.testBlockState(blockPos, (s) -> !s.isAir()) && i1 < verticalRange; ++i1) {
                        blockPos.move(Direction.UP);
                    }
                    for (int k = 0; worldAccess.testBlockState(blockPos, AbstractBlock.AbstractBlockState::isAir) && k < verticalRange; ++k) {
                        blockPos.move(Direction.DOWN);
                    }
                    do {
                        BlockState plantState = worldAccess.getBlockState(blockPos);
                        if (plantState.isAir()) {
                            break;
                        }
                        if (plantState.isIn(BlockTagRegistry.BLIGHTED_PLANTS)) {
                            break;
                        }
                        if ((plantState.getMaterial().isReplaceable() || plantState.isIn(REPLACEABLE_PLANTS) || plantState.isIn(FLOWERS))) {
                            filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(Blocks.AIR.getDefaultState(), blockPos.toImmutable()));
                            blockPos.move(Direction.DOWN);
                        } else {
                            break;
                        }
                    }
                    while (true);

                    BlockPos immutable = blockPos.toImmutable();
                    if (worldAccess.getBlockState(immutable).isIn(MOSS_REPLACEABLE)) {
                        filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(MalumBlockRegistry.BLIGHTED_SOIL.getDefaultState(), immutable));
                        if (worldAccess.getBlockState(immutable.down()).isIn(DIRT)) {
                            filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(MalumBlockRegistry.BLIGHTED_EARTH.getDefaultState(), immutable.down()));
                        }
                        if (worldAccess.getRandom().nextFloat() < 0.8f) {
                            BlockPos plantPos = immutable.up();
                            BlockState blockState = worldAccess.getBlockState(plantPos);
                            if (naturalNoiseValue > 2.5f) {
                                if (lastSaplingPos == null || lastSaplingPos.squaredDistanceTo(plantPos.getX(), plantPos.getY(), plantPos.getZ()) > 5) {
                                    if (BlockHelper.fromBlockPos(center).squaredDistanceTo(plantPos.getX(), plantPos.getY(), plantPos.getZ()) > 4) {
                                        if (worldAccess.getRandom().nextFloat() < 0.5f / (Math.pow(saplingsPlaced + 1, 2))) {
                                            filler.entries.add(new LodestoneBlockFiller.BlockStateEntry(MalumBlockRegistry.SOULWOOD_GROWTH.getDefaultState(), plantPos));
                                            lastSaplingPos = new Vec3d(plantPos.getX(), plantPos.getY(), plantPos.getZ());
                                            saplingsPlaced++;
                                        }
                                        continue;
                                    }
                                }
                            }
                            if (blockState.isAir() && !blockState.isIn(BlockTagRegistry.BLIGHTED_PLANTS)) {
                                filler.entries.add(new LodestoneBlockFiller.BlockStateEntry((worldAccess.getRandom().nextFloat() < 0.2f ? MalumBlockRegistry.BLIGHTED_TUMOR : MalumBlockRegistry.BLIGHTED_WEED).getDefaultState(), plantPos));
                            }
                        }
                    }
                }
            }
        }
    }

    public static float pointDistancePlane(double x1, double z1, double x2, double z2) {
        return (float) Math.hypot(x1 - x2, z1 - z2);
    }
}
