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
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

public interface MalumItemRegistry {
	Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
	Set<MalumScytheItem> SCYTHES = new ReferenceOpenHashSet<>();


	Item ENCYCLOPEDIA_ARCANA = register("encyclopedia_arcana", new EncyclopediaArcanaItem(settings().rarity(Rarity.UNCOMMON)));

	Item BLAZING_TORCH = register("blazing_torch", new WallStandingBlockItem(MalumBlockRegistry.BLAZING_TORCH, MalumBlockRegistry.WALL_BLAZING_TORCH, new Item.Settings(), Direction.NORTH));
	//Item BLAZING_SCONCE = register("blazing_sconce", new WallStandingBlockItem(MalumBlockRegistry.BLAZING_SCONCE, MalumBlockRegistry.WALL_BLAZING_SCONCE, SupplementariesCompat.LOADED ? new Item.Settings().tab(CreativeModeTab.TAB_MISC) : settings()));

	//region tainted rock
	Item TAINTED_ROCK = register("tainted_rock", new BlockItem(MalumBlockRegistry.TAINTED_ROCK, settings()));
	Item SMOOTH_TAINTED_ROCK = register("smooth_tainted_rock", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK, settings()));
	Item POLISHED_TAINTED_ROCK = register("polished_tainted_rock", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK, settings()));
	Item TAINTED_ROCK_BRICKS = register("tainted_rock_bricks", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS, settings()));
	Item CRACKED_TAINTED_ROCK_BRICKS = register("cracked_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS, settings()));
	Item TAINTED_ROCK_TILES = register("tainted_rock_tiles", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES, settings()));
	Item CRACKED_TAINTED_ROCK_TILES = register("cracked_tainted_rock_tiles", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES, settings()));
	Item SMALL_TAINTED_ROCK_BRICKS = register("small_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS, settings()));
	Item CRACKED_SMALL_TAINTED_ROCK_BRICKS = register("cracked_small_tainted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS, settings()));

	Item TAINTED_ROCK_COLUMN = register("tainted_rock_column", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_COLUMN, settings()));
	Item TAINTED_ROCK_COLUMN_CAP = register("tainted_rock_column_cap", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_COLUMN_CAP, settings()));

	Item CUT_TAINTED_ROCK = register("cut_tainted_rock", new BlockItem(MalumBlockRegistry.CUT_TAINTED_ROCK, settings()));
	Item CHISELED_TAINTED_ROCK = register("chiseled_tainted_rock", new BlockItem(MalumBlockRegistry.CHISELED_TAINTED_ROCK, settings()));
	Item TAINTED_ROCK_PRESSURE_PLATE = register("tainted_rock_pressure_plate", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_PRESSURE_PLATE, settings()));
	Item TAINTED_ROCK_BUTTON = register("tainted_rock_button", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BUTTON, settings()));

	Item TAINTED_ROCK_WALL = register("tainted_rock_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_WALL, settings()));
	Item TAINTED_ROCK_BRICKS_WALL = register("tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_WALL, settings()));
	Item CRACKED_TAINTED_ROCK_BRICKS_WALL = register("cracked_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_WALL, settings()));
	Item TAINTED_ROCK_TILES_WALL = register("tainted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_WALL, settings()));
	Item CRACKED_TAINTED_ROCK_TILES_WALL = register("cracked_tainted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_WALL, settings()));
	Item SMALL_TAINTED_ROCK_BRICKS_WALL = register("small_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_WALL, settings()));
	Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL = register("cracked_small_tainted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL, settings()));

	Item TAINTED_ROCK_SLAB = register("tainted_rock_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_SLAB, settings()));
	Item SMOOTH_TAINTED_ROCK_SLAB = register("smooth_tainted_rock_slab", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK_SLAB, settings()));
	Item POLISHED_TAINTED_ROCK_SLAB = register("polished_tainted_rock_slab", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK_SLAB, settings()));
	Item TAINTED_ROCK_BRICKS_SLAB = register("tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_SLAB, settings()));
	Item CRACKED_TAINTED_ROCK_BRICKS_SLAB = register("cracked_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_SLAB, settings()));
	Item TAINTED_ROCK_TILES_SLAB = register("tainted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_SLAB, settings()));
	Item CRACKED_TAINTED_ROCK_TILES_SLAB = register("cracked_tainted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_SLAB, settings()));
	Item SMALL_TAINTED_ROCK_BRICKS_SLAB = register("small_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_SLAB, settings()));
	Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB = register("cracked_small_tainted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB, settings()));

	Item TAINTED_ROCK_STAIRS = register("tainted_rock_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_STAIRS, settings()));
	Item SMOOTH_TAINTED_ROCK_STAIRS = register("smooth_tainted_rock_stairs", new BlockItem(MalumBlockRegistry.SMOOTH_TAINTED_ROCK_STAIRS, settings()));
	Item POLISHED_TAINTED_ROCK_STAIRS = register("polished_tainted_rock_stairs", new BlockItem(MalumBlockRegistry.POLISHED_TAINTED_ROCK_STAIRS, settings()));
	Item TAINTED_ROCK_BRICKS_STAIRS = register("tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_BRICKS_STAIRS, settings()));
	Item CRACKED_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_BRICKS_STAIRS, settings()));
	Item TAINTED_ROCK_TILES_STAIRS = register("tainted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_TILES_STAIRS, settings()));
	Item CRACKED_TAINTED_ROCK_TILES_STAIRS = register("cracked_tainted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TAINTED_ROCK_TILES_STAIRS, settings()));
	Item SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("small_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.SMALL_TAINTED_ROCK_BRICKS_STAIRS, settings()));
	Item CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_small_tainted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS, settings()));

	Item TAINTED_ROCK_ITEM_STAND = register("tainted_rock_item_stand", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_ITEM_STAND, settings()));
	Item TAINTED_ROCK_ITEM_PEDESTAL = register("tainted_rock_item_pedestal", new BlockItem(MalumBlockRegistry.TAINTED_ROCK_ITEM_PEDESTAL, settings()));

	//endregion

	//region twisted rock
	Item TWISTED_ROCK = register("twisted_rock", new BlockItem(MalumBlockRegistry.TWISTED_ROCK, settings()));
	Item SMOOTH_TWISTED_ROCK = register("smooth_twisted_rock", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK, settings()));
	Item POLISHED_TWISTED_ROCK = register("polished_twisted_rock", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK, settings()));
	Item TWISTED_ROCK_BRICKS = register("twisted_rock_bricks", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS, settings()));
	Item CRACKED_TWISTED_ROCK_BRICKS = register("cracked_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS, settings()));
	Item TWISTED_ROCK_TILES = register("twisted_rock_tiles", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES, settings()));
	Item CRACKED_TWISTED_ROCK_TILES = register("cracked_twisted_rock_tiles", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES, settings()));
	Item SMALL_TWISTED_ROCK_BRICKS = register("small_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS, settings()));
	Item CRACKED_SMALL_TWISTED_ROCK_BRICKS = register("cracked_small_twisted_rock_bricks", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS, settings()));

	Item TWISTED_ROCK_COLUMN = register("twisted_rock_column", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_COLUMN, settings()));
	Item TWISTED_ROCK_COLUMN_CAP = register("twisted_rock_column_cap", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_COLUMN_CAP, settings()));

	Item CUT_TWISTED_ROCK = register("cut_twisted_rock", new BlockItem(MalumBlockRegistry.CUT_TWISTED_ROCK, settings()));
	Item CHISELED_TWISTED_ROCK = register("chiseled_twisted_rock", new BlockItem(MalumBlockRegistry.CHISELED_TWISTED_ROCK, settings()));
	Item TWISTED_ROCK_PRESSURE_PLATE = register("twisted_rock_pressure_plate", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_PRESSURE_PLATE, settings()));
	Item TWISTED_ROCK_BUTTON = register("twisted_rock_button", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BUTTON, settings()));

	Item TWISTED_ROCK_WALL = register("twisted_rock_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_WALL, settings()));
	Item TWISTED_ROCK_BRICKS_WALL = register("twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_WALL, settings()));
	Item CRACKED_TWISTED_ROCK_BRICKS_WALL = register("cracked_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_WALL, settings()));
	Item TWISTED_ROCK_TILES_WALL = register("twisted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_WALL, settings()));
	Item SMALL_TWISTED_ROCK_BRICKS_WALL = register("small_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_WALL, settings()));
	Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL = register("cracked_small_twisted_rock_bricks_wall", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL, settings()));
	Item CRACKED_TWISTED_ROCK_TILES_WALL = register("cracked_twisted_rock_tiles_wall", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_WALL, settings()));

	Item TWISTED_ROCK_SLAB = register("twisted_rock_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_SLAB, settings()));
	Item SMOOTH_TWISTED_ROCK_SLAB = register("smooth_twisted_rock_slab", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK_SLAB, settings()));
	Item POLISHED_TWISTED_ROCK_SLAB = register("polished_twisted_rock_slab", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK_SLAB, settings()));
	Item TWISTED_ROCK_BRICKS_SLAB = register("twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_SLAB, settings()));
	Item CRACKED_TWISTED_ROCK_BRICKS_SLAB = register("cracked_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_SLAB, settings()));
	Item TWISTED_ROCK_TILES_SLAB = register("twisted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_SLAB, settings()));
	Item CRACKED_TWISTED_ROCK_TILES_SLAB = register("cracked_twisted_rock_tiles_slab", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_SLAB, settings()));
	Item SMALL_TWISTED_ROCK_BRICKS_SLAB = register("small_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_SLAB, settings()));
	Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB = register("cracked_small_twisted_rock_bricks_slab", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB, settings()));

	Item TWISTED_ROCK_STAIRS = register("twisted_rock_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_STAIRS, settings()));
	Item SMOOTH_TWISTED_ROCK_STAIRS = register("smooth_twisted_rock_stairs", new BlockItem(MalumBlockRegistry.SMOOTH_TWISTED_ROCK_STAIRS, settings()));
	Item POLISHED_TWISTED_ROCK_STAIRS = register("polished_twisted_rock_stairs", new BlockItem(MalumBlockRegistry.POLISHED_TWISTED_ROCK_STAIRS, settings()));
	Item TWISTED_ROCK_BRICKS_STAIRS = register("twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_BRICKS_STAIRS, settings()));
	Item CRACKED_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_BRICKS_STAIRS, settings()));
	Item TWISTED_ROCK_TILES_STAIRS = register("twisted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_TILES_STAIRS, settings()));
	Item CRACKED_TWISTED_ROCK_TILES_STAIRS = register("cracked_twisted_rock_tiles_stairs", new BlockItem(MalumBlockRegistry.CRACKED_TWISTED_ROCK_TILES_STAIRS, settings()));
	Item SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("small_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.SMALL_TWISTED_ROCK_BRICKS_STAIRS, settings()));
	Item CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_small_twisted_rock_bricks_stairs", new BlockItem(MalumBlockRegistry.CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS, settings()));

	Item TWISTED_ROCK_ITEM_STAND = register("twisted_rock_item_stand", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_ITEM_STAND, settings()));
	Item TWISTED_ROCK_ITEM_PEDESTAL = register("twisted_rock_item_pedestal", new BlockItem(MalumBlockRegistry.TWISTED_ROCK_ITEM_PEDESTAL, settings()));
	//endregion twisted rock

	//region runewood
	Item HOLY_SAP = register("holy_sap", new Item(settings().recipeRemainder(GLASS_BOTTLE)));
	Item HOLY_SAPBALL = register("holy_sapball", new Item(settings()));
	Item HOLY_SYRUP = register("holy_syrup", new HolySyrupItem(settings().recipeRemainder(GLASS_BOTTLE).food((new FoodComponent.Builder()).hunger(6).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1).build())));
	//Item HOLY_CARAMEL = register("holy_caramel", new HolyCaramelItem(FarmersDelightCompat.LOADED ? settings().food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.15F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1).build()) : settings()));

	Item RUNEWOOD_LEAVES = register("runewood_leaves", new BlockItem(MalumBlockRegistry.RUNEWOOD_LEAVES, settings()));
	Item RUNEWOOD_SAPLING = register("runewood_sapling", new BlockItem(MalumBlockRegistry.RUNEWOOD_SAPLING, settings()));

	Item RUNEWOOD_LOG = register("runewood_log", new BlockItem(MalumBlockRegistry.RUNEWOOD_LOG, settings()));
	Item STRIPPED_RUNEWOOD_LOG = register("stripped_runewood_log", new BlockItem(MalumBlockRegistry.STRIPPED_RUNEWOOD_LOG, settings()));
	Item RUNEWOOD = register("runewood", new BlockItem(MalumBlockRegistry.RUNEWOOD, settings()));
	Item STRIPPED_RUNEWOOD = register("stripped_runewood", new BlockItem(MalumBlockRegistry.STRIPPED_RUNEWOOD, settings()));

	Item EXPOSED_RUNEWOOD_LOG = register("exposed_runewood_log", new BlockItem(MalumBlockRegistry.EXPOSED_RUNEWOOD_LOG, settings()));
	Item REVEALED_RUNEWOOD_LOG = register("revealed_runewood_log", new BlockItem(MalumBlockRegistry.REVEALED_RUNEWOOD_LOG, settings()));

	Item RUNEWOOD_PLANKS = register("runewood_planks", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS, settings()));
	Item VERTICAL_RUNEWOOD_PLANKS = register("vertical_runewood_planks", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS, settings()));
	Item RUNEWOOD_PANEL = register("runewood_panel", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL, settings()));
	Item RUNEWOOD_TILES = register("runewood_tiles", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES, settings()));

	Item RUNEWOOD_PLANKS_SLAB = register("runewood_planks_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_SLAB, settings()));
	Item VERTICAL_RUNEWOOD_PLANKS_SLAB = register("vertical_runewood_planks_slab", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS_SLAB, settings()));
	Item RUNEWOOD_PANEL_SLAB = register("runewood_panel_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL_SLAB, settings()));
	Item RUNEWOOD_TILES_SLAB = register("runewood_tiles_slab", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES_SLAB, settings()));

	Item RUNEWOOD_PLANKS_STAIRS = register("runewood_planks_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_STAIRS, settings()));
	Item VERTICAL_RUNEWOOD_PLANKS_STAIRS = register("vertical_runewood_planks_stairs", new BlockItem(MalumBlockRegistry.VERTICAL_RUNEWOOD_PLANKS_STAIRS, settings()));
	Item RUNEWOOD_PANEL_STAIRS = register("runewood_panel_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_PANEL_STAIRS, settings()));
	Item RUNEWOOD_TILES_STAIRS = register("runewood_tiles_stairs", new BlockItem(MalumBlockRegistry.RUNEWOOD_TILES_STAIRS, settings()));

	Item CUT_RUNEWOOD_PLANKS = register("cut_runewood_planks", new BlockItem(MalumBlockRegistry.CUT_RUNEWOOD_PLANKS, settings()));
	Item RUNEWOOD_BEAM = register("runewood_beam", new BlockItem(MalumBlockRegistry.RUNEWOOD_BEAM, settings()));
/*
	Item RUNEWOOD_DOOR = register("runewood_door", new BlockItem(MalumBlockRegistry.RUNEWOOD_DOOR, settings()));
	Item RUNEWOOD_TRAPDOOR = register("runewood_trapdoor", new BlockItem(MalumBlockRegistry.RUNEWOOD_TRAPDOOR, settings()));
	Item SOLID_RUNEWOOD_TRAPDOOR = register("solid_runewood_trapdoor", new BlockItem(MalumBlockRegistry.SOLID_RUNEWOOD_TRAPDOOR, settings()));

	Item RUNEWOOD_PLANKS_BUTTON = register("runewood_planks_button", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_BUTTON, settings()));
	Item RUNEWOOD_PLANKS_PRESSURE_PLATE = register("runewood_planks_pressure_plate", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_PRESSURE_PLATE, settings()));

	Item RUNEWOOD_PLANKS_FENCE = register("runewood_planks_fence", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_FENCE, settings()));
	Item RUNEWOOD_PLANKS_FENCE_GATE = register("runewood_planks_fence_gate", new BlockItem(MalumBlockRegistry.RUNEWOOD_PLANKS_FENCE_GATE, settings()));

	Item RUNEWOOD_ITEM_STAND = register("runewood_item_stand", new BlockItem(MalumBlockRegistry.RUNEWOOD_ITEM_STAND, settings()));
	Item RUNEWOOD_ITEM_PEDESTAL = register("runewood_item_pedestal", new BlockItem(MalumBlockRegistry.RUNEWOOD_ITEM_PEDESTAL, settings()));

	Item RUNEWOOD_SIGN = register("runewood_sign", new SignItem(settings().maxCount(16), MalumBlockRegistry.RUNEWOOD_SIGN, MalumBlockRegistry.RUNEWOOD_WALL_SIGN));
	//Item RUNEWOOD_BOAT = register("runewood_boat", new LodestoneBoatItem(settings().maxCount(1), EntityRegistry.RUNEWOOD_BOAT));

 */
	//endregion

	//region blight
	Item BLIGHTED_EARTH = register("blighted_earth", new BlockItem(MalumBlockRegistry.BLIGHTED_EARTH, settings()));
	Item BLIGHTED_SOIL = register("blighted_soil", new BlockItem(MalumBlockRegistry.BLIGHTED_SOIL, settings()));
	Item BLIGHTED_WEED = register("blighted_weed", new BlockItem(MalumBlockRegistry.BLIGHTED_WEED, settings()));
	Item BLIGHTED_TUMOR = register("blighted_tumor", new BlockItem(MalumBlockRegistry.BLIGHTED_TUMOR, settings()));
	Item BLIGHTED_SOULWOOD = register("blighted_soulwood", new BlockItem(MalumBlockRegistry.BLIGHTED_SOULWOOD, settings()));
	Item BLIGHTED_GUNK = register("blighted_gunk", new Item(settings()));
	//endregion

	//region soulwood
	Item UNHOLY_SAP = register("unholy_sap", new Item(settings().recipeRemainder(GLASS_BOTTLE)));
	Item UNHOLY_SAPBALL = register("unholy_sapball", new Item(settings()));
	Item UNHOLY_SYRUP = register("unholy_syrup", new UnholySyrupItem(settings().recipeRemainder(GLASS_BOTTLE).food((new FoodComponent.Builder()).hunger(6).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 1).build())));
	//Item UNHOLY_CARAMEL = register("unholy_caramel", new HolyCaramelItem(FarmersDelightCompat.LOADED ? settings().food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.15F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0), 1).build()) : settings()));

	Item SOULWOOD_LEAVES = register("soulwood_leaves", new BlockItem(MalumBlockRegistry.SOULWOOD_LEAVES, settings()));
	Item SOULWOOD_GROWTH = register("soulwood_growth", new BlockItem(MalumBlockRegistry.SOULWOOD_GROWTH, settings()));

	Item SOULWOOD_LOG = register("soulwood_log", new BlockItem(MalumBlockRegistry.SOULWOOD_LOG, settings()));
	Item STRIPPED_SOULWOOD_LOG = register("stripped_soulwood_log", new BlockItem(MalumBlockRegistry.STRIPPED_SOULWOOD_LOG, settings()));
	Item SOULWOOD = register("soulwood", new BlockItem(MalumBlockRegistry.SOULWOOD, settings()));
	Item STRIPPED_SOULWOOD = register("stripped_soulwood", new BlockItem(MalumBlockRegistry.STRIPPED_SOULWOOD, settings()));

	Item EXPOSED_SOULWOOD_LOG = register("exposed_soulwood_log", new BlockItem(MalumBlockRegistry.EXPOSED_SOULWOOD_LOG, settings()));
	Item REVEALED_SOULWOOD_LOG = register("revealed_soulwood_log", new BlockItem(MalumBlockRegistry.REVEALED_SOULWOOD_LOG, settings()));

	Item SOULWOOD_PLANKS = register("soulwood_planks", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS, settings()));
	Item VERTICAL_SOULWOOD_PLANKS = register("vertical_soulwood_planks", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS, settings()));
	Item SOULWOOD_PANEL = register("soulwood_panel", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL, settings()));
	Item SOULWOOD_TILES = register("soulwood_tiles", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES, settings()));

	Item SOULWOOD_PLANKS_SLAB = register("soulwood_planks_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_SLAB, settings()));
	Item VERTICAL_SOULWOOD_PLANKS_SLAB = register("vertical_soulwood_planks_slab", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS_SLAB, settings()));
	Item SOULWOOD_PANEL_SLAB = register("soulwood_panel_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL_SLAB, settings()));
	Item SOULWOOD_TILES_SLAB = register("soulwood_tiles_slab", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES_SLAB, settings()));

	Item SOULWOOD_PLANKS_STAIRS = register("soulwood_planks_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_STAIRS, settings()));
	Item VERTICAL_SOULWOOD_PLANKS_STAIRS = register("vertical_soulwood_planks_stairs", new BlockItem(MalumBlockRegistry.VERTICAL_SOULWOOD_PLANKS_STAIRS, settings()));
	Item SOULWOOD_PANEL_STAIRS = register("soulwood_panel_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_PANEL_STAIRS, settings()));
	Item SOULWOOD_TILES_STAIRS = register("soulwood_tiles_stairs", new BlockItem(MalumBlockRegistry.SOULWOOD_TILES_STAIRS, settings()));

	Item CUT_SOULWOOD_PLANKS = register("cut_soulwood_planks", new BlockItem(MalumBlockRegistry.CUT_SOULWOOD_PLANKS, settings()));
	Item SOULWOOD_BEAM = register("soulwood_beam", new BlockItem(MalumBlockRegistry.SOULWOOD_BEAM, settings()));

	Item SOULWOOD_DOOR = register("soulwood_door", new BlockItem(MalumBlockRegistry.SOULWOOD_DOOR, settings()));
	Item SOULWOOD_TRAPDOOR = register("soulwood_trapdoor", new BlockItem(MalumBlockRegistry.SOULWOOD_TRAPDOOR, settings()));
	Item SOLID_SOULWOOD_TRAPDOOR = register("solid_soulwood_trapdoor", new BlockItem(MalumBlockRegistry.SOLID_SOULWOOD_TRAPDOOR, settings()));

	Item SOULWOOD_PLANKS_BUTTON = register("soulwood_planks_button", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_BUTTON, settings()));
	Item SOULWOOD_PLANKS_PRESSURE_PLATE = register("soulwood_planks_pressure_plate", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_PRESSURE_PLATE, settings()));

	Item SOULWOOD_PLANKS_FENCE = register("soulwood_planks_fence", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_FENCE, settings()));
	Item SOULWOOD_PLANKS_FENCE_GATE = register("soulwood_planks_fence_gate", new BlockItem(MalumBlockRegistry.SOULWOOD_PLANKS_FENCE_GATE, settings()));

	Item SOULWOOD_ITEM_STAND = register("soulwood_item_stand", new BlockItem(MalumBlockRegistry.SOULWOOD_ITEM_STAND, settings()));
	Item SOULWOOD_ITEM_PEDESTAL = register("soulwood_item_pedestal", new BlockItem(MalumBlockRegistry.SOULWOOD_ITEM_PEDESTAL, settings()));

	//Item SOULWOOD_SIGN = register("soulwood_sign", new SignItem(settings().maxCount(16), MalumBlockRegistry.SOULWOOD_SIGN, MalumBlockRegistry.SOULWOOD_WALL_SIGN));
	//Item SOULWOOD_BOAT = register("soulwood_boat", new LodestoneBoatItem(settings().maxCount(1), EntityRegistry.SOULWOOD_BOAT));


	//endregion

	//region spirits
	Item SACRED_SPIRIT = register("sacred_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.SACRED_SPIRIT));
	Item WICKED_SPIRIT = register("wicked_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.WICKED_SPIRIT));
	Item ARCANE_SPIRIT = register("arcane_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.ARCANE_SPIRIT));
	Item ELDRITCH_SPIRIT = register("eldritch_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.ELDRITCH_SPIRIT));
	Item EARTHEN_SPIRIT = register("earthen_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.EARTHEN_SPIRIT));
	Item INFERNAL_SPIRIT = register("infernal_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.INFERNAL_SPIRIT));
	Item AERIAL_SPIRIT = register("aerial_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.AERIAL_SPIRIT));
	Item AQUEOUS_SPIRIT = register("aqueous_spirit", new MalumSpiritItem(settings(), MalumSpiritTypeRegistry.AQUEOUS_SPIRIT));
	//endregion

	//region ores
	Item COPPER_NUGGET = register("copper_nugget", new Item(new Item.Settings()));

	Item COAL_FRAGMENT = register("coal_fragment", new Item(new Item.Settings()));
	Item CHARCOAL_FRAGMENT = register("charcoal_fragment", new Item(new Item.Settings()));

	Item ARCANE_CHARCOAL = register("arcane_charcoal", new Item(new Item.Settings()));
	Item ARCANE_CHARCOAL_FRAGMENT = register("arcane_charcoal_fragment", new Item(new Item.Settings()));
	Item BLOCK_OF_ARCANE_CHARCOAL = register("block_of_arcane_charcoal", new BlockItem(MalumBlockRegistry.BLOCK_OF_ARCANE_CHARCOAL, new Item.Settings()));

	Item BLAZING_QUARTZ_ORE = register("blazing_quartz_ore", new BlockItem(MalumBlockRegistry.BLAZING_QUARTZ_ORE, new Item.Settings()));
	Item BLAZING_QUARTZ = register("blazing_quartz", new Item(new Item.Settings()));
	Item BLAZING_QUARTZ_FRAGMENT = register("blazing_quartz_fragment", new Item(new Item.Settings()));
	Item BLOCK_OF_BLAZING_QUARTZ = register("block_of_blazing_quartz", new BlockItem(MalumBlockRegistry.BLOCK_OF_BLAZING_QUARTZ, new Item.Settings()));



	Item NATURAL_QUARTZ_ORE = register("natural_quartz_ore", new BlockItem(MalumBlockRegistry.NATURAL_QUARTZ_ORE, new Item.Settings()));
	Item DEEPSLATE_QUARTZ_ORE = register("deepslate_quartz_ore", new BlockItem(MalumBlockRegistry.DEEPSLATE_QUARTZ_ORE, new Item.Settings()));
	Item NATURAL_QUARTZ = register("natural_quartz", new AliasedBlockItem(MalumBlockRegistry.NATURAL_QUARTZ_CLUSTER, new Item.Settings()));

	Item BRILLIANT_STONE = register("brilliant_stone", new BlockItem(MalumBlockRegistry.BRILLIANT_STONE, new Item.Settings()));
	Item BRILLIANT_DEEPSLATE = register("brilliant_deepslate", new BlockItem(MalumBlockRegistry.BRILLIANT_DEEPSLATE, new Item.Settings()));
	Item CLUSTER_OF_BRILLIANCE = register("cluster_of_brilliance", new Item(new Item.Settings()));
	Item CRUSHED_BRILLIANCE = register("crushed_brilliance", new Item(new Item.Settings()));
	Item CHUNK_OF_BRILLIANCE = register("chunk_of_brilliance", new BrillianceChunkItem(new Item.Settings().food((new FoodComponent.Builder()).snack().alwaysEdible().build())));
	Item BLOCK_OF_BRILLIANCE = register("block_of_brilliance", new BlockItem(MalumBlockRegistry.BLOCK_OF_BRILLIANCE, new Item.Settings()));

	Item SOULSTONE_ORE = register("soulstone_ore", new BlockItem(MalumBlockRegistry.SOULSTONE_ORE, settings()));
	Item DEEPSLATE_SOULSTONE_ORE = register("deepslate_soulstone_ore", new BlockItem(MalumBlockRegistry.DEEPSLATE_SOULSTONE_ORE, settings()));
	Item RAW_SOULSTONE = register("raw_soulstone", new Item(settings()));
	Item CRUSHED_SOULSTONE = register("crushed_soulstone", new Item(settings()));
	Item BLOCK_OF_RAW_SOULSTONE = register("block_of_raw_soulstone", new BlockItem(MalumBlockRegistry.BLOCK_OF_RAW_SOULSTONE, settings()));
	Item PROCESSED_SOULSTONE = register("processed_soulstone", new Item(settings()));
	Item BLOCK_OF_SOULSTONE = register("block_of_soulstone", new BlockItem(MalumBlockRegistry.BLOCK_OF_SOULSTONE, settings()));
	//endregion

	//region crafting blocks
	Item SPIRIT_ALTAR = register("spirit_altar", new BlockItem(MalumBlockRegistry.SPIRIT_ALTAR, settings()));
	Item SPIRIT_JAR = register("spirit_jar", new SpiritJarItem(MalumBlockRegistry.SPIRIT_JAR, settings()));
	Item SOUL_VIAL = register("soul_vial", new SoulVialItem(MalumBlockRegistry.SOUL_VIAL, settings()));
	Item RUNEWOOD_OBELISK = register("runewood_obelisk", new MultiBlockItem(MalumBlockRegistry.RUNEWOOD_OBELISK, settings(), RunewoodObeliskBlockEntity.STRUCTURE));
	Item BRILLIANT_OBELISK = register("brilliant_obelisk", new MultiBlockItem(MalumBlockRegistry.BRILLIANT_OBELISK, settings(), BrilliantObeliskBlockEntity.STRUCTURE));
	Item SPIRIT_CRUCIBLE = register("spirit_crucible", new MultiBlockItem(MalumBlockRegistry.SPIRIT_CRUCIBLE, settings(), SpiritCrucibleCoreBlockEntity.STRUCTURE));
	Item TWISTED_TABLET = register("twisted_tablet", new BlockItem(MalumBlockRegistry.TWISTED_TABLET, settings()));
	Item SPIRIT_CATALYZER = register("spirit_catalyzer", new MultiBlockItem(MalumBlockRegistry.SPIRIT_CATALYZER, settings(), SpiritCatalyzerCoreBlockEntity.STRUCTURE));
	Item EMITTER_MIRROR = register("emitter_mirror", new BlockItem(MalumBlockRegistry.EMITTER_MIRROR, settings()));
	Item RUNEWOOD_TOTEM_BASE = register("runewood_totem_base", new BlockItem(MalumBlockRegistry.RUNEWOOD_TOTEM_BASE, settings()));
	Item SOULWOOD_TOTEM_BASE = register("soulwood_totem_base", new BlockItem(MalumBlockRegistry.SOULWOOD_TOTEM_BASE, settings()));
	Item ALTERATION_PLINTH = register("alteration_plinth", new BlockItem(MalumBlockRegistry.ALTERATION_PLINTH, settings()));
	Item SOULWOOD_PLINTH = register("soulwood_plinth", new MultiBlockItem(MalumBlockRegistry.SOULWOOD_PLINTH, settings(), PlinthCoreBlockEntity.STRUCTURE));
	Item SOULWOOD_FUSION_PLATE = register("soulwood_fusion_plate", new MultiBlockItem(MalumBlockRegistry.SOULWOOD_FUSION_PLATE, settings(), FusionPlateBlockEntity.STRUCTURE));
	//endregion

	//region materials
	Item ROTTING_ESSENCE = register("rotting_essence", new Item(settings().food((new FoodComponent.Builder()).hunger
(6).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 1), 0.95f).build())));
	Item GRIM_TALC = register("grim_talc", new Item(settings()));
	Item ALCHEMICAL_CALX = register("alchemical_calx", new Item(settings()));
	Item ASTRAL_WEAVE = register("astral_weave", new Item(settings()));
	Item RARE_EARTHS = register("rare_earths", new Item(settings()));
	Item HEX_ASH = register("hex_ash", new Item(settings()));

	Item BLOCK_OF_ROTTING_ESSENCE = register("block_of_rotting_essence", new BlockItem(MalumBlockRegistry.BLOCK_OF_ROTTING_ESSENCE, settings()));
	Item BLOCK_OF_GRIM_TALC = register("block_of_grim_talc", new BlockItem(MalumBlockRegistry.BLOCK_OF_GRIM_TALC, settings()));
	Item BLOCK_OF_ALCHEMICAL_CALX = register("block_of_alchemical_calx", new BlockItem(MalumBlockRegistry.BLOCK_OF_ALCHEMICAL_CALX, settings()));
	Item BLOCK_OF_ASTRAL_WEAVE = register("block_of_astral_weave", new BlockItem(MalumBlockRegistry.BLOCK_OF_ASTRAL_WEAVE, settings()));
	Item BLOCK_OF_RARE_EARTHS = register("block_of_rare_earths", new AliasedBlockItem(MalumBlockRegistry.BLOCK_OF_RARE_EARTHS, new Item.Settings()));
	Item BLOCK_OF_HEX_ASH = register("block_of_hex_ash", new BlockItem(MalumBlockRegistry.BLOCK_OF_HEX_ASH, settings()));
	Item BLOCK_OF_CURSED_GRIT = register("block_of_cursed_grit", new BlockItem(MalumBlockRegistry.BLOCK_OF_CURSED_GRIT, settings()));

	Item SPIRIT_FABRIC = register("spirit_fabric", new Item(settings()));
	Item SPECTRAL_LENS = register("spectral_lens", new Item(settings()));
	Item POPPET = register("poppet", new Item(settings()));
	Item CURSED_GRIT = register("cursed_grit", new Item(settings()));
	Item CORRUPTED_RESONANCE = register("corrupted_resonance", new CorruptResonanceItem(settings()));

	Item HALLOWED_GOLD_INGOT = register("hallowed_gold_ingot", new Item(settings()));
	Item HALLOWED_GOLD_NUGGET = register("hallowed_gold_nugget", new Item(settings()));
	Item BLOCK_OF_HALLOWED_GOLD = register("block_of_hallowed_gold", new BlockItem(MalumBlockRegistry.BLOCK_OF_HALLOWED_GOLD, settings()));
	Item HALLOWED_SPIRIT_RESONATOR = register("hallowed_spirit_resonator", new Item(settings()));

	Item SOUL_STAINED_STEEL_INGOT = register("soul_stained_steel_ingot", new Item(settings()));
	Item SOUL_STAINED_STEEL_NUGGET = register("soul_stained_steel_nugget", new Item(settings()));
	Item BLOCK_OF_SOUL_STAINED_STEEL = register("block_of_soul_stained_steel", new BlockItem(MalumBlockRegistry.BLOCK_OF_SOUL_STAINED_STEEL, settings()));
	Item STAINED_SPIRIT_RESONATOR = register("stained_spirit_resonator", new Item(settings()));

	CrackedImpetusItem CRACKED_IRON_IMPETUS = register("cracked_iron_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem IRON_IMPETUS = register("iron_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_IRON_IMPETUS));
	Item IRON_NODE = register("iron_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_COPPER_IMPETUS = register("cracked_copper_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem COPPER_IMPETUS = register("copper_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_COPPER_IMPETUS));
	Item COPPER_NODE = register("copper_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_GOLD_IMPETUS = register("cracked_gold_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem GOLD_IMPETUS = register("gold_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_GOLD_IMPETUS));
	Item GOLD_NODE = register("gold_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_LEAD_IMPETUS = register("cracked_lead_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem LEAD_IMPETUS = register("lead_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_LEAD_IMPETUS));
	Item LEAD_NODE = register("lead_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_SILVER_IMPETUS = register("cracked_silver_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem SILVER_IMPETUS = register("silver_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_SILVER_IMPETUS));
	Item SILVER_NODE = register("silver_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_ALUMINUM_IMPETUS = register("cracked_aluminum_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem ALUMINUM_IMPETUS = register("aluminum_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ALUMINUM_IMPETUS));
	Item ALUMINUM_NODE = register("aluminum_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_NICKEL_IMPETUS = register("cracked_nickel_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem NICKEL_IMPETUS = register("nickel_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_NICKEL_IMPETUS));
	Item NICKEL_NODE = register("nickel_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_URANIUM_IMPETUS = register("cracked_uranium_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem URANIUM_IMPETUS = register("uranium_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_URANIUM_IMPETUS));
	Item URANIUM_NODE = register("uranium_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_OSMIUM_IMPETUS = register("cracked_osmium_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem OSMIUM_IMPETUS = register("osmium_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_OSMIUM_IMPETUS));
	Item OSMIUM_NODE = register("osmium_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_ZINC_IMPETUS = register("cracked_zinc_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem ZINC_IMPETUS = register("zinc_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ZINC_IMPETUS));
	Item ZINC_NODE = register("zinc_node", new NodeItem(settings()));
	CrackedImpetusItem CRACKED_TIN_IMPETUS = register("cracked_tin_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem TIN_IMPETUS = register("tin_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_TIN_IMPETUS));
	Item TIN_NODE = register("tin_node", new NodeItem(settings()));

	CrackedImpetusItem CRACKED_ALCHEMICAL_IMPETUS = register("cracked_alchemical_impetus", new CrackedImpetusItem(settings()));
	ImpetusItem ALCHEMICAL_IMPETUS = register("alchemical_impetus", new ImpetusItem(settings().maxDamage(100)).setCrackedVariant(CRACKED_ALCHEMICAL_IMPETUS));

	//endregion

	//region ether
	Item ETHER = register("ether", new EtherItem(MalumBlockRegistry.ETHER, settings(), false));
	Item ETHER_TORCH = register("ether_torch", new EtherTorchItem(MalumBlockRegistry.ETHER_TORCH, MalumBlockRegistry.WALL_ETHER_TORCH, settings(), false));
	Item TAINTED_ETHER_BRAZIER = register("tainted_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TAINTED_ETHER_BRAZIER, settings(), false));
	Item TWISTED_ETHER_BRAZIER = register("twisted_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TWISTED_ETHER_BRAZIER, settings(), false));

	//Item ETHER_SCONCE = register("ether_sconce", new EtherSconceItem(MalumBlockRegistry.ETHER_SCONCE, MalumBlockRegistry.WALL_ETHER_SCONCE, SupplementariesCompat.LOADED ? settings() : settings(), false));

	Item IRIDESCENT_ETHER = register("iridescent_ether", new EtherItem(MalumBlockRegistry.IRIDESCENT_ETHER, settings(), true));
	Item IRIDESCENT_ETHER_TORCH = register("iridescent_ether_torch", new EtherTorchItem(MalumBlockRegistry.IRIDESCENT_ETHER_TORCH, MalumBlockRegistry.IRIDESCENT_WALL_ETHER_TORCH, settings(), true));
	Item TAINTED_IRIDESCENT_ETHER_BRAZIER = register("tainted_iridescent_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TAINTED_IRIDESCENT_ETHER_BRAZIER, settings(), true));
	Item TWISTED_IRIDESCENT_ETHER_BRAZIER = register("twisted_iridescent_ether_brazier", new EtherBrazierItem(MalumBlockRegistry.TWISTED_IRIDESCENT_ETHER_BRAZIER, settings(), true));

	//Item IRIDESCENT_ETHER_SCONCE = register("iridescent_ether_sconce", new EtherSconceItem(MalumBlockRegistry.IRIDESCENT_ETHER_SCONCE, MalumBlockRegistry.IRIDESCENT_WALL_ETHER_SCONCE, SupplementariesCompat.LOADED ? settings() : settings(), true));
	//endregion


	//region contents
	Item SPIRIT_POUCH = register("spirit_pouch", new SpiritPouchItem(settings()));
	Item CRUDE_SCYTHE = registerScytheItem("crude_scythe", new MalumScytheItem(ToolMaterials.IRON, 0, 0.1f, settings().maxDamage(350)));
	Item SOUL_STAINED_STEEL_SCYTHE = registerScytheItem("soul_stained_steel_scythe", new MagicScytheItem(SOUL_STAINED_STEEL, -2.5f, 0.1f, 4, settings()));
	Item SOULWOOD_STAVE = register("soulwood_stave", new SoulStaveItem(settings()));

	Item SOUL_STAINED_STEEL_SWORD = register("soul_stained_steel_sword", new MagicSwordItem(SOUL_STAINED_STEEL, -3, 0, 3, settings()));
	Item SOUL_STAINED_STEEL_PICKAXE = register("soul_stained_steel_pickaxe", new MagicPickaxeItem(SOUL_STAINED_STEEL, -2, 0, 2, settings()));
	Item SOUL_STAINED_STEEL_AXE = register("soul_stained_steel_axe", new MagicAxeItem(SOUL_STAINED_STEEL, -3f, 0, 4, settings()));
	Item SOUL_STAINED_STEEL_SHOVEL = register("soul_stained_steel_shovel", new MagicShovelItem(SOUL_STAINED_STEEL, -2, 0, 2, settings()));
	Item SOUL_STAINED_STEEL_HOE = register("soul_stained_steel_hoe", new MagicHoeItem(SOUL_STAINED_STEEL, 0, -1.5f, 1, settings()));
	//Item SOUL_STAINED_STEEL_KNIFE = register("soul_stained_steel_knife", FarmersDelightCompat.LOADED ? FarmersDelightCompat.LoadedOnly.makeMagicKnife() : new Item(settings()));

	Item SOUL_STAINED_STEEL_HELMET = register("soul_stained_steel_helmet", new SoulStainedSteelArmorItem(EquipmentSlot.HEAD, settings()));
	Item SOUL_STAINED_STEEL_CHESTPLATE = register("soul_stained_steel_chestplate", new SoulStainedSteelArmorItem(EquipmentSlot.CHEST, settings()));
	Item SOUL_STAINED_STEEL_LEGGINGS = register("soul_stained_steel_leggings", new SoulStainedSteelArmorItem(EquipmentSlot.LEGS, settings()));
	Item SOUL_STAINED_STEEL_BOOTS = register("soul_stained_steel_boots", new SoulStainedSteelArmorItem(EquipmentSlot.FEET, settings()));

	Item SOUL_HUNTER_CLOAK = register("soul_hunter_cloak", new SoulHunterArmorItem(EquipmentSlot.HEAD, settings()));
	Item SOUL_HUNTER_ROBE = register("soul_hunter_robe", new SoulHunterArmorItem(EquipmentSlot.CHEST, settings()));
	Item SOUL_HUNTER_LEGGINGS = register("soul_hunter_leggings", new SoulHunterArmorItem(EquipmentSlot.LEGS, settings()));
	Item SOUL_HUNTER_BOOTS = register("soul_hunter_boots", new SoulHunterArmorItem(EquipmentSlot.FEET, settings()));

	Item ETHERIC_NITRATE = register("etheric_nitrate", new EthericNitrateItem(settings()));
	Item VIVID_NITRATE = register("vivid_nitrate", new VividNitrateItem(settings()));

	Item TYRVING = register("tyrving", new TyrvingItem(ItemTiers.ItemTierEnum.TYRVING, 0, -0.3f, settings()));

	Item GILDED_RING = register("gilded_ring", new CurioGildedRing(settings()));
	Item GILDED_BELT = register("gilded_belt", new CurioGildedBelt(settings()));
	Item ORNATE_RING = register("ornate_ring", new CurioOrnateRing(settings()));
	Item ORNATE_NECKLACE = register("ornate_necklace", new CurioOrnateNecklace(settings()));

	Item RING_OF_ESOTERIC_SPOILS = register("ring_of_esoteric_spoils", new CurioArcaneSpoilRing(settings()));
	Item RING_OF_CURATIVE_TALENT = register("ring_of_curative_talent", new CurioCurativeRing(settings()));
	Item RING_OF_ARCANE_PROWESS = register("ring_of_arcane_prowess", new CurioRingOfProwess(settings()));
	Item RING_OF_ALCHEMICAL_MASTERY = register("ring_of_alchemical_mastery", new CurioAlchemicalRing(settings()));
	Item RING_OF_DESPERATE_VORACITY = register("ring_of_desperate_voracity", new CurioVeraciousRing(settings()));
	Item RING_OF_THE_HOARDER = register("ring_of_the_hoarder", new CurioHoarderRing(settings()));
	Item RING_OF_THE_DEMOLITIONIST = register("ring_of_the_demolitionist", new CurioDemolitionistRing(settings()));

	Item NECKLACE_OF_THE_MYSTIC_MIRROR = register("necklace_of_the_mystic_mirror", new CurioMirrorNecklace(settings()));
	Item NECKLACE_OF_TIDAL_AFFINITY = register("necklace_of_tidal_affinity", new CurioWaterNecklace(settings()));
	Item NECKLACE_OF_THE_NARROW_EDGE = register("necklace_of_the_narrow_edge", new CurioNarrowNecklace(settings()));
	Item NECKLACE_OF_THE_HIDDEN_BLADE = register("necklace_of_the_hidden_blade", new CurioHiddenBladeNecklace(settings()));
	Item NECKLACE_OF_BLISSFUL_HARMONY = register("necklace_of_blissful_harmony", new CurioHarmonyNecklace(settings()));

	Item BELT_OF_THE_STARVED = register("belt_of_the_starved", new CurioStarvedBelt(settings()));
	Item BELT_OF_THE_PROSPECTOR = register("belt_of_the_prospector", new CurioProspectorBelt(settings()));
	Item BELT_OF_THE_MAGEBANE = register("belt_of_the_magebane", new CurioMagebaneBelt(settings()));
	//endregion

	//region cosmetics

	Item ESOTERIC_SPOOL = register("esoteric_spool", new Item(settings()));
	Item ANCIENT_WEAVE = register("ancient_weave", new AncientWeaveItem(settings()));

	/*TODO pride
	PrideweaveItem ACE_PRIDEWEAVE = register("ace_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem AGENDER_PRIDEWEAVE = register("agender_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem ARO_PRIDEWEAVE = register("aro_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem AROACE_PRIDEWEAVE = register("aroace_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem BI_PRIDEWEAVE = register("bi_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem DEMIBOY_PRIDEWEAVE = register("demiboy_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem DEMIGIRL_PRIDEWEAVE = register("demigirl_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem ENBY_PRIDEWEAVE = register("enby_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem GAY_PRIDEWEAVE = register("gay_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem GENDERFLUID_PRIDEWEAVE = register("genderfluid_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem GENDERQUEER_PRIDEWEAVE = register("genderqueer_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem INTERSEX_PRIDEWEAVE = register("intersex_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem LESBIAN_PRIDEWEAVE = register("lesbian_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem PAN_PRIDEWEAVE = register("pan_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem PLURAL_PRIDEWEAVE = register("plural_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem POLY_PRIDEWEAVE = register("poly_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem PRIDE_PRIDEWEAVE = register("pride_prideweave", new PrideweaveItem(settings()));
	PrideweaveItem TRANS_PRIDEWEAVE = register("trans_prideweave", new PrideweaveItem(settings()));

	 */

	//endregion

	//region hidden items
	Item THE_DEVICE = register("the_device", new BlockItem(MalumBlockRegistry.THE_DEVICE, settings()));
	Item CREATIVE_SCYTHE = register("creative_scythe", new MagicScytheItem(ToolMaterials.IRON, 9993, 9.1f, 999f, settings().maxDamage(-1)));
	Item TOKEN_OF_GRATITUDE = register("token_of_gratitude", new CurioTokenOfGratitude(settings()));
	//endregion



	static QuiltItemSettings settings(){
		return new QuiltItemSettings();
	}

	static <T extends Item> T register(String id, T item) {
		ITEMS.put(new Identifier(Malum.MODID, id), item);
		return item;
	}

	static <T extends MalumScytheItem> T registerScytheItem(String id, T item) {
		SCYTHES.add(item);
		return register(id, item);
	}

	static void init() {
		ITEMS.forEach((id, item) -> Registry.register(Registries.ITEM, id, item));
	}
}
