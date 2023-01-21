package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class MalumSoundRegistry {
	public static Map<Identifier, SoundEvent> SOUND_EVENTS = new LinkedHashMap<>();

	public static SoundEvent SOULSTONE_BREAK = register("soulstone_break");
	public static SoundEvent SOULSTONE_PLACE = register("soulstone_place");
	public static SoundEvent SOULSTONE_STEP = register("soulstone_step");
	public static SoundEvent SOULSTONE_HIT = register("soulstone_hit");

	public static SoundEvent DEEPSLATE_SOULSTONE_BREAK = register("deepslate_soulstone_break");
	public static SoundEvent DEEPSLATE_SOULSTONE_PLACE = register("deepslate_soulstone_place");
	public static SoundEvent DEEPSLATE_SOULSTONE_STEP = register("deepslate_soulstone_step");
	public static SoundEvent DEEPSLATE_SOULSTONE_HIT = register("deepslate_soulstone_hit");

	public static SoundEvent ARCANE_CHARCOAL_BREAK = register("arcane_charcoal_block_break");
	public static SoundEvent ARCANE_CHARCOAL_STEP = register("arcane_charcoal_block_step");
	public static SoundEvent ARCANE_CHARCOAL_PLACE = register("arcane_charcoal_block_place");
	public static SoundEvent ARCANE_CHARCOAL_HIT = register("arcane_charcoal_block_hit");

	public static SoundEvent BRILLIANCE_BREAK = register("brilliance_break");
	public static SoundEvent BRILLIANCE_PLACE = register("brilliance_place");

	public static SoundEvent BLAZING_QUARTZ_ORE_BREAK = register("blazing_quartz_ore_break");
	public static SoundEvent BLAZING_QUARTZ_ORE_PLACE = register("blazing_quartz_ore_place");

	public static SoundEvent BLAZING_QUARTZ_BLOCK_BREAK = register("blazing_quartz_block_break");
	public static SoundEvent BLAZING_QUARTZ_BLOCK_PLACE = register("blazing_quartz_block_place");
	public static SoundEvent BLAZING_QUARTZ_BLOCK_STEP = register("blazing_quartz_block_step");
	public static SoundEvent BLAZING_QUARTZ_BLOCK_HIT = register("blazing_quartz_block_hit");

	public static SoundEvent ARCANE_CHARCOAL_BLOCK_BREAK = register("arcane_charcoal_block_break");
	public static SoundEvent ARCANE_CHARCOAL_BLOCK_PLACE = register("arcane_charcoal_block_place");
	public static SoundEvent ARCANE_CHARCOAL_BLOCK_STEP = register("arcane_charcoal_block_step");
	public static SoundEvent ARCANE_CHARCOAL_BLOCK_HIT = register("arcane_charcoal_block_hit");

	public static SoundEvent TAINTED_ROCK_BREAK = register("tainted_rock_break");
	public static SoundEvent TAINTED_ROCK_PLACE = register("tainted_rock_place");
	public static SoundEvent TAINTED_ROCK_STEP = register("tainted_rock_step");
	public static SoundEvent TAINTED_ROCK_HIT = register("tainted_rock_hit");

	public static SoundEvent HALLOWED_GOLD_BREAK = register("hallowed_gold_break");
	public static SoundEvent HALLOWED_GOLD_HIT = register("hallowed_gold_hit");
	public static SoundEvent HALLOWED_GOLD_PLACE = register("hallowed_gold_place");
	public static SoundEvent HALLOWED_GOLD_STEP = register("hallowed_gold_step");

	public static SoundEvent SOUL_STAINED_STEEL_BREAK = register("soul_stained_steel_break");
	public static SoundEvent SOUL_STAINED_STEEL_HIT = register("soul_stained_steel_hit");
	public static SoundEvent SOUL_STAINED_STEEL_PLACE = register("soul_stained_steel_place");
	public static SoundEvent SOUL_STAINED_STEEL_STEP = register("soul_stained_steel_step");

	public static SoundEvent ETHER_PLACE = register("ether_place");
	public static SoundEvent ETHER_BREAK = register("ether_break");

	public static SoundEvent SCYTHE_CUT = register("scythe_cut");
	public static SoundEvent SPIRIT_HARVEST = register("spirit_harvest");

	public static SoundEvent TOTEM_CHARGE = register("totem_charge");
	public static SoundEvent TOTEM_ACTIVATED = register("totem_activated");
	public static SoundEvent TOTEM_CANCELLED = register("totem_cancelled");
	public static SoundEvent TOTEM_ENGRAVE = register("totem_engrave");

	public static SoundEvent ALTAR_CRAFT = register("altar_craft");
	public static SoundEvent ALTAR_LOOP = register("altar_loop");
	public static SoundEvent ALTAR_CONSUME  = register("altar_consume");
	public static SoundEvent ALTAR_SPEED_UP = register("altar_speed_up");

	public static SoundEvent CRUCIBLE_CRAFT = register("crucible_craft");
	public static SoundEvent CRUCIBLE_LOOP = register("crucible_loop");
	public static SoundEvent IMPETUS_CRACK = register("impetus_crack");

	public static SoundEvent SINISTER_EQUIP = register("sinister_equip");
	public static SoundEvent HOLY_EQUIP = register("holy_equip");

	public static SoundEvent VOID_SLASH = register("void_slash");

	public static SoundEvent SOUL_WARD_HIT = register("soul_ward_hit");
	public static SoundEvent SOUL_WARD_GROW = register("soul_ward_grow");
	public static SoundEvent SOUL_WARD_CHARGE = register("soul_ward_charge");

	public static SoundEvent HEART_OF_STONE_HIT = register("heart_of_stone_hit");
	public static SoundEvent HEART_OF_STONE_GROW = register("heart_of_stone_grow");

	public static SoundEvent SUSPICIOUS_SOUND = register("suspicious_sound");

	public static SoundEvent AERIAL_FALL = register("aerial_magic_swooshes");
	public static SoundEvent HIDDEN_BLADE_STRIKES = register("hidden_blade_strikes");
	public static SoundEvent HUNGRY_BELT_FEEDS = register("hungry_belt_feeds");
	public static SoundEvent NITRATE_THROWN = register("nitrate_thrown");

	public static SoundEvent MAJOR_BLIGHT_MOTIF = register("blight_reacts");
	public static SoundEvent MINOR_BLIGHT_MOTIF = register("blight_reacts_faintly");

	public static SoundEvent ALTERATION_PLINTH_ALTERS = register("alteration_plinth_alters");

	public static SoundEvent QUARTZ_CLUSTER_BLOCK_BREAK = register("quartz_cluster_block_break");
	public static SoundEvent QUARTZ_CLUSTER_BLOCK_PLACE = register("quartz_cluster_block_place");
	public static SoundEvent QUARTZ_CLUSTER_BLOCK_STEP = register("quartz_cluster_block_step");
	public static SoundEvent QUARTZ_CLUSTER_BLOCK_HIT = register("quartz_cluster_block_hit");

	public static SoundEvent SOULWOOD_LEAVES_BREAK = register("soulwood_leaves_break");
	public static SoundEvent SOULWOOD_LEAVES_HIT = register("soulwood_leaves_hit");
	public static SoundEvent SOULWOOD_LEAVES_PLACE = register("soulwood_leaves_place");
	public static SoundEvent SOULWOOD_LEAVES_STEP = register("soulwood_leaves_step");

	public static SoundEvent RUNEWOOD_LEAVES_BREAK = register("runewood_leaves_break");
	public static SoundEvent RUNEWOOD_LEAVES_HIT = register("runewood_leaves_hit");
	public static SoundEvent RUNEWOOD_LEAVES_PLACE = register("runewood_leaves_place");
	public static SoundEvent RUNEWOOD_LEAVES_STEP = register("runewood_leaves_step");

	public static BlockSoundGroup RUNEWOOD_LEAVES = new BlockSoundGroup(
			1.0F,
			1.0F,
			RUNEWOOD_LEAVES_BREAK,
			RUNEWOOD_LEAVES_STEP,
			RUNEWOOD_LEAVES_PLACE,
			RUNEWOOD_LEAVES_HIT,
			SoundEvents.BLOCK_AZALEA_LEAVES_FALL
	);

	public static BlockSoundGroup TAINTED_ROCK = new BlockSoundGroup(
			1.0F,
			1.0F,
			TAINTED_ROCK_BREAK,
			TAINTED_ROCK_STEP,
			TAINTED_ROCK_PLACE,
			TAINTED_ROCK_HIT,
			SoundEvents.BLOCK_BASALT_FALL
	);

	public static BlockSoundGroup TWISTED_ROCK = new BlockSoundGroup(
			1.0F,
			1.0F,
			TAINTED_ROCK_BREAK,
			TAINTED_ROCK_STEP,
			TAINTED_ROCK_PLACE,
			TAINTED_ROCK_HIT,
			SoundEvents.BLOCK_BASALT_FALL
	);

	public static BlockSoundGroup SOULSTONE = new BlockSoundGroup(
			1.0F,
			1.0F,
			SOULSTONE_BREAK,
			SOULSTONE_STEP,
			SOULSTONE_PLACE,
			SOULSTONE_HIT,
			SoundEvents.BLOCK_BASALT_FALL
	);

	public static BlockSoundGroup DEEPSLATE_SOULSTONE = new BlockSoundGroup(
			1.0F,
			1.0F,
			DEEPSLATE_SOULSTONE_BREAK,
			DEEPSLATE_SOULSTONE_STEP,
			DEEPSLATE_SOULSTONE_PLACE,
			DEEPSLATE_SOULSTONE_HIT,
			SoundEvents.BLOCK_BASALT_FALL
	);

	public static BlockSoundGroup BLAZING_QUARTZ_ORE = new BlockSoundGroup(
			1.0F,
			1.0F,
			BLAZING_QUARTZ_ORE_BREAK,
			SoundEvents.BLOCK_NETHER_ORE_STEP,
			SoundEvents.BLOCK_NETHER_ORE_PLACE,
			DEEPSLATE_SOULSTONE_HIT,
			SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL
	);

	public static BlockSoundGroup BLAZING_QUARTZ_BLOCK = new BlockSoundGroup(
			1.0F,
			1.25F,
			BLAZING_QUARTZ_BLOCK_BREAK,
			BLAZING_QUARTZ_BLOCK_STEP,
			BLAZING_QUARTZ_BLOCK_PLACE,
			BLAZING_QUARTZ_BLOCK_HIT,
			SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL
	);

	public static BlockSoundGroup ARCANE_CHARCOAL_BLOCK = new BlockSoundGroup(
			1.0F,
			1.0F,
			ARCANE_CHARCOAL_BLOCK_BREAK,
			ARCANE_CHARCOAL_BLOCK_STEP,
			ARCANE_CHARCOAL_BLOCK_PLACE,
			ARCANE_CHARCOAL_BLOCK_HIT,
			SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL
	);

	public static BlockSoundGroup SOULWOOD = new BlockSoundGroup(
			1.0F,
			1.0F,
			SoundEvents.BLOCK_WOOD_BREAK,
			SoundEvents.BLOCK_WOOD_STEP,
			SoundEvents.BLOCK_WOOD_PLACE,
			SoundEvents.BLOCK_WOOD_HIT,
			SoundEvents.BLOCK_WOOD_FALL
	);

	public static BlockSoundGroup BLIGHTED_EARTH = new BlockSoundGroup(
			1.0F,
			1.0F,
			SoundEvents.BLOCK_NYLIUM_BREAK,
			SoundEvents.BLOCK_NYLIUM_STEP,
			SoundEvents.BLOCK_NYLIUM_PLACE,
			SoundEvents.BLOCK_NYLIUM_HIT,
			SoundEvents.BLOCK_NYLIUM_FALL
	);

	public static BlockSoundGroup BLIGHTED_FOLIAGE = new BlockSoundGroup(
			1.0F,
			1.0F,
			SoundEvents.BLOCK_NETHER_WART_BREAK,
			SoundEvents.BLOCK_STONE_STEP,
			SoundEvents.ITEM_NETHER_WART_PLANT,
			SoundEvents.BLOCK_STONE_HIT,
			SoundEvents.BLOCK_STONE_FALL
	);

	public static BlockSoundGroup ETHER = new BlockSoundGroup(
			1.0F,
			1.0F,
			ETHER_BREAK,
			SoundEvents.BLOCK_WOOL_STEP,
			ETHER_PLACE,
			SoundEvents.BLOCK_ANCIENT_DEBRIS_HIT,
			SoundEvents.BLOCK_WOOL_FALL
	);

	public static BlockSoundGroup HALLOWED_GOLD = new BlockSoundGroup(
			1.0F,
			1.0F,
			HALLOWED_GOLD_BREAK,
			HALLOWED_GOLD_STEP,
			HALLOWED_GOLD_PLACE,
			HALLOWED_GOLD_HIT,
			SoundEvents.BLOCK_METAL_FALL
	);

	public static BlockSoundGroup SOUL_STAINED_STEEL = new BlockSoundGroup(
			1.0F,
			1.0F,
			SOUL_STAINED_STEEL_BREAK,
			SOUL_STAINED_STEEL_STEP,
			SOUL_STAINED_STEEL_PLACE,
			SOUL_STAINED_STEEL_HIT,
			SoundEvents.BLOCK_METAL_FALL
	);

	public static BlockSoundGroup NATURAL_QUARTZ = new BlockSoundGroup(
			1.0F,
			0.9f,
			SoundEvents.BLOCK_STONE_BREAK,
			SoundEvents.BLOCK_STONE_STEP,
			SoundEvents.BLOCK_STONE_PLACE,
			SoundEvents.BLOCK_STONE_HIT,
			SoundEvents.BLOCK_STONE_FALL
	);

	public static BlockSoundGroup QUARTZ_CLUSTER = new BlockSoundGroup(
			1.0F,
			1.0F,
			QUARTZ_CLUSTER_BLOCK_BREAK,
			QUARTZ_CLUSTER_BLOCK_STEP,
			QUARTZ_CLUSTER_BLOCK_PLACE,
			QUARTZ_CLUSTER_BLOCK_HIT,
			SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL
	);

	public static BlockSoundGroup RARE_EARTH = new BlockSoundGroup(
			1.0F,
			1.15F,
			SOULSTONE_BREAK,
			SOULSTONE_STEP,
			SOULSTONE_PLACE,
			DEEPSLATE_SOULSTONE_HIT,
			SoundEvents.BLOCK_DEEPSLATE_FALL
	);

	public static BlockSoundGroup DEEPSLATE_QUARTZ = new BlockSoundGroup(
			1.0F,
			0.9f,
			SoundEvents.BLOCK_DEEPSLATE_BREAK,
			SoundEvents.BLOCK_DEEPSLATE_STEP,
			SoundEvents.BLOCK_DEEPSLATE_PLACE,
			SoundEvents.BLOCK_DEEPSLATE_HIT,
			SoundEvents.BLOCK_DEEPSLATE_FALL
	);

	public static BlockSoundGroup SOULWOOD_LEAVES = new BlockSoundGroup(
			1.0F,
			0.9F,
			SOULWOOD_LEAVES_BREAK,
			SOULWOOD_LEAVES_STEP,
			SOULWOOD_LEAVES_PLACE,
			SOULWOOD_LEAVES_HIT,
			SoundEvents.BLOCK_AZALEA_LEAVES_FALL);

	public static SoundEvent register(String name) {
		Identifier id = new Identifier(Malum.MODID, name);
		SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(id);
		SOUND_EVENTS.put(id, soundEvent);
		return soundEvent;
	}


	public static void init() {
		SOUND_EVENTS.forEach((id, sound) -> Registry.register(Registries.SOUND_EVENT, id, sound));
	}
}
