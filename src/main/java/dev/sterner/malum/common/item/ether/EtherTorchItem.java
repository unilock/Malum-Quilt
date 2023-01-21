package dev.sterner.malum.common.item.ether;

import com.sammy.lodestone.setup.LodestoneScreenParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.screen.base.ScreenParticle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;

public class EtherTorchItem extends AbstractEtherItem {
    protected final Block wallBlock;

    public EtherTorchItem(Block blockIn, Block wallBlockIn, Settings builder, boolean iridescent) {
        super(blockIn, builder, iridescent);
        this.wallBlock = wallBlockIn;
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockstate = this.wallBlock.getPlacementState(context);
        BlockState blockstate1 = null;
        World level = context.getWorld();
        BlockPos blockpos = context.getBlockPos();

        for (Direction direction : context.getPlacementDirections()) {
            if (direction != Direction.UP) {
                BlockState blockstate2 = direction == Direction.DOWN ? this.getBlock().getPlacementState(context) : blockstate;
                if (blockstate2 != null && blockstate2.canPlaceAt(level, blockpos)) {
                    blockstate1 = blockstate2;
                    break;
                }
            }
        }

        return blockstate1 != null && level.canPlace(blockstate1, blockpos, ShapeContext.absent()) ? blockstate1 : null;
    }

    public void appendBlocks(Map<Block, Item> blockToItemMap, Item itemIn) {
        super.appendBlocks(blockToItemMap, itemIn);
        blockToItemMap.put(this.wallBlock, itemIn);
    }

    //TODO forge patch
    /*
    public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
        super.removeFromBlockToItemMap(blockToItemMap, itemIn);
        blockToItemMap.remove(this.wallBlock);
    }


     */
    @Override
    public void particleTick(ItemStack stack, float x, float y, ScreenParticle.RenderOrder renderOrder) {
        World world = MinecraftClient.getInstance().world;
        float gameTime = world.getTime() + MinecraftClient.getInstance().getTickDelta();
        AbstractEtherItem etherItem = (AbstractEtherItem) stack.getItem();
        Color firstColor = new Color(etherItem.getFirstColor(stack));
        Color secondColor = new Color(etherItem.getSecondColor(stack));
        float alphaMultiplier = etherItem.iridescent ? 0.75f : 0.5f;
        ParticleBuilders.create(LodestoneScreenParticles.STAR)
            .setAlpha(0.11f * alphaMultiplier, 0f)
            .setLifetime(7)
            .setScale((float) (0.75f + Math.sin(gameTime * 0.05f) * 0.125f), 0)
            .setColor(firstColor, secondColor)
            .setColorCoefficient(1.25f)
            .randomOffset(0.05f)
            .setSpinOffset(0.025f * gameTime % 6.28f)
            .setSpin(0, 1)
            .setSpinEasing(Easing.EXPO_IN_OUT)
            .setAlphaEasing(Easing.QUINTIC_IN)
            .overwriteRenderOrder(renderOrder)
            .centerOnStack(stack, 0, -1)
            .repeat(x, y, 1)
            .setScale((float) (0.75f - Math.sin(gameTime * 0.075f) * 0.125f), 0)
            .setColor(secondColor, firstColor)
            .setSpinOffset(0.785f - 0.01f * gameTime % 6.28f)
            .repeat(x, y, 1);
    }
}
