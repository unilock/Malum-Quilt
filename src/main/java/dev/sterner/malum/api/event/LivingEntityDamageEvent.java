package dev.sterner.malum.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public final class LivingEntityDamageEvent {

	public static final Event<OnDamage> ON_DAMAGE_EVENT = EventFactory.createArrayBacked(OnDamage.class,
			(listeners) -> (hurtEntity, attacker, world) -> {
				for (OnDamage event : listeners) {
					return event.react(hurtEntity, attacker, world);
				}
				return false;
			}
	);

	@FunctionalInterface
	public interface OnDamage {
		/**
		 * @param hurtEntity the entity who took the damage
		 * @param attacker the entity who delivered the damage
		 * @param world za warudo
		 * @return true if damage should be negated
		 */
		boolean react(LivingEntity hurtEntity, @Nullable Entity attacker, World world);
	}

	public static final Event<Modify> MODIFY_EVENT = EventFactory.createArrayBacked(Modify.class,
	    (l) -> (hurtEntity, world, amount) -> {
			for(Modify event : l){
				return event.modify(hurtEntity, world, amount);
			}
			return amount;
		});

	@FunctionalInterface
	public interface Modify {
		float modify(LivingEntity hurtEntity, World world, float amount);
	}

}
