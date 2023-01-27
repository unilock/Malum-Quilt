package dev.sterner.malum.common.event;

import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static dev.sterner.malum.Malum.MODID;
import static dev.sterner.malum.common.registry.MalumObjects.*;
import static dev.sterner.malum.common.registry.MalumObjects.BELT_OF_THE_MAGEBANE;

public class MalumItemGroupEvents {
	public static final ItemGroup MALUM = FabricItemGroup.builder(new Identifier(MODID, MODID)).icon(() -> new ItemStack(SPIRIT_ALTAR)).build();
	public static final ItemGroup MALUM_ARCANE_ROCKS = FabricItemGroup.builder(new Identifier(MODID, "malum_shaped_stones")).icon(() -> new ItemStack(TAINTED_ROCK)).build();
	public static final ItemGroup MALUM_NATURAL_WONDERS = FabricItemGroup.builder(new Identifier(MODID, "malum_natural_wonders")).icon(() -> new ItemStack(RUNEWOOD_SAPLING)).build();
	public static final ItemGroup MALUM_SPIRITS = FabricItemGroup.builder(new Identifier(MODID, "malum_spirits")).icon(() -> new ItemStack(ARCANE_SPIRIT)).build();
	public static final ItemGroup MALUM_METALLURGIC_MAGIC = FabricItemGroup.builder(new Identifier(MODID, "malum_metallurgic_magic")).icon(() -> new ItemStack(ALCHEMICAL_IMPETUS)).build();


	public static void init(){
		ItemGroupEvents.modifyEntriesEvent(MALUM).register(MalumItemGroupEvents::malumGroup);
		ItemGroupEvents.modifyEntriesEvent(MALUM_ARCANE_ROCKS).register(MalumItemGroupEvents::rocksGroup);
		ItemGroupEvents.modifyEntriesEvent(MALUM_NATURAL_WONDERS).register(MalumItemGroupEvents::naturalGroup);
		ItemGroupEvents.modifyEntriesEvent(MALUM_SPIRITS).register(MalumItemGroupEvents::spiritsGroup);
		ItemGroupEvents.modifyEntriesEvent(MALUM_METALLURGIC_MAGIC).register(MalumItemGroupEvents::metallurgicGroup);

	}

	private static void metallurgicGroup(FabricItemGroupEntries e) {
		e.addItem(CRACKED_IRON_IMPETUS);
		e.addItem(IRON_IMPETUS);
		e.addItem(IRON_NODE);

		e.addItem(CRACKED_COPPER_IMPETUS);
		e.addItem(COPPER_IMPETUS);
		e.addItem(COPPER_NODE);

		e.addItem(CRACKED_GOLD_IMPETUS);
		e.addItem(GOLD_IMPETUS);
		e.addItem(GOLD_NODE);

		e.addItem(CRACKED_ALCHEMICAL_IMPETUS);
		e.addItem(ALCHEMICAL_IMPETUS);
		//TODO add compat, lead, silver, aluminium, nickel, uranium, zinc, tin

	}

