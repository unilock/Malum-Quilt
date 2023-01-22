package dev.sterner.malum;

import com.sammy.lodestone.helpers.DataHelper;
import dev.sterner.malum.client.model.SoulStainedSteelArmorModel;
import dev.sterner.malum.client.model.SpiritHunterArmorModel;
import dev.sterner.malum.client.render.CloakArmorRenderer;
import dev.sterner.malum.client.render.SteelArmorRenderer;
import dev.sterner.malum.client.render.block.*;
import dev.sterner.malum.client.render.entity.EthericNitrateEntityRenderer;
import dev.sterner.malum.client.render.entity.FloatingItemEntityRenderer;
import dev.sterner.malum.client.render.entity.ScytheBoomerangEntityRenderer;
import dev.sterner.malum.client.render.entity.VividNitrateEntityRenderer;
import dev.sterner.malum.client.render.item.ScytheItemRenderer;
import dev.sterner.malum.client.screen.SpiritPouchScreen;
import dev.sterner.malum.common.block.MalumLeavesBlock;
import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import dev.sterner.malum.common.network.packet.s2c.block.*;
import dev.sterner.malum.common.network.packet.s2c.block.blight.BlightMistParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.block.blight.BlightTransformItemParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.block.functional.AltarConsumeParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.block.functional.AltarCraftParticlePacket;
import dev.sterner.malum.common.registry.*;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.util.function.Function;

import static dev.sterner.malum.Malum.MODID;
import static dev.sterner.malum.common.registry.MalumBlockRegistry.*;

public class MalumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityModelLayerRegistry.registerModelLayer(SpiritHunterArmorModel.LAYER, SpiritHunterArmorModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(SoulStainedSteelArmorModel.LAYER, SoulStainedSteelArmorModel::getTexturedModelData);
		ArmorRenderer.register(new CloakArmorRenderer(DataHelper.prefix("textures/armor/spirit_hunter_reforged.png")), MalumItemRegistry.SOUL_HUNTER_CLOAK, MalumItemRegistry.SOUL_HUNTER_ROBE, MalumItemRegistry.SOUL_HUNTER_LEGGINGS, MalumItemRegistry.SOUL_HUNTER_BOOTS);
		ArmorRenderer.register(new SteelArmorRenderer(DataHelper.prefix("textures/armor/soul_stained_steel.png")), MalumItemRegistry.SOUL_STAINED_STEEL_HELMET, MalumItemRegistry.SOUL_STAINED_STEEL_CHESTPLATE, MalumItemRegistry.SOUL_STAINED_STEEL_LEGGINGS, MalumItemRegistry.SOUL_STAINED_STEEL_BOOTS);
		EntityRendererRegistry.register(MalumEntityRegistry.SCYTHE_BOOMERANG, ScytheBoomerangEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.NATURAL_SPIRIT, FloatingItemEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.ETHERIC_NITRATE, EthericNitrateEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.VIVID_NITRATE, VividNitrateEntityRenderer::new);
		EntityRendererRegistry.register(MalumEntityRegistry.MIRROR_ITEM, FloatingItemEntityRenderer::new);

		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_ALTAR, SpiritAltarRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_CRUCIBLE, SpiritCrucibleRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_CATALYZER, SpiritCatalyzerRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.TWISTED_TABLET, ItemStandRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.PLINTH, PlinthRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.TOTEM_POLE, TotemPoleRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ITEM_STAND, ItemStandRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ITEM_PEDESTAL, ItemPedestalRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.ALTERATION_PLINTH, ItemPedestalRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SPIRIT_JAR, SpiritJarRenderer::new);
		BlockEntityRendererFactories.register(MalumBlockEntityRegistry.SOUL_VIAL, SoulVialRenderer::new);


		for (Item item : MalumItemRegistry.SCYTHES) {
			Identifier scytheId = Registries.ITEM.getId(item);
			ScytheItemRenderer scytheItemRenderer = new ScytheItemRenderer(scytheId);
			ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(scytheItemRenderer);
			BuiltinItemRendererRegistry.INSTANCE.register(item, scytheItemRenderer);
			ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
				out.accept(new ModelIdentifier(scytheId.withPath(scytheId.getPath() + "_gui"), "inventory"));
				out.accept(new ModelIdentifier(scytheId.withPath(scytheId.getPath() + "_handheld"), "inventory"));
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

		HandledScreens.register(MalumScreenHandlerRegistry.SPIRIT_POUCH_SCREEN_HANDLER, SpiritPouchScreen::new);

		BlockRenderLayerMap.put(RenderLayer.getCutout(),
				RUNEWOOD_SAPLING,
				RUNEWOOD_DOOR,
				SOULWOOD_DOOR,
				RUNEWOOD_TRAPDOOR,
				SOULWOOD_TRAPDOOR,
				ETHER_TORCH,
				ETHER_SCONCE,
				WALL_ETHER_TORCH,
				WALL_ETHER_SCONCE,
				WALL_BLAZING_TORCH,
				BLAZING_TORCH,
				TAINTED_ETHER_BRAZIER,
				TWISTED_ETHER_BRAZIER,
				IRIDESCENT_ETHER_TORCH,
				IRIDESCENT_WALL_ETHER_TORCH,
				IRIDESCENT_WALL_ETHER_SCONCE,
				TAINTED_IRIDESCENT_ETHER_BRAZIER,
				TWISTED_IRIDESCENT_ETHER_BRAZIER,
				BRILLIANT_DEEPSLATE,
				BRILLIANT_STONE,
				BLAZING_QUARTZ_ORE,
				SPIRIT_ALTAR,
				SPIRIT_JAR
		);
		registerColors();
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
