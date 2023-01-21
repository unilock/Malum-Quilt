package dev.sterner.malum.common.item;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BrillianceChunkItem extends Item {
    public BrillianceChunkItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int i = 3 + world.random.nextInt(5) + world.random.nextInt(5);

        while (i > 0) {
            int j = ExperienceOrbEntity.roundToOrbSize(i);
            i -= j;
            world.spawnEntity(new ExperienceOrbEntity(world, user.getX(), user.getY(), user.getZ(), j));
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 5;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
