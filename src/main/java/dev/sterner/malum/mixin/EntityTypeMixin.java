package dev.sterner.malum.mixin;

import dev.sterner.malum.api.event.EntitySpawnedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(EntityType.class)
public class EntityTypeMixin {


	@Inject(method = "m_ixnkjsoi",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnEntityAndPassengers(Lnet/minecraft/entity/Entity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void malum$spawnEvent(ServerWorld world, @Nullable NbtCompound nbtCompound, @Nullable Consumer consumer, BlockPos pos, SpawnReason reason, boolean bl, boolean bl2, CallbackInfoReturnable cir, Entity entity) {
		EntitySpawnedEvent.EVENT.invoker().onEntitySpawned(entity, world, (float)entity.getX(), (float)entity.getY(), (float)entity.getZ(), null, reason);
	}
}
