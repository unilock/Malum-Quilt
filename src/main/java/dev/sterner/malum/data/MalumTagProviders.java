package dev.sterner.malum.data;

import dev.sterner.malum.common.item.NodeItem;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumTagRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.*;
import net.minecraft.item.Items;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import static dev.sterner.malum.common.registry.MalumObjects.*;
import static dev.sterner.malum.common.registry.MalumTagRegistry.*;
import static net.minecraft.registry.tag.BlockTags.*;

public class MalumTagProviders {

	public static class MalumBlockTags extends FabricTagProvider.BlockTagProvider {
		public MalumBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}


		@Override
		protected void configure(HolderLookup.Provider arg) {
			getOrCreateTagBuilder(SLABS).add(getModBlocks(b -> b instanceof SlabBlock));
			getOrCreateTagBuilder(STAIRS).add(getModBlocks(b -> b instanceof StairsBlock));
			getOrCreateTagBuilder(WALLS).add(getModBlocks(b -> b instanceof WallBlock));
			getOrCreateTagBuilder(FENCES).add(getModBlocks(b -> b instanceof FenceBlock));
			getOrCreateTagBuilder(FENCE_GATES).add(getModBlocks(b -> b instanceof FenceGateBlock));
			getOrCreateTagBuilder(LEAVES).add(getModBlocks(b -> b instanceof LeavesBlock));

			getOrCreateTagBuilder(DOORS).add(getModBlocks(b -> b instanceof DoorBlock));
			getOrCreateTagBuilder(TRAPDOORS).add(getModBlocks(b -> b instanceof TrapdoorBlock));
			getOrCreateTagBuilder(BUTTONS).add(getModBlocks(b -> b instanceof AbstractButtonBlock));
			getOrCreateTagBuilder(PRESSURE_PLATES).add(getModBlocks(b -> b instanceof PressurePlateBlock));
			getOrCreateTagBuilder(DIRT).add(getModBlocks(b -> b instanceof GrassBlock || b instanceof FarmlandBlock));
			getOrCreateTagBuilder(SAPLINGS).add(getModBlocks(b -> b instanceof SaplingBlock));

			getOrCreateTagBuilder(LOGS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_log") || b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("wood")));
			getOrCreateTagBuilder(PLANKS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_planks")));
			getOrCreateTagBuilder(WOODEN_BUTTONS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_button") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_FENCES).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_fence") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_DOORS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_door") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_STAIRS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_stairs") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_SLABS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_slab") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_TRAPDOORS).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_trapdoor") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));
			getOrCreateTagBuilder(WOODEN_PRESSURE_PLATES).add(getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().endsWith("_pressure_plate") && b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("wood")));

			getOrCreateTagBuilder(MalumTagRegistry.BLIGHTED_BLOCKS).add(BLIGHTED_SOIL);
			getOrCreateTagBuilder(MalumTagRegistry.BLIGHTED_PLANTS).add(BLIGHTED_WEED, BLIGHTED_TUMOR, SOULWOOD_GROWTH);

			for (Block block : getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("tainted_"))) {
				getOrCreateTagBuilder(MalumTagRegistry.TAINTED_ROCK).add(block);
			}
			for (Block block : getModBlocks(b -> b.getBuiltInRegistryHolder().getRegistryKey().getValue().getPath().contains("twisted_"))) {
				getOrCreateTagBuilder(MalumTagRegistry.TWISTED_ROCK).add(block);
			}
			getOrCreateTagBuilder(MalumTagRegistry.RITE_IMMUNE).add(RUNEWOOD_TOTEM_BASE, RUNEWOOD_TOTEM_POLE, SOULWOOD_TOTEM_BASE, SOULWOOD_TOTEM_POLE);
			getOrCreateTagBuilder(MalumTagRegistry.RITE_IMMUNE).add(MalumObjects.TAINTED_ROCK, MalumObjects.TWISTED_ROCK);

			getOrCreateTagBuilder(MalumTagRegistry.ENDLESS_FLAME);
			getOrCreateTagBuilder(MalumTagRegistry.GREATER_AERIAL_WHITELIST);


		}

		@NotNull
		private Block[] getModBlocks(Predicate<Block> predicate) {
			List<Block> ret = new ArrayList<>(Collections.emptyList());
			BLOCKS.keySet().stream().filter(predicate).forEach(ret::add);
			return ret.toArray(new Block[0]);
		}
	}

	public static class MalumItemTags extends FabricTagProvider.ItemTagProvider {
		public MalumItemTags(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(dataOutput, completableFuture, new MalumTagProviders.MalumBlockTags(dataOutput, completableFuture));
		}

		@Override
		protected void configure(HolderLookup.Provider arg) {
			this.copy(WOOL, ItemTags.WOOL);
			this.copy(PLANKS, ItemTags.PLANKS);
			this.copy(STONE_BRICKS, ItemTags.STONE_BRICKS);
			this.copy(WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
			this.copy(BUTTONS, ItemTags.BUTTONS);
			this.copy(WOOL_CARPETS, ItemTags.WOOL_CARPETS);
			this.copy(WOODEN_DOORS, ItemTags.WOODEN_DOORS);
			this.copy(WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
			this.copy(WOODEN_SLABS, ItemTags.WOODEN_SLABS);
			this.copy(WOODEN_FENCES, ItemTags.WOODEN_FENCES);
			this.copy(WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
			this.copy(DOORS, ItemTags.DOORS);
			this.copy(SAPLINGS, ItemTags.SAPLINGS);
			this.copy(LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
			this.copy(LOGS, ItemTags.LOGS);
			this.copy(SAND, ItemTags.SAND);
			this.copy(SLABS, ItemTags.SLABS);
			this.copy(WALLS, ItemTags.WALLS);
			this.copy(STAIRS, ItemTags.STAIRS);
			this.copy(ANVILS, ItemTags.ANVILS);
			this.copy(LEAVES, ItemTags.LEAVES);
			this.copy(WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
			this.copy(TRAPDOORS, ItemTags.TRAPDOORS);
			this.copy(SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
			this.copy(BEDS, ItemTags.BEDS);
			this.copy(FENCES, ItemTags.FENCES);
			this.copy(TALL_FLOWERS, ItemTags.TALL_FLOWERS);
			this.copy(FLOWERS, ItemTags.FLOWERS);
			this.copy(GOLD_ORES, ItemTags.GOLD_ORES);
			this.copy(SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);

			getOrCreateTagBuilder(SAPBALLS).add(HOLY_SAPBALL, UNHOLY_SAPBALL);
			getOrCreateTagBuilder(GROSS_FOODS).add(Items.ROTTEN_FLESH, ROTTING_ESSENCE);
			ITEMS.keySet().stream().filter(i -> i instanceof NodeItem).forEach(i -> {
				getOrCreateTagBuilder(METAL_NODES).add(i);
			});

			getOrCreateTagBuilder(RUNEWOOD_LOGS_BLOCK).add(RUNEWOOD_LOG.asItem(), STRIPPED_RUNEWOOD_LOG.asItem(), RUNEWOOD.asItem(), STRIPPED_RUNEWOOD.asItem(), EXPOSED_RUNEWOOD_LOG.asItem(), REVEALED_RUNEWOOD_LOG.asItem());
			getOrCreateTagBuilder(SOULWOOD_LOGS_BLOCK).add(SOULWOOD_LOG.asItem(), STRIPPED_SOULWOOD_LOG.asItem(), SOULWOOD.asItem(), STRIPPED_SOULWOOD.asItem(), EXPOSED_SOULWOOD_LOG.asItem(), REVEALED_SOULWOOD_LOG.asItem(), BLIGHTED_SOULWOOD.asItem());

			getOrCreateTagBuilder(SCYTHE).add(CRUDE_SCYTHE, SOUL_STAINED_STEEL_SCYTHE, CREATIVE_SCYTHE);

			getOrCreateTagBuilder(SOUL_HUNTER_WEAPON).add(TYRVING, CRUDE_SCYTHE, SOUL_STAINED_STEEL_SCYTHE, CREATIVE_SCYTHE);
			getOrCreateTagBuilder(SOUL_HUNTER_WEAPON).add(SOUL_STAINED_STEEL_AXE, SOUL_STAINED_STEEL_PICKAXE, SOUL_STAINED_STEEL_SHOVEL, SOUL_STAINED_STEEL_SWORD, SOUL_STAINED_STEEL_HOE);

		}
	}

	public static class MalumEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {
		public MalumEntityTypeTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void configure(HolderLookup.Provider arg) {

		}
	}
}
