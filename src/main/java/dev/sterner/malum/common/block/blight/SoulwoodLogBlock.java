package dev.sterner.malum.common.block.blight;

import dev.sterner.malum.common.block.MalumLogBlock;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoulwoodLogBlock extends MalumLogBlock {
	public SoulwoodLogBlock(Block stripped, AbstractBlock.Settings settings, boolean isCorrupt) {
		super(stripped, settings, isCorrupt);
	}

	@Override
	public boolean createTotemPole(World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, ItemStack stack, MalumSpiritItem spirit) {
		boolean success = super.createTotemPole(world, pos, player, hand, hit, stack, spirit);
		if (success) {
			world.playSound(null, pos, MalumSoundRegistry.MAJOR_BLIGHT_MOTIF, SoundCategory.BLOCKS, 1, 1);
		}
		return super.createTotemPole(world, pos, player, hand, hit, stack, spirit);
	}
}
