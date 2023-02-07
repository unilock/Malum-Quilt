package dev.sterner.malum.mixin.common;

import dev.sterner.malum.api.event.EntitySpawnedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(MobSpawnerLogic.class)
public class MobSpawnerLogicMixin {

	@Inject(method = "serverTick",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;initialize(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/entity/EntityData;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void serverTick(ServerWorld world, BlockPos pos, CallbackInfo ci, boolean bl, RandomGenerator randomGenerator, MobSpawnerEntry mobSpawnerEntry, int i, NbtCompound nbtCompound, Optional optional, NbtList nbtList, int j, double d, double e, double f, BlockPos blockPos, Entity entity) {
		EntitySpawnedEvent.EVENT.invoker().onEntitySpawned(entity, world, (float)entity.getX(), (float)entity.getY(), (float)entity.getZ(), (MobSpawnerLogic) (Object) this, SpawnReason.SPAWNER);
	}
}
