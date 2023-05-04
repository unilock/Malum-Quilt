package dev.sterner.malum.mixin.common;

import dev.sterner.malum.api.event.LivingEntityEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.UpdateAttackTargetTask;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UpdateAttackTargetTask.class)
public class UpdateAttackTargetTaskMixin {

	@Inject(method = "updateAttackTarget", at = @At("TAIL"))
	private static <E extends MobEntity> void gore_lib$updateAttackTarget(E mob, LivingEntity livingEntity, CallbackInfo ci) {
		LivingEntityEvent.ON_TARGETING_EVENT.invoker().react(mob, null);
	}
}
