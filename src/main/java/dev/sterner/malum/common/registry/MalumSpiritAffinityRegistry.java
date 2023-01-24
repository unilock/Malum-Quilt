package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.spirit.MalumSpiritAffinity;
import dev.sterner.malum.common.spirit.affinity.ArcaneAffinity;

import java.util.HashMap;

public interface MalumSpiritAffinityRegistry {
	HashMap<String, MalumSpiritAffinity> AFFINITIES = new HashMap<>();

	MalumSpiritAffinity ARCANE_AFFINITY = register(new ArcaneAffinity());

	static MalumSpiritAffinity register(MalumSpiritAffinity affinity) {
		AFFINITIES.put(affinity.identifier, affinity);
		return affinity;
	}
}
