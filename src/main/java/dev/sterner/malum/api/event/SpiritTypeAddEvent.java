package dev.sterner.malum.api.event;

import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import net.fabricmc.fabric.api.event.Event;

import java.util.Map;
import java.util.function.Supplier;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

public interface SpiritTypeAddEvent {
	Event<SpiritTypeAddEvent> EVENT = createArrayBacked(SpiritTypeAddEvent.class, listeners -> (entries) -> {
		for (SpiritTypeAddEvent listener : listeners) {
			listener.addSpiritType(entries);
		}
	});

	void addSpiritType(Map<Supplier<MalumSpiritItem>, String> spirits);
}
