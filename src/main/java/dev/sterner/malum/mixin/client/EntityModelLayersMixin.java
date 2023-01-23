package dev.sterner.malum.mixin.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.sterner.malum.Malum.MODID;

@Mixin(EntityModelLayers.class)
final class EntityModelLayersMixin {
	@Inject(method = "create", at = @At("HEAD"), cancellable = true)
	private static void malum$changeSignNamespace(String id, String layer, CallbackInfoReturnable<EntityModelLayer> cir) {
		if (id.equals("sign/runewood") || id.equals("sign/soulwood")) {
			cir.setReturnValue(new EntityModelLayer(new Identifier(MODID, id), layer));
		}
	}
}
