package dev.sterner.malum.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.HolderLookup;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MalumTagProviders {

	public static class MalumBlockTags extends FabricTagProvider.BlockTagProvider {
		public MalumBlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}


		@Override
		protected void configure(HolderLookup.Provider arg) {

		}
	}

	public static class MalumItemTags extends FabricTagProvider.ItemTagProvider {
		public MalumItemTags(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(dataOutput, completableFuture, new MalumTagProviders.MalumBlockTags(dataOutput, completableFuture));
		}

		@Override
		protected void configure(HolderLookup.Provider arg) {

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
