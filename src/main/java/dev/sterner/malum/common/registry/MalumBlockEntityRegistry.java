package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.block.ether.EtherBlock;
import dev.sterner.malum.common.block.mirror.EmitterMirrorBlock;
import dev.sterner.malum.common.block.storage.ItemPedestalBlock;
import dev.sterner.malum.common.block.storage.ItemStandBlock;
import dev.sterner.malum.common.block.totem.TotemBaseBlock;
import dev.sterner.malum.common.block.totem.TotemPoleBlock;
import dev.sterner.malum.common.blockentity.EtherBlockEntity;
import dev.sterner.malum.common.blockentity.FusionPlateBlockEntity;
import dev.sterner.malum.common.blockentity.alteration_plinth.AlterationPlinthBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCatalyzerCoreBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCrucibleCoreBlockEntity;
import dev.sterner.malum.common.blockentity.mirror.EmitterMirrorBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.BrilliantObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.RunewoodObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.spirit_altar.SpiritAltarBlockEntity;
import dev.sterner.malum.common.blockentity.storage.*;
import dev.sterner.malum.common.blockentity.tablet.TwistedTabletBlockEntity;
import dev.sterner.malum.common.blockentity.totem.TotemBaseBlockEntity;
import dev.sterner.malum.common.blockentity.totem.TotemPoleBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static dev.sterner.malum.Malum.MODID;

public interface MalumBlockEntityRegistry {
	Map<Identifier, BlockEntityType<?>> BLOCK_ENTITY_TYPES  = new LinkedHashMap<>();


	BlockEntityType<SpiritAltarBlockEntity> SPIRIT_ALTAR = register("spirit_altar",  BlockEntityType.Builder.create(SpiritAltarBlockEntity::new, MalumObjects.SPIRIT_ALTAR).build(null));
	BlockEntityType<SpiritJarBlockEntity> SPIRIT_JAR = register("spirit_jar",  BlockEntityType.Builder. create(SpiritJarBlockEntity::new, MalumObjects.SPIRIT_JAR).build(null));
	BlockEntityType<SoulVialBlockEntity> SOUL_VIAL = register("soul_vial",  BlockEntityType.Builder. create(SoulVialBlockEntity::new, MalumObjects.SOUL_VIAL).build(null));

	BlockEntityType<SpiritCrucibleCoreBlockEntity> SPIRIT_CRUCIBLE = register("spirit_crucible",  BlockEntityType.Builder. create(SpiritCrucibleCoreBlockEntity::new, MalumObjects.SPIRIT_CRUCIBLE).build(null));
	BlockEntityType<SpiritCatalyzerCoreBlockEntity> SPIRIT_CATALYZER = register("spirit_catalyzer",  BlockEntityType.Builder. create(SpiritCatalyzerCoreBlockEntity::new, MalumObjects.SPIRIT_CATALYZER).build(null));
	BlockEntityType<TwistedTabletBlockEntity> TWISTED_TABLET = register("twisted_tablet",  BlockEntityType.Builder. create(TwistedTabletBlockEntity::new, MalumObjects.TWISTED_TABLET).build(null));
	BlockEntityType<RunewoodObeliskBlockEntity> RUNEWOOD_OBELISK = register("runewood_obelisk",  BlockEntityType.Builder. create(RunewoodObeliskBlockEntity::new, MalumObjects.RUNEWOOD_OBELISK).build(null));
	BlockEntityType<BrilliantObeliskBlockEntity> BRILLIANT_OBELISK = register("brilliant_obelisk",  BlockEntityType.Builder. create(BrilliantObeliskBlockEntity::new, MalumObjects.BRILLIANT_OBELISK).build(null));
	BlockEntityType<AlterationPlinthBlockEntity> ALTERATION_PLINTH = register("alteration_plinth",  BlockEntityType.Builder. create(AlterationPlinthBlockEntity::new, MalumObjects.ALTERATION_PLINTH).build(null));
	BlockEntityType<PlinthCoreBlockEntity> PLINTH = register("plinth",  BlockEntityType.Builder. create(PlinthCoreBlockEntity::new, MalumObjects.SOULWOOD_PLINTH).build(null));
	BlockEntityType<FusionPlateBlockEntity> FUSION_PLATE = register("fusion_plate",  BlockEntityType.Builder. create(FusionPlateBlockEntity::new, MalumObjects.SOULWOOD_FUSION_PLATE).build(null));

	BlockEntityType<EtherBlockEntity> ETHER = register("ether",  BlockEntityType.Builder. create(EtherBlockEntity::new, getBlocks(EtherBlock.class)).build(null));

	BlockEntityType<ItemStandBlockEntity> ITEM_STAND = register("item_stand",  BlockEntityType.Builder. create(ItemStandBlockEntity::new, getBlocks(ItemStandBlock.class)).build(null));
	BlockEntityType<ItemPedestalBlockEntity> ITEM_PEDESTAL = register("item_pedestal",  BlockEntityType.Builder. create(ItemPedestalBlockEntity::new, getBlocks(ItemPedestalBlock.class)).build(null));

	BlockEntityType<EmitterMirrorBlockEntity> EMITTER_MIRROR = register("emitter_mirror",  BlockEntityType.Builder. create(EmitterMirrorBlockEntity::new, getBlocks(EmitterMirrorBlock.class)).build(null));

	BlockEntityType<TotemBaseBlockEntity> TOTEM_BASE = register("totem_base",  BlockEntityType.Builder. create(TotemBaseBlockEntity::new, getBlocks(TotemBaseBlock.class)).build(null));
	BlockEntityType<TotemPoleBlockEntity> TOTEM_POLE = register("totem_pole",  BlockEntityType.Builder. create(TotemPoleBlockEntity::new, getBlocks(TotemPoleBlock.class)).build(null));



	static Block[] getBlocks(Class<?>... blockClasses) {
		Registry<Block> blocks = Registries.BLOCK;
		ArrayList<Block> matchingBlocks = new ArrayList<>();
		for (Block block : blocks) {
			if (Arrays.stream(blockClasses).anyMatch(b -> b.isInstance(block))) {
				matchingBlocks.add(block);
			}
		}
		return matchingBlocks.toArray(new Block[0]);
	}

	static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
		BLOCK_ENTITY_TYPES.put(new Identifier(MODID, id), type);
		return type;
	}

	static void init() {
		BLOCK_ENTITY_TYPES.forEach((id, entityType) -> Registry.register(Registries.BLOCK_ENTITY_TYPE, id, entityType));
	}
}
