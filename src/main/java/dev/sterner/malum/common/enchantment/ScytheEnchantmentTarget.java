package dev.sterner.malum.common.enchantment;

import dev.sterner.malum.common.registry.MalumTags;
import dev.sterner.malum.mixin.client.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class ScytheEnchantmentTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item.getDefaultStack().isIn(MalumTags.SCYTHE);
    }
}
