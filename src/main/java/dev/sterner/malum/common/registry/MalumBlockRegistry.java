package dev.sterner.malum.common.registry;

import com.sammy.lodestone.systems.block.LodestoneLogBlock;
import com.sammy.lodestone.systems.block.sign.LodestoneStandingSignBlock;
import com.sammy.lodestone.systems.block.sign.LodestoneWallSignBlock;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.block.*;
import dev.sterner.malum.common.block.alteration_plinth.AlterationPlinthBlock;
import dev.sterner.malum.common.block.blight.*;
import dev.sterner.malum.common.block.ether.*;
import dev.sterner.malum.common.block.fusion_plate.FusionPlateComponentBlock;
import dev.sterner.malum.common.block.fusion_plate.FusionPlateCoreBlock;
import dev.sterner.malum.common.block.mirror.EmitterMirrorBlock;
import dev.sterner.malum.common.block.obelisk.BrillianceObeliskCoreBlock;
import dev.sterner.malum.common.block.obelisk.ObeliskComponentBlock;
import dev.sterner.malum.common.block.obelisk.RunewoodObeliskCoreBlock;
import dev.sterner.malum.common.block.sapling.RunewoodSaplingGenerator;
import dev.sterner.malum.common.block.sapling.SoulwoodSaplingGenerator;
import dev.sterner.malum.common.block.spirit_altar.SpiritAltarBlock;
import dev.sterner.malum.common.block.spirit_crucible.SpiritCatalyzerComponentBlock;
import dev.sterner.malum.common.block.spirit_crucible.SpiritCatalyzerCoreBlock;
import dev.sterner.malum.common.block.spirit_crucible.SpiritCrucibleComponentBlock;
import dev.sterner.malum.common.block.spirit_crucible.SpiritCrucibleCoreBlock;
import dev.sterner.malum.common.block.storage.*;
import dev.sterner.malum.common.block.tablet.TwistedTabletBlock;
import dev.sterner.malum.common.block.totem.TotemBaseBlock;
import dev.sterner.malum.common.block.totem.TotemPoleBlock;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MalumBlockRegistry {

	public static Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();


	public static QuiltBlockSettings TAINTED_ROCK_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).sounds(MalumSoundRegistry.TAINTED_ROCK).strength(1.25F, 9.0F);
	}

	public static QuiltBlockSettings TWISTED_ROCK_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).sounds(MalumSoundRegistry.TWISTED_ROCK).strength(1.25F, 9.0F);
	}

	public static QuiltBlockSettings SOULSTONE_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.DARK_RED).strength(5.0F, 3.0F).sounds(MalumSoundRegistry.SOULSTONE);
	}

	public static QuiltBlockSettings DEEPSLATE_SOULSTONE_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.DARK_RED).strength(7.0F, 6.0F).sounds(MalumSoundRegistry.DEEPSLATE_SOULSTONE);
	}

	public static QuiltBlockSettings BLAZE_QUARTZ_ORE_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.DARK_RED).strength(3.0F, 3.0F).sounds(MalumSoundRegistry.BLAZING_QUARTZ_ORE);
	}

	public static QuiltBlockSettings BLAZE_QUARTZ_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.RED).strength(5.0F, 6.0F).sounds(MalumSoundRegistry.BLAZING_QUARTZ_BLOCK);
	}

	public static QuiltBlockSettings ARCANE_CHARCOAL_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.BLACK).strength(5.0F, 6.0F).sounds(MalumSoundRegistry.ARCANE_CHARCOAL_BLOCK);
	}

	public static QuiltBlockSettings RUNEWOOD_PROPERTIES() {
		return QuiltBlockSettings.of(Material.WOOD, MapColor.YELLOW).sounds(BlockSoundGroup.WOOD).strength(1.75F, 4.0F);
	}

	public static QuiltBlockSettings RUNEWOOD_PLANTS_PROPERTIES() {
		return QuiltBlockSettings.of(Material.PLANT, MapColor.YELLOW).noCollision().nonOpaque().sounds(BlockSoundGroup.GRASS).breakInstantly();
	}

	public static QuiltBlockSettings RUNEWOOD_LEAVES_PROPERTIES() {
		return QuiltBlockSettings.of(Material.LEAVES, MapColor.YELLOW).strength(0.2F).ticksRandomly().nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).sounds(MalumSoundRegistry.RUNEWOOD_LEAVES);
	}

	public static QuiltBlockSettings SOULWOOD_PROPERTIES() {
		return QuiltBlockSettings.of(Material.WOOD, MapColor.PURPLE).sounds(MalumSoundRegistry.SOULWOOD).strength(1.75F, 4.0F);
	}

	public static QuiltBlockSettings BLIGHT_PROPERTIES() {
		return QuiltBlockSettings.of(Material.MOSS_BLOCK, MapColor.PURPLE).sounds(MalumSoundRegistry.BLIGHTED_EARTH).strength(0.7f);
	}

	public static QuiltBlockSettings BLIGHT_PLANTS_PROPERTIES() {
		return QuiltBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.PURPLE).noCollision().nonOpaque().sounds(MalumSoundRegistry.BLIGHTED_FOLIAGE).breakInstantly();
	}

	public static QuiltBlockSettings SOULWOOD_LEAVES_PROPERTIES() {
		return QuiltBlockSettings.of(Material.LEAVES, MapColor.PURPLE).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).sounds(MalumSoundRegistry.SOULWOOD_LEAVES);
	}

	public static QuiltBlockSettings ETHER_BLOCK_PROPERTIES() {
		return QuiltBlockSettings.of(Material.WOOL, MapColor.YELLOW).sounds(MalumSoundRegistry.ETHER).noCollision().breakInstantly().luminance((b) -> 14);
	}

	public static QuiltBlockSettings HALLOWED_GOLD_PROPERTIES() {
		return QuiltBlockSettings.of(Material.METAL, MapColor.YELLOW).sounds(MalumSoundRegistry.HALLOWED_GOLD).nonOpaque().strength(2F, 16.0F);
	}

	public static QuiltBlockSettings SOUL_STAINED_STEEL_BLOCK_PROPERTIES() {
		return QuiltBlockSettings.of(Material.METAL, MapColor.PURPLE).sounds(MalumSoundRegistry.SOUL_STAINED_STEEL).strength(5f, 64.0f);
	}

	public static QuiltBlockSettings SPIRIT_JAR_PROPERTIES() {
		return QuiltBlockSettings.of(Material.GLASS, MapColor.BLUE).strength(0.5f, 64f).sounds(MalumSoundRegistry.HALLOWED_GOLD).nonOpaque();
	}

	public static QuiltBlockSettings SOUL_VIAL_PROPERTIES() {
		return QuiltBlockSettings.of(Material.GLASS, MapColor.BLUE).strength(0.75f, 64f).sounds(MalumSoundRegistry.SOUL_STAINED_STEEL).nonOpaque();
	}

	public static QuiltBlockSettings BRILLIANCE_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).strength(3f, 3f).sounds(BlockSoundGroup.STONE);
	}

	public static QuiltBlockSettings DEEPSLATE_BRILLIANCE_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE);
	}

	public static QuiltBlockSettings NATURAL_QUARTZ_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).strength(4f, 3f).sounds(MalumSoundRegistry.NATURAL_QUARTZ);
	}

	public static QuiltBlockSettings DEEPSLATE_QUARTZ_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.DEEPSLATE_GRAY).strength(6F, 3.0F).sounds(MalumSoundRegistry.DEEPSLATE_QUARTZ);
	}

	public static QuiltBlockSettings NATURAL_QUARTZ_CLUSTER_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).strength(1.5F).sounds(MalumSoundRegistry.QUARTZ_CLUSTER);
	}

	public static QuiltBlockSettings RARE_EARTH_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.GRAY).strength(25f, 9999f).sounds(MalumSoundRegistry.RARE_EARTH);
	}

	public static QuiltBlockSettings MOTE_OF_MANA_PROPERTIES() {
		return QuiltBlockSettings.of(Material.STONE, MapColor.CYAN).strength(25f, 9999f).sounds(MalumSoundRegistry.RARE_EARTH);
	}

	public static final Block SPIRIT_ALTAR = register("spirit_altar", new SpiritAltarBlock<>(RUNEWOOD_PROPERTIES().nonOpaque()));
	public static final Block SPIRIT_JAR = register("spirit_jar", new SpiritJarBlock<>(SPIRIT_JAR_PROPERTIES().nonOpaque()));
	public static final Block ALTERATION_PLINTH = register("alteration_plinth", new AlterationPlinthBlock<>(SOULWOOD_PROPERTIES().nonOpaque()));

	public static final Block SOUL_VIAL = register("soul_vial", new SoulVialBlock<>(SOUL_VIAL_PROPERTIES().nonOpaque()));
	public static final Block EMITTER_MIRROR = register("emitter_mirror", new EmitterMirrorBlock<>(HALLOWED_GOLD_PROPERTIES().nonOpaque()));

	public static final Block MOTE_OF_MANA = register("mote_of_mana", new Block(TAINTED_ROCK_PROPERTIES().nonOpaque()));

	public static final Block TWISTED_TABLET = register("twisted_tablet", new TwistedTabletBlock<>(TAINTED_ROCK_PROPERTIES().nonOpaque()));

	public static final Block RUNEWOOD_OBELISK = register("runewood_obelisk", new RunewoodObeliskCoreBlock(RUNEWOOD_PROPERTIES().nonOpaque()));
	public static final Block RUNEWOOD_OBELISK_COMPONENT = register("runewood_obelisk_component", new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().nonOpaque(), MalumItemRegistry.RUNEWOOD_OBELISK));

	public static final Block BRILLIANT_OBELISK = register("brilliant_obelisk", new BrillianceObeliskCoreBlock(RUNEWOOD_PROPERTIES().nonOpaque()));
	public static final Block BRILLIANT_OBELISK_COMPONENT = register("brilliant_obelisk_component", new ObeliskComponentBlock(RUNEWOOD_PROPERTIES().nonOpaque(), MalumItemRegistry.BRILLIANT_OBELISK));

	public static final Block SPIRIT_CRUCIBLE = register("spirit_crucible", new SpiritCrucibleCoreBlock<>(TAINTED_ROCK_PROPERTIES().nonOpaque()));
	public static final Block SPIRIT_CRUCIBLE_COMPONENT = register("spirit_crucible_component", new SpiritCrucibleComponentBlock(TAINTED_ROCK_PROPERTIES().nonOpaque()));

	public static final Block SPIRIT_CATALYZER = register("spirit_catalyzer", new SpiritCatalyzerCoreBlock<>(TAINTED_ROCK_PROPERTIES().nonOpaque()));
	public static final Block SPIRIT_CATALYZER_COMPONENT = register("spirit_catalyzer_component", new SpiritCatalyzerComponentBlock(TAINTED_ROCK_PROPERTIES().nonOpaque(), MalumItemRegistry.SPIRIT_CATALYZER));

	public static final Block SOULWOOD_PLINTH = register("soulwood_plinth", new PlinthCoreBlock<>(SOULWOOD_PROPERTIES().nonOpaque()));
	public static final Block SOULWOOD_PLINTH_COMPONENT = register("soulwood_plinth_component", new PlinthComponentBlock(SOULWOOD_PROPERTIES().nonOpaque(), MalumItemRegistry.SOULWOOD_PLINTH));

	public static final Block SOULWOOD_FUSION_PLATE = register("soulwood_fusion_plate", new FusionPlateCoreBlock<>(SOULWOOD_PROPERTIES().nonOpaque()));
	public static final Block SOULWOOD_FUSION_PLATE_COMPONENT = register("soulwood_fusion_plate_component", new FusionPlateComponentBlock(SOULWOOD_PROPERTIES().nonOpaque(), MalumItemRegistry.SOULWOOD_FUSION_PLATE));

	public static final Block RUNEWOOD_TOTEM_BASE = register("runewood_totem_base", new TotemBaseBlock<>(RUNEWOOD_PROPERTIES().nonOpaque(), false));
	public static final Block RUNEWOOD_TOTEM_POLE = register("runewood_totem_pole", new TotemPoleBlock<>(MalumBlockRegistry.RUNEWOOD_LOG, RUNEWOOD_PROPERTIES().nonOpaque(), false));

	public static final Block SOULWOOD_TOTEM_BASE = register("soulwood_totem_base", new TotemBaseBlock<>(SOULWOOD_PROPERTIES().nonOpaque(), true));
	public static final Block SOULWOOD_TOTEM_POLE = register("soulwood_totem_pole", new TotemPoleBlock<>(MalumBlockRegistry.SOULWOOD_LOG, SOULWOOD_PROPERTIES().nonOpaque(), true));
	//endregion

	//region tainted rock
	public static final Block TAINTED_ROCK = register("tainted_rock", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TAINTED_ROCK = register("smooth_tainted_rock", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TAINTED_ROCK = register("polished_tainted_rock", new Block(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_BRICKS = register("tainted_rock_bricks", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_BRICKS = register("cracked_tainted_rock_bricks", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TAINTED_ROCK_BRICKS = register("small_tainted_rock_bricks", new Block(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_TILES = register("tainted_rock_tiles", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_TILES = register("cracked_tainted_rock_tiles", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS = register("cracked_small_tainted_rock_bricks", new Block(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_COLUMN = register("tainted_rock_column", new PillarBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_COLUMN_CAP = register("tainted_rock_column_cap", new PillarBlock(TAINTED_ROCK_PROPERTIES()));

	public static final Block CUT_TAINTED_ROCK = register("cut_tainted_rock", new Block(TAINTED_ROCK_PROPERTIES()));
	public static final Block CHISELED_TAINTED_ROCK = register("chiseled_tainted_rock", new Block(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_SLAB = register("tainted_rock_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TAINTED_ROCK_SLAB = register("smooth_tainted_rock_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TAINTED_ROCK_SLAB = register("polished_tainted_rock_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_BRICKS_SLAB = register("tainted_rock_bricks_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_BRICKS_SLAB = register("cracked_tainted_rock_bricks_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TAINTED_ROCK_BRICKS_SLAB = register("small_tainted_rock_bricks_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_TILES_SLAB = register("tainted_rock_tiles_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_TILES_SLAB = register("cracked_tainted_rock_tiles_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_SLAB = register("cracked_small_tainted_rock_bricks_slab", new SlabBlock(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_STAIRS = register("tainted_rock_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TAINTED_ROCK_STAIRS = register("smooth_tainted_rock_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TAINTED_ROCK_STAIRS = register("polished_tainted_rock_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_BRICKS_STAIRS = register("tainted_rock_bricks_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_tainted_rock_bricks_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("small_tainted_rock_bricks_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_TILES_STAIRS = register("tainted_rock_tiles_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_TILES_STAIRS = register("cracked_tainted_rock_tiles_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_STAIRS = register("cracked_small_tainted_rock_bricks_stairs", new StairsBlock(TAINTED_ROCK.getDefaultState(), TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_PRESSURE_PLATE = register("tainted_rock_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, TAINTED_ROCK_PROPERTIES(), SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));
	public static final Block TAINTED_ROCK_BUTTON = register("tainted_rock_button", new AbstractButtonBlock(TAINTED_ROCK_PROPERTIES(), 1,false, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));

	public static final Block TAINTED_ROCK_WALL = register("tainted_rock_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_BRICKS_WALL = register("tainted_rock_bricks_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TAINTED_ROCK_BRICKS_WALL = register("small_tainted_rock_bricks_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_BRICKS_WALL = register("cracked_tainted_rock_bricks_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block TAINTED_ROCK_TILES_WALL = register("tainted_rock_tiles_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TAINTED_ROCK_BRICKS_WALL = register("cracked_small_tainted_rock_bricks_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TAINTED_ROCK_TILES_WALL = register("cracked_tainted_rock_tiles_wall", new WallBlock(TAINTED_ROCK_PROPERTIES()));

	public static final Block TAINTED_ROCK_ITEM_STAND = register("tainted_rock_item_stand", new ItemStandBlock<>(TAINTED_ROCK_PROPERTIES().nonOpaque()));
	public static final Block TAINTED_ROCK_ITEM_PEDESTAL = register("tainted_rock_item_pedestal", new ItemPedestalBlock<>(TAINTED_ROCK_PROPERTIES().nonOpaque()));
	//endregion

	//region twisted rock
	public static final Block TWISTED_ROCK = register("twisted_rock", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TWISTED_ROCK = register("smooth_twisted_rock", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TWISTED_ROCK = register("polished_twisted_rock", new Block(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_BRICKS = register("twisted_rock_bricks", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_BRICKS = register("cracked_twisted_rock_bricks", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TWISTED_ROCK_BRICKS = register("small_twisted_rock_bricks", new Block(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_TILES = register("twisted_rock_tiles", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_TILES = register("cracked_twisted_rock_tiles", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS = register("cracked_small_twisted_rock_bricks", new Block(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_COLUMN = register("twisted_rock_column", new PillarBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_COLUMN_CAP = register("twisted_rock_column_cap", new PillarBlock(TWISTED_ROCK_PROPERTIES()));

	public static final Block CUT_TWISTED_ROCK = register("cut_twisted_rock", new Block(TWISTED_ROCK_PROPERTIES()));
	public static final Block CHISELED_TWISTED_ROCK = register("chiseled_twisted_rock", new Block(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_SLAB = register("twisted_rock_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TWISTED_ROCK_SLAB = register("smooth_twisted_rock_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TWISTED_ROCK_SLAB = register("polished_twisted_rock_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_BRICKS_SLAB = register("twisted_rock_bricks_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_BRICKS_SLAB = register("cracked_twisted_rock_bricks_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TWISTED_ROCK_BRICKS_SLAB = register("small_twisted_rock_bricks_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_TILES_SLAB = register("twisted_rock_tiles_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_TILES_SLAB = register("cracked_twisted_rock_tiles_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_SLAB = register("cracked_small_twisted_rock_bricks_slab", new SlabBlock(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_STAIRS = register("twisted_rock_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block SMOOTH_TWISTED_ROCK_STAIRS = register("smooth_twisted_rock_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block POLISHED_TWISTED_ROCK_STAIRS = register("polished_twisted_rock_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_BRICKS_STAIRS = register("twisted_rock_bricks_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_twisted_rock_bricks_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("small_twisted_rock_bricks_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_TILES_STAIRS = register("twisted_rock_tiles_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_TILES_STAIRS = register("cracked_twisted_rock_tiles_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_STAIRS = register("cracked_small_twisted_rock_bricks_stairs", new StairsBlock(TWISTED_ROCK.getDefaultState(), TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_PRESSURE_PLATE = register("twisted_rock_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, TWISTED_ROCK_PROPERTIES(), SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));
	public static final Block TWISTED_ROCK_BUTTON = register("twisted_rock_button",  new AbstractButtonBlock(TWISTED_ROCK_PROPERTIES(), 1,false, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF,SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));


	public static final Block TWISTED_ROCK_WALL = register("twisted_rock_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_BRICKS_WALL = register("twisted_rock_bricks_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block SMALL_TWISTED_ROCK_BRICKS_WALL = register("small_twisted_rock_bricks_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_BRICKS_WALL = register("cracked_twisted_rock_bricks_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block TWISTED_ROCK_TILES_WALL = register("twisted_rock_tiles_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_SMALL_TWISTED_ROCK_BRICKS_WALL = register("cracked_small_twisted_rock_bricks_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));
	public static final Block CRACKED_TWISTED_ROCK_TILES_WALL = register("cracked_twisted_rock_tiles_wall", new WallBlock(TWISTED_ROCK_PROPERTIES()));

	public static final Block TWISTED_ROCK_ITEM_STAND = register("twisted_rock_item_stand", new ItemStandBlock<>(TWISTED_ROCK_PROPERTIES().nonOpaque()));
	public static final Block TWISTED_ROCK_ITEM_PEDESTAL = register("twisted_rock_item_pedestal", new ItemPedestalBlock<>(TWISTED_ROCK_PROPERTIES().nonOpaque()));
	//endregion

	//region runewood
	public static final Block RUNEWOOD_SAPLING = register("runewood_sapling", new MalumSaplingBlock(new RunewoodSaplingGenerator(), RUNEWOOD_PLANTS_PROPERTIES().ticksRandomly()));
	public static final Block RUNEWOOD_LEAVES = register("runewood_leaves", new MalumLeavesBlock(RUNEWOOD_LEAVES_PROPERTIES(), new Color(175, 65, 48), new Color(251, 193, 76)));

	public static final Block STRIPPED_RUNEWOOD_LOG = register("stripped_runewood_log", new PillarBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_LOG = register("runewood_log", new MalumLogBlock(STRIPPED_RUNEWOOD_LOG, RUNEWOOD_PROPERTIES(), false));
	public static final Block STRIPPED_RUNEWOOD = register("stripped_runewood", new PillarBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD = register("runewood", new LodestoneLogBlock(STRIPPED_RUNEWOOD, RUNEWOOD_PROPERTIES()));

	public static final Block REVEALED_RUNEWOOD_LOG = register("revealed_runewood_log", new SapFilledLogBlock(RUNEWOOD_PROPERTIES(), STRIPPED_RUNEWOOD_LOG, MalumItemRegistry.HOLY_SAP, MalumSpiritTypeRegistry.INFERNAL_SPIRIT.getColor()));
	public static final Block EXPOSED_RUNEWOOD_LOG = register("exposed_runewood_log", new LodestoneLogBlock(REVEALED_RUNEWOOD_LOG, RUNEWOOD_PROPERTIES()));

	public static final Block RUNEWOOD_PLANKS = register("runewood_planks", new Block(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_PLANKS_SLAB = register("runewood_planks_slab", new SlabBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_PLANKS_STAIRS = register("runewood_planks_stairs", new StairsBlock(RUNEWOOD_PLANKS.getDefaultState(), RUNEWOOD_PROPERTIES()));

	public static final Block VERTICAL_RUNEWOOD_PLANKS = register("vertical_runewood_planks", new Block(RUNEWOOD_PROPERTIES()));
	public static final Block VERTICAL_RUNEWOOD_PLANKS_SLAB = register("vertical_runewood_planks_slab", new SlabBlock(RUNEWOOD_PROPERTIES()));
	public static final Block VERTICAL_RUNEWOOD_PLANKS_STAIRS = register("vertical_runewood_planks_stairs", new StairsBlock(VERTICAL_RUNEWOOD_PLANKS.getDefaultState(), RUNEWOOD_PROPERTIES()));

	public static final Block RUNEWOOD_PANEL = register("runewood_panel", new Block(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_PANEL_SLAB = register("runewood_panel_slab", new SlabBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_PANEL_STAIRS = register("runewood_panel_stairs", new StairsBlock(RUNEWOOD_PANEL.getDefaultState(), RUNEWOOD_PROPERTIES()));

	public static final Block RUNEWOOD_TILES = register("runewood_tiles", new Block(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_TILES_SLAB = register("runewood_tiles_slab", new SlabBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_TILES_STAIRS = register("runewood_tiles_stairs", new StairsBlock(RUNEWOOD_TILES.getDefaultState(), RUNEWOOD_PROPERTIES()));

	public static final Block CUT_RUNEWOOD_PLANKS = register("cut_runewood_planks", new Block(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_BEAM = register("runewood_beam", new PillarBlock(RUNEWOOD_PROPERTIES()));

	public static final Block RUNEWOOD_DOOR = register("runewood_door", new DoorBlock(RUNEWOOD_PROPERTIES().nonOpaque(), SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));
	public static final Block RUNEWOOD_TRAPDOOR = register("runewood_trapdoor", new TrapdoorBlock(RUNEWOOD_PROPERTIES().nonOpaque(), SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));
	public static final Block SOLID_RUNEWOOD_TRAPDOOR = register("solid_runewood_trapdoor", new TrapdoorBlock(RUNEWOOD_PROPERTIES().nonOpaque(),  SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));

	public static final Block RUNEWOOD_PLANKS_BUTTON = register("runewood_planks_button", new AbstractButtonBlock(RUNEWOOD_PROPERTIES(),1 , false, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));
	public static final Block RUNEWOOD_PLANKS_PRESSURE_PLATE = register("runewood_planks_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, RUNEWOOD_PROPERTIES(),  SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));

	public static final Block RUNEWOOD_PLANKS_FENCE = register("runewood_planks_fence", new FenceBlock(RUNEWOOD_PROPERTIES()));
	public static final Block RUNEWOOD_PLANKS_FENCE_GATE = register("runewood_planks_fence_gate", new FenceGateBlock(RUNEWOOD_PROPERTIES(),  SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));

	public static final Block RUNEWOOD_ITEM_STAND = register("runewood_item_stand", new ItemStandBlock<>(RUNEWOOD_PROPERTIES().nonOpaque()));
	public static final Block RUNEWOOD_ITEM_PEDESTAL = register("runewood_item_pedestal", new WoodItemPedestalBlock<>(RUNEWOOD_PROPERTIES().nonOpaque()));

	//public static final Block RUNEWOOD_SIGN = register("runewood_sign", new LodestoneStandingSignBlock(RUNEWOOD_PROPERTIES().nonOpaque().noCollision(), MalumWoodTypeRegistry.RUNEWOOD));
	//public static final Block RUNEWOOD_WALL_SIGN = register("runewood_wall_sign", new LodestoneWallSignBlock(RUNEWOOD_PROPERTIES().nonOpaque().noCollision(), MalumWoodTypeRegistry.RUNEWOOD));
	//endregion

	//region soulwood
	public static final Block SOULWOOD_GROWTH = register("soulwood_growth", new SoulwoodGrowthBlock(new SoulwoodSaplingGenerator(),BLIGHT_PLANTS_PROPERTIES().ticksRandomly()));
	public static final Block SOULWOOD_LEAVES = register("soulwood_leaves", new MalumLeavesBlock(SOULWOOD_LEAVES_PROPERTIES(), new Color(152, 6, 45), new Color(224, 30, 214)));

	public static final Block STRIPPED_SOULWOOD_LOG = register("stripped_soulwood_log", new PillarBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_LOG = register("soulwood_log", new SoulwoodLogBlock(STRIPPED_SOULWOOD_LOG,SOULWOOD_PROPERTIES(), true));
	public static final Block STRIPPED_SOULWOOD = register("stripped_soulwood", new PillarBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD = register("soulwood", new SoulwoodBlock(STRIPPED_SOULWOOD, SOULWOOD_PROPERTIES()));

	public static final Block REVEALED_SOULWOOD_LOG = register("revealed_soulwood_log", new SapFilledSoulwoodLogBlock(SOULWOOD_PROPERTIES(), STRIPPED_SOULWOOD_LOG, MalumItemRegistry.UNHOLY_SAP, new Color(214, 46, 83)));
	public static final Block EXPOSED_SOULWOOD_LOG = register("exposed_soulwood_log", new SoulwoodBlock(REVEALED_SOULWOOD_LOG, SOULWOOD_PROPERTIES()));

	public static final Block SOULWOOD_PLANKS = register("soulwood_planks", new Block(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_PLANKS_SLAB = register("soulwood_planks_slab", new SlabBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_PLANKS_STAIRS = register("soulwood_planks_stairs", new StairsBlock(SOULWOOD_PLANKS.getDefaultState(), SOULWOOD_PROPERTIES()));

	public static final Block VERTICAL_SOULWOOD_PLANKS = register("vertical_soulwood_planks", new Block(SOULWOOD_PROPERTIES()));
	public static final Block VERTICAL_SOULWOOD_PLANKS_SLAB = register("vertical_soulwood_planks_slab", new SlabBlock(SOULWOOD_PROPERTIES()));
	public static final Block VERTICAL_SOULWOOD_PLANKS_STAIRS = register("vertical_soulwood_planks_stairs", new StairsBlock(VERTICAL_SOULWOOD_PLANKS.getDefaultState(), SOULWOOD_PROPERTIES()));

	public static final Block SOULWOOD_PANEL = register("soulwood_panel", new Block(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_PANEL_SLAB = register("soulwood_panel_slab", new SlabBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_PANEL_STAIRS = register("soulwood_panel_stairs", new StairsBlock(SOULWOOD_PANEL.getDefaultState(), SOULWOOD_PROPERTIES()));

	public static final Block SOULWOOD_TILES = register("soulwood_tiles", new Block(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_TILES_SLAB = register("soulwood_tiles_slab", new SlabBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_TILES_STAIRS = register("soulwood_tiles_stairs", new StairsBlock(SOULWOOD_TILES.getDefaultState(), SOULWOOD_PROPERTIES()));

	public static final Block CUT_SOULWOOD_PLANKS = register("cut_soulwood_planks", new Block(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_BEAM = register("soulwood_beam", new PillarBlock(SOULWOOD_PROPERTIES()));

	public static final Block SOULWOOD_DOOR = register("soulwood_door", new DoorBlock(SOULWOOD_PROPERTIES().nonOpaque(), SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF));
	public static final Block SOULWOOD_TRAPDOOR = register("soulwood_trapdoor", new TrapdoorBlock(SOULWOOD_PROPERTIES().nonOpaque(), SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN));
	public static final Block SOLID_SOULWOOD_TRAPDOOR = register("solid_soulwood_trapdoor", new TrapdoorBlock(SOULWOOD_PROPERTIES().nonOpaque(), SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF));

	public static final Block SOULWOOD_PLANKS_BUTTON = register("soulwood_planks_button", new AbstractButtonBlock(SOULWOOD_PROPERTIES(), 1, true, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF));
	public static final Block SOULWOOD_PLANKS_PRESSURE_PLATE = register("soulwood_planks_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, SOULWOOD_PROPERTIES(), SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON));

	public static final Block SOULWOOD_PLANKS_FENCE = register("soulwood_planks_fence", new FenceBlock(SOULWOOD_PROPERTIES()));
	public static final Block SOULWOOD_PLANKS_FENCE_GATE = register("soulwood_planks_fence_gate", new FenceGateBlock(SOULWOOD_PROPERTIES(), SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF));

	public static final Block SOULWOOD_ITEM_STAND = register("soulwood_item_stand", new ItemStandBlock<>(SOULWOOD_PROPERTIES().nonOpaque()));
	public static final Block SOULWOOD_ITEM_PEDESTAL = register("soulwood_item_pedestal", new WoodItemPedestalBlock<>(SOULWOOD_PROPERTIES().nonOpaque()));

	//public static final Block SOULWOOD_SIGN = register("soulwood_sign", new LodestoneStandingSignBlock(SOULWOOD_PROPERTIES().nonOpaque().noCollision(), MalumWoodTypeRegistry.SOULWOOD));
	//public static final Block SOULWOOD_WALL_SIGN = register("soulwood_wall_sign", new LodestoneWallSignBlock(SOULWOOD_PROPERTIES().nonOpaque().noCollision(), MalumWoodTypeRegistry.SOULWOOD));
	//endregion

	//region blight
	public static final Block BLIGHTED_EARTH = register("blighted_earth", new BlightedSoilBlock(BLIGHT_PROPERTIES()));
	public static final Block BLIGHTED_SOIL = register("blighted_soil", new BlightedSoilBlock(BLIGHT_PROPERTIES()));
	public static final Block BLIGHTED_WEED = register("blighted_weed", new BlightedGrassBlock(BLIGHT_PLANTS_PROPERTIES()));
	public static final Block BLIGHTED_TUMOR = register("blighted_tumor", new BlightedGrassBlock(BLIGHT_PLANTS_PROPERTIES()));
	public static final Block BLIGHTED_SOULWOOD = register("blighted_soulwood", new BlightedSoulwoodBlock(SOULWOOD_PROPERTIES()));
	//endregion

	//region ether
	public static final Block ETHER_TORCH = register("ether_torch", new EtherTorchBlock<>(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block WALL_ETHER_TORCH = register("wall_ether_torch", new EtherWallTorchBlock<>(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block ETHER = register("ether", new EtherBlock<>(ETHER_BLOCK_PROPERTIES()));
	public static final Block TAINTED_ETHER_BRAZIER = register("tainted_ether_brazier", new EtherBrazierBlock<>(TAINTED_ROCK_PROPERTIES().luminance((b) -> 14).nonOpaque()));
	public static final Block TWISTED_ETHER_BRAZIER = register("twisted_ether_brazier", new EtherBrazierBlock<>(TWISTED_ROCK_PROPERTIES().luminance((b) -> 14).nonOpaque()));

	public static final Block ETHER_SCONCE = register("ether_sconce", new EtherSconceBlock<>(RUNEWOOD_PROPERTIES().sounds(BlockSoundGroup.LANTERN).noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block WALL_ETHER_SCONCE = register("wall_ether_sconce", new EtherWallSconceBlock<>(RUNEWOOD_PROPERTIES().sounds(BlockSoundGroup.LANTERN).noCollision().breakInstantly().luminance((b) -> 14)));

	public static final Block IRIDESCENT_ETHER_TORCH = register("iridescent_ether_torch", new EtherTorchBlock<>(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block IRIDESCENT_WALL_ETHER_TORCH = register("iridescent_wall_ether_torch", new EtherWallTorchBlock<>(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block IRIDESCENT_ETHER = register("iridescent_ether", new EtherBlock<>(ETHER_BLOCK_PROPERTIES()));
	public static final Block TAINTED_IRIDESCENT_ETHER_BRAZIER = register("tainted_iridescent_ether_brazier", new EtherBrazierBlock<>(TAINTED_ROCK_PROPERTIES().luminance((b) -> 14).nonOpaque()));
	public static final Block TWISTED_IRIDESCENT_ETHER_BRAZIER = register("twisted_iridescent_ether_brazier", new EtherBrazierBlock<>(TWISTED_ROCK_PROPERTIES().luminance((b) -> 14).nonOpaque()));

	public static final Block IRIDESCENT_ETHER_SCONCE = register("iridescent_ether_sconce", new EtherSconceBlock<>(RUNEWOOD_PROPERTIES().sounds(BlockSoundGroup.LANTERN).noCollision().breakInstantly().luminance((b) -> 14)));
	public static final Block IRIDESCENT_WALL_ETHER_SCONCE = register("iridescent_wall_ether_sconce", new EtherWallSconceBlock<>(RUNEWOOD_PROPERTIES().sounds(BlockSoundGroup.LANTERN).noCollision().breakInstantly().luminance((b) -> 14)));

	//endregion
	public static final Block BLAZING_TORCH = register("blazing_torch", new TorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14), ParticleTypes.FLAME));
	public static final Block WALL_BLAZING_TORCH = register("wall_blazing_torch", new WallTorchBlock(RUNEWOOD_PROPERTIES().noCollision().breakInstantly().luminance((b) -> 14), ParticleTypes.FLAME));
	//public static final Block BLAZING_SCONCE = register("blazing_sconce", SupplementariesCompat.LOADED ? SupplementariesCompat.LoadedOnly.makeBlazingSconce() : new WallTorchBlock(RUNEWOOD_PROPERTIES().sounds(SoundType.LANTERN).noCollision().breakInstantly().luminance((b) -> 14), ParticleTypes.FLAME));
	//public static final Block WALL_BLAZING_SCONCE = register("wall_blazing_sconce", SupplementariesCompat.LOADED ? SupplementariesCompat.LoadedOnly.makeBlazingWallSconce() : new WallTorchBlock(RUNEWOOD_PROPERTIES().sounds(SoundType.LANTERN).noCollision().breakInstantly().luminance((b) -> 14), ParticleTypes.FLAME));

	public static final Block BLOCK_OF_ARCANE_CHARCOAL = register("block_of_arcane_charcoal", new Block(ARCANE_CHARCOAL_PROPERTIES()));

	public static final Block BLAZING_QUARTZ_ORE = register("blazing_quartz_ore", new ExperienceDroppingBlock(BLAZE_QUARTZ_ORE_PROPERTIES().luminance((b) -> 6), UniformIntProvider.create(4, 7)));
	public static final Block BLOCK_OF_BLAZING_QUARTZ = register("block_of_blazing_quartz", new Block(BLAZE_QUARTZ_PROPERTIES().luminance((b) -> 14)));

	public static final Block NATURAL_QUARTZ_ORE = register("natural_quartz_ore", new ExperienceDroppingBlock(NATURAL_QUARTZ_PROPERTIES(), UniformIntProvider.create(1, 4)));
	public static final Block DEEPSLATE_QUARTZ_ORE = register("deepslate_quartz_ore", new ExperienceDroppingBlock(DEEPSLATE_QUARTZ_PROPERTIES(), UniformIntProvider.create(2, 5)));
	public static final Block NATURAL_QUARTZ_CLUSTER = register("natural_quartz_cluster", new AmethystClusterBlock(6, 3, NATURAL_QUARTZ_CLUSTER_PROPERTIES()));

	public static final Block BLOCK_OF_RARE_EARTHS = register("block_of_rare_earths", new ExperienceDroppingBlock(RARE_EARTH_PROPERTIES(), UniformIntProvider.create(10, 100)));

	public static final Block BRILLIANT_STONE = register("brilliant_stone", new ExperienceDroppingBlock(BRILLIANCE_PROPERTIES(), UniformIntProvider.create(14, 18)));
	public static final Block BRILLIANT_DEEPSLATE = register("brilliant_deepslate", new ExperienceDroppingBlock(DEEPSLATE_BRILLIANCE_PROPERTIES(), UniformIntProvider.create(16, 26)));
	public static final Block BLOCK_OF_BRILLIANCE = register("block_of_brilliance", new Block(BRILLIANCE_PROPERTIES()));

	public static final Block SOULSTONE_ORE = register("soulstone_ore", new ExperienceDroppingBlock(SOULSTONE_PROPERTIES()));
	public static final Block DEEPSLATE_SOULSTONE_ORE = register("deepslate_soulstone_ore", new ExperienceDroppingBlock(DEEPSLATE_SOULSTONE_PROPERTIES().strength(6f, 4f)));


	public static final Block BLOCK_OF_RAW_SOULSTONE = register("block_of_raw_soulstone", new Block(SOULSTONE_PROPERTIES()));
	public static final Block BLOCK_OF_SOULSTONE = register("block_of_soulstone", new Block(SOULSTONE_PROPERTIES()));

	public static final Block BLOCK_OF_HALLOWED_GOLD = register("block_of_hallowed_gold", new Block(HALLOWED_GOLD_PROPERTIES()));
	public static final Block BLOCK_OF_SOUL_STAINED_STEEL = register("block_of_soul_stained_steel", new Block(SOUL_STAINED_STEEL_BLOCK_PROPERTIES()));

	public static final Block THE_DEVICE = register("the_device", new TheDevice(TAINTED_ROCK_PROPERTIES()));

	//    public static final Block BLOCK_OF_RARE_EARTHS = register("block_of_rare_earths", new OreBlock(RARE_EARTH_PROPERTIES(), UniformIntProvider.create(10, 100)));
	public static final Block BLOCK_OF_ROTTING_ESSENCE = register("block_of_rotting_essence", new Block(AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_BLOCK)));
	public static final Block BLOCK_OF_GRIM_TALC = register("block_of_grim_talc", new Block(AbstractBlock.Settings.copy(Blocks.BONE_BLOCK)));
	public static final Block BLOCK_OF_ALCHEMICAL_CALX = register("block_of_alchemical_calx", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block BLOCK_OF_ASTRAL_WEAVE = register("block_of_astral_weave", new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_WOOL)));
	public static final Block BLOCK_OF_HEX_ASH = register("block_of_hex_ash", new Block(AbstractBlock.Settings.copy(Blocks.PURPLE_CONCRETE_POWDER)));
	public static final Block BLOCK_OF_CURSED_GRIT = register("block_of_cursed_grit", new Block(AbstractBlock.Settings.copy(Blocks.RED_CONCRETE_POWDER)));


	public static <T extends Block> T register(String id, T block) {
		BLOCKS.put(new Identifier(Malum.MODID, id), block);
		return block;
	}

	public static void init() {
		BLOCKS.forEach((id, block) -> Registry.register(Registries.BLOCK, id, block));
	}
}
