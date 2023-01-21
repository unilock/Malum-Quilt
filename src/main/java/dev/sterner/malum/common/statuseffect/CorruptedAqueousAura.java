package dev.sterner.malum.common.statuseffect;

import com.sammy.lodestone.helpers.ColorHelper;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class CorruptedAqueousAura extends StatusEffect {
    public CorruptedAqueousAura() {
        super(StatusEffectType.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypeRegistry.AQUEOUS_SPIRIT.getColor()));
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

    }
}
