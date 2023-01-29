package dev.sterner.malum.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public final class SoulwardDamageAbsorbDamageEvent {

	public static final Event<OnAbsorbDamage> ON_ABSORB_DAMAGE_EVENT = EventFactory.createArrayBacked(OnAbsorbDamage.class,
			(listeners) -> (hurtEntity, source, soulwardLost, absorbed) -> {
				for (OnAbsorbDamage event : listeners) {
					event.react(hurtEntity, source, soulwardLost, absorbed);
				}
			}
	);

	@FunctionalInterface
	public interface OnAbsorbDamage {
		void react(LivingEntity hurtEntity, DamageSource source, float soulwardLost, float absorbed);
	}
}
