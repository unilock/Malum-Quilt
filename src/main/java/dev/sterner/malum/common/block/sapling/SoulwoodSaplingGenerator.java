package dev.sterner.malum.common.block.sapling;

import dev.sterner.malum.common.registry.MalumWorldRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class SoulwoodSaplingGenerator extends SaplingGenerator {
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
        return null;//MalumWorldRegistry.RUNEWOOD_TREE;
    }
}
