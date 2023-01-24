package dev.sterner.malum.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class HauntedEnchantment extends Enchantment {
    public HauntedEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot... slots) {
        super(rarity, target, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public int getMagicDamage(int world) {
        return 1 + world;
    }
}
