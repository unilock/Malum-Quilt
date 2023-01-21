package dev.sterner.malum.common.item.impedus;

import dev.sterner.malum.common.recipe.SpiritRepairRecipe;
import net.minecraft.item.Item;

public class CrackedImpetusItem extends Item implements SpiritRepairRecipe.IRepairOutputOverride {
    public ImpetusItem impetus;
    public CrackedImpetusItem(Settings settings) {
        super(settings);
    }

    public CrackedImpetusItem setRepairedVariant(ImpetusItem cracked) {
        this.impetus = cracked;
        return this;
    }

    @Override
    public Item overrideRepairResult() {
        return impetus;
    }
}
