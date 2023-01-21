package dev.sterner.malum.common.registry;

import com.sammy.lodestone.systems.item.tools.magic.*;
import com.sammy.lodestone.systems.multiblock.MultiBlockItem;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.blockentity.FusionPlateBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCatalyzerCoreBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCrucibleCoreBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.BrilliantObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.RunewoodObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.storage.PlinthCoreBlockEntity;
import dev.sterner.malum.common.item.*;
import dev.sterner.malum.common.item.cosmetic.AncientWeaveItem;
import dev.sterner.malum.common.item.equipment.armor.SoulHunterArmorItem;
import dev.sterner.malum.common.item.equipment.armor.SoulStainedSteelArmorItem;
import dev.sterner.malum.common.item.equipment.trinket.*;
import dev.sterner.malum.common.item.ether.EtherBrazierItem;
import dev.sterner.malum.common.item.ether.EtherItem;
import dev.sterner.malum.common.item.ether.EtherSconceItem;
import dev.sterner.malum.common.item.ether.EtherTorchItem;
import dev.sterner.malum.common.item.food.HolyCaramelItem;
import dev.sterner.malum.common.item.food.HolySyrupItem;
import dev.sterner.malum.common.item.food.UnholySyrupItem;
import dev.sterner.malum.common.item.impedus.CrackedImpetusItem;
import dev.sterner.malum.common.item.impedus.ImpetusItem;
import dev.sterner.malum.common.item.nitrate.EthericNitrateItem;
import dev.sterner.malum.common.item.nitrate.VividNitrateItem;
import dev.sterner.malum.common.item.spirit.*;
import dev.sterner.malum.common.item.tools.MalumScytheItem;
import dev.sterner.malum.common.item.tools.magic.MagicScytheItem;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static dev.sterner.malum.common.item.ItemTiers.ItemTierEnum.SOUL_STAINED_STEEL;
import static net.minecraft.item.Items.GLASS_BOTTLE;

public class MalumItemRegistry {
	public static Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
	public static Set<MalumScytheItem> SCYTHES = new ReferenceOpenHashSet<>();


	public static final Item ENCYCLOPEDIA_ARCANA = register("encyclopedia_arcana", new EncyclopediaArcanaItem(settings().rarity(Rarity.UNCOMMON)));

	public static final Item BLAZING_TORCH = register("blazing_torch", new WallStandingBlockItem(MalumBlockRegistry.BLAZING_TORCH, MalumBlockRegistry.WALL_BLAZING_TORCH, new Item.Settings(), Direction.NORTH));
	//public static final Item BLAZING_SCONCE = register("blazing_sconce", new WallStandingBlockItem(MalumBlockRegistry.BLAZING_SCONCE, MalumBlockRegistry.WALL_BLAZING_SCONCE, SupplementariesCompat.LOADED ? new Item.Settings().tab(CreativeModeTab.TAB_MISC) : settings()));

