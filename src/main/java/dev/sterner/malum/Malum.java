package dev.sterner.malum;

import dev.sterner.malum.common.enchantment.ReboundEnchantment;
import dev.sterner.malum.common.event.MalumEvents;
import dev.sterner.malum.common.event.MalumItemGroupEvents;
import dev.sterner.malum.common.event.MalumTrinketEvents;
import dev.sterner.malum.common.reaping.ReapingDataReloadListener;
import dev.sterner.malum.common.registry.*;
import dev.sterner.malum.common.spirit.SpiritDataReloadListener;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.reloader.IdentifiableResourceReloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.function.Function;


/**TODO
 * Add all pridewears
 * Add config
 * Add boat and sign
 */
public class Malum implements ModInitializer {
	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();
	public static final Logger LOGGER = LoggerFactory.getLogger("Malum");
	public static final String MODID = "malum";

	@Override
	public void onInitialize(ModContainer mod) {
		MalumObjects.init();
		MalumSpiritTypeRegistry.init();

		MalumAttributeRegistry.init();
		MalumParticleRegistry.init();
		MalumEnchantmentRegistry.init();
		MalumSoundRegistry.init();


		MalumEntityRegistry.init();
		MalumBlockEntityRegistry.init();


		MalumStatusEffectRegistry.init();
		MalumTagRegistry.init();
		MalumRecipeTypeRegistry.init();
		MalumRecipeSerializerRegistry.init();
		MalumScreenHandlerRegistry.init();

		MalumRiteRegistry.init();
		MalumTrinketEvents.init();
		MalumEvents.init();

		MalumStructures.init();
		MalumFeatureRegistry.init();
		MalumConfiguredFeatureRegistry.init();
		MalumPlacedFeatureRegistry.init();

		UseItemCallback.EVENT.register(ReboundEnchantment::onRightClickItem);
		ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(new SpiritDataReloadListenerFabricImpl());
		ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(new ReapingDataReloadListenerFabricImpl());
	}


	public static class SpiritDataReloadListenerFabricImpl extends SpiritDataReloadListener implements IdentifiableResourceReloader {
		@Override
		public Identifier getQuiltId() {
			return new Identifier(MODID, "spirit_data");
		}
	}

	public static class ReapingDataReloadListenerFabricImpl extends ReapingDataReloadListener implements IdentifiableResourceReloader {
		@Override
		public Identifier getQuiltId() {
			return new Identifier(MODID, "reaping_data");
		}
	}

	public static Identifier id(String name){
		return new Identifier(MODID, name);
	}

	//TODO Add this to Lodestone
	public static int getOrDefaultInt(Function<NbtCompound, Integer> getter, int defaultValue, NbtCompound nbt) {
		try {
			return getter.apply(nbt);
		} catch (Exception ignored) {
			return defaultValue;
		}
	}

	public static int getOrThrowInt(NbtCompound nbt, String value) {
		if (!nbt.contains(value)) {
			throw new NullPointerException();
		}
		return nbt.getInt(value);
	}
}
