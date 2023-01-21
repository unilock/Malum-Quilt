package dev.sterner.malum.common.item.impedus;

import dev.sterner.malum.common.recipe.SpiritRepairRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ImpetusItem extends Item implements SpiritRepairRecipe.IRepairOutputOverride {
    private CrackedImpetusItem cracked;

    public ImpetusItem(Settings settings) {
        super(settings);
    }

    public ImpetusItem setCrackedVariant(CrackedImpetusItem cracked) {
        this.cracked = cracked;
        cracked.setRepairedVariant(this);
        return this;
    }
    public CrackedImpetusItem getCrackedVariant() {
        return cracked;
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean ignoreDuringLookup() {
        return true;
    }
}
