package dev.sterner.malum.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class ExplosionEvent {
	public static Event<Detonate> DETONATE = EventFactory.createArrayBacked(Detonate.class, callbacks -> ((world, explosion, list, diameter) -> {
		for (Detonate event : callbacks)
			event.onDetonate(world, explosion, list, diameter);
	}));


	@FunctionalInterface
	public interface Detonate {
		void onDetonate(World world, Explosion explosion, List<Entity> list, double diameter);
	}
}
