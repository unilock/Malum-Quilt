package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.spiritrite.*;
import dev.sterner.malum.common.spiritrite.eldritch.*;
import dev.sterner.malum.common.spirit.MalumSpiritType;

import java.util.ArrayList;
import java.util.List;

public interface MalumRiteRegistry {
	List<MalumRiteType> RITES = new ArrayList<>();

	MalumRiteType AERIAL_RITE = register(new AerialRiteType());
	MalumRiteType AQUEOUS_RITE = register(new AqueousRiteType());
	MalumRiteType ARCANE_RITE = register(new ArcaneRiteType());
	MalumRiteType EARTHEN_RITE = register(new SacredRiteType());
	MalumRiteType INFERNAL_RITE = register(new EarthenRiteType());
	MalumRiteType SACRED_RITE = register(new SacredRiteType());
	MalumRiteType WICKED_RITE = register(new WickedRiteType());

	MalumRiteType ELDRITCH_AERIAL_RITE = register(new EldritchAerialRiteType());
	MalumRiteType ELDRITCH_AQUEOUS_RITE = register(new EldritchAqueousRiteType());
	MalumRiteType ELDRITCH_EARTHEN_RITE = register(new EldritchEarthenRiteType());
	MalumRiteType ELDRITCH_INFERNAL_RITE = register(new EldritchInfernalRiteType());
	MalumRiteType ELDRITCH_SACRED_RITE = register(new EldritchSacredRiteType());
	MalumRiteType ELDRITCH_WICKED_RITE = register(new EldritchWickedRiteType());


	static MalumRiteType register(MalumRiteType type) {
		RITES.add(type);
		return type;
	}

	static MalumRiteType getRite(String identifier) {
		for (MalumRiteType rite : RITES) {
			if (rite.identifier.equals(identifier)) {
				return rite;
			}
		}
		return null;
	}

	static MalumRiteType getRite(List<MalumSpiritType> spirits) {
		for (MalumRiteType rite : RITES) {
			if (rite.spirits.equals(spirits)) {
				return rite;
			}
		}
		return null;
	}

	static void init() {

	}
}
