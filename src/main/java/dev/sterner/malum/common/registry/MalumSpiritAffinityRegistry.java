package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.spirit.MalumSpiritAffinity;
import dev.sterner.malum.common.spirit.affinity.ArcaneAffinity;

import java.util.HashMap;

public class MalumSpiritAffinityRegistry {
	public static HashMap<String, MalumSpiritAffinity> AFFINITIES = new HashMap<>();

	public static MalumSpiritAffinity ARCANE_AFFINITY = register(new ArcaneAffinity());
//    public static final MalumSpiritAffinity EARTHEN_AFFINITY = create(new EarthenAffinity());

	public static MalumSpiritAffinity register(MalumSpiritAffinity affinity) {
		AFFINITIES.put(affinity.identifier, affinity);
		return affinity;
	}
}
