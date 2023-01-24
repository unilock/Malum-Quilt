package dev.sterner.malum;

import dev.sterner.malum.common.enchantment.ReboundEnchantment;
import dev.sterner.malum.common.event.MalumTrinketEvents;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.registry.*;
import dev.sterner.malum.common.spirit.SpiritDataReloadListener;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.random.RandomGenerator;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.sterner.malum.common.registry.MalumObjects.*;


public class Malum implements ModInitializer {
	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();
	public static final Logger LOGGER = LoggerFactory.getLogger("Malum");
	public static final String MODID = "malum";
	public static final ItemGroup MALUM = FabricItemGroup.builder(new Identifier(MODID, MODID)).icon(() -> new ItemStack(SPIRIT_ALTAR)).build();
	public static final ItemGroup MALUM_ARCANE_ROCKS = FabricItemGroup.builder(new Identifier(MODID, "malum_shaped_stones")).icon(() -> new ItemStack(TAINTED_ROCK)).build();
	public static final ItemGroup MALUM_NATURAL_WONDERS = FabricItemGroup.builder(new Identifier(MODID, "malum_natural_wonders")).icon(() -> new ItemStack(RUNEWOOD_SAPLING)).build();
	public static final ItemGroup MALUM_SPIRITS = FabricItemGroup.builder(new Identifier(MODID, "malum_spirits")).icon(() -> new ItemStack(ARCANE_SPIRIT)).build();
	public static final ItemGroup MALUM_METALLURGIC_MAGIC = FabricItemGroup.builder(new Identifier(MODID, "malum_metallurgic_magic")).icon(() -> new ItemStack(ALCHEMICAL_IMPETUS)).build();

	@Override
	public void onInitialize(ModContainer mod) {
		MalumAttributeRegistry.init();
		MalumParticleRegistry.init();
		MalumEnchantmentRegistry.init();
		MalumSoundRegistry.init();

		MalumBlockEntityRegistry.init();
		MalumObjects.init();

		MalumEntityRegistry.init();
		MalumStatusEffectRegistry.init();
		MalumTagRegistry.init();
		MalumRecipeTypeRegistry.init();
		MalumRecipeSerializerRegistry.init();
		MalumFeatureRegistry.init();
		MalumScreenHandlerRegistry.init();
		MalumSpiritTypeRegistry.init();
		MalumRiteRegistry.init();
		//MalumPlacedFeatureRegistry.init();
		MalumTrinketEvents.init();

		UseItemCallback.EVENT.register(ReboundEnchantment::onRightClickItem);
		ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(new SpiritDataReloadListenerFabricImpl());

		ItemGroupEvents.modifyEntriesEvent(MALUM).register(entries -> {
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
				//entries.addItem(RUNEWOOD_OBELISK);
				//entries.addItem(BRILLIANT_OBELISK);
				entries.addItem(SPIRIT_CRUCIBLE);
				entries.addItem(TWISTED_TABLET);
				entries.addItem(SPIRIT_CATALYZER);
				entries.addItem(RUNEWOOD_TOTEM_BASE);
				entries.addItem(SOULWOOD_TOTEM_BASE);

				entries.addItem(ROTTING_ESSENCE);
				entries.addItem(GRIM_TALC);
				entries.addItem(ALCHEMICAL_CALX);
				entries.addItem(ASTRAL_WEAVE);
				//entries.addItem(CTHONIC_GOLD);
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
				entries.addItem(RING_OF_THE_DEMOLITIONIST);
				entries.addItem(NECKLACE_OF_THE_MYSTIC_MIRROR);
				entries.addItem(NECKLACE_OF_TIDAL_AFFINITY);
				entries.addItem(NECKLACE_OF_THE_NARROW_EDGE);
				entries.addItem(NECKLACE_OF_THE_HIDDEN_BLADE);
				entries.addItem(NECKLACE_OF_BLISSFUL_HARMONY);
				entries.addItem(BELT_OF_THE_STARVED);
				entries.addItem(BELT_OF_THE_PROSPECTOR);
				entries.addItem(BELT_OF_THE_MAGEBANE);


				//entries.addItem(BRILLIANT_OBELISK);

		});



		ItemGroupEvents.modifyEntriesEvent(MALUM_SPIRITS).register(entries -> ITEMS.forEach((item, identifier) -> {
			if(item instanceof MalumSpiritItem){
				entries.addItem(item);
			}
		}));



	}


	public static class SpiritDataReloadListenerFabricImpl extends SpiritDataReloadListener implements IdentifiableResourceReloader {
		@Override
		public Identifier getQuiltId() {
			return new Identifier(MODID, "spirit_data");
		}
	}

	public static Identifier id(String name){
		return new Identifier(MODID, name);
	}
}
