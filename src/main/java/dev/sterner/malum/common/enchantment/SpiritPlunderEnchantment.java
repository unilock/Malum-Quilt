package dev.sterner.malum.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class SpiritPlunderEnchantment extends Enchantment {
    public SpiritPlunderEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot... slots) {
        super(rarity, target, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
