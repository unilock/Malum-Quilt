package dev.sterner.malum.common.util;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public class TrinketsHelper {


	public static boolean hasTrinket(LivingEntity livingEntity, Item item){
		return TrinketsApi.getTrinketComponent(livingEntity).map(trinketComponent -> trinketComponent.isEquipped(item)).orElse(false);
	}
}
