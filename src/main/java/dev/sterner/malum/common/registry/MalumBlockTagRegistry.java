package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MalumBlockTagRegistry {
	public static final TagKey<Block> BLIGHTED_BLOCKS = malumTag("blighted_blocks");
	public static final TagKey<Block> BLIGHTED_PLANTS = malumTag("blighted_plants");

	public static final TagKey<Block> RUNEWOOD_LOGS = malumTag("runewood_logs");
	public static final TagKey<Block> SOULWOOD_LOGS = malumTag("soulwood_logs");

	public static final TagKey<Block> TAINTED_ROCK = malumTag("tainted_rock");
	public static final TagKey<Block> TWISTED_ROCK = malumTag("twisted_rock");

	public static final TagKey<Block> RITE_IMMUNE = malumTag("rite_immune");

	public static final TagKey<Block> ENDLESS_FLAME = malumTag("endless_flame");

	public static final TagKey<Block> GREATER_AERIAL_WHITELIST = malumTag("greater_aerial_whitelist");

	public static final TagKey<Block> TRAY_HEAT_SOURCES = modTag("farmersdelight:tray_heat_sources");
	public static final TagKey<Block> HEAT_SOURCES = modTag("farmersdelight:heat_sources");


	private static TagKey<Block> modTag(String path) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(path));
	}

	private static TagKey<Block> malumTag(String path) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(Malum.MODID, path));
	}
}
