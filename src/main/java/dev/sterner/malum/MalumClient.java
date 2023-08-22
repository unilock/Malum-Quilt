package dev.sterner.malum;

import dev.sterner.malum.client.model.*;
import dev.sterner.malum.client.particles.cut.ScytheAttackParticle;
import dev.sterner.malum.client.particles.spiritflame.SpiritFlameParticleType;
import dev.sterner.malum.client.render.CloakArmorRenderer;
import dev.sterner.malum.client.render.SteelArmorRenderer;
import dev.sterner.malum.client.render.block.*;
import dev.sterner.malum.client.render.entity.*;
import dev.sterner.malum.client.render.item.ScytheItemRenderer;
import dev.sterner.malum.client.render.item.SpiritJarItemRenderer;
import dev.sterner.malum.client.screen.SpiritPouchScreen;
import dev.sterner.malum.common.block.MalumLeavesBlock;
import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import dev.sterner.malum.common.network.packet.s2c.client.VoidRejectionPacket;
import dev.sterner.malum.common.network.packet.s2c.client.block.*;
import dev.sterner.malum.common.network.packet.s2c.client.block.blight.BlightMistParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.client.block.blight.BlightTransformItemParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.client.block.functional.AltarConsumeParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.client.block.functional.AltarCraftParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.client.entity.*;
import dev.sterner.malum.common.registry.*;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

import java.awt.*;

import static dev.sterner.malum.common.registry.MalumObjects.*;