	//region tainted rock
	public static final Item TAINTED_ROCK = register("tainted_rock", new BlockItem(MalumBlockRegistry.TAINTED_ROCK, settings()));
	public static final Item SMOOTH_TAINTED_ROCK = register("smooth_tainted_rock", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK, settings()));
	public static final Item POLISHED_TAINTED_ROCK = register("polished_tainted_rock", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK, settings()));
	public static final Item TAINTED_ROCK_BRICKS = register("tainted_rock_bricks", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS, settings()));
	public static final Item CRACKED_TAINTED_ROCK_BRICKS = register("cracked_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS, settings()));
	public static final Item TAINTED_ROCK_TILES = register("tainted_rock_tiles", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES, settings()));
	public static final Item CRACKED_TAINTED_ROCK_TILES = register("cracked_tainted_rock_tiles", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES, settings()));
	public static final Item SMALL_TAINTED_ROCK_BRICKS = register("small_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS, settings()));
	public static final Item CRACKED_SMALL_TAINTED_ROCK_BRICKS = register("cracked_small_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS, settings()));

	public static final Item TAINTED_ROCK_COLUMN = register("tainted_rock_column", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_COLUMN, settings()));
	public static final Item TAINTED_ROCK_COLUMN_CAP = register("tainted_rock_column_cap", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_COLUMN_CAP, settings()));

	public static final Item CUT_TAINTED_ROCK = register("cut_tainted_rock", new BlockItem(MalumBlockRegistry.CUT_TAINTED_ROCK, settings()));
	public static final Item CHISELED_TAINTED_ROCK = register("chiseled_tainted_rock", new BlockItem(MalumBlockRegistry.CHISELED_TAINTED_ROCK, settings()));
	public static final Item TAINTED_ROCK_PRESSURE_PLATE = register("tainted_rock_pressure_plate", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_PRESSURE_PLATE, settings()));
	public static final Item TAINTED_ROCK_BUTTON = register("tainted_rock_button", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BUTTON, settings()));

	public static final Item TAINTED_ROCK_WALL = register("tainted_rock_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_WALL, settings()));
	public static final Item TAINTED_ROCK_BRICKS_WALL = register("tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_WALL, settings()));
	public static final Item CRACKED_TAINTED_ROCK_BRICKS_WALL = register("cracked_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_WALL, settings()));
	public static final Item TAINTED_ROCK_TILES_WALL = register("tainted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_WALL, settings()));
	public static final Item CRACKED_TAINTED_ROCK_TILES_WALL = register("cracked_tainted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_WALL, settings()));
	public static final Item SMALL_TAINTED_ROCK_BRICKS_WALL = register("small_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_WALL, settings()));
	public static final Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL = register("cracked_small_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL, settings()));

	public static final Item TAINTED_ROCK_SLAB = register("tainted_rock_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_SLAB, settings()));
	public static final Item SMOOTH_TAINTED_ROCK_SLAB = register("smooth_tainted_rock_slab", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK_SLAB, settings()));
	public static final Item POLISHED_TAINTED_ROCK_SLAB = register("polished_tainted_rock_slab", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK_SLAB, settings()));
	public static final Item TAINTED_ROCK_BRICKS_SLAB = register("tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item CRACKED_TAINTED_ROCK_BRICKS_SLAB = register("cracked_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item TAINTED_ROCK_TILES_SLAB = register("tainted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_SLAB, settings()));
	public static final Item CRACKED_TAINTED_ROCK_TILES_SLAB = register("cracked_tainted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_SLAB, settings()));
	public static final Item SMALL_TAINTED_ROCK_BRICKS_SLAB = register("small_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB = register("cracked_small_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB, settings()));

	public static final Item TAINTED_ROCK_STAIRS = register("tainted_rock_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_STAIRS, settings()));
	public static final Item SMOOTH_TAINTED_ROCK_STAIRS = register("smooth_tainted_rock_stairs", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK_STAIRS, settings()));
	public static final Item POLISHED_TAINTED_ROCK_STAIRS = register("polished_tainted_rock_stairs", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK_STAIRS, settings()));
	public static final Item TAINTED_ROCK_BRICKS_STAIRS = register("tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item CRACKED_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item TAINTED_ROCK_TILES_STAIRS = register("tainted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_STAIRS, settings()));
	public static final Item CRACKED_TAINTED_ROCK_TILES_STAIRS = register("cracked_tainted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_STAIRS, settings()));
	public static final Item SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("small_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_small_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS, settings()));

	public static final Item TAINTED_ROCK_ITEM_STAND = register("tainted_rock_item_stand", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_ITEM_STAND, settings()));
	public static final Item TAINTED_ROCK_ITEM_PEDESTAL = register("tainted_rock_item_pedestal", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_ITEM_PEDESTAL, settings()));

	//endregion

	//region twisted rock
	public static final Item TWISTED_ROCK = register("twisted_rock", new BlockItem(MalumBlockRegistry.TWISTED_ROCK, settings()));
	public static final Item SMOOTH_TWISTED_ROCK = register("smooth_twisted_rock", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK, settings()));
	public static final Item POLISHED_TWISTED_ROCK = register("polished_twisted_rock", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK, settings()));
	public static final Item TWISTED_ROCK_BRICKS = register("twisted_rock_bricks", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS, settings()));
	public static final Item CRACKED_TWISTED_ROCK_BRICKS = register("cracked_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS, settings()));
	public static final Item TWISTED_ROCK_TILES = register("twisted_rock_tiles", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES, settings()));
	public static final Item CRACKED_TWISTED_ROCK_TILES = register("cracked_twisted_rock_tiles", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES, settings()));
	public static final Item SMALL_TWISTED_ROCK_BRICKS = register("small_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS, settings()));
	public static final Item CRACKED_SMALL_TWISTED_ROCK_BRICKS = register("cracked_small_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS, settings()));

	public static final Item TWISTED_ROCK_COLUMN = register("twisted_rock_column", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_COLUMN, settings()));
	public static final Item TWISTED_ROCK_COLUMN_CAP = register("twisted_rock_column_cap", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_COLUMN_CAP, settings()));

	public static final Item CUT_TWISTED_ROCK = register("cut_twisted_rock", new BlockItem(MalumBlockRegistry.CUT_TWISTED_ROCK, settings()));
	public static final Item CHISELED_TWISTED_ROCK = register("chiseled_twisted_rock", new BlockItem(MalumBlockRegistry.CHISELED_TWISTED_ROCK, settings()));
	public static final Item TWISTED_ROCK_PRESSURE_PLATE = register("twisted_rock_pressure_plate", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_PRESSURE_PLATE, settings()));
	public static final Item TWISTED_ROCK_BUTTON = register("twisted_rock_button", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BUTTON, settings()));

	public static final Item TWISTED_ROCK_WALL = register("twisted_rock_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_WALL, settings()));
	public static final Item TWISTED_ROCK_BRICKS_WALL = register("twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_WALL, settings()));
	public static final Item CRACKED_TWISTED_ROCK_BRICKS_WALL = register("cracked_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_WALL, settings()));
	public static final Item TWISTED_ROCK_TILES_WALL = register("twisted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_WALL, settings()));
	public static final Item SMALL_TWISTED_ROCK_BRICKS_WALL = register("small_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_WALL, settings()));
	public static final Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL = register("cracked_small_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL, settings()));
	public static final Item CRACKED_TWISTED_ROCK_TILES_WALL = register("cracked_twisted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_WALL, settings()));

	public static final Item TWISTED_ROCK_SLAB = register("twisted_rock_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_SLAB, settings()));
	public static final Item SMOOTH_TWISTED_ROCK_SLAB = register("smooth_twisted_rock_slab", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK_SLAB, settings()));
	public static final Item POLISHED_TWISTED_ROCK_SLAB = register("polished_twisted_rock_slab", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK_SLAB, settings()));
	public static final Item TWISTED_ROCK_BRICKS_SLAB = register("twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item CRACKED_TWISTED_ROCK_BRICKS_SLAB = register("cracked_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item TWISTED_ROCK_TILES_SLAB = register("twisted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_SLAB, settings()));
	public static final Item CRACKED_TWISTED_ROCK_TILES_SLAB = register("cracked_twisted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_SLAB, settings()));
	public static final Item SMALL_TWISTED_ROCK_BRICKS_SLAB = register("small_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_SLAB, settings()));
	public static final Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB = register("cracked_small_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB, settings()));

	public static final Item TWISTED_ROCK_STAIRS = register("twisted_rock_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_STAIRS, settings()));
	public static final Item SMOOTH_TWISTED_ROCK_STAIRS = register("smooth_twisted_rock_stairs", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK_STAIRS, settings()));
	public static final Item POLISHED_TWISTED_ROCK_STAIRS = register("polished_twisted_rock_stairs", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK_STAIRS, settings()));
	public static final Item TWISTED_ROCK_BRICKS_STAIRS = register("twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item CRACKED_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item TWISTED_ROCK_TILES_STAIRS = register("twisted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_STAIRS, settings()));
	public static final Item CRACKED_TWISTED_ROCK_TILES_STAIRS = register("cracked_twisted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_STAIRS, settings()));
	public static final Item SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("small_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_STAIRS, settings()));
	public static final Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_small_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS, settings()));

	public static final Item TWISTED_ROCK_ITEM_STAND = register("twisted_rock_item_stand", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_ITEM_STAND, settings()));
	public static final Item TWISTED_ROCK_ITEM_PEDESTAL = register("twisted_rock_item_pedestal", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_ITEM_PEDESTAL, settings()));
	//endregion twisted rock

	//region runewood
	public static final Item HOLY_SAP = register("holy_sap", new Item(settings().recipeRemainder(GLASS_BOTTLE)));
	public static final Item HOLY_SAPBALL = register("holy_sapball", new Item(settings()));
	public static final Item HOLY_SYRUP = register("holy_syrup", new HolySyrupItem(settings().recipeRemainder(GLASS_BOTTLE).food((new FoodComponent.Builder()).hunger(6).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1).build())));
	//public static final Item HOLY_CARAMEL = register("holy_caramel", new HolyCaramelItem(FarmersDelightCompat.LOADED ? settings().food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.15F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1).build()) : settings()));

	public static final Item RUNEWOOD_LEAVES = register("runewood_leaves", new BlockItem(MalumBlockRegistry.RUNEWOOD_LEAVES, settings()));
	public static final Item RUNEWOOD_SAPLING = register("runewood_sapling", new BlockItem(MalumBlockRegistry.RUNEWOOD_SAPLING, settings()));

	public static final Item RUNEWOOD_LOG = register("runewood_log", new BlockItem(MalumBlockRegistry.RUNEWOOD_LOG, settings()));
	public static final Item STRIPPED_RUNEWOOD_LOG = register("stripped_runewood_log", new BlockItem(MalumBlockRegistry.STRIPPED_RUNEWOOD_LOG, settings()));
	public static final Item RUNEWOOD = register("runewood", new BlockItem(MalumBlockRegistry.RUNEWOOD, settings()));
	public static final Item STRIPPED_RUNEWOOD = register("stripped_runewood", new BlockItem(MalumBlockRegistry.STRIPPED_RUNEWOOD, settings()));

	public static final Item EXPOSED_RUNEWOOD_LOG = register("exposed_runewood_log", new BlockItem(MalumBlockRegistry.EXPOSED_RUNEWOOD_LOG, settings()));
	public static final Item REVEALED_RUNEWOOD_LOG = register("revealed_runewood_log", new BlockItem(MalumBlockRegistry.REVEALED_RUNEWOOD_LOG, settings()));

	public static final Item RUNEWOOD_PLANKS = register("runewood_planks", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS, settings()));
	public static final Item VERTICAL_RUNEWOOD_PLANKS = register("vertical_runewood_planks", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS, settings()));
	public static final Item RUNEWOOD_PANEL = register("runewood_panel", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL, settings()));
	public static final Item RUNEWOOD_TILES = register("runewood_tiles", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES, settings()));

	public static final Item RUNEWOOD_PLANKS_SLAB = register("runewood_planks_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_SLAB, settings()));
	public static final Item VERTICAL_RUNEWOOD_PLANKS_SLAB = register("vertical_runewood_planks_slab", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS_SLAB, settings()));
	public static final Item RUNEWOOD_PANEL_SLAB = register("runewood_panel_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL_SLAB, settings()));
	public static final Item RUNEWOOD_TILES_SLAB = register("runewood_tiles_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES_SLAB, settings()));

	public static final Item RUNEWOOD_PLANKS_STAIRS = register("runewood_planks_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_STAIRS, settings()));
	public static final Item VERTICAL_RUNEWOOD_PLANKS_STAIRS = register("vertical_runewood_planks_stairs", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS_STAIRS, settings()));
	public static final Item RUNEWOOD_PANEL_STAIRS = register("runewood_panel_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL_STAIRS, settings()));
	public static final Item RUNEWOOD_TILES_STAIRS = register("runewood_tiles_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES_STAIRS, settings()));

	public static final Item CUT_RUNEWOOD_PLANKS = register("cut_runewood_planks", new BlockItem(MalumBlockRegistry.CUT_RUNEWOOD_PLANKS, settings()));
	public static final Item RUNEWOOD_BEAM = register("runewood_beam", new BlockItem(MalumBlockRegistry.RUNEWOOD_BEAM, settings()));
/*
	public static final Item RUNEWOOD_DOOR = register("runewood_door", new BlockItem(MalumBlockRegistry.RUNEWOOD_DOOR, settings()));
	public static final Item RUNEWOOD_TRAPDOOR = register("runewood_trapdoor", new BlockItem(MalumBlockRegistry.RUNEWOOD_TRAPDOOR, settings()));
	public static final Item SOLID_RUNEWOOD_TRAPDOOR = register("solid_runewood_trapdoor", new BlockItem(MalumBlockRegistry.SOLID_RUNEWOOD_TRAPDOOR, settings()));

	public static final Item RUNEWOOD_PLANKS_BUTTON = register("runewood_planks_button", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_BUTTON, settings()));
	public static final Item RUNEWOOD_PLANKS_PRESSURE_PLATE = register("runewood_planks_pressure_plate", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_PRESSURE_PLATE, settings()));

	public static final Item RUNEWOOD_PLANKS_FENCE = register("runewood_planks_fence", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_FENCE, settings()));
	public static final Item RUNEWOOD_PLANKS_FENCE_GATE = register("runewood_planks_fence_gate", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_FENCE_GATE, settings()));

	public static final Item RUNEWOOD_ITEM_STAND = register("runewood_item_stand", new BlockItem(MalumBlockRegistry.RUNEWOOD_ITEM_STAND, settings()));
	public static final Item RUNEWOOD_ITEM_PEDESTAL = register("runewood_item_pedestal", new BlockItem(MalumBlockRegistry.RUNEWOOD_ITEM_PEDESTAL, settings()));

	public static final Item RUNEWOOD_SIGN = register("runewood_sign", new SignItem(settings().maxCount(16), MalumBlockRegistry.RUNEWOOD_SIGN, MalumBlockRegistry.RUNEWOOD_WALL_SIGN));
	//public static final Item RUNEWOOD_BOAT = register("runewood_boat", new LodestoneBoatItem(settings().maxCount(1), EntityRegistry.RUNEWOOD_BOAT));

 */
	//endregion

	//region blight
	public static final Item BLIGHTED_EARTH = register("blighted_earth", new BlockItem(MalumBlockRegistry.BLIGHTED_EARTH, settings()));
	public static final Item BLIGHTED_SOIL = register("blighted_soil", new BlockItem(MalumBlockRegistry.BLIGHTED_SOIL, settings()));
	public static final Item BLIGHTED_WEED = register("blighted_weed", new BlockItem(MalumBlockRegistry.BLIGHTED_WEED, settings()));
	public static final Item BLIGHTED_TUMOR = register("blighted_tumor", new BlockItem(MalumBlockRegistry.BLIGHTED_TUMOR, settings()));
	public static final Item BLIGHTED_SOULWOOD = register("blighted_soulwood", new BlockItem(MalumBlockRegistry.BLIGHTED_SOULWOOD, settings()));
	public static final Item BLIGHTED_GUNK = register("blighted_gunk", new Item(settings()));
	//endregion

	//region soulwood
	public static final Item UNHOLY_SAP = register("unholy_sap", new Item(settings().recipeRemainder(GLASS_BOTTLE)));
	public static final Item UNHOLY_SAPBALL = register("unholy_sapball", new Item(settings()));
	public static final Item UNHOLY_SYRUP = register("unholy_syrup", new UnholySyrupItem(settings().recipeRemainder(GLASS_BOTTLE).food((new FoodComponent.Builder()).hunger(6).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 1).build())));
	//public static final Item UNHOLY_CARAMEL = register("unholy_caramel", new HolyCaramelItem(FarmersDelightCompat.LOADED ? settings().food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.15F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0), 1).build()) : settings()));

	public static final Item SOULWOOD_LEAVES = register("soulwood_leaves", new BlockItem(MalumBlockRegistry.SOULWOOD_LEAVES, settings()));
	public static final Item SOULWOOD_GROWTH = register("soulwood_growth", new BlockItem(MalumBlockRegistry.SOULWOOD_GROWTH, settings()));

	public static final Item SOULWOOD_LOG = register("soulwood_log", new BlockItem(MalumBlockRegistry.SOULWOOD_LOG, settings()));
	public static final Item STRIPPED_SOULWOOD_LOG = register("stripped_soulwood_log", new BlockItem(MalumBlockRegistry.STRIPPED_SOULWOOD_LOG, settings()));
	public static final Item SOULWOOD = register("soulwood", new BlockItem(MalumBlockRegistry.SOULWOOD, settings()));
	public static final Item STRIPPED_SOULWOOD = register("stripped_soulwood", new BlockItem(MalumBlockRegistry.STRIPPED_SOULWOOD, settings()));

	public static final Item EXPOSED_SOULWOOD_LOG = register("exposed_soulwood_log", new BlockItem(MalumBlockRegistry.EXPOSED_SOULWOOD_LOG, settings()));
	public static final Item REVEALED_SOULWOOD_LOG = register("revealed_soulwood_log", new BlockItem(MalumBlockRegistry.REVEALED_SOULWOOD_LOG, settings()));

	public static final Item SOULWOOD_PLANKS = register("soulwood_planks", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS, settings()));
	public static final Item VERTICAL_SOULWOOD_PLANKS = register("vertical_soulwood_planks", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS, settings()));
	public static final Item SOULWOOD_PANEL = register("soulwood_panel", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL, settings()));
	public static final Item SOULWOOD_TILES = register("soulwood_tiles", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES, settings()));

	public static final Item SOULWOOD_PLANKS_SLAB = register("soulwood_planks_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_SLAB, settings()));
	public static final Item VERTICAL_SOULWOOD_PLANKS_SLAB = register("vertical_soulwood_planks_slab", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS_SLAB, settings()));
	public static final Item SOULWOOD_PANEL_SLAB = register("soulwood_panel_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL_SLAB, settings()));
	public static final Item SOULWOOD_TILES_SLAB = register("soulwood_tiles_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES_SLAB, settings()));

	public static final Item SOULWOOD_PLANKS_STAIRS = register("soulwood_planks_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_STAIRS, settings()));
	public static final Item VERTICAL_SOULWOOD_PLANKS_STAIRS = register("vertical_soulwood_planks_stairs", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS_STAIRS, settings()));
	public static final Item SOULWOOD_PANEL_STAIRS = register("soulwood_panel_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL_STAIRS, settings()));
	public static final Item SOULWOOD_TILES_STAIRS = register("soulwood_tiles_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES_STAIRS, settings()));

	public static final Item CUT_SOULWOOD_PLANKS = register("cut_soulwood_planks", new BlockItem(MalumBlockRegistry.CUT_SOULWOOD_PLANKS, settings()));
	public static final Item SOULWOOD_BEAM = register("soulwood_beam", new BlockItem(MalumBlockRegistry.SOULWOOD_BEAM, settings()));

	public static final Item SOULWOOD_DOOR = register("soulwood_door", new BlockItem(MalumBlockRegistry.SOULWOOD_DOOR, settings()));
	public static final Item SOULWOOD_TRAPDOOR = register("soulwood_trapdoor", new BlockItem(MalumBlockRegistry.SOULWOOD_TRAPDOOR, settings()));
	public static final Item SOLID_SOULWOOD_TRAPDOOR = register("solid_soulwood_trapdoor", new BlockItem(MalumBlockRegistry.SOLID_SOULWOOD_TRAPDOOR, settings()));

	public static final Item SOULWOOD_PLANKS_BUTTON = register("soulwood_planks_button", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_BUTTON, settings()));
	public static final Item SOULWOOD_PLANKS_PRESSURE_PLATE = register("soulwood_planks_pressure_plate", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_PRESSURE_PLATE, settings()));

	public static final Item SOULWOOD_PLANKS_FENCE = register("soulwood_planks_fence", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_FENCE, settings()));
	public static final Item SOULWOOD_PLANKS_FENCE_GATE = register("soulwood_planks_fence_gate", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_FENCE_GATE, settings()));

	public static final Item SOULWOOD_ITEM_STAND = register("soulwood_item_stand", new BlockItem(MalumBlockRegistry.SOULWOOD_ITEM_STAND, settings()));
	public static final Item SOULWOOD_ITEM_PEDESTAL = register("soulwood_item_pedestal", new BlockItem(MalumBlockRegistry.SOULWOOD_ITEM_PEDESTAL, settings()));

	//public static final Item SOULWOOD_SIGN = register("soulwood_sign", new SignItem(settings().maxCount(16), MalumBlockRegistry.SOULWOOD_SIGN, MalumBlockRegistry.SOULWOOD_WALL_SIGN));
	//public static final Item SOULWOOD_BOAT = register("soulwood_boat", new LodestoneBoatItem(settings().maxCount(1), EntityRegistry.SOULWOOD_BOAT));


	//endregion

	//region spirits
	public static final Item SACRED_SPIRIT = register("sacred_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.SACRED_SPIRIT));
	public static final Item WICKED_SPIRIT = register("wicked_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.WICKED_SPIRIT));
	public static final Item ARCANE_SPIRIT = register("arcane_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.ARCANE_SPIRIT));
	public static final Item ELDRITCH_SPIRIT = register("eldritch_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.ELDRITCH_SPIRIT));
	public static final Item EARTHEN_SPIRIT = register("earthen_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.EARTHEN_SPIRIT));
	public static final Item INFERNAL_SPIRIT = register("infernal_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.INFERNAL_SPIRIT));
	public static final Item AERIAL_SPIRIT = register("aerial_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.AERIAL_SPIRIT));
	public static final Item AQUEOUS_SPIRIT = register("aqueous_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.AQUEOUS_SPIRIT));
	//endregion

	//region ores
	public static final Item COPPER_NUGGET = register("copper_nugget", new Item(new Item.Settings()));

	public static final Item COAL_FRAGMENT = register("coal_fragment", new Item(new Item.Settings()));
	public static final Item CHARCOAL_FRAGMENT = register("charcoal_fragment", new Item(new Item.Settings()));

	public static final Item ARCANE_CHARCOAL = register("arcane_charcoal", new Item(new Item.Settings()));
	public static final Item ARCANE_CHARCOAL_FRAGMENT = register("arcane_charcoal_fragment", new Item(new Item.Settings()));
	public static final Item BLOCK_OF_ARCANE_CHARCOAL = register("block_of_arcane_charcoal", new BlockItem(MalumBlockRegistry.BLOCK_OF_ARCANE_CHARCOAL, new Item.Settings()));

	public static final Item BLAZING_QUARTZ_ORE = register("blazing_quartz_ore", new BlockItem(MalumBlockRegistry.BLAZING_QUARTZ_ORE, new Item.Settings()));
	public static final Item BLAZING_QUARTZ = register("blazing_quartz", new Item(new Item.Settings()));
	public static final Item BLAZING_QUARTZ_FRAGMENT = register("blazing_quartz_fragment", new Item(new Item.Settings()));
	public static final Item BLOCK_OF_BLAZING_QUARTZ = register("block_of_blazing_quartz", new BlockItem(MalumBlockRegistry.BLOCK_OF_BLAZING_QUARTZ, new Item.Settings()));



	public static final Item NATURAL_QUARTZ_ORE = register("natural_quartz_ore", new BlockItem(MalumBlockRegistry.NATURAL_QUARTZ_ORE, new Item.Settings()));
	public static final Item DEEPSLATE_QUARTZ_ORE = register("deepslate_quartz_ore", new BlockItem(MalumBlockRegistry.DEEPSLATE_QUARTZ_ORE, new Item.Settings()));
	public static final Item NATURAL_QUARTZ = register("natural_quartz", new AliasedBlockItem(MalumBlockRegistry.NATURAL_QUARTZ_CLUSTER, new Item.Settings()));

	public static final Item BRILLIANT_STONE = register("brilliant_stone", new BlockItem(MalumBlockRegistry.BRILLIANT_STONE, new Item.Settings()));
	public static final Item BRILLIANT_DEEPSLATE = register("brilliant_deepslate", new BlockItem(MalumBlockRegistry.BRILLIANT_DEEPSLATE, new Item.Settings()));
	public static final Item CLUSTER_OF_BRILLIANCE = register("cluster_of_brilliance", new Item(new Item.Settings()));
	public static final Item CRUSHED_BRILLIANCE = register("crushed_brilliance", new Item(new Item.Settings()));
	public static final Item CHUNK_OF_BRILLIANCE = register("chunk_of_brilliance", new BrillianceChunkItem(new Item.Settings().food((new FoodComponent.Builder()).snack().alwaysEdible().build())));
	public static final Item BLOCK_OF_BRILLIANCE = register("block_of_brilliance", new BlockItem(MalumBlockRegistry.BLOCK_OF_BRILLIANCE, new Item.Settings()));

	public static final Item SOULSTONE_ORE = register("soulstone_ore", new BlockItem(MalumBlockRegistry.SOULSTONE_ORE, settings()));
	public static final Item DEEPSLATE_SOULSTONE_ORE = register("deepslate_soulstone_ore", new BlockItem(MalumBlockRegistry.DEEPSLATE_SOULSTONE_ORE, settings()));
	public static final Item RAW_SOULSTONE = register("raw_soulstone", new Item(settings()));
	public static final Item CRUSHED_SOULSTONE = register("crushed_soulstone", new Item(settings()));
	public static final Item BLOCK_OF_RAW_SOULSTONE = register("block_of_raw_soulstone", new BlockItem(MalumBlockRegistry.BLOCK_OF_RAW_SOULSTONE, settings()));
	public static final Item PROCESSED_SOULSTONE = register("processed_soulstone", new Item(settings()));
	public static final Item BLOCK_OF_SOULSTONE = register("block_of_soulstone", new BlockItem(MalumBlockRegistry.BLOCK_OF_SOULSTONE, settings()));
	//endregion

	//region crafting blocks
	public static final Item SPIRIT_ALTAR = register("spirit_altar", new BlockItem(MalumBlockRegistry.SPIRIT_ALTAR, settings()));
	public static final Item SPIRIT_JAR = register("spirit_jar", new SpiritJarItem(MalumBlockRegistry.SPIRIT_JAR, settings()));
	public static final Item SOUL_VIAL = register("soul_vial", new SoulVialItem(MalumBlockRegistry.SOUL_VIAL, settings()));
	public static final Item RUNEWOOD_OBELISK = register("runewood_obelisk", new MultiBlockItem(MalumBlockRegistry.RUNEWOOD_OBELISK, settings(), RunewoodObeliskBlockEntity.STRUCTURE));
	public static final Item BRILLIANT_OBELISK = register("brilliant_obelisk", new MultiBlockItem(MalumBlockRegistry.BRILLIANT_OBELISK, settings(), BrilliantObeliskBlockEntity.STRUCTURE));
	public static final Item SPIRIT_CRUCIBLE = register("spirit_crucible", new MultiBlockItem(MalumBlockRegistry.SPIRIT_CRUCIBLE, settings(), SpiritCrucibleCoreBlockEntity.STRUCTURE));
	public static final Item TWISTED_TABLET = register("twisted_tablet", new BlockItem(MalumBlockRegistry.TWISTED_TABLET, settings()));
	public static final Item SPIRIT_CATALYZER = register("spirit_catalyzer", new MultiBlockItem(MalumBlockRegistry.SPIRIT_CATALYZER, settings(), SpiritCatalyzerCoreBlockEntity.STRUCTURE));
	public static final Item EMITTER_MIRROR = register("emitter_mirror", new BlockItem(MalumBlockRegistry.EMITTER_MIRROR, settings()));
	public static final Item RUNEWOOD_TOTEM_BASE = register("runewood_totem_base", new BlockItem(MalumBlockRegistry.RUNEWOOD_TOTEM_BASE, settings()));
	public static final Item SOULWOOD_TOTEM_BASE = register("soulwood_totem_base", new BlockItem(MalumBlockRegistry.SOULWOOD_TOTEM_BASE, settings()));
	public static final Item ALTERATION_PLINTH = register("alteration_plinth", new BlockItem(MalumBlockRegistry.ALTERATION_PLINTH, settings()));
	public static final Item SOULWOOD_PLINTH = register("soulwood_plinth", new MultiBlockItem(MalumBlockRegistry.SOULWOOD_PLINTH, settings(), PlinthCoreBlockEntity.STRUCTURE));
	public static final Item SOULWOOD_FUSION_PLATE = register("soulwood_fusion_plate", new MultiBlockItem(MalumBlockRegistry.SOULWOOD_FUSION_PLATE, settings(), FusionPlateBlockEntity.STRUCTURE));
	//endregion

	//region materials
	public static final Item ROTTING_ESSENCE = register("rotting_essence", new Item(settings().food((new FoodComponent.Builder()).hunger
(6).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 1), 0.95f).build())));
	public static final Item GRIM_TALC = register("grim_talc", new Item(settings()));
	public static final Item ALCHEMICAL_CALX = register("alchemical_calx", new Item(settings()));
	public static final Item ASTRAL_WEAVE = register("astral_weave", new Item(settings()));
	public static final Item RARE_EARTHS = register("rare_earths", new Item(settings()));
	public static final Item HEX_ASH = register("hex_ash", new Item(settings()));

	public static final Item BLOCK_OF_ROTTING_ESSENCE = register("block_of_rotting_essence", new BlockItem(MalumBlockRegistry.BLOCK_OF_ROTTING_ESSENCE, settings()));
	public static final Item BLOCK_OF_GRIM_TALC = register("block_of_grim_talc", new BlockItem(MalumBlockRegistry.BLOCK_OF_GRIM_TALC, settings()));
	public static final Item BLOCK_OF_ALCHEMICAL_CALX = register("block_of_alchemical_calx", new BlockItem(MalumBlockRegistry.BLOCK_OF_ALCHEMICAL_CALX, settings()));
	public static final Item BLOCK_OF_ASTRAL_WEAVE = register("block_of_astral_weave", new BlockItem(MalumBlockRegistry.BLOCK_OF_ASTRAL_WEAVE, settings()));
	public static final Item BLOCK_OF_RARE_EARTHS = register("block_of_rare_earths", new AliasedBlockItem(MalumBlockRegistry.BLOCK_OF_RARE_EARTHS, new Item.Settings()));
	public static final Item BLOCK_OF_HEX_ASH = register("block_of_hex_ash", new BlockItem(MalumBlockRegistry.BLOCK_OF_HEX_ASH, settings()));
	public static final Item BLOCK_OF_CURSED_GRIT = register("block_of_cursed_grit", new BlockItem(MalumBlockRegistry.BLOCK_OF_CURSED_GRIT, settings()));

	public static final Item SPIRIT_FABRIC = register("spirit_fabric", new Item(settings()));
	public static final Item SPECTRAL_LENS = register("spectral_lens", new Item(settings()));
	public static final Item POPPET = register("poppet", new Item(settings()));
	public static final Item CURSED_GRIT = register("cursed_grit", new Item(settings()));
	public static final Item CORRUPTED_RESONANCE = register("corrupted_resonance", new CorruptResonanceItem(settings()));

	public static final Item HALLOWED_GOLD_INGOT = register("hallowed_gold_ingot", new Item(settings()));
	public static final Item HALLOWED_GOLD_NUGGET = register("hallowed_gold_nugget", new Item(settings()));
	public static final Item BLOCK_OF_HALLOWED_GOLD = register("block_of_hallowed_gold", new BlockItem(MalumBlockRegistry.BLOCK_OF_HALLOWED_GOLD, settings()));
	public static final Item HALLOWED_SPIRIT_RESONATOR = register("hallowed_spirit_resonator", new Item(settings()));

	public static final Item SOUL_STAINED_STEEL_INGOT = register("soul_stained_steel_ingot", new Item(settings()));
	public static final Item SOUL_STAINED_STEEL_NUGGET = register("soul_stained_steel_nugget", new Item(settings()));
	public static final Item BLOCK_OF_SOUL_STAINED_STEEL = register("block_of_soul_stained_steel", new BlockItem(MalumBlockRegistry.BLOCK_OF_SOUL_STAINED_STEEL, settings()));
	public static final Item STAINED_SPIRIT_RESONATOR = register("stained_spirit_resonator", new Item(settings()));

	public static final CrackedImpetusItem CRACKED_IRON_IMPETUS = register("cracked_iron_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem IRON_IMPETUS = register("iron_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_IRON_IMPETUS));
	public static final Item IRON_NODE = register("iron_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_COPPER_IMPETUS = register("cracked_copper_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem COPPER_IMPETUS = register("copper_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_COPPER_IMPETUS));
	public static final Item COPPER_NODE = register("copper_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_GOLD_IMPETUS = register("cracked_gold_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem GOLD_IMPETUS = register("gold_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_GOLD_IMPETUS));
	public static final Item GOLD_NODE = register("gold_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_LEAD_IMPETUS = register("cracked_lead_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem LEAD_IMPETUS = register("lead_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_LEAD_IMPETUS));
	public static final Item LEAD_NODE = register("lead_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_SILVER_IMPETUS = register("cracked_silver_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem SILVER_IMPETUS = register("silver_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_SILVER_IMPETUS));
	public static final Item SILVER_NODE = register("silver_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_ALUMINUM_IMPETUS = register("cracked_aluminum_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem ALUMINUM_IMPETUS = register("aluminum_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ALUMINUM_IMPETUS));
	public static final Item ALUMINUM_NODE = register("aluminum_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_NICKEL_IMPETUS = register("cracked_nickel_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem NICKEL_IMPETUS = register("nickel_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_NICKEL_IMPETUS));
	public static final Item NICKEL_NODE = register("nickel_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_URANIUM_IMPETUS = register("cracked_uranium_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem URANIUM_IMPETUS = register("uranium_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_URANIUM_IMPETUS));
	public static final Item URANIUM_NODE = register("uranium_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_OSMIUM_IMPETUS = register("cracked_osmium_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem OSMIUM_IMPETUS = register("osmium_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_OSMIUM_IMPETUS));
	public static final Item OSMIUM_NODE = register("osmium_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_ZINC_IMPETUS = register("cracked_zinc_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem ZINC_IMPETUS = register("zinc_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ZINC_IMPETUS));
	public static final Item ZINC_NODE = register("zinc_node", new NodeItem(settings()));
	public static final CrackedImpetusItem CRACKED_TIN_IMPETUS = register("cracked_tin_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem TIN_IMPETUS = register("tin_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_TIN_IMPETUS));
	public static final Item TIN_NODE = register("tin_node", new NodeItem(settings()));

	public static final CrackedImpetusItem CRACKED_ALCHEMICAL_IMPETUS = register("cracked_alchemical_impetus", new CrackedImpetusItem(settings()));
	public static final ImpetusItem ALCHEMICAL_IMPETUS = register("alchemical_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ALCHEMICAL_IMPETUS));

	//endregion

	//region ether
	public static final Item ETHER = register("ether", new EtherItem(MalumBlockRegistry.ETHER, settings(), false));
	public static final Item ETHER_TORCH = register("ether_torch", new EtherTorchItem(MalumBlockRegistry.ETHER_TORCH, MalumBlockRegistry.WALL_ETHER_TORCH, settings(), false));
	public static final Item TAINTED_ETHER_BRAZIER = register("tainted_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TAINTED_ETHER_BRAZIER, settings(), false));
	public static final Item TWISTED_ETHER_BRAZIER = register("twisted_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TWISTED_ETHER_BRAZIER, settings(), false));

	//public static final Item ETHER_SCONCE = register("ether_sconce", new EtherSconceItem(MalumBlockRegistry.ETHER_SCONCE, MalumBlockRegistry.WALL_ETHER_SCONCE, SupplementariesCompat.LOADED ? settings() : settings(), false));

	public static final Item IRIDESCENT_ETHER = register("iridescent_ether", new EtherItem(MalumBlockRegistry.IRIDESCENT_ETHER, settings(), true));
	public static final Item IRIDESCENT_ETHER_TORCH = register("iridescent_ether_torch", new EtherTorchItem(MalumBlockRegistry.IRIDESCENT_ETHER_TORCH, MalumBlockRegistry.IRIDESCENT_WALL_ETHER_TORCH, settings(), true));
	public static final Item TAINTED_IRIDESCENT_ETHER_BRAZIER = register("tainted_iridescent_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TAINTED_IRIDESCENT_ETHER_BRAZIER, settings(), true));
	public static final Item TWISTED_IRIDESCENT_ETHER_BRAZIER = register("twisted_iridescent_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TWISTED_IRIDESCENT_ETHER_BRAZIER, settings(), true));

	//public static final Item IRIDESCENT_ETHER_SCONCE = register("iridescent_ether_sconce", new EtherSconceItem(MalumBlockRegistry.IRIDESCENT_ETHER_SCONCE, MalumBlockRegistry.IRIDESCENT_WALL_ETHER_SCONCE, SupplementariesCompat.LOADED ? settings() : settings(), true));
	//endregion


	//region contents
	public static final Item SPIRIT_POUCH = register("spirit_pouch", new SpiritPouchItem(settings()));
	public static final Item CRUDE_SCYTHE = register("crude_scythe", new MalumScytheItem(ToolMaterials.IRON, 0, 0.1f, settings().maxDamage(350)));
	public static final Item SOUL_STAINED_STEEL_SCYTHE = register("soul_stained_steel_scythe", new MagicScytheItem(SOUL_STAINED_STEEL, -2.5f, 0.1f, 4, settings()));
	public static final Item SOULWOOD_STAVE = register("soulwood_stave", new SoulStaveItem(settings()));

	public static final Item SOUL_STAINED_STEEL_SWORD = register("soul_stained_steel_sword", new MagicSwordItem(SOUL_STAINED_STEEL, -3, 0, 3, settings()));
	public static final Item SOUL_STAINED_STEEL_PICKAXE = register("soul_stained_steel_pickaxe", new MagicPickaxeItem(SOUL_STAINED_STEEL, -2, 0, 2, settings()));
	public static final Item SOUL_STAINED_STEEL_AXE = register("soul_stained_steel_axe", new MagicAxeItem(SOUL_STAINED_STEEL, -3f, 0, 4, settings()));
	public static final Item SOUL_STAINED_STEEL_SHOVEL = register("soul_stained_steel_shovel", new MagicShovelItem(SOUL_STAINED_STEEL, -2, 0, 2, settings()));
	public static final Item SOUL_STAINED_STEEL_HOE = register("soul_stained_steel_hoe", new MagicHoeItem(SOUL_STAINED_STEEL, 0, -1.5f, 1, settings()));
	//public static final Item SOUL_STAINED_STEEL_KNIFE = register("soul_stained_steel_knife", FarmersDelightCompat.LOADED ? FarmersDelightCompat.LoadedOnly.makeMagicKnife() : new Item(settings()));

	public static final Item SOUL_STAINED_STEEL_HELMET = register("soul_stained_steel_helmet", new SoulStainedSteelArmorItem(EquipmentSlot.HEAD, settings()));
	public static final Item SOUL_STAINED_STEEL_CHESTPLATE = register("soul_stained_steel_chestplate", new SoulStainedSteelArmorItem(EquipmentSlot.CHEST, settings()));
	public static final Item SOUL_STAINED_STEEL_LEGGINGS = register("soul_stained_steel_leggings", new SoulStainedSteelArmorItem(EquipmentSlot.LEGS, settings()));
	public static final Item SOUL_STAINED_STEEL_BOOTS = register("soul_stained_steel_boots", new SoulStainedSteelArmorItem(EquipmentSlot.FEET, settings()));

	public static final Item SOUL_HUNTER_CLOAK = register("soul_hunter_cloak", new SoulHunterArmorItem(EquipmentSlot.HEAD, settings()));
	public static final Item SOUL_HUNTER_ROBE = register("soul_hunter_robe", new SoulHunterArmorItem(EquipmentSlot.CHEST, settings()));
	public static final Item SOUL_HUNTER_LEGGINGS = register("soul_hunter_leggings", new SoulHunterArmorItem(EquipmentSlot.LEGS, settings()));
	public static final Item SOUL_HUNTER_BOOTS = register("soul_hunter_boots", new SoulHunterArmorItem(EquipmentSlot.FEET, settings()));

	public static final Item ETHERIC_NITRATE = register("etheric_nitrate", new EthericNitrateItem(settings()));
	public static final Item VIVID_NITRATE = register("vivid_nitrate", new VividNitrateItem(settings()));

	public static final Item TYRVING = register("tyrving", new TyrvingItem(ItemTiers.ItemTierEnum.TYRVING, 0, -0.3f, settings()));

	public static final Item GILDED_RING = register("gilded_ring", new CurioGildedRing(settings()));
	public static final Item GILDED_BELT = register("gilded_belt", new CurioGildedBelt(settings()));
	public static final Item ORNATE_RING = register("ornate_ring", new CurioOrnateRing(settings()));
	public static final Item ORNATE_NECKLACE = register("ornate_necklace", new CurioOrnateNecklace(settings()));

	public static final Item RING_OF_ESOTERIC_SPOILS = register("ring_of_esoteric_spoils", new CurioArcaneSpoilRing(settings()));
	public static final Item RING_OF_CURATIVE_TALENT = register("ring_of_curative_talent", new CurioCurativeRing(settings()));
	public static final Item RING_OF_ARCANE_PROWESS = register("ring_of_arcane_prowess", new CurioRingOfProwess(settings()));
	public static final Item RING_OF_ALCHEMICAL_MASTERY = register("ring_of_alchemical_mastery", new CurioAlchemicalRing(settings()));
	public static final Item RING_OF_DESPERATE_VORACITY = register("ring_of_desperate_voracity", new CurioVeraciousRing(settings()));
	public static final Item RING_OF_THE_HOARDER = register("ring_of_the_hoarder", new CurioHoarderRing(settings()));
	public static final Item RING_OF_THE_DEMOLITIONIST = register("ring_of_the_demolitionist", new CurioDemolitionistRing(settings()));

	public static final Item NECKLACE_OF_THE_MYSTIC_MIRROR = register("necklace_of_the_mystic_mirror", new CurioMirrorNecklace(settings()));
	public static final Item NECKLACE_OF_TIDAL_AFFINITY = register("necklace_of_tidal_affinity", new CurioWaterNecklace(settings()));
	public static final Item NECKLACE_OF_THE_NARROW_EDGE = register("necklace_of_the_narrow_edge", new CurioNarrowNecklace(settings()));
	public static final Item NECKLACE_OF_THE_HIDDEN_BLADE = register("necklace_of_the_hidden_blade", new CurioHiddenBladeNecklace(settings()));
	public static final Item NECKLACE_OF_BLISSFUL_HARMONY = register("necklace_of_blissful_harmony", new CurioHarmonyNecklace(settings()));

	public static final Item BELT_OF_THE_STARVED = register("belt_of_the_starved", new CurioStarvedBelt(settings()));
	public static final Item BELT_OF_THE_PROSPECTOR = register("belt_of_the_prospector", new CurioProspectorBelt(settings()));
	public static final Item BELT_OF_THE_MAGEBANE = register("belt_of_the_magebane", new CurioMagebaneBelt(settings()));
	//endregion

	//region cosmetics

	public static final Item ESOTERIC_SPOOL = register("esoteric_spool", new Item(settings()));
	public static final Item ANCIENT_WEAVE = register("ancient_weave", new AncientWeaveItem(settings()));

	/*TODO pride
	public static final PrideweaveItem ACE_PRIDEWEAVE = register("ace_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem AGENDER_PRIDEWEAVE = register("agender_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem ARO_PRIDEWEAVE = register("aro_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem AROACE_PRIDEWEAVE = register("aroace_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem BI_PRIDEWEAVE = register("bi_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem DEMIBOY_PRIDEWEAVE = register("demiboy_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem DEMIGIRL_PRIDEWEAVE = register("demigirl_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem ENBY_PRIDEWEAVE = register("enby_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem GAY_PRIDEWEAVE = register("gay_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem GENDERFLUID_PRIDEWEAVE = register("genderfluid_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem GENDERQUEER_PRIDEWEAVE = register("genderqueer_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem INTERSEX_PRIDEWEAVE = register("intersex_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem LESBIAN_PRIDEWEAVE = register("lesbian_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem PAN_PRIDEWEAVE = register("pan_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem PLURAL_PRIDEWEAVE = register("plural_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem POLY_PRIDEWEAVE = register("poly_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem PRIDE_PRIDEWEAVE = register("pride_prideweave", new PrideweaveItem(settings()));
	public static final PrideweaveItem TRANS_PRIDEWEAVE = register("trans_prideweave", new PrideweaveItem(settings()));

	 */

	//endregion

	//region hidden items
	public static final Item THE_DEVICE = register("the_device", new BlockItem(MalumBlockRegistry.THE_DEVICE, settings()));
	public static final Item CREATIVE_SCYTHE = register("creative_scythe", new MagicScytheItem(ToolMaterials.IRON, 9993, 9.1f, 999f, settings().maxDamage(-1)));
	public static final Item TOKEN_OF_GRATITUDE = register("token_of_gratitude", new CurioTokenOfGratitude(settings()));
	//endregion



	public static QuiltItemSettings settings(){
		return new QuiltItemSettings();
	}

	public static <T extends Item> T register(String id, T item) {
		ITEMS.put(new Identifier(Malum.MODID, id), item);
		return item;
	}

	public static <T extends MalumScytheItem> T registerScytheItem(String id, T item) {
		SCYTHES.add(item);
		return register(id, item);
	}

	public static void init() {
		ITEMS.forEach((id, item) -> Registry.register(Registries.ITEM, id, item));
	}
}
