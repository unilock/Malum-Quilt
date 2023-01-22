package dev.sterner.malum.client.screen.codex;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sammy.lodestone.handlers.ScreenParticleHandler;
import com.sammy.lodestone.setup.LodestoneShaders;
import com.sammy.lodestone.systems.recipe.IRecipeComponent;
import com.sammy.lodestone.systems.rendering.ExtendedShader;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import dev.sterner.malum.Malum;
import dev.sterner.malum.api.event.ProgressionBookEntriesSetEvent;
import dev.sterner.malum.client.screen.codex.objects.*;
import dev.sterner.malum.client.screen.codex.page.*;
import dev.sterner.malum.common.rite.MalumRiteType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.ShaderProgram;
import net.minecraft.client.util.ColorUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.sammy.lodestone.systems.rendering.particle.screen.base.ScreenParticle.RenderOrder.BEFORE_TOOLTIPS;
import static dev.sterner.malum.Malum.id;
import static dev.sterner.malum.common.registry.MalumObjects.*;
import static net.minecraft.client.util.ColorUtil.ARGB32.getArgb;
import static net.minecraft.item.Items.*;
import static org.lwjgl.opengl.GL11C.GL_SCISSOR_TEST;

public class ProgressionBookScreen extends Screen {
	public enum BookTheme {
		DEFAULT, EASY_READING
	}

	public static final VFXBuilders.ScreenVFXBuilder BUILDER = VFXBuilders.createScreen().setPosTexDefaultFormat();

	public static final Identifier FRAME_TEXTURE = id("textures/gui/book/frame.png");
	public static final Identifier FADE_TEXTURE = id("textures/gui/book/fade.png");

	public static final Identifier BACKGROUND_TEXTURE = id("textures/gui/book/background.png");

	public int bookWidth = 378;
	public int bookHeight = 250;
	public int bookInsideWidth = 344;
	public int bookInsideHeight = 218;

	public final int parallax_width = 1024;
	public final int parallax_height = 2560;
	public static ProgressionBookScreen screen;
	public float xOffset;
	public float yOffset;
	public float cachedXOffset;
	public float cachedYOffset;
	public boolean ignoreNextMouseInput;

	public static List<BookEntry> ENTRIES = new ArrayList<>();
	public static List<BookObject> OBJECTS = new ArrayList<>();

	protected ProgressionBookScreen() {
		super(Text.translatable("malum.gui.book.title"));
		client = MinecraftClient.getInstance();
		setupEntries();
		ProgressionBookEntriesSetEvent.EVENT.invoker().addExtraEntry(ENTRIES);
		setupObjects();
	}

	public static void setupEntries() {
		ENTRIES.clear();
		Item EMPTY = ItemStack.EMPTY.getItem();

		ENTRIES.add(new BookEntry(
				"introduction", ENCYCLOPEDIA_ARCANA, 0, 0)
				.setObjectSupplier(ImportantEntryObject::new)
				.addPage(new HeadlineTextPage("introduction", "introduction.1"))
				.addPage(new TextPage("introduction.2"))
				.addPage(new TextPage("introduction.3"))
				.addPage(new TextPage("introduction.4"))
				.addPage(new TextPage("introduction.5"))
		);

		ENTRIES.add(new BookEntry(
				"spirit_crystals", 0, 1)
				.setObjectSupplier((e, x, y) -> new IconObject(e, id("textures/gui/book/icons/soul_shard.png"), x, y))
				.addPage(new HeadlineTextPage("spirit_crystals", "spirit_crystals.1"))
				.addPage(new TextPage("spirit_crystals.2"))
				.addPage(new TextPage("spirit_crystals.3"))
		);

		ENTRIES.add(new BookEntry(
				"runewood", RUNEWOOD_SAPLING.asItem(), 1, 2)
				.addPage(new HeadlineTextItemPage("runewood", "runewood.1", RUNEWOOD_SAPLING.asItem()))
				.addPage(new HeadlineTextPage("runewood.arcane_charcoal", "runewood.arcane_charcoal.1"))
				.addPage(new SmeltingBookPage(RUNEWOOD_LOG.asItem(), ARCANE_CHARCOAL.asItem()))
				.addPage(CraftingBookPage.fullPage(BLOCK_OF_ARCANE_CHARCOAL.asItem(), ARCANE_CHARCOAL.asItem()))
				.addPage(new HeadlineTextPage("runewood.holy_sap", "runewood.holy_sap.1"))
				.addPage(new SmeltingBookPage(HOLY_SAP, HOLY_SYRUP))
				.addPage(new CraftingBookPage(new ItemStack(HOLY_SAPBALL, 3), Items.SLIME_BALL, HOLY_SAP))
		);
		/*

		ENTRIES.add(new BookEntry(
				"natural_quartz", NATURAL_QUARTZ, 3, 1)
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextItemPage("natural_quartz", "natural_quartz.1", NATURAL_QUARTZ))
		);

		 */

		ENTRIES.add(new BookEntry(
				"blazing_quartz", BLAZING_QUARTZ, 4, 2)
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextItemPage("blazing_quartz", "blazing_quartz.1", BLAZING_QUARTZ))
				.addPage(CraftingBookPage.fullPage(BLOCK_OF_BLAZING_QUARTZ.asItem(), BLAZING_QUARTZ.asItem()))
		);

