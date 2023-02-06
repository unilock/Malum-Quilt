package dev.sterner.malum.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;

public final class LivingEntityEvent {

	public static final Event<Tick> EVENT = EventFactory.createArrayBacked(Tick.class,
			(listeners) -> (livingEntity) -> {
				for (Tick event : listeners) {
					event.react(livingEntity);
				}
			}
	);

	public static final Event<Target> TARGET = EventFactory.createArrayBacked(Target.class,
			(listeners) -> (mobEntity, target) -> {
				for (Target event : listeners) {
					event.react(mobEntity, target);
				}
			}
	);

	public static final Event<Add> ADDED = EventFactory.createArrayBacked(Add.class,
			(listeners) -> (mobEntity, existing) -> {
				for (Add event : listeners) {
					event.react(mobEntity, existing);
				}
			}
	);

	@FunctionalInterface
	public interface Tick {
		void react(LivingEntity livingEntity);
	}

	@FunctionalInterface
	public interface Target {
		void react(MobEntity mobEntity, @Nullable LivingEntity target);
	}

	@FunctionalInterface
	public interface Add {
		void react(LivingEntity livingEntity, boolean existing);
	}

}
