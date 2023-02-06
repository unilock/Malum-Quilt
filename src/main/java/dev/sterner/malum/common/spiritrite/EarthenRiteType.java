package dev.sterner.malum.common.spiritrite;

import dev.sterner.malum.common.registry.MalumStatusEffectRegistry;
import dev.sterner.malum.common.spiritrite.effect.AuraRiteEffect;
import dev.sterner.malum.common.spiritrite.effect.MalumRiteEffect;
import net.minecraft.entity.LivingEntity;

import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.ARCANE_SPIRIT;
import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.EARTHEN_SPIRIT;

public class EarthenRiteType extends MalumRiteType {
	public EarthenRiteType() {
		super("earthen_rite", ARCANE_SPIRIT, EARTHEN_SPIRIT, EARTHEN_SPIRIT);
	}

	@Override
	public MalumRiteEffect getNaturalRiteEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.GAIAN_BULWARK, EARTHEN_SPIRIT);
	}

	@Override
	public MalumRiteEffect getCorruptedEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.EARTHEN_MIGHT, EARTHEN_SPIRIT);
	}
}
