package dev.sterner.malum.mixin.common;

import dev.sterner.malum.api.event.LivingEntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerEntityManager.class)
public class ServerEntityManagerMixin<T extends EntityLike> {

	@Inject(method = "addEntity(Lnet/minecraft/world/entity/EntityLike;Z)Z", at = @At("HEAD"))
	private void malum$injectEvent(T entity, boolean existing, CallbackInfoReturnable<Boolean> cir){
		if(entity instanceof LivingEntity livingEntity){
			LivingEntityEvent.ADDED_EVENT.invoker().react(livingEntity, existing);
		}
	}
}