		ENTRIES.add(new BookEntry(
				"brilliance", CLUSTER_OF_BRILLIANCE, -3, 1)
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextItemPage("brilliance", "brilliance.1", CLUSTER_OF_BRILLIANCE))
				.addPage(new TextPage("brilliance.2"))
				.addPage(CraftingBookPage.fullPage(BLOCK_OF_BRILLIANCE.asItem(), CLUSTER_OF_BRILLIANCE))
				.addPage(new SmeltingBookPage(new ItemStack(CLUSTER_OF_BRILLIANCE), new ItemStack(CHUNK_OF_BRILLIANCE, 2)))
		);

		ENTRIES.add(new BookEntry(
				"rare_earths", RARE_EARTHS, -4, 2)
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextItemPage("rare_earths", "rare_earths", RARE_EARTHS))
		);

		ENTRIES.add(new BookEntry(
				"soulstone", PROCESSED_SOULSTONE, -1, 2)
				.addPage(new HeadlineTextItemPage("soulstone", "soulstone.1", PROCESSED_SOULSTONE))
				.addPage(new TextPage("soulstone.2"))
				.addPage(new SmeltingBookPage(new ItemStack(RAW_SOULSTONE), new ItemStack(PROCESSED_SOULSTONE, 2)))
				.addPage(CraftingBookPage.fullPage(BLOCK_OF_SOULSTONE.asItem(), PROCESSED_SOULSTONE))
				.addPage(CraftingBookPage.fullPage(BLOCK_OF_RAW_SOULSTONE.asItem(), RAW_SOULSTONE))
		);

		ENTRIES.add(new BookEntry(
				"scythes", CRUDE_SCYTHE, 0, 3)
				.addPage(new HeadlineTextPage("scythes", "scythes.1"))
				.addPage(CraftingBookPage.scythePage(CRUDE_SCYTHE, Items.IRON_INGOT, PROCESSED_SOULSTONE))
				.addPage(new TextPage("scythes.2"))
				.addPage(new HeadlineTextPage("scythes.enchanting", "scythes.enchanting.1"))
				.addPage(new HeadlineTextPage("scythes.enchanting.haunted", "scythes.enchanting.haunted.1"))
				.addPage(new HeadlineTextPage("scythes.enchanting.spirit_plunder", "scythes.enchanting.spirit_plunder.1"))
				.addPage(new HeadlineTextPage("scythes.enchanting.rebound", "scythes.enchanting.rebound.1"))
		);

		ENTRIES.add(new BookEntry(
				"spirit_infusion", SPIRIT_ALTAR.asItem(), 0, 5)
				.setObjectSupplier(ImportantEntryObject::new)
				.addPage(new HeadlineTextPage("spirit_infusion", "spirit_infusion.1"))
				.addPage(new CraftingBookPage(SPIRIT_ALTAR.asItem(), AIR, PROCESSED_SOULSTONE, AIR, GOLD_INGOT, RUNEWOOD_PLANKS.asItem(), GOLD_INGOT, RUNEWOOD_PLANKS.asItem(), RUNEWOOD_PLANKS.asItem(), RUNEWOOD_PLANKS.asItem()))
				.addPage(new TextPage("spirit_infusion.2"))
				.addPage(new TextPage("spirit_infusion.3"))
				.addPage(CraftingBookPage.itemPedestalPage(RUNEWOOD_ITEM_PEDESTAL.asItem(), RUNEWOOD_PLANKS.asItem(), RUNEWOOD_PLANKS_SLAB.asItem()))
				.addPage(CraftingBookPage.itemStandPage(RUNEWOOD_ITEM_STAND.asItem(), RUNEWOOD_PLANKS.asItem(), RUNEWOOD_PLANKS_SLAB.asItem()))
				.addPage(new HeadlineTextPage("spirit_infusion.hex_ash", "spirit_infusion.hex_ash.1"))
				.addPage(SpiritInfusionPage.fromOutput(HEX_ASH))
		);



		ENTRIES.add(new BookEntry(
				"esoteric_reaping", ROTTING_ESSENCE, 0, 6)
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextPage("esoteric_reaping", "esoteric_reaping.1"))
				.addPage(new TextPage("esoteric_reaping.2"))
				.addPage(new TextPage("esoteric_reaping.3"))
				.addPage(new HeadlineTextItemPage("esoteric_reaping.rotting_essence", "esoteric_reaping.rotting_essence.1", ROTTING_ESSENCE))
				.addPage(new HeadlineTextItemPage("esoteric_reaping.grim_talc", "esoteric_reaping.grim_talc.1", GRIM_TALC))
				.addPage(new HeadlineTextItemPage("esoteric_reaping.astral_weave", "esoteric_reaping.astral_weave.1", ASTRAL_WEAVE))
				.addPage(new HeadlineTextItemPage("esoteric_reaping.calx", "esoteric_reaping.calx.1", ALCHEMICAL_CALX))
		);

		ENTRIES.add(new BookEntry(
				"primary_arcana", SACRED_SPIRIT, -2, 4)
				.addPage(new HeadlineTextItemPage("primary_arcana.sacred", "primary_arcana.sacred.1", SACRED_SPIRIT))
				.addPage(new TextPage("primary_arcana.sacred.2"))
				.addPage(new HeadlineTextItemPage("primary_arcana.wicked", "primary_arcana.wicked.1", WICKED_SPIRIT))
				.addPage(new TextPage("primary_arcana.wicked.2"))
				.addPage(new HeadlineTextItemPage("primary_arcana.arcane", "primary_arcana.arcane.1", ARCANE_SPIRIT))
				.addPage(new TextPage("primary_arcana.arcane.2"))
				.addPage(new TextPage("primary_arcana.arcane.3"))
		);

		ENTRIES.add(new BookEntry(
				"elemental_arcana", EARTHEN_SPIRIT, 2, 4)
				.addPage(new HeadlineTextItemPage("elemental_arcana.aerial", "elemental_arcana.aerial.1", AERIAL_SPIRIT))
				.addPage(new TextPage("elemental_arcana.aerial.2"))
				.addPage(new HeadlineTextItemPage("elemental_arcana.earthen", "elemental_arcana.earthen.1", EARTHEN_SPIRIT))
				.addPage(new TextPage("elemental_arcana.earthen.2"))
				.addPage(new HeadlineTextItemPage("elemental_arcana.infernal", "elemental_arcana.infernal.1", INFERNAL_SPIRIT))
				.addPage(new TextPage("elemental_arcana.infernal.2"))
				.addPage(new HeadlineTextItemPage("elemental_arcana.aqueous", "elemental_arcana.aqueous.1", AQUEOUS_SPIRIT))
				.addPage(new TextPage("elemental_arcana.aqueous.2"))
		);

		ENTRIES.add(new BookEntry(
				"eldritch_arcana", ELDRITCH_SPIRIT, 0, 7)
				.addPage(new HeadlineTextItemPage("eldritch_arcana", "eldritch_arcana.1", ELDRITCH_SPIRIT))
				.addPage(new TextPage("eldritch_arcana.2"))
		);

		ENTRIES.add(new BookEntry(
				"spirit_stones", TAINTED_ROCK.asItem(), 3, 6)
				.addPage(new HeadlineTextPage("spirit_stones.tainted_rock", "spirit_stones.tainted_rock.1"))
				.addPage(SpiritInfusionPage.fromOutput(TAINTED_ROCK.asItem()))
				.addPage(CraftingBookPage.itemPedestalPage(TAINTED_ROCK_ITEM_PEDESTAL.asItem(), TAINTED_ROCK.asItem(), TAINTED_ROCK_SLAB.asItem()))
				.addPage(CraftingBookPage.itemStandPage(TAINTED_ROCK_ITEM_STAND.asItem(), TAINTED_ROCK.asItem(), TAINTED_ROCK_SLAB.asItem()))
				.addPage(new HeadlineTextPage("spirit_stones.twisted_rock", "spirit_stones.twisted_rock.1"))
				.addPage(SpiritInfusionPage.fromOutput(TWISTED_ROCK.asItem()))
				.addPage(CraftingBookPage.itemPedestalPage(TWISTED_ROCK_ITEM_PEDESTAL.asItem(), TWISTED_ROCK.asItem(), TWISTED_ROCK_SLAB.asItem()))
				.addPage(CraftingBookPage.itemStandPage(TWISTED_ROCK_ITEM_STAND.asItem(), TWISTED_ROCK.asItem(), TWISTED_ROCK_SLAB.asItem()))
		);
		ENTRIES.add(new BookEntry(
				"ether", ETHER.asItem(), 5, 6)
				.addPage(new HeadlineTextPage("ether", "ether.1"))
				.addPage(SpiritInfusionPage.fromOutput(ETHER.asItem()))
				.addPage(new TextPage("ether.2"))
				.addPage(new CraftingBookPage(ETHER_TORCH.asItem(), EMPTY, EMPTY, EMPTY, EMPTY, ETHER.asItem(), EMPTY, EMPTY, STICK, EMPTY))
				.addPage(new CraftingBookPage(TAINTED_ETHER_BRAZIER.asItem(), EMPTY, EMPTY, EMPTY, TAINTED_ROCK.asItem(), ETHER.asItem(), TAINTED_ROCK.asItem(), STICK, TAINTED_ROCK.asItem(), STICK))
				.addPage(new CraftingBookPage(TWISTED_ETHER_BRAZIER.asItem(), EMPTY, EMPTY, EMPTY, TWISTED_ROCK.asItem(), ETHER.asItem(), TWISTED_ROCK.asItem(), STICK, TWISTED_ROCK.asItem(), STICK))
				.addPage(new HeadlineTextPage("ether.iridescent", "ether.iridescent.1"))
				.addPage(new TextPage("ether.iridescent.2"))
				.addPage(SpiritInfusionPage.fromOutput(IRIDESCENT_ETHER.asItem()))
				.addPage(new CraftingBookPage(IRIDESCENT_ETHER_TORCH.asItem(), EMPTY, EMPTY, EMPTY, EMPTY, IRIDESCENT_ETHER.asItem(), EMPTY, EMPTY, STICK, EMPTY))
				.addPage(new CraftingBookPage(TAINTED_IRIDESCENT_ETHER_BRAZIER.asItem(), EMPTY, EMPTY, EMPTY, TAINTED_ROCK.asItem(), IRIDESCENT_ETHER.asItem(), TAINTED_ROCK.asItem(), STICK, TAINTED_ROCK.asItem(), STICK))
				.addPage(new CraftingBookPage(TWISTED_IRIDESCENT_ETHER_BRAZIER.asItem(), EMPTY, EMPTY, EMPTY, TWISTED_ROCK.asItem(), IRIDESCENT_ETHER.asItem(), TWISTED_ROCK.asItem(), STICK, TWISTED_ROCK.asItem(), STICK))
		);

		ENTRIES.add(new BookEntry(
				"spirit_fabric", SPIRIT_FABRIC, 4, 5)
				.addPage(new HeadlineTextPage("spirit_fabric", "spirit_fabric.1"))
				.addPage(SpiritInfusionPage.fromOutput(SPIRIT_FABRIC))
				.addPage(new HeadlineTextPage("spirit_fabric.pouch", "spirit_fabric.pouch.1"))
				.addPage(new CraftingBookPage(SPIRIT_POUCH, EMPTY, STRING, EMPTY, SPIRIT_FABRIC, SOUL_SAND, SPIRIT_FABRIC, EMPTY, SPIRIT_FABRIC, EMPTY))
		);

		ENTRIES.add(new BookEntry(
				"soulhunter_gear", SOUL_HUNTER_CLOAK, 4, 7)
				.addPage(new HeadlineTextPage("soulhunter_gear", "soulhunter_gear.1"))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_HUNTER_CLOAK))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_HUNTER_ROBE))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_HUNTER_LEGGINGS))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_HUNTER_BOOTS))
		);


		ENTRIES.add(new BookEntry(
				"soul_something", 3, 8)
				.setObjectSupplier((e, x, y) -> new IconObject(e, id("textures/gui/book/icons/soul_blade.png"), x, y))
		);

		ENTRIES.add(new BookEntry(
				"spirit_focusing", SPIRIT_CRUCIBLE.asItem(), 7, 6)
				.addPage(new HeadlineTextItemPage("spirit_focusing", "spirit_focusing.1", SPIRIT_CRUCIBLE.asItem()))
				.addPage(new TextPage("spirit_focusing.2"))
				.addPage(SpiritInfusionPage.fromOutput(SPIRIT_CRUCIBLE.asItem()))
				.addPage(SpiritInfusionPage.fromOutput(ALCHEMICAL_IMPETUS))
		);

		ENTRIES.add(new BookEntry(
				"focus_ashes", GUNPOWDER, 6, 5)
				.addPage(new HeadlineTextPage("focus_ashes", "focus_ashes.1"))
				.addPage(SpiritCruciblePage.fromOutput(GUNPOWDER))
				.addPage(SpiritCruciblePage.fromOutput(GLOWSTONE_DUST))
				.addPage(SpiritCruciblePage.fromOutput(REDSTONE))
		);

		ENTRIES.add(new BookEntry(
				"focus_metals", IRON_NODE, 8, 7)
				.addPage(new HeadlineTextItemPage("focus_metals", "focus_metals.1", IRON_NODE))
				.addPage(new TextPage("focus_metals.2"))
				.addPage(SpiritInfusionPage.fromOutput(IRON_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(IRON_NODE))
				.addPage(SpiritInfusionPage.fromOutput(GOLD_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(GOLD_NODE))
				.addPage(SpiritInfusionPage.fromOutput(COPPER_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(COPPER_NODE))
				.addPage(SpiritInfusionPage.fromOutput(LEAD_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(LEAD_NODE))
				.addPage(SpiritInfusionPage.fromOutput(SILVER_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(SILVER_NODE))
				.addPage(SpiritInfusionPage.fromOutput(ALUMINUM_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(ALUMINUM_NODE))
				.addPage(SpiritInfusionPage.fromOutput(NICKEL_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(NICKEL_NODE))
				.addPage(SpiritInfusionPage.fromOutput(URANIUM_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(URANIUM_NODE))
				.addPage(SpiritInfusionPage.fromOutput(OSMIUM_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(OSMIUM_NODE))
				.addPage(SpiritInfusionPage.fromOutput(ZINC_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(ZINC_NODE))
				.addPage(SpiritInfusionPage.fromOutput(TIN_IMPETUS))
				.addPage(SpiritCruciblePage.fromOutput(TIN_NODE))
		);

		ENTRIES.add(new BookEntry(
				"focus_crystals", QUARTZ, 9, 5)
				.addPage(new HeadlineTextPage("focus_crystals", "focus_crystals.1"))
				.addPage(SpiritCruciblePage.fromOutput(QUARTZ))
				.addPage(SpiritCruciblePage.fromOutput(AMETHYST_SHARD))
				.addPage(SpiritCruciblePage.fromOutput(BLAZING_QUARTZ))
				.addPage(SpiritCruciblePage.fromOutput(PRISMARINE))
		);

		ENTRIES.add(new BookEntry(
				"crucible_acceleration", SPIRIT_CATALYZER.asItem(), 7, 4)
				.addPage(new HeadlineTextPage("crucible_acceleration", "crucible_acceleration.1"))
				.addPage(new TextPage("crucible_acceleration.2"))
				.addPage(new TextPage("crucible_acceleration.3"))
				.addPage(SpiritInfusionPage.fromOutput(SPIRIT_CATALYZER.asItem()))
		);

		ENTRIES.add(new BookEntry(
				"arcane_restoration", TWISTED_TABLET.asItem(), 7, 8)
				.addPage(new HeadlineTextPage("arcane_restoration", "arcane_restoration.1"))
				.addPage(new TextPage("arcane_restoration.2"))
				.addPage(SpiritInfusionPage.fromOutput(TWISTED_TABLET.asItem()))
				.addPage(SpiritRepairPage.fromInput(CRACKED_ALCHEMICAL_IMPETUS))
				.addPage(SpiritRepairPage.fromInput(CRACKED_COPPER_IMPETUS))
				.addPage(SpiritRepairPage.fromInput(WOODEN_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(STONE_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(IRON_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(DIAMOND_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(GOLDEN_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(NETHERITE_PICKAXE))
				.addPage(new TextPage("arcane_restoration.3"))
				.addPage(SpiritRepairPage.fromInput(SOUL_STAINED_STEEL_PICKAXE))
				.addPage(SpiritRepairPage.fromInput(SOUL_STAINED_STEEL_SCYTHE))
				.addPage(SpiritRepairPage.fromInput(SOUL_HUNTER_BOOTS))
		);

		ENTRIES.add(new BookEntry(
				"spirit_metals", SOUL_STAINED_STEEL_INGOT, -3, 6)
				.addPage(new HeadlineTextItemPage("spirit_metals.hallowed_gold", "spirit_metals.hallowed_gold.1", HALLOWED_GOLD_INGOT))
				.addPage(new TextPage("spirit_metals.hallowed_gold.2"))
				.addPage(SpiritInfusionPage.fromOutput(HALLOWED_GOLD_INGOT))
				.addPage(CraftingBookPage.resonatorPage(HALLOWED_SPIRIT_RESONATOR, QUARTZ, HALLOWED_GOLD_INGOT, RUNEWOOD_PLANKS.asItem()))
				.addPage(new HeadlineTextPage("spirit_metals.hallowed_gold.spirit_jar", "spirit_metals.hallowed_gold.spirit_jar.1"))
				.addPage(new CraftingBookPage(SPIRIT_JAR.asItem(), GLASS_PANE, HALLOWED_GOLD_INGOT, GLASS_PANE, GLASS_PANE, EMPTY, GLASS_PANE, GLASS_PANE, GLASS_PANE, GLASS_PANE))
				.addPage(new HeadlineTextItemPage("spirit_metals.soulstained_steel", "spirit_metals.soulstained_steel.1", SOUL_STAINED_STEEL_INGOT))
				.addPage(new TextPage("spirit_metals.soulstained_steel.2"))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_INGOT))
				.addPage(CraftingBookPage.resonatorPage(STAINED_SPIRIT_RESONATOR, QUARTZ, SOUL_STAINED_STEEL_INGOT, RUNEWOOD_PLANKS.asItem()))
				.addPage(CraftingBookPage.toolPage(SOUL_STAINED_STEEL_PICKAXE, SOUL_STAINED_STEEL_INGOT))
				.addPage(CraftingBookPage.toolPage(SOUL_STAINED_STEEL_AXE, SOUL_STAINED_STEEL_INGOT))
				.addPage(CraftingBookPage.toolPage(SOUL_STAINED_STEEL_HOE, SOUL_STAINED_STEEL_INGOT))
				.addPage(CraftingBookPage.toolPage(SOUL_STAINED_STEEL_SHOVEL, SOUL_STAINED_STEEL_INGOT))
				.addPage(CraftingBookPage.toolPage(SOUL_STAINED_STEEL_SWORD, SOUL_STAINED_STEEL_INGOT))
				//.addModCompatPage(new CraftingBookPage(SOUL_STAINED_STEEL_KNIFE, EMPTY, EMPTY, EMPTY, EMPTY, SOUL_STAINED_STEEL_INGOT, EMPTY, STICK), "farmersdelight")
		);

		ENTRIES.add(new BookEntry(
				"soulstained_scythe", SOUL_STAINED_STEEL_SCYTHE, -4, 5)
				.addPage(new HeadlineTextPage("soulstained_scythe", "soulstained_scythe.1"))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_SCYTHE))
		);

		ENTRIES.add(new BookEntry(
				"soulstained_armor", SOUL_STAINED_STEEL_HELMET, -4, 7)
				.addPage(new HeadlineTextPage("soulstained_armor", "soulstained_armor.1"))
				.addPage(new TextPage("soulstained_armor.2"))
				.addPage(new TextPage("soulstained_armor.3"))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_HELMET))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_CHESTPLATE))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_LEGGINGS))
				.addPage(SpiritInfusionPage.fromOutput(SOUL_STAINED_STEEL_BOOTS))
		);

		ENTRIES.add(new BookEntry(
				"soul_ward", -3, 8)
				.setObjectSupplier((e, x, y) -> new IconObject(e, id("textures/gui/book/icons/soul_ward.png"), x, y))
		);

		ENTRIES.add(new BookEntry(
				"spirit_trinkets", ORNATE_RING, -5, 6)
				.addPage(new HeadlineTextPage("spirit_trinkets", "spirit_trinkets.1"))
				.addPage(new TextPage("spirit_trinkets.2"))
				.addPage(CraftingBookPage.ringPage(GILDED_RING, LEATHER, HALLOWED_GOLD_INGOT))
				.addPage(new CraftingBookPage(GILDED_BELT, LEATHER, LEATHER, LEATHER, HALLOWED_GOLD_INGOT, PROCESSED_SOULSTONE, HALLOWED_GOLD_INGOT, EMPTY, HALLOWED_GOLD_INGOT, EMPTY))
				.addPage(CraftingBookPage.ringPage(ORNATE_RING, LEATHER, SOUL_STAINED_STEEL_INGOT))
				.addPage(new CraftingBookPage(ORNATE_NECKLACE, EMPTY, STRING, EMPTY, STRING, EMPTY, STRING, EMPTY, SOUL_STAINED_STEEL_INGOT, EMPTY))
		);

		ENTRIES.add(new BookEntry(
				"reactive_trinkets", RING_OF_ALCHEMICAL_MASTERY, -7, 6)
				.addPage(new HeadlineTextPage("reactive_trinkets.ring_of_alchemical_mastery", "reactive_trinkets.ring_of_alchemical_mastery.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_ALCHEMICAL_MASTERY))
				.addPage(new HeadlineTextPage("reactive_trinkets.ring_of_curative_talent", "reactive_trinkets.ring_of_curative_talent.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_CURATIVE_TALENT))
				.addPage(new HeadlineTextPage("reactive_trinkets.ring_of_prowess", "reactive_trinkets.ring_of_prowess.1"))
				.addPage(new TextPage("reactive_trinkets.ring_of_prowess.2"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_ARCANE_PROWESS))
		);

		ENTRIES.add(new BookEntry(
				"ring_of_esoteric_spoils", RING_OF_ESOTERIC_SPOILS, -9, 5)
				.addPage(new HeadlineTextPage("ring_of_esoteric_spoils", "ring_of_esoteric_spoils.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_ESOTERIC_SPOILS))
		);

		ENTRIES.add(new BookEntry(
				"belt_of_the_starved", BELT_OF_THE_STARVED, -8, 7)
				.addPage(new HeadlineTextPage("belt_of_the_starved", "belt_of_the_starved.1"))
				.addPage(new TextPage("belt_of_the_starved.2"))
				.addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_STARVED))
				.addPage(new HeadlineTextPage("belt_of_the_starved.ring_of_desperate_voracity", "belt_of_the_starved.ring_of_desperate_voracity.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_DESPERATE_VORACITY))
		);

		ENTRIES.add(new BookEntry(
				"necklace_of_the_narrow_edge", NECKLACE_OF_THE_NARROW_EDGE, -7, 8)
				.addPage(new HeadlineTextPage("necklace_of_the_narrow_edge", "necklace_of_the_narrow_edge.1"))
				.addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_NARROW_EDGE))
		);

		ENTRIES.add(new BookEntry(
				"belt_of_the_prospector", BELT_OF_THE_PROSPECTOR, -6, 5)
				.addPage(new HeadlineTextPage("belt_of_the_prospector", "belt_of_the_prospector.1"))
				.addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_PROSPECTOR))
				.addPage(new HeadlineTextPage("belt_of_the_prospector.ring_of_the_hoarder", "belt_of_the_prospector.ring_of_the_hoarder.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_THE_HOARDER))
		);

//        ENTRIES.add(new BookEntry(
//            "necklace_of_elemental_shielding", GILDED_BELT, -7, 4)
//            .addPage(new HeadlineTextPage("necklace_of_elemental_shielding", "necklace_of_elemental_shielding.1"))
//            .addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_TIDAL_AFFINITY))
//        );

		ENTRIES.add(new BookEntry(
				"necklace_of_the_mystic_mirror", NECKLACE_OF_THE_MYSTIC_MIRROR, 6, 12)
				.addPage(new HeadlineTextPage("necklace_of_the_mystic_mirror", "necklace_of_the_mystic_mirror.1"))
				.addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_MYSTIC_MIRROR))
		);

		ENTRIES.add(new BookEntry(
				"mirror_magic", SPECTRAL_LENS, 6, 10)
				.setObjectSupplier(ImportantEntryObject::new)
				.addPage(new HeadlineTextPage("mirror_magic", "mirror_magic.1"))
				.addPage(SpiritInfusionPage.fromOutput(SPECTRAL_LENS))
		);

		ENTRIES.add(new BookEntry(
				"voodoo_magic", POPPET, -6, 10)
				.setObjectSupplier(ImportantEntryObject::new)
				.addPage(new HeadlineTextPage("voodoo_magic", "voodoo_magic.1"))
				.addPage(SpiritInfusionPage.fromOutput(POPPET))
		);

		ENTRIES.add(new BookEntry(
				"altar_acceleration", RUNEWOOD_OBELISK.asItem(), -1, 8)
				.addPage(new HeadlineTextPage("altar_acceleration.runewood_obelisk", "altar_acceleration.runewood_obelisk.1"))
				.addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_OBELISK.asItem()))
				.addPage(new HeadlineTextPage("altar_acceleration.brilliant_obelisk", "altar_acceleration.brilliant_obelisk.1"))
				.addPage(SpiritInfusionPage.fromOutput(BRILLIANT_OBELISK.asItem()))
		);

		ENTRIES.add(new BookEntry(
				"totem_magic", RUNEWOOD_TOTEM_BASE.asItem(), 0, 9)
				.setObjectSupplier(ImportantEntryObject::new)
				.addPage(new HeadlineTextItemPage("totem_magic", "totem_magic.1", RUNEWOOD_TOTEM_BASE.asItem()))
				.addPage(new TextPage("totem_magic.2"))
				.addPage(new TextPage("totem_magic.3"))
				.addPage(new TextPage("totem_magic.4"))
				.addPage(new TextPage("totem_magic.5"))
				.addPage(SpiritInfusionPage.fromOutput(RUNEWOOD_TOTEM_BASE.asItem()))
		);
/*TODO
		ENTRIES.add(new BookEntry(
				"sacred_rite", -2, 10)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.SACRED_RITE, "sacred_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.SACRED_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_SACRED_RITE, "sacred_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_SACRED_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_sacred_rite", -3, 10).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.SACRED_RITE, "corrupt_sacred_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.SACRED_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_SACRED_RITE, "corrupt_sacred_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_SACRED_RITE))
		);

		ENTRIES.add(new BookEntry(
				"infernal_rite", -3, 11)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.INFERNAL_RITE, "infernal_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.INFERNAL_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_INFERNAL_RITE, "infernal_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_INFERNAL_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_infernal_rite", -4, 11).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.INFERNAL_RITE, "corrupt_infernal_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.INFERNAL_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_INFERNAL_RITE, "corrupt_infernal_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_INFERNAL_RITE))
		);

		ENTRIES.add(new BookEntry(
				"earthen_rite", -3, 12)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.EARTHEN_RITE, "earthen_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.EARTHEN_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_EARTHEN_RITE, "earthen_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_EARTHEN_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_earthen_rite", -4, 12).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.EARTHEN_RITE, "corrupt_earthen_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.EARTHEN_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_EARTHEN_RITE, "corrupt_earthen_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_EARTHEN_RITE))
		);

		ENTRIES.add(new BookEntry(
				"wicked_rite", 2, 10)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.WICKED_RITE, "wicked_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.WICKED_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_WICKED_RITE, "wicked_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_WICKED_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_wicked_rite", 3, 10).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.WICKED_RITE, "corrupt_wicked_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.WICKED_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_WICKED_RITE, "corrupt_wicked_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_WICKED_RITE))
		);

		ENTRIES.add(new BookEntry(
				"aerial_rite", 3, 11)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.AERIAL_RITE, "aerial_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.AERIAL_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_AERIAL_RITE, "aerial_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_AERIAL_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_aerial_rite", 4, 11).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.AERIAL_RITE, "corrupt_aerial_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.AERIAL_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_AERIAL_RITE, "corrupt_aerial_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_AERIAL_RITE))
		);

		ENTRIES.add(new BookEntry(
				"aqueous_rite", 3, 12)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.AQUEOUS_RITE, "aqueous_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.AQUEOUS_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_AQUEOUS_RITE, "aqueous_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_AQUEOUS_RITE))
		);

		ENTRIES.add(new BookEntry(
				"corrupt_aqueous_rite", 4, 12).setSoulwood()
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.AQUEOUS_RITE, "corrupt_aqueous_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.AQUEOUS_RITE))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ELDRITCH_AQUEOUS_RITE, "corrupt_aqueous_rite.greater"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ELDRITCH_AQUEOUS_RITE))
		);



		ENTRIES.add(new BookEntry(
				"arcane_rite", 0, 11)
				.setObjectSupplier(RiteEntryObject::new)
				.addPage(new HeadlineTextPage("arcane_rite", "arcane_rite.description.1"))
				.addPage(new TextPage("arcane_rite.description.2"))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ARCANE_RITE, "arcane_rite"))
				.addPage(new SpiritRiteRecipePage(SpiritRiteRegistry.ARCANE_RITE))
				.addPage(new TextPage("arcane_rite.description.3"))
				.addPage(new SpiritRiteTextPage(SpiritRiteRegistry.ARCANE_RITE, "corrupt_arcane_rite"))
				.addPage(new TextPage("arcane_rite.description.4"))
				.addPage(SpiritInfusionPage.fromOutput(SOULWOOD_TOTEM_BASE))
		);
*/
		/*
		ENTRIES.add(new BookEntry(
				"blight", BLIGHTED_GUNK, -1, 12).setSoulwood()
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextPage("blight.intro", "blight.intro.1"))
				.addPage(new HeadlineTextPage("blight.composition", "blight.composition.1"))
				.addPage(new HeadlineTextPage("blight.spread", "blight.spread.1"))
				.addPage(new HeadlineTextPage("blight.arcane_rite", "blight.arcane_rite.1"))
		);


		 */
		ENTRIES.add(new BookEntry(
				"soulwood", SOULWOOD_GROWTH.asItem(), 1, 12).setSoulwood()
				.setObjectSupplier(MinorEntryObject::new)
				.addPage(new HeadlineTextPage("soulwood.intro", "soulwood.intro.1"))
				.addPage(new HeadlineTextPage("soulwood.bonemeal", "soulwood.bonemeal.1"))
				.addPage(new HeadlineTextPage("soulwood.color", "soulwood.color.1"))
				.addPage(new HeadlineTextPage("soulwood.blight", "soulwood.blight.1"))
				.addPage(new HeadlineTextPage("soulwood.sap", "soulwood.sap.1"))
		);
		ENTRIES.add(new BookEntry(
				"transmutation", BLIGHTED_SOIL.asItem(), 0, 13).setSoulwood()
				.addPage(new HeadlineTextPage("transmutation", "transmutation.intro.1"))
				.addPage(new TextPage("transmutation.intro.2"))
				.addPage(new SpiritTransmutationPage("transmutation.stone", STONE))
				.addPage(new SpiritTransmutationPage("transmutation.deepslate", DEEPSLATE))
				.addPage(new SpiritTransmutationPage("transmutation.smooth_basalt", SMOOTH_BASALT))
		);

//        ENTRIES.add(new BookEntry(
//                "alteration_plinth", ALTERATION_PLINTH, 1, 13).setSoulwood()
//                .addPage(new HeadlineTextPage("alteration_plinth", "alteration_plinth.intro.1"))
//                .addPage(SpiritInfusionPage.fromOutput(ALTERATION_PLINTH))
//        );

		ENTRIES.add(new BookEntry( //TODO: also name this something better
				"metallurgic_trinkets", NECKLACE_OF_BLISSFUL_HARMONY, -2, 14).setSoulwood()
				.addPage(new HeadlineTextPage("necklace_of_blissful_harmony", "necklace_of_blissful_harmony.1"))
				.addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_BLISSFUL_HARMONY))
				.addPage(new HeadlineTextPage("ring_of_the_demolitionist", "ring_of_the_demolitionist.1"))
				.addPage(SpiritInfusionPage.fromOutput(RING_OF_THE_DEMOLITIONIST))
				.addPage(new HeadlineTextPage("necklace_of_tidal_affinity", "necklace_of_tidal_affinity.1"))
				.addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_TIDAL_AFFINITY))
		);

		ENTRIES.add(new BookEntry(
				"etheric_nitrate", ETHERIC_NITRATE, 2, 14).setSoulwood()
				.addPage(new HeadlineTextPage("etheric_nitrate", "etheric_nitrate.1"))
				.addPage(SpiritInfusionPage.fromOutput(ETHERIC_NITRATE))
				.addPage(new HeadlineTextPage("etheric_nitrate.vivid_nitrate", "etheric_nitrate.vivid_nitrate.1"))
				.addPage(SpiritInfusionPage.fromOutput(VIVID_NITRATE))
		);

		ENTRIES.add(new BookEntry(
				"corrupted_resonance", CORRUPTED_RESONANCE, 0, 15).setSoulwood()
				.addPage(new HeadlineTextPage("corrupted_resonance", "corrupted_resonance.1"))
				.addPage(SpiritInfusionPage.fromOutput(CORRUPTED_RESONANCE))
		);

		ENTRIES.add(new BookEntry(
				"magebane_belt", BELT_OF_THE_MAGEBANE, -1, 16).setSoulwood()
				.addPage(new HeadlineTextPage("magebane_belt", "magebane_belt.1"))
				.addPage(SpiritInfusionPage.fromOutput(BELT_OF_THE_MAGEBANE))
		);

		ENTRIES.add(new BookEntry(
				"necklace_of_the_hidden_blade", NECKLACE_OF_THE_HIDDEN_BLADE, 1, 16).setSoulwood()
				.addPage(new HeadlineTextPage("necklace_of_the_hidden_blade", "necklace_of_the_hidden_blade.1"))
				.addPage(SpiritInfusionPage.fromOutput(NECKLACE_OF_THE_HIDDEN_BLADE))
		);

		ENTRIES.add(new BookEntry(
				"tyrving", TYRVING, 0, 17).setSoulwood()
				.addPage(new HeadlineTextPage("tyrving", "tyrving.1"))
				.addPage(SpiritInfusionPage.fromOutput(TYRVING))
				.addPage(new TextPage("tyrving.2"))
				.addPage(SpiritRepairPage.fromInput(TYRVING))
		);

		ENTRIES.add(new BookEntry(
				"the_device", THE_DEVICE.asItem(), 0, -10)
				.setObjectSupplier(VanishingEntryObject::new)
				.addPage(new HeadlineTextPage("the_device", "the_device"))
				.addPage(new CraftingBookPage(THE_DEVICE.asItem(), TWISTED_ROCK.asItem(), TAINTED_ROCK.asItem(), TWISTED_ROCK.asItem(), TAINTED_ROCK.asItem(), TWISTED_ROCK.asItem(), TAINTED_ROCK.asItem(), TWISTED_ROCK.asItem(), TAINTED_ROCK.asItem(), TWISTED_ROCK.asItem()))
		);
	}

	public void setupObjects() {
		OBJECTS.clear();
		this.width = client.getWindow().getScaledWidth();
		this.height = client.getWindow().getScaledHeight();
		int guiLeft = (width - bookWidth) / 2;
		int guiTop = (height - bookHeight) / 2;
		int coreX = guiLeft + bookInsideWidth;
		int coreY = guiTop + bookInsideHeight;
		int width = 40;
		int height = 48;
		for (BookEntry entry : ENTRIES) {
			OBJECTS.add(entry.objectSupplier.getBookObject(entry, coreX + entry.xOffset * width, coreY - entry.yOffset * height));
		}
		faceObject(OBJECTS.get(0));
	}

	public void faceObject(BookObject object) {
		this.width = client.getWindow().getScaledWidth();
		this.height = client.getWindow().getScaledHeight();
		int guiLeft = (width - bookWidth) / 2;
		int guiTop = (height - bookHeight) / 2;
		xOffset = -object.posX + guiLeft + bookInsideWidth;
		yOffset = -object.posY + guiTop + bookInsideHeight;
	}

	@Override
	public void render(MatrixStack poseStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		int guiLeft = (width - bookWidth) / 2;
		int guiTop = (height - bookHeight) / 2;

		renderBackground(BACKGROUND_TEXTURE, poseStack, 0.1f, 0.4f);
		GL11.glEnable(GL_SCISSOR_TEST);
		cut();

		renderEntries(poseStack, mouseX, mouseY, partialTicks);
		ScreenParticleHandler.renderParticles(BEFORE_TOOLTIPS);
		GL11.glDisable(GL_SCISSOR_TEST);

		renderTransparentTexture(FADE_TEXTURE, poseStack, guiLeft, guiTop, 1, 1, bookWidth, bookHeight, 512, 512);
		renderTexture(FRAME_TEXTURE, poseStack, guiLeft, guiTop, 1, 1, bookWidth, bookHeight, 512, 512);
		lateEntryRender(poseStack, mouseX, mouseY, partialTicks);
	}


	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		xOffset += dragX;
		yOffset += dragY;
		return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		cachedXOffset = xOffset;
		cachedYOffset = yOffset;
		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (ignoreNextMouseInput) {
			ignoreNextMouseInput = false;
			return super.mouseReleased(mouseX, mouseY, button);
		}
		if (xOffset != cachedXOffset || yOffset != cachedYOffset) {
			return super.mouseReleased(mouseX, mouseY, button);
		}
		for (BookObject object : OBJECTS) {
			if (object.isHovering(xOffset, yOffset, mouseX, mouseY)) {
				object.click(xOffset, yOffset, mouseX, mouseY);
				break;
			}
		}
		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (MinecraftClient.getInstance().options.inventoryKey.matchesKey(keyCode, scanCode)) {
			closeScreen();
			return true;
		}
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	public void renderEntries(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		for (int i = OBJECTS.size() - 1; i >= 0; i--) {
			BookObject object = OBJECTS.get(i);
			boolean isHovering = object.isHovering(xOffset, yOffset, mouseX, mouseY);
			object.isHovering = isHovering;
			object.hover = isHovering ? Math.min(object.hover++, object.hoverCap()) : Math.max(object.hover--, 0);
			object.render(client, stack, xOffset, yOffset, mouseX, mouseY, partialTicks);
		}
	}

	public void lateEntryRender(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		for (int i = OBJECTS.size() - 1; i >= 0; i--) {
			BookObject object = OBJECTS.get(i);
			object.lateRender(client, stack, xOffset, yOffset, mouseX, mouseY, partialTicks);
		}
	}

	public static boolean isHovering(double mouseX, double mouseY, int posX, int posY, int width, int height) {
		if (!isInView(mouseX, mouseY)) {
			return false;
		}
		return mouseX > posX && mouseX < posX + width && mouseY > posY && mouseY < posY + height;
	}

	public static boolean isInView(double mouseX, double mouseY) {
		int guiLeft = (screen.width - screen.bookWidth) / 2;
		int guiTop = (screen.height - screen.bookHeight) / 2;
		return !(mouseX < guiLeft + 17) && !(mouseY < guiTop + 14) && !(mouseX > guiLeft + (screen.bookWidth - 17)) && !(mouseY > (guiTop + screen.bookHeight - 14));
	}

	public void renderBackground(Identifier texture, MatrixStack poseStack, float xModifier, float yModifier) {
		int guiLeft = (width - bookWidth) / 2; //TODO: literally just redo this entire garbage method, please
		int guiTop = (height - bookHeight) / 2;
		int insideLeft = guiLeft + 17;
		int insideTop = guiTop + 14;
		float uOffset = (parallax_width - xOffset) * xModifier;
		float vOffset = Math.min(parallax_height - bookInsideHeight, (parallax_height - bookInsideHeight - yOffset * yModifier));
		if (vOffset <= parallax_height / 2f) {
			vOffset = parallax_height / 2f;
		}
		if (uOffset <= 0) {
			uOffset = 0;
		}
		if (uOffset > (bookInsideWidth - 8) / 2f) {
			uOffset = (bookInsideWidth - 8) / 2f;
		}
		renderTexture(texture, poseStack, insideLeft, insideTop, uOffset, vOffset, bookInsideWidth, bookInsideHeight, parallax_width / 2, parallax_height / 2);
	}

	public void cut() {
		int scale = (int) getClient().getWindow().getScaleFactor();
		int guiLeft = (width - bookWidth) / 2;
		int guiTop = (height - bookHeight) / 2;
		int insideLeft = guiLeft + 17;
		int insideTop = guiTop + 18;
		GL11.glScissor(insideLeft * scale, insideTop * scale, bookInsideWidth * scale, (bookInsideHeight + 1) * scale); // do not ask why the 1 is needed please
	}

	public static void renderRiteIcon(MalumRiteType rite, MatrixStack stack, boolean corrupted, int x, int y) {
		renderRiteIcon(rite, stack, corrupted, x, y, 0);
	}
	public static void renderRiteIcon(MalumRiteType rite, MatrixStack stack, boolean corrupted, int x, int y, int z) {
		ExtendedShader shaderInstance = (ExtendedShader) LodestoneShaders.DISTORTED_TEXTURE.getInstance();
		shaderInstance.getUniformOrDefault("YFrequency").setFloat(corrupted ? 5f : 11f);
		shaderInstance.getUniformOrDefault("XFrequency").setFloat(corrupted ? 12f : 17f);
		shaderInstance.getUniformOrDefault("Speed").setFloat(2000f * (corrupted ? -0.75f : 1));
		shaderInstance.getUniformOrDefault("Intensity").setFloat(corrupted ? 14f : 50f);
		Supplier<ShaderProgram> shaderInstanceSupplier = () -> shaderInstance;
		Color color = rite.getEffectSpirit().getColor();

		VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
				.setPosColorTexLightmapDefaultFormat()
				.setShader(shaderInstanceSupplier)
				.setColor(color)
				.setAlpha(0.9f)
				.setZLevel(z)
				.setShader(() -> shaderInstance);

		RenderSystem.enableBlend();
		RenderSystem.disableDepthTest();
		RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		renderTexture(rite.getIcon(), stack, builder, x, y, 0, 0, 16, 16, 16, 16);
		builder.setAlpha(0.4f);
		renderTexture(rite.getIcon(), stack, builder, x - 1, y, 0, 0, 16, 16, 16, 16);
		renderTexture(rite.getIcon(), stack, builder, x + 1, y, 0, 0, 16, 16, 16, 16);
		renderTexture(rite.getIcon(), stack, builder, x, y - 1, 0, 0, 16, 16, 16, 16);
		if (corrupted) {
			builder.setColor(rite.getEffectSpirit().getEndColor());
		}
		renderTexture(rite.getIcon(), stack, builder, x, y + 1, 0, 0, 16, 16, 16, 16);
		shaderInstance.setUniformDefaults();
		RenderSystem.enableDepthTest();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}

	public static void renderWavyIcon(Identifier location, MatrixStack stack, int x, int y) {
		renderWavyIcon(location, stack, x, y, 0);
	}

	public static void renderWavyIcon(Identifier location, MatrixStack stack, int x, int y, int z) {
		ExtendedShader shaderInstance = (ExtendedShader) LodestoneShaders.DISTORTED_TEXTURE.getInstance();
		shaderInstance.getUniformOrDefault("YFrequency").setFloat(10f);
		shaderInstance.getUniformOrDefault("XFrequency").setFloat(12f);
		shaderInstance.getUniformOrDefault("Speed").setFloat(1000f);
		shaderInstance.getUniformOrDefault("Intensity").setFloat(50f);
		shaderInstance.getUniformOrDefault("UVCoordinates").setVec4(new Vector4f(0f, 1f, 0f, 1f));
		Supplier<ShaderProgram> shaderInstanceSupplier = () -> shaderInstance;

		VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
				.setPosColorTexLightmapDefaultFormat()
				.setShader(shaderInstanceSupplier)
				.setAlpha(0.7f)
				.setZLevel(z)
				.setShader(() -> shaderInstance);

		RenderSystem.enableBlend();
		RenderSystem.disableDepthTest();
		RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		renderTexture(location, stack, builder, x, y, 0, 0, 16, 16, 16, 16);
		builder.setAlpha(0.1f);
		renderTexture(location, stack, builder, x - 1, y, 0, 0, 16, 16, 16, 16);
		renderTexture(location, stack, builder, x + 1, y, 0, 0, 16, 16, 16, 16);
		renderTexture(location, stack, builder, x, y - 1, 0, 0, 16, 16, 16, 16);
		renderTexture(location, stack, builder, x, y + 1, 0, 0, 16, 16, 16, 16);
		shaderInstance.setUniformDefaults();
		RenderSystem.enableDepthTest();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}
	public static void renderTexture(Identifier texture, MatrixStack poseStack, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		renderTexture(texture, poseStack, BUILDER, x, y, u, v, width, height, textureWidth, textureHeight);
	}
	public static void renderTexture(Identifier texture, MatrixStack poseStack, VFXBuilders.ScreenVFXBuilder builder, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		builder.setPositionWithWidth(x, y, width, height)
				.setShaderTexture(texture)
				.setUVWithWidth(u, v, width, height, textureWidth, textureHeight)
				.draw(poseStack);
	}

	public static void renderTransparentTexture(Identifier texture, MatrixStack poseStack, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		renderTransparentTexture(texture, poseStack, BUILDER, x, y, u, v, width, height, textureWidth, textureHeight);
	}
	public static void renderTransparentTexture(Identifier texture, MatrixStack poseStack, VFXBuilders.ScreenVFXBuilder builder, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		renderTexture(texture, poseStack, builder, x, y, u, v, width, height, textureWidth, textureHeight);
		RenderSystem.disableDepthTest();
		RenderSystem.disableBlend();
	}

	public static void renderComponents(MatrixStack poseStack, List<? extends IRecipeComponent> components, int left, int top, int mouseX, int mouseY, boolean vertical) {
		List<ItemStack> items = components.stream().map(IRecipeComponent::getStack).collect(Collectors.toList());
		ProgressionBookScreen.renderItemList(poseStack, items, left, top, mouseX, mouseY, vertical).run();
	}

	public static Runnable renderBufferedComponents(MatrixStack poseStack, List<? extends IRecipeComponent> components, int left, int top, int mouseX, int mouseY, boolean vertical) {
		List<ItemStack> items = components.stream().map(IRecipeComponent::getStack).collect(Collectors.toList());
		return ProgressionBookScreen.renderItemList(poseStack, items, left, top, mouseX, mouseY, vertical);
	}

	public static void renderComponent(MatrixStack poseStack, IRecipeComponent component, int posX, int posY, int mouseX, int mouseY) {
		if (component.getStacks().size() == 1) {
			renderItem(poseStack, component.getStack(), posX, posY, mouseX, mouseY);
			return;
		}
		int index = (int) (MinecraftClient.getInstance().world.getTime() % (20L * component.getStacks().size()) / 20);
		ItemStack stack = component.getStacks().get(index);
		MinecraftClient.getInstance().getItemRenderer().renderInGuiWithOverrides(stack, posX, posY);
		MinecraftClient.getInstance().getItemRenderer().renderGuiItemOverlay(MinecraftClient.getInstance().textRenderer, stack, posX, posY, null);
		if (isHovering(mouseX, mouseY, posX, posY, 16, 16)) {
			screen.renderTooltip(poseStack, screen.getTooltipFromItem(stack), mouseX, mouseY);
		}
	}

	public static void renderItem(MatrixStack poseStack, Ingredient ingredient, int posX, int posY, int mouseX, int mouseY) {
		renderItem(poseStack, List.of(ingredient.getMatchingStacks()), posX, posY, mouseX, mouseY);
	}

	public static void renderItem(MatrixStack poseStack, List<ItemStack> stacks, int posX, int posY, int mouseX, int mouseY) {
		if (stacks.size() == 1) {
			renderItem(poseStack, stacks.get(0), posX, posY, mouseX, mouseY);
			return;
		}
		int index = (int) (MinecraftClient.getInstance().world.getTime() % (20L * stacks.size()) / 20);
		ItemStack stack = stacks.get(index);
		MinecraftClient.getInstance().getItemRenderer().renderInGuiWithOverrides(stack, posX, posY);
		MinecraftClient.getInstance().getItemRenderer().renderGuiItemOverlay(MinecraftClient.getInstance().textRenderer, stack, posX, posY, null);
		if (isHovering(mouseX, mouseY, posX, posY, 16, 16)) {
			screen.renderTooltip(poseStack, screen.getTooltipFromItem(stack), mouseX, mouseY);
		}
	}

	public static void renderItem(MatrixStack poseStack, ItemStack stack, int posX, int posY, int mouseX, int mouseY) {
		MinecraftClient.getInstance().getItemRenderer().renderInGuiWithOverrides(stack, posX, posY);
		MinecraftClient.getInstance().getItemRenderer().renderGuiItemOverlay(MinecraftClient.getInstance().textRenderer, stack, posX, posY, null);
		if (isHovering(mouseX, mouseY, posX, posY, 16, 16)) {
			screen.renderTooltip(poseStack, screen.getTooltipFromItem(stack), mouseX, mouseY);
		}
	}

	public static Runnable renderItemList(MatrixStack poseStack, List<ItemStack> items, int left, int top, int mouseX, int mouseY, boolean vertical) {
		int slots = items.size();
		renderItemFrames(poseStack, slots, left, top, vertical);
		return () -> {
			int finalLeft = left;
			int finalTop = top;
			if (vertical) {
				finalTop -= 10 * (slots - 1);
			} else {
				finalLeft -= 10 * (slots - 1);
			}
			for (int i = 0; i < slots; i++) {
				ItemStack stack = items.get(i);
				int offset = i * 20;
				int oLeft = finalLeft + 2 + (vertical ? 0 : offset);
				int oTop = finalTop + 2 + (vertical ? offset : 0);
				ProgressionBookScreen.renderItem(poseStack, stack, oLeft, oTop, mouseX, mouseY);
			}
		};
	}

	public static void renderItemFrames(MatrixStack poseStack, int slots, int left, int top, boolean vertical) {
		if (vertical) {
			top -= 10 * (slots - 1);
		} else {
			left -= 10 * (slots - 1);
		}
		//item slot
		for (int i = 0; i < slots; i++) {
			int offset = i * 20;
			int oLeft = left + (vertical ? 0 : offset);
			int oTop = top + (vertical ? offset : 0);
			renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, oLeft, oTop, 75, 192, 20, 20, 512, 512);

			if (vertical) {
				//bottom fade
				if (slots > 1 && i != slots - 1) {
					renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, left + 1, oTop + 19, 75, 213, 18, 2, 512, 512);
				}
				//bottommost fade
				if (i == slots - 1) {
					renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, oLeft + 1, oTop + 19, 75, 216, 18, 2, 512, 512);
				}
			} else {
				//bottom fade
				renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, oLeft + 1, top + 19, 75, 216, 18, 2, 512, 512);
				if (slots > 1 && i != slots - 1) {
					//side fade
					renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, oLeft + 19, top, 96, 192, 2, 20, 512, 512);
				}
			}
		}

		//crown
		int crownLeft = left + 5 + (vertical ? 0 : 10 * (slots - 1));
		renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, crownLeft, top - 5, 128, 192, 10, 6, 512, 512);

		//side-bars
		if (vertical) {
			renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, left - 4, top - 4, 99, 200, 28, 7, 512, 512);
			renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, left - 4, top + 17 + 20 * (slots - 1), 99, 192, 28, 7, 512, 512);
		}
		// top bars
		else {
			renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, left - 4, top - 4, 59, 192, 7, 28, 512, 512);
			renderTexture(EntryScreen.BOOK_TEXTURE, poseStack, left + 17 + 20 * (slots - 1), top - 4, 67, 192, 7, 28, 512, 512);
		}
	}

	public static void renderWrappingText(MatrixStack mStack, String text, int x, int y, int w) {
		TextRenderer font = MinecraftClient.getInstance().textRenderer;
		text = Text.translatable(text).getString() + "\n";
		List<String> lines = new ArrayList<>();

		boolean italic = false;
		boolean bold = false;
		boolean strikethrough = false;
		boolean underline = false;
		boolean obfuscated = false;

		StringBuilder line = new StringBuilder();
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char chr = text.charAt(i);
			if (chr == ' ' || chr == '\n') {
				if (word.length() > 0) {
					if (font.getWidth(line.toString()) + font.getWidth(word.toString()) > w) {
						line = newLine(lines, italic, bold, strikethrough, underline, obfuscated, line);
					}
					line.append(word).append(' ');
					word = new StringBuilder();
				}

				String noFormatting = Formatting.strip(line.toString());

				if (chr == '\n' && !(noFormatting == null || noFormatting.isEmpty())) {
					line = newLine(lines, italic, bold, strikethrough, underline, obfuscated, line);
				}
			} else if (chr == '$') {
				if (i != text.length() - 1) {
					char peek = text.charAt(i + 1);
					switch (peek) {
						case 'i' -> {
							word.append(Formatting.ITALIC);
							italic = true;
							i++;
						}
						case 'b' -> {
							word.append(Formatting.BOLD);
							bold = true;
							i++;
						}
						case 's' -> {
							word.append(Formatting.STRIKETHROUGH);
							strikethrough = true;
							i++;
						}
						case 'u' -> {
							word.append(Formatting.UNDERLINE);
							underline = true;
							i++;
						}
						case 'k' -> {
							word.append(Formatting.OBFUSCATED);
							obfuscated = true;
							i++;
						}
						default -> word.append(chr);
					}
				} else {
					word.append(chr);
				}
			} else if (chr == '/') {
				if (i != text.length() - 1) {
					char peek = text.charAt(i + 1);
					if (peek == '$') {
						italic = bold = strikethrough = underline = obfuscated = false;
						word.append(Formatting.RESET);
						i++;
					} else
						word.append(chr);
				} else
					word.append(chr);
			} else {
				word.append(chr);
			}
		}

		for (int i = 0; i < lines.size(); i++) {
			String currentLine = lines.get(i);
			renderRawText(mStack, currentLine, x, y + i * (font.fontHeight + 1), getTextGlow(i / 4f));
		}
	}

	private static StringBuilder newLine(List<String> lines, boolean italic, boolean bold, boolean strikethrough, boolean underline, boolean obfuscated, StringBuilder line) {
		lines.add(line.toString());
		line = new StringBuilder();
		if (italic) line.append(Formatting.ITALIC);
		if (bold) line.append(Formatting.BOLD);
		if (strikethrough) line.append(Formatting.STRIKETHROUGH);
		if (underline) line.append(Formatting.UNDERLINE);
		if (obfuscated) line.append(Formatting.OBFUSCATED);
		return line;
	}

	public static void renderText(MatrixStack stack, String text, int x, int y) {
		renderText(stack, Text.translatable(text), x, y, getTextGlow(0));
	}

	public static void renderText(MatrixStack stack, Text component, int x, int y) {
		String text = component.getString();
		renderRawText(stack, text, x, y, getTextGlow(0));
	}

	public static void renderText(MatrixStack stack, String text, int x, int y, float glow) {
		renderText(stack, Text.translatable(text), x, y, glow);
	}

	public static void renderText(MatrixStack stack, Text component, int x, int y, float glow) {
		String text = component.getString();
		renderRawText(stack, text, x, y, glow);
	}

	private static void renderRawText(MatrixStack stack, String text, int x, int y, float glow) {
		var font = MinecraftClient.getInstance().textRenderer;
		if (false){//TODO ClientConfig.BOOK_THEME.getConfigValue().equals(BookTheme.EASY_READING)) {
			font.draw(stack, text, x, y, 0);
			return;
		}

		glow = Easing.CUBIC_IN.ease(glow, 0, 1, 1);
		int r = (int) MathHelper.lerp(glow, 163, 227);
		int g = (int) MathHelper.lerp(glow, 44, 39);
		int b = (int) MathHelper.lerp(glow, 191, 228);

		font.draw(stack, text, x - 1, y, getArgb(96, 255, 210, 243));
		font.draw(stack, text, x + 1, y, getArgb(128, 240, 131, 232));
		font.draw(stack, text, x, y - 1, getArgb(128, 255, 183, 236));
		font.draw(stack, text, x, y + 1, getArgb(96, 236, 110, 226));

		font.draw(stack, text, x, y, getArgb(255, r, g, b));
	}

	public static float getTextGlow(float offset) {
		return MathHelper.sin(offset + MinecraftClient.getInstance().player.world.getTime() / 40f) / 2f + 0.5f;
	}

	public void playSound() {
		PlayerEntity playerEntity = MinecraftClient.getInstance().player;
		playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}

	public static void openScreen(boolean ignoreNextMouseClick) {
		MinecraftClient.getInstance().setScreen(getInstance());
		ScreenParticleHandler.wipeParticles();
		screen.playSound();
		screen.ignoreNextMouseInput = ignoreNextMouseClick;
	}

	public static ProgressionBookScreen getInstance() {
		if (screen == null) {
			screen = new ProgressionBookScreen();
		}
		return screen;
	}
}
