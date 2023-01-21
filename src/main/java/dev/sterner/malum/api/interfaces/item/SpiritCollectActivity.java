package dev.sterner.malum.api.interfaces.item;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface SpiritCollectActivity {
	default void collect(ItemStack spirit, LivingEntity livingEntity, SlotReference slot, ItemStack trinket, double arcaneResonance){

	}
}
