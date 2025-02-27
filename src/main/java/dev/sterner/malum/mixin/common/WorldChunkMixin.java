package dev.sterner.malum.mixin.common;

import com.sammy.lodestone.forge.CustomUpdateTagHandlingBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.BlendingData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin extends Chunk {
	public WorldChunkMixin(ChunkPos pos, UpgradeData upgradeData, HeightLimitView heightLimitView, Registry<Biome> biomeRegistry, long inhabitedTime, @Nullable ChunkSection[] sectionArrayInitializer, @Nullable BlendingData blendingData) {
		super(pos, upgradeData, heightLimitView, biomeRegistry, inhabitedTime, sectionArrayInitializer, blendingData);
	}

	@Inject(method = "method_31716" , at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;readNbt(Lnet/minecraft/nbt/NbtCompound;)V"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private void malum$handleBlockEntityUpdateTag(BlockPos pos, BlockEntityType<?> type, NbtCompound tag, CallbackInfo ci, BlockEntity blockEntity) {
		if (blockEntity instanceof CustomUpdateTagHandlingBlockEntity handler) {
			handler.handleUpdateTag(tag);
			ci.cancel();
		}
	}
}
