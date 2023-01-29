package dev.sterner.malum.mixin;

import dev.sterner.malum.api.event.EntitySpawnedEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(SpawnHelper.class)
public class SpawnHelperMixin {


	@Inject(method = "spawnEntitiesInChunk(Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/SpawnHelper$Checker;Lnet/minecraft/world/SpawnHelper$Runner;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;initialize(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/entity/EntityData;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void malum$spawnEvent(SpawnGroup group, ServerWorld world, Chunk chunk, BlockPos pos, SpawnHelper.Checker checker, SpawnHelper.Runner runner, CallbackInfo ci, StructureManager structureManager, ChunkGenerator chunkGenerator, int i, BlockState blockState, BlockPos.Mutable mutable, int j, int k, int l, int m, int n, SpawnSettings.SpawnEntry spawnEntry, EntityData entityData, int o, int p, int q, double d, double e, PlayerEntity playerEntity, double f, MobEntity mobEntity) {
		EntitySpawnedEvent.EVENT.invoker().onEntitySpawned(mobEntity, world, (float)mobEntity.getX(), (float)mobEntity.getY(), (float)mobEntity.getZ(), null, SpawnReason.NATURAL);
	}

}
