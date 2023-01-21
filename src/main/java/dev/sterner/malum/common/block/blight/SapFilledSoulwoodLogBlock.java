package dev.sterner.malum.common.block.blight;

import dev.sterner.malum.common.block.SapFilledLogBlock;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;

public class SapFilledSoulwoodLogBlock extends SapFilledLogBlock {
    public SapFilledSoulwoodLogBlock(Settings settings, Block drained, Item sap, Color sapColor) {
        super(settings, drained, sap, sapColor);
    }

    @Override
    public void collectSap(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, MalumSoundRegistry.MAJOR_BLIGHT_MOTIF, SoundCategory.BLOCKS, 1, 1);
        super.collectSap(world, pos, player);
    }
}
