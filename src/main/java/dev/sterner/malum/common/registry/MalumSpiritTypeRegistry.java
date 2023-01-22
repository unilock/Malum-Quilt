package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritTypeProperty;
import net.minecraft.item.Item;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class MalumSpiritTypeRegistry {
	public static Map<String, MalumSpiritType> SPIRITS = new LinkedHashMap<>();

	public static MalumSpiritType SACRED_SPIRIT = create("sacred", new Color(243, 40, 143), MalumObjects.SACRED_SPIRIT);
	public static MalumSpiritType WICKED_SPIRIT = create("wicked", new Color(155, 62, 245), MalumObjects.WICKED_SPIRIT);
	public static MalumSpiritType ARCANE_SPIRIT = create("arcane", new Color(212, 55, 255), MalumObjects.ARCANE_SPIRIT);
	public static MalumSpiritType ELDRITCH_SPIRIT = create("eldritch", new Color(125, 29, 215), new Color(39, 201, 103), MalumObjects.ELDRITCH_SPIRIT);
	public static MalumSpiritType AERIAL_SPIRIT = create("aerial", new Color(75, 243, 218), MalumObjects.AERIAL_SPIRIT);
	public static MalumSpiritType AQUEOUS_SPIRIT = create("aqueous", new Color(29, 100, 232), MalumObjects.AQUEOUS_SPIRIT);
	public static MalumSpiritType INFERNAL_SPIRIT = create("infernal", new Color(210, 134, 39), MalumObjects.INFERNAL_SPIRIT);
	public static MalumSpiritType EARTHEN_SPIRIT = create("earthen", new Color(73, 234, 27), MalumObjects.EARTHEN_SPIRIT);

	public static SpiritTypeProperty SPIRIT_TYPE_PROPERTY = new SpiritTypeProperty("spirit_type", SPIRITS.values());

	public static MalumSpiritType create(String identifier, Color color, Item splinterItem) {
		MalumSpiritType spiritType = new MalumSpiritType(identifier, color, splinterItem);
		SPIRITS.put(identifier, spiritType);
		return spiritType;
	}

	public static MalumSpiritType create(String identifier, Color color, Color endColor, Item splinterItem) {
		MalumSpiritType spiritType = new MalumSpiritType(identifier, color, endColor, splinterItem);
		SPIRITS.put(identifier, spiritType);
		return spiritType;
	}

	public static int getIndexForSpiritType(MalumSpiritType type) {
		List<MalumSpiritType> types = SPIRITS.values().stream().toList();
		return types.indexOf(type);
	}

	public static MalumSpiritType getSpiritTypeForIndex(int slot) {
		if (slot >= SPIRITS.size())
			return null;
		List<MalumSpiritType> types = SPIRITS.values().stream().toList();
		return types.get(slot);
	}

}