	private static void naturalGroup(FabricItemGroupEntries e) {
		e.addItem(HOLY_SAP);
		e.addItem(HOLY_SAPBALL);
		e.addItem(HOLY_SYRUP);
		e.addItem(RUNEWOOD_LEAVES);
		e.addItem(RUNEWOOD_SAPLING);
		e.addItem(RUNEWOOD_LOG);
		e.addItem(STRIPPED_RUNEWOOD_LOG);
		e.addItem(RUNEWOOD);
		e.addItem(STRIPPED_RUNEWOOD);
		e.addItem(EXPOSED_RUNEWOOD_LOG);
		e.addItem(REVEALED_RUNEWOOD_LOG);
		e.addItem(RUNEWOOD_PLANKS);
		e.addItem(VERTICAL_RUNEWOOD_PLANKS);
		e.addItem(RUNEWOOD_PANEL);
		e.addItem(RUNEWOOD_TILES);
		e.addItem(RUNEWOOD_PLANKS_SLAB);
		e.addItem(VERTICAL_RUNEWOOD_PLANKS_SLAB);
		e.addItem(RUNEWOOD_PANEL_SLAB);
		e.addItem(RUNEWOOD_TILES_SLAB);
		e.addItem(RUNEWOOD_PLANKS_STAIRS);
		e.addItem(VERTICAL_RUNEWOOD_PLANKS_STAIRS);
		e.addItem(RUNEWOOD_PANEL_STAIRS);
		e.addItem(RUNEWOOD_TILES_STAIRS);
		e.addItem(CUT_RUNEWOOD_PLANKS);
		e.addItem(RUNEWOOD_BEAM);
		e.addItem(RUNEWOOD_DOOR);
		e.addItem(RUNEWOOD_TRAPDOOR);
		e.addItem(SOLID_RUNEWOOD_TRAPDOOR);
		e.addItem(RUNEWOOD_PLANKS_BUTTON);
		e.addItem(RUNEWOOD_PLANKS_PRESSURE_PLATE);
		e.addItem(RUNEWOOD_PLANKS_FENCE);
		e.addItem(RUNEWOOD_PLANKS_FENCE_GATE);
		e.addItem(RUNEWOOD_ITEM_STAND);
		e.addItem(RUNEWOOD_ITEM_PEDESTAL);
		//TODO SIGN
		//TODO BOAT
		e.addItem(BLIGHTED_EARTH);
		e.addItem(BLIGHTED_SOIL);
		e.addItem(BLIGHTED_WEED);
		e.addItem(BLIGHTED_TUMOR);
		e.addItem(BLIGHTED_SOULWOOD);
		e.addItem(BLIGHTED_GUNK);
		e.addItem(UNHOLY_SAP);
		e.addItem(UNHOLY_SAPBALL);
		e.addItem(UNHOLY_SYRUP);
		e.addItem(SOULWOOD_LEAVES);
		e.addItem(SOULWOOD_SAPLING);
		e.addItem(SOULWOOD_LOG);
		e.addItem(STRIPPED_SOULWOOD_LOG);
		e.addItem(SOULWOOD);
		e.addItem(STRIPPED_SOULWOOD);
		e.addItem(EXPOSED_SOULWOOD_LOG);
		e.addItem(REVEALED_SOULWOOD_LOG);
		e.addItem(SOULWOOD_PLANKS);
		e.addItem(VERTICAL_SOULWOOD_PLANKS);
		e.addItem(SOULWOOD_PANEL);
		e.addItem(SOULWOOD_TILES);
		e.addItem(SOULWOOD_PLANKS_SLAB);
		e.addItem(VERTICAL_SOULWOOD_PLANKS_SLAB);
		e.addItem(SOULWOOD_PANEL_SLAB);
		e.addItem(SOULWOOD_TILES_SLAB);
		e.addItem(SOULWOOD_PLANKS_STAIRS);
		e.addItem(VERTICAL_SOULWOOD_PLANKS_STAIRS);
		e.addItem(SOULWOOD_PANEL_STAIRS);
		e.addItem(SOULWOOD_TILES_STAIRS);
		e.addItem(CUT_SOULWOOD_PLANKS);
		e.addItem(SOULWOOD_BEAM);
		e.addItem(SOULWOOD_DOOR);
		e.addItem(SOULWOOD_TRAPDOOR);
		e.addItem(SOLID_SOULWOOD_TRAPDOOR);
		e.addItem(SOULWOOD_PLANKS_BUTTON);
		e.addItem(SOULWOOD_PLANKS_PRESSURE_PLATE);
		e.addItem(SOULWOOD_PLANKS_FENCE);
		e.addItem(SOULWOOD_PLANKS_FENCE_GATE);
		e.addItem(SOULWOOD_ITEM_STAND);
		e.addItem(SOULWOOD_ITEM_PEDESTAL);
		//TODO SOUL SIGN
		//TODO SOUL BOAT
	}

