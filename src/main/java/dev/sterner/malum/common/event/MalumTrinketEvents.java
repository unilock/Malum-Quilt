package dev.sterner.malum.common.event;


import dev.sterner.malum.api.event.LivingEntityDamageEvent;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.util.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;

public class MalumTrinketEvents {

	public static void init(){
		LivingEntityDamageEvent.MODIFY_EVENT.register(MalumTrinketEvents::waterNecklace);
	}

	private static float waterNecklace(LivingEntity livingEntity, World world, float v) {
		if(TrinketsHelper.hasTrinket(livingEntity, MalumObjects.NECKLACE_OF_TIDAL_AFFINITY)){
			if (livingEntity.hasStatusEffect(StatusEffects.CONDUIT_POWER)) {
				v = v * 0.5f;
			}
		}
		return v;
	}
}
