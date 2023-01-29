package dev.sterner.malum.mixin;

import dev.sterner.malum.api.event.LivingEntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {

	@Inject(method = "setTarget", at = @At("HEAD"))
	private void malum$setTargetEvent(LivingEntity target, CallbackInfo ci){
		LivingEntityEvent.TARGET.invoker().react((MobEntity)(Object)this, target);
	}
}
