package dev.sterner.malum.mixin.client;

import dev.sterner.malum.Malum;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.sterner.malum.common.registry.MalumObjects.RUNEWOOD_SIGN_TYPE;
import static dev.sterner.malum.common.registry.MalumObjects.SOULWOOD_SIGN_TYPE;

@Mixin(TexturedRenderLayers.class)
final class TexturedRenderLayersMixin {
	@Inject(method = "createSignTextureId", at = @At("HEAD"), cancellable = true)
	private static void malum$changeSignNamespace(SignType type, CallbackInfoReturnable<SpriteIdentifier> cir) {
		if (type == RUNEWOOD_SIGN_TYPE || type == SOULWOOD_SIGN_TYPE) {
			cir.setReturnValue(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, Malum.id("entity/signs/" + type.getName())));
		}
	}
}
