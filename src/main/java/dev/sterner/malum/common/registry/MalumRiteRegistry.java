package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.rite.*;
import dev.sterner.malum.common.rite.eldritch.*;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

import static dev.sterner.malum.Malum.MODID;

public interface MalumRiteRegistry {
	RegistryKey<Registry<MalumRiteType>> RITE_KEY = RegistryKey.ofRegistry(new Identifier(MODID, "rite"));
	Registry<MalumRiteType> RITE = Registries.registerSimple(RITE_KEY, registry -> MalumRiteRegistry.SACRED_RITE);

	MalumRiteType AERIAL_RITE = register("aerial_rite", new AerialRiteType());
	MalumRiteType AQUEOUS_RITE = register("aqueous_rite", new AqueousRiteType());
	MalumRiteType ARCANE_RITE = register("arcane_rite", new ArcaneRiteType());
	MalumRiteType EARTHEN_RITE = register("earthen_rite", new SacredRiteType());
	MalumRiteType INFERNAL_RITE = register("infernal_rite", new EarthenRiteType());
	MalumRiteType SACRED_RITE = register("sacred_rite", new SacredRiteType());
	MalumRiteType WICKED_RITE = register("wicked_rite", new WickedRiteType());

	MalumRiteType ELDRITCH_AERIAL_RITE = register("eldritch_aerial_rite", new EldritchAerialRiteType());
	MalumRiteType ELDRITCH_AQUEOUS_RITE = register("eldritch_aqueous_rite", new EldritchAqueousRiteType());
	MalumRiteType ELDRITCH_EARTHEN_RITE = register("eldritch_earthen_rite", new EldritchEarthenRiteType());
	MalumRiteType ELDRITCH_INFERNAL_RITE = register("eldritch_infernal_rite", new EldritchInfernalRiteType());
	MalumRiteType ELDRITCH_SACRED_RITE = register("eldritch_sacred_rite", new EldritchSacredRiteType());
	MalumRiteType ELDRITCH_WICKED_RITE = register("eldritch_wicked_rite", new EldritchWickedRiteType());


	static MalumRiteType getRite(String identifier) {
		for (MalumRiteType rite : RITE) {
			if (rite.identifier.equals(identifier)) {
				return rite;
			}
		}
		return null;
	}

	static MalumRiteType getRite(List<MalumSpiritType> spirits) {
		for (MalumRiteType rite : RITE) {
			if (rite.spirits.equals(spirits)) {
				return rite;
			}
		}
		return null;
	}

	static <T extends MalumRiteType> T register(String id, T rite) {
		return Registry.register(RITE, new Identifier(MODID, id), rite);
	}

	static void init() {

	}
}
