package dev.sterner.malum.data;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.HolderLookup;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MalumLootTableProviders {

	public static void registerProviders(FabricDataGenerator.Pack dataGenerator) {
		dataGenerator.addProvider(BlockLoot::new);
		dataGenerator.addProvider(EntityLoot::new);
	}

	public static class BlockLoot extends FabricBlockLootTableProvider {

		public BlockLoot(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void m_mkxtlejp() {

		}

		@Override
		public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {

		}
	}

	public static class EntityLoot extends SimpleFabricLootTableProvider {
		private final Map<Identifier, LootTable.Builder> loot = Maps.newHashMap();

		public EntityLoot(FabricDataOutput output) {
			super(output, LootContextTypes.ENTITY);
		}

		protected void generateLoot() {

		}

		@Override
		public void accept(BiConsumer<Identifier, LootTable.Builder> consumer) {
			this.generateLoot();
			for (Map.Entry<Identifier, LootTable.Builder> entry : loot.entrySet()) {
				consumer.accept(entry.getKey(), entry.getValue());
			}
		}
	}
}
