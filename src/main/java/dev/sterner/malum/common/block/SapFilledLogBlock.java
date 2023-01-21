package dev.sterner.malum.common.block;


import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.helpers.ItemHelper;
import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.awt.*;

public class SapFilledLogBlock extends PillarBlock {
    public final Block drained;
    public final Item sap;
    public final Color sapColor;

    public SapFilledLogBlock(Settings settings, Block drained, Item sap, Color sapColor) {
        super(settings);
        this.drained = drained;
        this.sap = sap;
        this.sapColor = sapColor;
    }

    public void collectSap(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        ItemHelper.giveItemToEntity(new ItemStack(sap), player);
        if (world.random.nextBoolean()) {
            BlockHelper.setBlockStateWithExistingProperties(world, pos, drained.getDefaultState(), 3);
        }
        if (world.isClient()) {
            ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                .setAlpha(0.16f, 0f)
                .setLifetime(20)
                .setSpin(0.2f)
                .setScale(0.2f, 0)
                .setColor(sapColor, sapColor)
                .enableNoClip()
                .randomOffset(0.1f, 0.1f)
                .randomMotion(0.001f, 0.001f)
				.repeatSurroundBlock(world, pos, 8, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

            ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                .setAlpha(0.08f, 0f)
                .setLifetime(40)
                .setSpin(0.1f)
                .setScale(0.4f, 0)
                .setColor(sapColor, sapColor)
                .randomOffset(0.2f)
                .enableNoClip()
                .randomMotion(0.001f, 0.001f)
                .repeatSurroundBlock(world, pos, 12, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
        }
    }
}
