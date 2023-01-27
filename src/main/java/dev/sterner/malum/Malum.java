package dev.sterner.malum;

import dev.sterner.malum.common.enchantment.ReboundEnchantment;
import dev.sterner.malum.common.event.MalumItemGroupEvents;
import dev.sterner.malum.common.event.MalumTrinketEvents;
import dev.sterner.malum.common.registry.*;
import dev.sterner.malum.common.spirit.SpiritDataReloadListener;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
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


/**TODO
 * Add all pridewears
 * Add config
 * Fix SpiritRepair recipes from crashing
 * Add boat and sign
 */
public class Malum implements ModInitializer {
	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();
	public static final Logger LOGGER = LoggerFactory.getLogger("Malum");
	public static final String MODID = "malum";

	@Override
	public void onInitialize(ModContainer mod) {
		MalumAttributeRegistry.init();
		MalumParticleRegistry.init();
		MalumEnchantmentRegistry.init();
		MalumSoundRegistry.init();

		MalumObjects.init();
		MalumBlockEntityRegistry.init();


		MalumEntityRegistry.init();
		MalumStatusEffectRegistry.init();
		MalumTagRegistry.init();
		MalumRecipeTypeRegistry.init();
		MalumRecipeSerializerRegistry.init();
		MalumScreenHandlerRegistry.init();
		MalumSpiritTypeRegistry.init();
		MalumRiteRegistry.init();
		MalumTrinketEvents.init();
		MalumFeatureRegistry.init();
		MalumItemGroupEvents.init();

		UseItemCallback.EVENT.register(ReboundEnchantment::onRightClickItem);
		ResourceLoader.get(ResourceType.SERVER_DATA).registerReloader(new SpiritDataReloadListenerFabricImpl());

		RegistryEvents.DYNAMIC_REGISTRY_SETUP.register((ctx) -> {
			ctx.withRegistries(registries -> {
				Registry<ConfiguredFeature<?, ?>> configured = registries.get(RegistryKeys.CONFIGURED_FEATURE);
				MalumConfiguredFeatureRegistry.init(configured);
				MalumPlacedFeatureRegistry.init(configured, registries);
			}, Set.of(RegistryKeys.PLACED_FEATURE, RegistryKeys.CONFIGURED_FEATURE));
		});
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