	private static void rocksGroup(FabricItemGroupEntries e) {
		e.addItem(TAINTED_ROCK);
		e.addItem(SMOOTH_TAINTED_ROCK);
		e.addItem(POLISHED_TAINTED_ROCK);
		e.addItem(TAINTED_ROCK_BRICKS);
		e.addItem(CRACKED_TAINTED_ROCK_BRICKS);
		e.addItem(TAINTED_ROCK_TILES);
		e.addItem(CRACKED_TAINTED_ROCK_TILES);
		e.addItem(SMALL_TAINTED_ROCK_BRICKS);
		e.addItem(CRACKED_SMALL_TAINTED_ROCK_BRICKS);
		e.addItem(TAINTED_ROCK_COLUMN);
		e.addItem(TAINTED_ROCK_COLUMN_CAP);
		e.addItem(CUT_TAINTED_ROCK);
		e.addItem(CHISELED_TAINTED_ROCK);
		e.addItem(TAINTED_ROCK_PRESSURE_PLATE);
		e.addItem(TAINTED_ROCK_BUTTON);
		e.addItem(TAINTED_ROCK_WALL);
		e.addItem(TAINTED_ROCK_BRICKS_WALL);
		e.addItem(CRACKED_TAINTED_ROCK_BRICKS_WALL);
		e.addItem(TAINTED_ROCK_TILES_WALL);
		e.addItem(CRACKED_TAINTED_ROCK_TILES_WALL);
		e.addItem(SMALL_TAINTED_ROCK_BRICKS_WALL);
		e.addItem(CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL);
		e.addItem(TAINTED_ROCK_SLAB);
		e.addItem(SMOOTH_TAINTED_ROCK_SLAB);
		e.addItem(POLISHED_TAINTED_ROCK_SLAB);
		e.addItem(TAINTED_ROCK_BRICKS_SLAB);
		e.addItem(CRACKED_TAINTED_ROCK_BRICKS_SLAB);
		e.addItem(TAINTED_ROCK_TILES_SLAB);
		e.addItem(CRACKED_TAINTED_ROCK_TILES_SLAB);
		e.addItem(SMALL_TAINTED_ROCK_BRICKS_SLAB);
		e.addItem(CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB);
		e.addItem(TAINTED_ROCK_STAIRS);
		e.addItem(SMOOTH_TAINTED_ROCK_STAIRS);
		e.addItem(POLISHED_TAINTED_ROCK_STAIRS);
		e.addItem(TAINTED_ROCK_BRICKS_STAIRS);
		e.addItem(CRACKED_TAINTED_ROCK_BRICKS_STAIRS);
		e.addItem(TAINTED_ROCK_TILES_STAIRS);
		e.addItem(CRACKED_TAINTED_ROCK_TILES_STAIRS);
		e.addItem(SMALL_TAINTED_ROCK_BRICKS_STAIRS);
		e.addItem(CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS);
		e.addItem(TAINTED_ROCK_ITEM_STAND);
		e.addItem(TAINTED_ROCK_ITEM_PEDESTAL);

		e.addItem(TWISTED_ROCK);
		e.addItem(SMOOTH_TWISTED_ROCK);
		e.addItem(POLISHED_TWISTED_ROCK);
		e.addItem(TWISTED_ROCK_BRICKS);
		e.addItem(CRACKED_TWISTED_ROCK_BRICKS);
		e.addItem(TWISTED_ROCK_TILES);
		e.addItem(CRACKED_TWISTED_ROCK_TILES);
		e.addItem(SMALL_TWISTED_ROCK_BRICKS);
		e.addItem(CRACKED_SMALL_TWISTED_ROCK_BRICKS);
		e.addItem(TWISTED_ROCK_COLUMN);
		e.addItem(TWISTED_ROCK_COLUMN_CAP);
		e.addItem(CUT_TWISTED_ROCK);
		e.addItem(CHISELED_TWISTED_ROCK);
		e.addItem(TWISTED_ROCK_PRESSURE_PLATE);
		e.addItem(TWISTED_ROCK_BUTTON);
		e.addItem(TWISTED_ROCK_WALL);
		e.addItem(TWISTED_ROCK_BRICKS_WALL);
		e.addItem(CRACKED_TWISTED_ROCK_BRICKS_WALL);
		e.addItem(TWISTED_ROCK_TILES_WALL);
		e.addItem(CRACKED_TWISTED_ROCK_TILES_WALL);
		e.addItem(SMALL_TWISTED_ROCK_BRICKS_WALL);
		e.addItem(CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL);
		e.addItem(TWISTED_ROCK_SLAB);
		e.addItem(SMOOTH_TWISTED_ROCK_SLAB);
		e.addItem(POLISHED_TWISTED_ROCK_SLAB);
		e.addItem(TWISTED_ROCK_BRICKS_SLAB);
		e.addItem(CRACKED_TWISTED_ROCK_BRICKS_SLAB);
		e.addItem(TWISTED_ROCK_TILES_SLAB);
		e.addItem(CRACKED_TWISTED_ROCK_TILES_SLAB);
		e.addItem(SMALL_TWISTED_ROCK_BRICKS_SLAB);
		e.addItem(CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB);
		e.addItem(TWISTED_ROCK_STAIRS);
		e.addItem(SMOOTH_TWISTED_ROCK_STAIRS);
		e.addItem(POLISHED_TWISTED_ROCK_STAIRS);
		e.addItem(TWISTED_ROCK_BRICKS_STAIRS);
		e.addItem(CRACKED_TWISTED_ROCK_BRICKS_STAIRS);
		e.addItem(TWISTED_ROCK_TILES_STAIRS);
		e.addItem(CRACKED_TWISTED_ROCK_TILES_STAIRS);
		e.addItem(SMALL_TWISTED_ROCK_BRICKS_STAIRS);
		e.addItem(CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS);
		e.addItem(TWISTED_ROCK_ITEM_STAND);
		e.addItem(TWISTED_ROCK_ITEM_PEDESTAL);

	}

