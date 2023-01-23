package dev.sterner.malum.common.statuseffect;

import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneAttributeRegistry;
import dev.sterner.malum.common.registry.MalumStatusEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class GluttonyEffect extends StatusEffect {
    public GluttonyEffect() {
        super(StatusEffectType.BENEFICIAL, ColorHelper.getColor(88, 86, 60));
        addAttributeModifier(LodestoneAttributeRegistry.MAGIC_PROFICIENCY, "4d82fd0a-24b6-45f5-8d7a-983f99fd6783", 2f, EntityAttributeModifier.Operation.ADDITION);
    }

    public static void canApplyPotion(LivingEntity livingEntity) {
        var collection = livingEntity.getStatusEffects();
        collection.forEach(statusEffectInstance -> {
            if (statusEffectInstance.getEffectType().equals(StatusEffects.HUNGER) && livingEntity.hasStatusEffect(MalumStatusEffectRegistry.GLUTTONY)) {
                //TODO event.setResult(Event.Result.DENY);
            }
        });
    }
/*
    public static void finishEating(ItemStack itemStack, LivingEntity livingEntity) {
        if (itemStack.isIn(GROSS_FOODS)) {
            var effect = livingEntity.getStatusEffect(MalumStatusEffectRegistry.GLUTTONY);
            if (effect != null) {
                EntityHelper.extendEffect(effect, livingEntity, 200, 1000);
                World world = livingEntity.getWorld();
                world.playSound(null, livingEntity.getBlockPos(), MalumSoundRegistry.HUNGRY_BELT_FEEDS, SoundCategory.PLAYERS, 1.7f, 1.2f + world.random.nextFloat() * 0.5f);
            }
        }
    }

 */

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            player.addExhaustion(0.004f * (amplifier + 1));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
