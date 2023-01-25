package dev.sterner.malum.mixin;

import dev.sterner.malum.api.event.LivingEntityDamageEvent;
import dev.sterner.malum.common.item.equipment.trinket.CurioVoraciousRing;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import dev.sterner.malum.common.util.handler.SpiritHarvestHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.sammy.lodestone.setup.LodestoneAttributeRegistry.MAGIC_RESISTANCE;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract double getAttributeValue(EntityAttribute attribute);

	@Shadow
	public abstract @Nullable StatusEffectInstance getStatusEffect(StatusEffect effect);

	@Shadow public abstract ItemStack getStackInHand(Hand hand);

	@Shadow public abstract void setHealth(float health);

	@Shadow public abstract boolean clearStatusEffects();

	@Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

	@Shadow public abstract void setStackInHand(Hand hand, ItemStack stack);

	@Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

	@Shadow
	protected int itemUseTimeLeft;

	@Shadow
	public abstract ItemStack getActiveItem();

	public LivingEntityMixin(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "createLivingAttributes", at = @At("RETURN"))
	private static void malum$createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		MalumAttributeRegistry.ATTRIBUTES.forEach((id, entityAttribute) -> info.getReturnValue().add(entityAttribute));
	}

	@Inject(method = "setCurrentHand", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I", shift = At.Shift.AFTER))
	private void malum$affectTimeLeft(Hand hand, CallbackInfo ci) {
		itemUseTimeLeft = CurioVoraciousRing.accelerateEating((LivingEntity) (Object) this, itemUseTimeLeft);

	}

	@Inject(method = "onTrackedDataSet", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I", shift = At.Shift.AFTER))
	private void malum$affectTimeLeft(TrackedData<?> data, CallbackInfo ci) {
		itemUseTimeLeft = CurioVoraciousRing.accelerateEating((LivingEntity) (Object) this, itemUseTimeLeft);

	}

	@Inject(method = "onDeath", at = @At("HEAD"))
	private void malum$onDeath(DamageSource source, CallbackInfo ci) {
		if (!world.isClient) {
			SpiritHarvestHandler.shatterSoul(source, (LivingEntity) (Object) this);
		}
	}

	@Inject(method = "damage", at = @At("HEAD"))
	private void malum$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (!world.isClient) {
			SpiritHarvestHandler.exposeSoul(source, amount, (LivingEntity) (Object) this);
		}
	}

	@Inject(method = "damage", at = @At("RETURN"), cancellable = true)
	private void malum$eventInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
		boolean result = LivingEntityDamageEvent.ON_DAMAGE_EVENT.invoker().react((LivingEntity) (Object) this, source.getAttacker(), getWorld());
		if (result) {
			cir.setReturnValue(false);
		}
	}

	@ModifyVariable(method = "applyDamage", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/entity/LivingEntity;getHealth()F"), ordinal = 0, argsOnly = true)
	private float malum$modifyDamage(float amount) {
		if(amount > 0){
			LivingEntity attacked = (LivingEntity) (Object) this;
			return LivingEntityDamageEvent.MODIFY_EVENT.invoker().modify(attacked, getWorld(), amount);
		}
		return amount;
	}

	@ModifyVariable(method = "applyEnchantmentsToDamage", at = @At(value = "RETURN", ordinal = 2, shift = At.Shift.BEFORE), index = 2, argsOnly = true)
	private float malum$applyEnchantmentsToDamage(float value, DamageSource source, float amount) {
		if (source == DamageSource.MAGIC) {
			float multiplier = 1.0f - (float) Math.max(((1 - (0.5 * (1 / (0.6 * this.getAttributeValue(MAGIC_RESISTANCE))))) * 0.6), 0);
			return value * multiplier;
		}
		return value;
	}

	@ModifyVariable(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasNoGravity()Z", ordinal = 1), index = 2)
	private double malum$setVelocity(double d) {
		// todo, fix corrupted aerial aura
		return d;
	}

	@ModifyArg(method = "jump", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V", ordinal = 0), index = 1)
	private double malum$jump(double y) {
        /*
        if (this.getStatusEffect(CORRUPTED_AERIAL_AURA) != null) {
            //noinspection ConstantConditions
            return y + this.getStatusEffect(CORRUPTED_AERIAL_AURA).getAmplifier() * 0.15d;
        }


         */

		return y;
	}

	@Inject(method = "consumeItem", at = @At(value = "INVOKE", shift = At.Shift.BY, by = 2, target = "Lnet/minecraft/item/ItemStack;finishUsing(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void malum$onFinishUsing(CallbackInfo ci, Hand hand, ItemStack result) {
		CurioVoraciousRing.finishEating((LivingEntity)(Object) this, result);
	}
}
