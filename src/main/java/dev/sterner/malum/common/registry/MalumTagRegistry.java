package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface MalumTagRegistry {
	TagKey<Item> SCYTHE = itemMalumTag("scythe");
	TagKey<Item> SOUL_HUNTER_WEAPON = itemMalumTag("soul_hunter_weapon");

	TagKey<Item> RUNEWOOD_LOGS_BLOCK = itemMalumTag("runewood_logs");
	TagKey<Item> SOULWOOD_LOGS_BLOCK = itemMalumTag("soulwood_logs");
	TagKey<Item> SAPBALLS = itemMalumTag("sapballs");
	TagKey<Item> GROSS_FOODS = itemMalumTag("gross_foods");
	TagKey<Item> PROSPECTORS_TREASURE = itemMalumTag("prospectors_treasure");
	TagKey<Item> METAL_NODES = itemMalumTag("metal_nodes");

	TagKey<Block> BLIGHTED_BLOCKS = blockMalumTag("blighted_blocks");
	TagKey<Block> BLIGHTED_PLANTS = blockMalumTag("blighted_plants");

	TagKey<Block> RUNEWOOD_LOGS = blockMalumTag("runewood_logs");
	TagKey<Block> SOULWOOD_LOGS = blockMalumTag("soulwood_logs");

	TagKey<Block> TAINTED_ROCK = blockMalumTag("tainted_rock");
	TagKey<Block> TWISTED_ROCK = blockMalumTag("twisted_rock");

	TagKey<Block> RITE_IMMUNE = blockMalumTag("rite_immune");

	TagKey<Block> ENDLESS_FLAME = blockMalumTag("endless_flame");

	TagKey<Block> GREATER_AERIAL_WHITELIST = blockMalumTag("greater_aerial_whitelist");

	TagKey<EntityType<?>> SURVIVES_REJECTION = entityMalumTag("survives_rejection");

	static TagKey<Block> blockModTag(String path) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(path));
	}

	static TagKey<Block> blockMalumTag(String path) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(Malum.MODID, path));
	}

	static TagKey<Item> itemMalumTag(String path) {
		return TagKey.of(RegistryKeys.ITEM, new Identifier(Malum.MODID, path));
	}

	static TagKey<EntityType<?>> entityMalumTag(String path) {
		return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Malum.MODID, path));
	}

	static void init() {

	}
}
