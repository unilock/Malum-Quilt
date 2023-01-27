package dev.sterner.malum.mixin;

import net.minecraft.world.gen.BootstrapContext;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfiguredFeatureUtil.class)
public class ConfiguredFeatureUtilMixin {

	@Inject(method = "bootstrap", at = @At("HEAD"))
	private static void malum$initConfFeatures(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, CallbackInfo ci) {
		//MalumWorldRegistry.bootstrap(bootstrapContext);
	}
}
