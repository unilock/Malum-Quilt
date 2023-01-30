package dev.sterner.malum;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class MalumConfig extends MidnightConfig {



    @MidnightConfig.Comment public static MidnightConfig.Comment common;
	@Entry(min=0) public static float d = 0f;

	@Entry public static boolean GENERATE_RUNEWOOD_TREES = true;

	@MidnightConfig.Comment public static MidnightConfig.Comment common_runewood_chance;
	@Entry(min=0,max=1) public static double COMMON_RUNEWOOD_CHANCE = 0.02D;
	@MidnightConfig.Comment public static MidnightConfig.Comment rare_runewood_chance;
	@Entry(min=0,max=1) public static double RARE_RUNEWOOD_CHANCE = 0.01D;

	@Entry(min=0,max=1) public static boolean GENERATE_WEEPING_WELLS = true;
	@Entry public static List<String> WEEPING_WELL_ALLOWED_DIMENSIONS = new ArrayList<>(List.of("minecraft:overworld"));

	@Entry(min=0,max=1) public static boolean GENERATE_BLAZE_QUARTZ = true;
	@MidnightConfig.Comment public static MidnightConfig.Comment blaze_quartz_size;
	@Entry public static int BLAZE_QUARTZ_SIZE = 14;
	@MidnightConfig.Comment public static MidnightConfig.Comment blaze_quartz_amount;
	@Entry public static int BLAZE_QUARTZ_AMOUNT = 16;



	@Entry public static boolean GENERATE_BRILLIANT_STONE =  true;
	@MidnightConfig.Comment public static MidnightConfig.Comment brilliant_stone_size;
	@Entry public static int BRILLIANT_STONE_SIZE =  4;
	@MidnightConfig.Comment public static MidnightConfig.Comment brilliant_stone_amount;
	@Entry public static int BRILLIANT_STONE_AMOUNT = 3;
	@Entry public static int BRILLIANT_STONE_MIN_Y = -64;
	@Entry public static int BRILLIANT_STONE_MAX_Y = 40;

	@Entry public static boolean GENERATE_SOULSTONE = true;
	@MidnightConfig.Comment public static MidnightConfig.Comment soulstone_size;
	@Entry public static int SOULSTONE_SIZE = 8;
	@MidnightConfig.Comment public static MidnightConfig.Comment soulstone_amount;
	@Entry public static int SOULSTONE_AMOUNT = 3;
	@Entry public static int SOULSTONE_MIN_Y = -64;
	@Entry public static int SOULSTONE_MAX_Y = 30;

	@Entry public static boolean GENERATE_SURFACE_SOULSTONE = true;
	@MidnightConfig.Comment public static MidnightConfig.Comment surface_soulstone_size;
	@Entry public static int SURFACE_SOULSTONE_SIZE = 4;
	@MidnightConfig.Comment public static MidnightConfig.Comment surface_soulstone_amount;
	@Entry public static int SURFACE_SOULSTONE_AMOUNT = 4;
	@Entry public static int SURFACE_SOULSTONE_MIN_Y = 60;
	@Entry public static int SURFACE_SOULSTONE_MAX_Y = 100;

	@Entry public static boolean GENERATE_NATURAL_QUARTZ = true;
	@MidnightConfig.Comment public static MidnightConfig.Comment natural_quartz_size;
	@Entry public static int NATURAL_QUARTZ_SIZE = 5;
	@MidnightConfig.Comment public static MidnightConfig.Comment natural_quartz_amount;
	@Entry public static int NATURAL_QUARTZ_AMOUNT = 2;
	@Entry public static int NATURAL_QUARTZ_MIN_Y = -64;
	@Entry public static int NATURAL_QUARTZ_MAX_Y = 10;



	@Entry public static boolean GENERATE_QUARTZ_GEODES = true;
	@Entry public static List<String> QUARTZ_GEODE_ALLOWED_DIMENSIONS = new ArrayList<>(List.of("minecraft:overworld"));

	@Entry public static boolean GENERATE_CTHONIC_GOLD = true;
	@Entry public static List<String> CTHONIC_GOLD_ALLOWED_DIMENSIONS = new ArrayList<>(List.of("minecraft:overworld"));

	@MidnightConfig.Comment public static MidnightConfig.Comment award_codex_on_kill;
	@Entry public static boolean AWARD_CODEX_ON_KILL = true;

	@MidnightConfig.Comment public static MidnightConfig.Comment no_fancy_spirits;
	@Entry public static boolean NO_FANCY_SPIRITS = false;

	@MidnightConfig.Comment public static MidnightConfig.Comment soulless_spawners;
	@Entry public static final boolean SOULLESS_SPAWNERS = false;

	@MidnightConfig.Comment public static MidnightConfig.Comment use_default_spirit_values;
	@Entry public static final boolean USE_DEFAULT_SPIRIT_VALUES = true;

	@MidnightConfig.Comment public static MidnightConfig.Comment soul_ward_physical;
	@Entry(min=0,max=1) public static double SOUL_WARD_PHYSICAL = 0.7d;
	@MidnightConfig.Comment public static MidnightConfig.Comment soul_ward_magic;
	@Entry(min=0,max=1) public static double SOUL_WARD_MAGIC = 0.1d;
	@MidnightConfig.Comment public static MidnightConfig.Comment soul_ward_rate;
	@Entry public static int SOUL_WARD_RATE = 60;

	@MidnightConfig.Comment public static MidnightConfig.Comment heart_of_stone_cost;
	@Entry(min=0,max=1) public static double HEART_OF_STONE_COST = 0.2d;
	@MidnightConfig.Comment public static MidnightConfig.Comment heart_of_stone_rate;
	@Entry public static int HEART_OF_STONE_RATE = 40;
}
