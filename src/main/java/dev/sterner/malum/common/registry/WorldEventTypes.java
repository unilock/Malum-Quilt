package dev.sterner.malum.common.registry;

import com.sammy.lodestone.systems.worldevent.WorldEventType;
import dev.sterner.malum.common.worldevent.ActiveBlightEvent;
import dev.sterner.malum.common.worldevent.TotemCreatedBlightEvent;

import static com.sammy.lodestone.setup.worldevent.LodestoneWorldEventTypeRegistry.registerEventType;

public class WorldEventTypes {
	public static WorldEventType ACTIVE_BLIGHT = registerEventType(new WorldEventType("active_blight", ActiveBlightEvent::new));
	public static WorldEventType TOTEM_CREATED_BLIGHT = registerEventType(new WorldEventType("totem_blight", TotemCreatedBlightEvent::new));

}
