package dev.sterner.malum.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public final class LivingEntityDamageEvent {

	public static final Event<OnDamage> ON_DAMAGE_EVENT = EventFactory.createArrayBacked(OnDamage.class,
			(listeners) -> (hurtEntity, damageSource, amount) -> {
				for (OnDamage event : listeners) {
					return event.react(hurtEntity, damageSource, amount);
				}
				return amount;
			}
	);

	@FunctionalInterface
	public interface OnDamage {
		float react(LivingEntity hurtEntity, DamageSource damageSource, float amount);
	}
}
