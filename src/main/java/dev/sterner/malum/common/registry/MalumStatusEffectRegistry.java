package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.statuseffect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;

public interface MalumStatusEffectRegistry {
	Map<Identifier, StatusEffect> STATUS_EFFECTS = new LinkedHashMap<>();
	Map<Identifier, Float> ALCHEMICAL_PROFICIENCY_MAP = new LinkedHashMap<>();

	StatusEffect GAIAN_BULWARK = register("gaian_bulwark", new EarthenAura());
	StatusEffect EARTHEN_MIGHT = register("earthen_might", new CorruptedEarthenAura());

	StatusEffect MINERS_RAGE = register("miners_rage", new InfernalAura());
	StatusEffect IFRITS_EMBRACE = register("ifrits_embrace", new CorruptedInfernalAura());

	StatusEffect ZEPHYRS_COURAGE = register("zephyrs_courage", new AerialAura());
	StatusEffect AETHERS_CHARM = register("aethers_charm", new CorruptedAerialAura());

	StatusEffect POSEIDONS_GRASP = register("poseidons_grasp", new AqueousAura());
	StatusEffect ANGLERS_LURE = register("anglers_lure", new CorruptedAqueousAura());

	StatusEffect GLUTTONY = registerWithproficiency("gluttony", new GluttonyEffect(), 0.5f);
	StatusEffect WICKED_INTENT = registerWithproficiency("wicked_intent", new WickedIntentEffect(), 0.2f);

	StatusEffect REJECTED = register("rejected", new RejectedEffect());

	static <T extends StatusEffect> StatusEffect registerWithproficiency(String id, T effect, float proficiency) {
		STATUS_EFFECTS.put(new Identifier(MODID, id), effect);
		ALCHEMICAL_PROFICIENCY_MAP.put(new Identifier(MODID, id), proficiency);
		return effect;
	}

	static <T extends StatusEffect> StatusEffect register(String id, T effect) {
		return registerWithproficiency(id, effect, 1);
	}

	static void init() {
		STATUS_EFFECTS.forEach((id, effect) -> Registry.register(Registries.STATUS_EFFECT, id, effect));
	}
}
