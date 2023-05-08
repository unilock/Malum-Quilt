package dev.sterner.malum.data;

import dev.sterner.malum.Malum;
import dev.sterner.malum.common.item.NodeItem;
import dev.sterner.malum.common.registry.MalumBlockProperties;
import me.alphamode.forgetags.Tags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static dev.sterner.malum.common.registry.MalumObjects.*;
import static dev.sterner.malum.common.registry.MalumTagRegistry.*;
import static net.minecraft.tag.BlockTags.*;

public class MalumTagProviders {

	public static class MalumBlockTags extends FabricTagProvider.BlockTagProvider {
		public static final Map<MalumBlockProperties.BlockSettings, MalumDatagenBlockData> CACHE = new HashMap<>();

		public MalumBlockTags(FabricDataGenerator dataGenerator) {
			super(dataGenerator);
		}

		@Override
		protected void generateTags() {
			BLOCKS.keySet().forEach((block) ->
			{
				Malum.LOGGER.info("Block: " + block);
				MalumBlockProperties.BlockSettings settings = (MalumBlockProperties.BlockSettings) block.settings;
				MalumDatagenBlockData data = settings.getDatagenData();
				data.getTags().forEach((tag) ->
				{
					Malum.LOGGER.info("- " + tag.id().toString());
					getOrCreateTagBuilder(tag).add(block);
				});
			});
		}

		@NotNull
		private Block[] getModBlocks(Predicate<Block> predicate) {
			List<Block> ret = new ArrayList<>(Collections.emptyList());
			BLOCKS.keySet().stream().filter(predicate).forEach(ret::add);
			return ret.toArray(new Block[0]);
		}


	}

	public static class MalumItemTags extends FabricTagProvider.ItemTagProvider {

		public MalumItemTags(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
			super(dataGenerator, blockTagProvider);
		}

		@Override
		protected void generateTags() {
			getOrCreateTagBuilder(Tags.Items.GEMS).add(PROCESSED_SOULSTONE, BLAZING_QUARTZ);

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
			this.copy(Tags.Blocks.ORES, Tags.Items.ORES);

			getOrCreateTagBuilder(Tags.Items.SLIMEBALLS).add(HOLY_SAPBALL, UNHOLY_SAPBALL);
			getOrCreateTagBuilder(SAPBALLS).add(HOLY_SAPBALL, UNHOLY_SAPBALL);
			getOrCreateTagBuilder(Tags.Items.GEMS_QUARTZ).add(NATURAL_QUARTZ);
			getOrCreateTagBuilder(Tags.Items.ORES_QUARTZ).add(NATURAL_QUARTZ_ORE.asItem(), DEEPSLATE_QUARTZ_ORE.asItem());
			getOrCreateTagBuilder(GROSS_FOODS).add(Items.ROTTEN_FLESH, ROTTING_ESSENCE);
			ITEMS.keySet().stream().filter(i -> i instanceof NodeItem).forEach(i -> {
				getOrCreateTagBuilder(METAL_NODES).add(i);
			});

			getOrCreateTagBuilder(PROSPECTORS_TREASURE).addTags(Tags.Items.ORES, Tags.Items.STORAGE_BLOCKS, Tags.Items.INGOTS, Tags.Items.NUGGETS, Tags.Items.GEMS, Tags.Items.RAW_MATERIALS, ItemTags.COALS, METAL_NODES);
			getOrCreateTagBuilder(PROSPECTORS_TREASURE).addOptional(new Identifier("tetra", "geode"));

			getOrCreateTagBuilder(RUNEWOOD_LOGS_BLOCK).add(RUNEWOOD_LOG.asItem(), STRIPPED_RUNEWOOD_LOG.asItem(), RUNEWOOD.asItem(), STRIPPED_RUNEWOOD.asItem(), EXPOSED_RUNEWOOD_LOG.asItem(), REVEALED_RUNEWOOD_LOG.asItem());
			getOrCreateTagBuilder(SOULWOOD_LOGS_BLOCK).add(SOULWOOD_LOG.asItem(), STRIPPED_SOULWOOD_LOG.asItem(), SOULWOOD.asItem(), STRIPPED_SOULWOOD.asItem(), EXPOSED_SOULWOOD_LOG.asItem(), REVEALED_SOULWOOD_LOG.asItem(), BLIGHTED_SOULWOOD.asItem());

			getOrCreateTagBuilder(SCYTHE).add(CRUDE_SCYTHE, SOUL_STAINED_STEEL_SCYTHE, CREATIVE_SCYTHE);

			getOrCreateTagBuilder(SOUL_HUNTER_WEAPON).add(TYRVING, CRUDE_SCYTHE, SOUL_STAINED_STEEL_SCYTHE, CREATIVE_SCYTHE);
			getOrCreateTagBuilder(SOUL_HUNTER_WEAPON).add(SOUL_STAINED_STEEL_AXE, SOUL_STAINED_STEEL_PICKAXE, SOUL_STAINED_STEEL_SHOVEL, SOUL_STAINED_STEEL_SWORD, SOUL_STAINED_STEEL_HOE);

			getOrCreateTagBuilder(Tags.Items.NUGGETS).add(COPPER_NUGGET, HALLOWED_GOLD_NUGGET, SOUL_STAINED_STEEL_NUGGET);

			getOrCreateTagBuilder(TagKey.of(Registry.ITEM_KEY, new Identifier("c", "copper_nuggets"))).add(COPPER_NUGGET);
		}
	}

	public static class MalumEntityTypeTags extends FabricTagProvider.EntityTypeTagProvider {

		public MalumEntityTypeTags(FabricDataGenerator dataGenerator) {
			super(dataGenerator);
		}

		@Override
		protected void generateTags() {
			getOrCreateTagBuilder(SURVIVES_REJECTION).add(EntityType.PLAYER);
		}
	}
}
