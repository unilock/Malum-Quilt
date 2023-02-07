package dev.sterner.malum.mixin;

import dev.sterner.malum.api.event.LivingEntityEvent;
import net.minecraft.entity.ai.brain.task.UpdateAttackTargetTask;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.unmapped.C_ujlmiamh;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;
import java.util.function.Predicate;

@Mixin(UpdateAttackTargetTask.class)
public class UpdateAttackTargetTaskMixin {

	@Inject(method = "m_msxejphh(Ljava/util/function/Predicate;Ljava/util/function/Function;Lnet/minecraft/unmapped/C_ujlmiamh;Lnet/minecraft/unmapped/C_ujlmiamh;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/MobEntity;J)Z",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/unmapped/C_ujlmiamh;m_klbizcog(Ljava/lang/Object;)V"))
	private static void malum$injectEvent(Predicate predicate, Function function, C_ujlmiamh c_ujlmiamh, C_ujlmiamh c_ujlmiamh2, ServerWorld world, MobEntity mobEntity, long l, CallbackInfoReturnable<Boolean> cir){
		LivingEntityEvent.ON_TARGETING_EVENT.invoker().react(mobEntity, null);
	}
}
