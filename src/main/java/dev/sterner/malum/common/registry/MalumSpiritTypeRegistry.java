package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritTypeProperty;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public interface MalumSpiritTypeRegistry {
	Map<String, MalumSpiritType> SPIRITS = new LinkedHashMap<>();

	MalumSpiritType SACRED_SPIRIT = create("sacred", new Color(243, 40, 143));
	MalumSpiritType WICKED_SPIRIT = create("wicked", new Color(155, 62, 245));
	MalumSpiritType ARCANE_SPIRIT = create("arcane", new Color(212, 55, 255));
	MalumSpiritType ELDRITCH_SPIRIT = create("eldritch", new Color(125, 29, 215), new Color(39, 201, 103));
	MalumSpiritType AERIAL_SPIRIT = create("aerial", new Color(75, 243, 218));
	MalumSpiritType AQUEOUS_SPIRIT = create("aqueous", new Color(29, 100, 232));
	MalumSpiritType INFERNAL_SPIRIT = create("infernal", new Color(210, 134, 39));
	MalumSpiritType EARTHEN_SPIRIT = create("earthen", new Color(73, 234, 27));

	SpiritTypeProperty SPIRIT_TYPE_PROPERTY = new SpiritTypeProperty("spirit_type", SPIRITS.values());

	static MalumSpiritType create(String identifier, Color color) {
		MalumSpiritType spiritType = new MalumSpiritType(identifier, color);
		SPIRITS.put(identifier, spiritType);
		return spiritType;
	}

	static MalumSpiritType create(String identifier, Color color, Color endColor) {
		MalumSpiritType spiritType = new MalumSpiritType(identifier, color, endColor);
		SPIRITS.put(identifier, spiritType);
		return spiritType;
	}

	static int getIndexForSpiritType(MalumSpiritType type) {
		List<MalumSpiritType> types = SPIRITS.values().stream().toList();
		return types.indexOf(type);
	}

	static MalumSpiritType getSpiritTypeForIndex(int slot) {
		if (slot >= SPIRITS.size())
			return null;
		List<MalumSpiritType> types = SPIRITS.values().stream().toList();
		return types.get(slot);
	}

	static void init(){

	}

}
