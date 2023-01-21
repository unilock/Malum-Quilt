package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.rite.MalumRiteType;
import dev.sterner.malum.common.rite.SacredRiteType;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

import static dev.sterner.malum.Malum.MODID;

public class MalumRiteRegistry {
	public static RegistryKey<Registry<MalumRiteType>> RITE_KEY = RegistryKey.ofRegistry(new Identifier(MODID, "rite"));
	public static Registry<MalumRiteType> RITE = Registries.registerSimple(RITE_KEY, registry -> MalumRiteRegistry.SACRED_RITE);

	public static MalumRiteType SACRED_RITE = register("sacred_rite", new SacredRiteType());


	public static MalumRiteType getRite(String identifier) {
		for (MalumRiteType rite : RITE) {
			if (rite.identifier.equals(identifier)) {
				return rite;
			}
		}
		return null;
	}

	public static MalumRiteType getRite(List<MalumSpiritType> spirits) {
		for (MalumRiteType rite : RITE) {
			if (rite.spirits.equals(spirits)) {
				return rite;
			}
		}
		return null;
	}

	public static <T extends MalumRiteType> T register(String id, T rite) {
		return Registry.register(RITE, new Identifier(MODID, id), rite);
	}

	public static void init() {

	}
}