	private static void spiritsGroup(FabricItemGroupEntries entries) {
		ITEMS.forEach((item, identifier) -> {
			if(item instanceof MalumSpiritItem){
				entries.addItem(item);
			}
		});
	}

	private static void malumGroup(FabricItemGroupEntries entries) {
		entries.addItem(ENCYCLOPEDIA_ARCANA);

		entries.addItem(ARCANE_CHARCOAL);
		entries.addItem(ARCANE_CHARCOAL_FRAGMENT);
		entries.addItem(BLOCK_OF_ARCANE_CHARCOAL);

		entries.addItem(BLAZING_QUARTZ_ORE);
		entries.addItem(BLAZING_QUARTZ);
		entries.addItem(BLAZING_QUARTZ_FRAGMENT);
		entries.addItem(BLOCK_OF_BLAZING_QUARTZ);

		entries.addItem(NATURAL_QUARTZ_ORE);
		entries.addItem(DEEPSLATE_QUARTZ_ORE);
		entries.addItem(NATURAL_QUARTZ);

		entries.addItem(BRILLIANT_STONE);
		entries.addItem(BRILLIANT_DEEPSLATE);
		entries.addItem(CLUSTER_OF_BRILLIANCE);
		entries.addItem(CRUSHED_BRILLIANCE);
		entries.addItem(BLOCK_OF_BRILLIANCE);

		entries.addItem(SOULSTONE_ORE);
		entries.addItem(DEEPSLATE_SOULSTONE_ORE);
		entries.addItem(RAW_SOULSTONE);
		entries.addItem(CRUSHED_SOULSTONE);
		entries.addItem(BLOCK_OF_RAW_SOULSTONE);
		entries.addItem(PROCESSED_SOULSTONE);
		entries.addItem(BLOCK_OF_SOULSTONE);

		entries.addItem(SPIRIT_ALTAR);
		entries.addItem(SPIRIT_JAR);
		entries.addItem(RUNEWOOD_OBELISK);
		entries.addItem(BRILLIANT_OBELISK);
		entries.addItem(SPIRIT_CRUCIBLE);
		entries.addItem(TWISTED_TABLET);
		entries.addItem(SPIRIT_CATALYZER);
		entries.addItem(RUNEWOOD_TOTEM_BASE);
		entries.addItem(SOULWOOD_TOTEM_BASE);

		entries.addItem(ROTTING_ESSENCE);
		entries.addItem(GRIM_TALC);
		entries.addItem(ALCHEMICAL_CALX);
		entries.addItem(ASTRAL_WEAVE);
		entries.addItem(CTHONIC_GOLD);
		entries.addItem(HEX_ASH);
		entries.addItem(CURSED_GRIT);
		entries.addItem(BLOCK_OF_ROTTING_ESSENCE);
		entries.addItem(BLOCK_OF_GRIM_TALC);
		entries.addItem(BLOCK_OF_ALCHEMICAL_CALX);
		entries.addItem(BLOCK_OF_ASTRAL_WEAVE);
		entries.addItem(BLOCK_OF_HEX_ASH);
		entries.addItem(BLOCK_OF_CURSED_GRIT);

		entries.addItem(SPIRIT_FABRIC);
		entries.addItem(SPECTRAL_LENS);
		entries.addItem(POPPET);
		entries.addItem(CORRUPTED_RESONANCE);
		entries.addItem(HALLOWED_GOLD_INGOT);
		entries.addItem(HALLOWED_GOLD_NUGGET);
		entries.addItem(BLOCK_OF_HALLOWED_GOLD);
		entries.addItem(HALLOWED_SPIRIT_RESONATOR);

		entries.addItem(SOUL_STAINED_STEEL_INGOT);
		entries.addItem(SOUL_STAINED_STEEL_NUGGET);
		entries.addItem(BLOCK_OF_SOUL_STAINED_STEEL);
		entries.addItem(STAINED_SPIRIT_RESONATOR);

		entries.addItem(ETHER);
		entries.addItem(ETHER_TORCH);
		entries.addItem(TAINTED_ETHER_BRAZIER);
		entries.addItem(TWISTED_ETHER_BRAZIER);
		entries.addItem(IRIDESCENT_ETHER);
		entries.addItem(IRIDESCENT_ETHER_TORCH);
		entries.addItem(TAINTED_IRIDESCENT_ETHER_BRAZIER);
		entries.addItem(TWISTED_IRIDESCENT_ETHER_BRAZIER);
		entries.addItem(SPIRIT_POUCH);

		entries.addItem(CRUDE_SCYTHE);
		entries.addItem(SOUL_STAINED_STEEL_SCYTHE);
		entries.addItem(SOUL_STAINED_STEEL_SWORD);
		entries.addItem(SOUL_STAINED_STEEL_PICKAXE);
		entries.addItem(SOUL_STAINED_STEEL_AXE);
		entries.addItem(SOUL_STAINED_STEEL_SHOVEL);
		entries.addItem(SOUL_STAINED_STEEL_HOE);

		entries.addItem(SOUL_STAINED_STEEL_HELMET);
		entries.addItem(SOUL_STAINED_STEEL_CHESTPLATE);
		entries.addItem(SOUL_STAINED_STEEL_LEGGINGS);
		entries.addItem(SOUL_STAINED_STEEL_BOOTS);
		entries.addItem(SOUL_HUNTER_CLOAK);
		entries.addItem(SOUL_HUNTER_ROBE);
		entries.addItem(SOUL_HUNTER_LEGGINGS);
		entries.addItem(SOUL_HUNTER_BOOTS);

		entries.addItem(TYRVING);
		entries.addItem(ETHERIC_NITRATE);
		entries.addItem(VIVID_NITRATE);

		entries.addItem(GILDED_RING);
		entries.addItem(GILDED_BELT);
		entries.addItem(ORNATE_RING);
		entries.addItem(ORNATE_NECKLACE);
		entries.addItem(RING_OF_ESOTERIC_SPOILS);
		entries.addItem(RING_OF_CURATIVE_TALENT);
		entries.addItem(RING_OF_ARCANE_PROWESS);
		entries.addItem(RING_OF_ALCHEMICAL_MASTERY);
		entries.addItem(RING_OF_DESPERATE_VORACITY);
		entries.addItem(RING_OF_THE_HOARDER);
		entries.addItem(RING_OF_THE_DEMOLITIONIST);
		entries.addItem(NECKLACE_OF_THE_MYSTIC_MIRROR);
		entries.addItem(NECKLACE_OF_TIDAL_AFFINITY);
		entries.addItem(NECKLACE_OF_THE_NARROW_EDGE);
		entries.addItem(NECKLACE_OF_THE_HIDDEN_BLADE);
		entries.addItem(NECKLACE_OF_BLISSFUL_HARMONY);
		entries.addItem(BELT_OF_THE_STARVED);
		entries.addItem(BELT_OF_THE_PROSPECTOR);
		entries.addItem(BELT_OF_THE_MAGEBANE);
	}
}
