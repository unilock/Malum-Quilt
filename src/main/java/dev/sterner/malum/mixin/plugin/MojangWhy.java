package dev.sterner.malum.mixin.plugin;

import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Util.class)
public class MojangWhy {
	@Inject(at = @At("HEAD"), method = "getMaxBackgroundThreads", cancellable = true)
	private static void stop(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(6);
	}
}
