package dev.sterner.malum.common.enchantment;

import dev.sterner.malum.common.registry.MalumTagRegistry;
import dev.sterner.malum.mixin.client.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class SoulHunterWeaponEnchantmentTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item.getDefaultStack().isIn(MalumTagRegistry.SOUL_HUNTER_WEAPON);
    }
}
