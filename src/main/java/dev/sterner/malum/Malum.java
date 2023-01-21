package dev.sterner.malum;

import dev.sterner.malum.common.registry.*;
import dev.sterner.malum.common.spirit.SpiritDataReloadListener;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
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

import static dev.sterner.malum.common.registry.MalumItemRegistry.*;

public class Malum implements ModInitializer {
	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();
	public static final Logger LOGGER = LoggerFactory.getLogger("Malum");
	public static final String MODID = "malum";

	@Override
	public void onInitialize(ModContainer mod) {

		MalumBlockEntityRegistry.init();
		MalumBlockRegistry.init();
		MalumItemRegistry.init();
		MalumEntityRegistry.init();
		MalumStatusEffectRegistry.init();
		MalumParticleRegistry.init();
		MalumSoundRegistry.init();

		MalumAttributeRegistry.init();
		MalumRecipeTypeRegistry.init();
		MalumRecipeSerializerRegistry.init();
		MalumFeatureRegistry.init();

		//ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(new SpiritDataReloadListenerFabricImpl());
	}

	public static final ItemGroup MALUM = FabricItemGroup.builder(new Identifier(MODID, MODID)).icon(() -> new ItemStack(SPIRIT_ALTAR)).entries((featureFlagBitSet, itemStackCollector, bl) -> {
		ITEMS.forEach((identifier, item) -> itemStackCollector.addItem(item));
	}).build();
	public static final ItemGroup MALUM_ARCANE_ROCKS = FabricItemGroup.builder(new Identifier(MODID, "malum_shaped_stones")).icon(() -> new ItemStack(TAINTED_ROCK)).build();
	public static final ItemGroup MALUM_NATURAL_WONDERS = FabricItemGroup.builder(new Identifier(MODID, "malum_natural_wonders")).icon(() -> new ItemStack(RUNEWOOD_SAPLING)).build();
	public static final ItemGroup MALUM_SPIRITS = FabricItemGroup.builder(new Identifier(MODID, "malum_spirits")).icon(() -> new ItemStack(ARCANE_SPIRIT)).entries((f, e, b) -> {
		e.addItem(AERIAL_SPIRIT);
		e.addItem(AQUEOUS_SPIRIT);
		e.addItem(INFERNAL_SPIRIT);
		e.addItem(ARCANE_SPIRIT);
		e.addItem(ELDRITCH_SPIRIT);
		e.addItem(EARTHEN_SPIRIT);
		e.addItem(WICKED_SPIRIT);
		e.addItem(SACRED_SPIRIT);
	}).build();
	public static final ItemGroup MALUM_METALLURGIC_MAGIC = FabricItemGroup.builder(new Identifier(MODID, "malum_metallurgic_magic")).icon(() -> new ItemStack(ALCHEMICAL_IMPETUS)).entries((f, e, b) -> {
		e.addItem(ALCHEMICAL_IMPETUS);
		e.addItem(CRACKED_ALCHEMICAL_IMPETUS);
		e.addItem(GOLD_NODE);
		e.addItem(GOLD_IMPETUS);
		e.addItem(CRACKED_GOLD_IMPETUS);
		e.addItem(IRON_NODE);
		e.addItem(IRON_IMPETUS);
		e.addItem(CRACKED_IRON_IMPETUS);
		e.addItem(COPPER_NODE);
		e.addItem(COPPER_IMPETUS);
		e.addItem(CRACKED_COPPER_IMPETUS);
		e.addItem(SILVER_NODE);
		e.addItem(SILVER_IMPETUS);
		e.addItem(CRACKED_SILVER_IMPETUS);
		e.addItem(LEAD_NODE);
		e.addItem(LEAD_IMPETUS);
		e.addItem(CRACKED_LEAD_IMPETUS);
	}).build();

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
