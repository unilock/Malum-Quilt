package dev.sterner.malum.common.block.totem;

import com.sammy.lodestone.systems.block.LodestoneEntityBlock;
import dev.sterner.malum.common.blockentity.totem.TotemBaseBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class TotemBaseBlock<T extends TotemBaseBlockEntity> extends LodestoneEntityBlock<T> {
    public final boolean corrupted;

    public TotemBaseBlock(Settings settings, boolean corrupted) {
        super(settings);
        this.corrupted = corrupted;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TotemBaseBlockEntity totem) {
            return totem.active ? totem.spirits.size() : 0;
        }
        return 0;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