public class MalumClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityModelLayerRegistry.registerModelLayer(SoulHunterArmorModel.LAYER, SoulHunterArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SoulStainedSteelArmorModel.LAYER, SoulStainedSteelArmorModel::getTexturedModelData);

		EntityModelLayerRegistry.registerModelLayer(AncientSoulHunterArmorModel.LAYER, AncientSoulHunterArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(AncientSoulStainedSteelArmorModel.LAYER, AncientSoulStainedSteelArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(PridewearArmorModel.LAYER, PridewearArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SlimPridewearArmorModel.LAYER, SlimPridewearArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(TailModel.LAYER, TailModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ScarfModel.LAYER, ScarfModel::getTexturedModelData);



		ArmorRenderer.register(new CloakArmorRenderer(Malum.id("textures/armor/spirit_hunter_reforged.png")), MalumObjects.SOUL_HUNTER_CLOAK, MalumObjects.SOUL_HUNTER_ROBE, MalumObjects.SOUL_HUNTER_LEGGINGS, MalumObjects.SOUL_HUNTER_BOOTS);
		ArmorRenderer.register(new SteelArmorRenderer(Malum.id("textures/armor/soul_stained_steel_reforged.png")), MalumObjects.SOUL_STAINED_STEEL_HELMET, MalumObjects.SOUL_STAINED_STEEL_CHESTPLATE, MalumObjects.SOUL_STAINED_STEEL_LEGGINGS, MalumObjects.SOUL_STAINED_STEEL_BOOTS);



		EntityRendererRegistry.register(MalumEntityRegistry.SCYTHE_BOOMERANG, ScytheBoomerangEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.NATURAL_SPIRIT, FloatingItemEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.ETHERIC_NITRATE, EthericNitrateEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.VIVID_NITRATE, VividNitrateEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.MIRROR_ITEM, FloatingItemEntityRenderer::new);

		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_ALTAR, SpiritAltarBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_CRUCIBLE, SpiritCrucibleBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_CATALYZER, SpiritCatalyzerBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.TWISTED_TABLET, ItemStandBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.PLINTH, PlinthBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.TOTEM_POLE, TotemPoleBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ITEM_STAND, ItemStandBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ITEM_PEDESTAL, ItemPedestalBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ALTERATION_PLINTH, ItemPedestalBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_JAR, SpiritJarBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SOUL_VIAL, SoulVialBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.VOID_CONDUIT, VoidConduitBlockEntityRenderer::new);

		BuiltinItemRendererRegistry.INSTANCE.register(SPIRIT_JAR.asItem(), new SpiritJarItemRenderer());

		for (Item item : MalumObjects.SCYTHES) {
			Identifier scytheId = Registry.ITEM.getId(item);
			ScytheItemRenderer scytheItemRenderer = new ScytheItemRenderer(scytheId);
			ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(scytheItemRenderer);
			BuiltinItemRendererRegistry.INSTANCE.register(item, scytheItemRenderer);
			ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
				out.accept(new ModelIdentifier(scytheId + "_gui", "inventory"));
				out.accept(new ModelIdentifier(scytheId + "_handheld", "inventory"));
			});
		}

		ClientPlayNetworking.registerGlobalReceiver(BlightMistParticlePacket.ID, BlightMistParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(BlightTransformItemParticlePacket.ID, BlightTransformItemParticlePacket::handle);

		ClientPlayNetworking.registerGlobalReceiver(AltarCraftParticlePacket.ID, AltarCraftParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(AltarConsumeParticlePacket.ID, AltarConsumeParticlePacket::handle);

		ClientPlayNetworking.registerGlobalReceiver(BlockDownwardSparkleParticlePacket.ID, BlockDownwardSparkleParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(BlockMistParticlePacket.ID, BlockMistParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(BlockParticlePacket.ID, BlockParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(BlockDownwardSparkleParticlePacket.ID, BlockDownwardSparkleParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(FireBlockExtinguishSparkleParticlePacket.ID, FireBlockExtinguishSparkleParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(MinorBlockSparkleParticlePacket.ID, MinorBlockSparkleParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(TotemBaseActivationParticlePacket.ID, TotemBaseActivationParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(VoidConduitParticlePacket.ID, VoidConduitParticlePacket::handle);

		ClientPlayNetworking.registerGlobalReceiver(EthericNitrateParticlePacket.ID, EthericNitrateParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(MajorEntityEffectParticlePacket.ID, MajorEntityEffectParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(MinorEntityEffectParticlePacket.ID, MinorEntityEffectParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(SuccessfulSoulHarvestParticlePacket.ID, SuccessfulSoulHarvestParticlePacket::handle);
		ClientPlayNetworking.registerGlobalReceiver(VividNitrateBounceParticlePacket.ID, VividNitrateBounceParticlePacket::handle);

		ClientPlayNetworking.registerGlobalReceiver(VoidRejectionPacket.ID, VoidRejectionPacket::handle);

		ParticleFactoryRegistry.getInstance().register(MalumParticleRegistry.SCYTHE_CUT_ATTACK_PARTICLE, ScytheAttackParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(MalumParticleRegistry.SCYTHE_SWEEP_ATTACK_PARTICLE, ScytheAttackParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(MalumParticleRegistry.SPIRIT_FLAME_PARTICLE, SpiritFlameParticleType.Factory::new);





		HandledScreens.register(MalumScreenHandlerRegistry.SPIRIT_POUCH_SCREEN_HANDLER, SpiritPouchScreen::new);

		BlockRenderLayerMap.put(RenderLayer.getCutout(),
				RUNEWOOD_SAPLING,
				RUNEWOOD_DOOR,
				SOULWOOD_DOOR,
				RUNEWOOD_TRAPDOOR,
				SOULWOOD_TRAPDOOR,
				ETHER_TORCH,
				WALL_ETHER_TORCH,
				WALL_BLAZING_TORCH,
				BLAZING_TORCH,
				TAINTED_ETHER_BRAZIER,
				TWISTED_ETHER_BRAZIER,
				IRIDESCENT_ETHER_TORCH,
				IRIDESCENT_WALL_ETHER_TORCH,
				TAINTED_IRIDESCENT_ETHER_BRAZIER,
				TWISTED_IRIDESCENT_ETHER_BRAZIER,
				BRILLIANT_DEEPSLATE,
				BRILLIANT_STONE,
				BLAZING_QUARTZ_ORE,
				SPIRIT_ALTAR,
				SPIRIT_JAR,
				BLIGHTED_WEED,
				BLIGHTED_TUMOR,
				SOULWOOD_GROWTH,
				WEEPING_WELL_SIDE,
				WEEPING_WELL_CORE,
				WEEPING_WELL_CORNER,
				BUDDING_NATURAL_QUARTZ,
				SMALL_QUARTZ_BUD,
				MEDIUM_QUARTZ_BUD,
				LARGE_QUARTZ_BUD,
				NATURAL_QUARTZ_CLUSTER,
				SOUL_VIAL
		);
		registerColors();

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
			.register((spriteAtlasTexture, registry) -> {
				MalumSpiritTypeRegistry.SPIRITS.forEach((s, t) -> {
					SpriteIdentifier spriteIdentifier = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, t.getOverlayTexture());
					TotemPoleBlockEntityRenderer.overlayHashmap.put(t, spriteIdentifier);
					registry.register(t.getOverlayTexture());
					});
			});
	}

	private void registerColors() {
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null && pos == null) return 224 << 16 | 30 << 8 | 214;
			float color = state.get(MalumLeavesBlock.COLOR);
			Color maxColor = new Color(152, 6, 45);
			Color minColor = new Color(224, 30, 214);
			int red = (int) MathHelper.lerp(color / 5.0f, minColor.getRed(), maxColor.getRed());
			int green = (int) MathHelper.lerp(color / 5.0f, minColor.getGreen(), maxColor.getGreen());
			int blue = (int) MathHelper.lerp(color / 5.0f, minColor.getBlue(), maxColor.getBlue());
			return red << 16 | green << 8 | blue;
		}, SOULWOOD_LEAVES);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null && pos == null) return 251 << 16 | 193 << 8 | 76;
			float color = state.get(MalumLeavesBlock.COLOR);
			Color maxColor = new Color(175, 65, 48);
			Color minColor = new Color(251, 193, 76);
			int red = (int) MathHelper.lerp(color / 5.0f, minColor.getRed(), maxColor.getRed());
			int green = (int) MathHelper.lerp(color / 5.0f, minColor.getGreen(), maxColor.getGreen());
			int blue = (int) MathHelper.lerp(color / 5.0f, minColor.getBlue(), maxColor.getBlue());
			return red << 16 | green << 8 | blue;
		}, RUNEWOOD_LEAVES);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (tintIndex != 1 || world == null || pos == null) return -1;
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (!(blockEntity instanceof EtherBlockEntity ether)) return -1;
			return ether.firstColorRGB;
		}, WALL_ETHER_TORCH, ETHER_TORCH);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (tintIndex == -1 || tintIndex == 0 || world == null || pos == null) return -1;
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (!(blockEntity instanceof EtherBlockEntity ether)) return -1;
			return tintIndex == 1 ? ether.firstColorRGB : ether.secondColorRGB;
		}, IRIDESCENT_ETHER_TORCH, IRIDESCENT_WALL_ETHER_TORCH);
	}
}
