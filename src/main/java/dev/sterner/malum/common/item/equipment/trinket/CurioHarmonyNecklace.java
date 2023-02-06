package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import dev.sterner.malum.common.spirit.MalumEntitySpiritData;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class CurioHarmonyNecklace extends TrinketItem {
    public CurioHarmonyNecklace(Settings settings) {
        super(settings);
    }


    public static double preventDetection(PlayerEntity livingEntity, Entity entity, double modifier) {
		if(entity instanceof LivingEntity watcher){
			var v = TrinketsApi.getTrinketComponent(livingEntity);
			if(v.isPresent()){
				if (v.get().isEquipped(MalumObjects.NECKLACE_OF_BLISSFUL_HARMONY)) {
					MalumEntitySpiritData data = SpiritHelper.getEntitySpiritData(watcher);
					float visibilityModifier = data == null ? 0.5f : 0.5f / (1+data.dataEntries.stream().map(s -> s.type.equals(MalumSpiritTypeRegistry.WICKED_SPIRIT) ? 1 : 0).count());
					modifier *= visibilityModifier;
					return modifier;
				}
			}
		}
		return modifier;
    }
}
