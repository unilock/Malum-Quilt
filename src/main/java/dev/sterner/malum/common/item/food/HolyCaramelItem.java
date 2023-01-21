package dev.sterner.malum.common.item.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HolyCaramelItem extends Item {
    public HolyCaramelItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.heal(3f);
        return super.finishUsing(stack, world, user);
    }
}
