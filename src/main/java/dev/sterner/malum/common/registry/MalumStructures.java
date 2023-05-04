package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import dev.sterner.malum.common.world.gen.structure.WeepingWellStructure;
import net.minecraft.structure.StructureType;
import net.minecraft.util.registry.Registry;

public class MalumStructures {
	public static StructureType<WeepingWellStructure> WEEPING_WELL;

	public static void init() {
		WEEPING_WELL = Registry.register(Registry.STRUCTURE_TYPE, Malum.id("weeping_well"), () -> WeepingWellStructure.CODEC);
	}
}
