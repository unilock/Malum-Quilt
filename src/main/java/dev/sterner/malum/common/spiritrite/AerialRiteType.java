package dev.sterner.malum.common.spiritrite;

import dev.sterner.malum.common.registry.MalumStatusEffectRegistry;
import dev.sterner.malum.common.spiritrite.effect.AuraRiteEffect;
import dev.sterner.malum.common.spiritrite.effect.MalumRiteEffect;
import net.minecraft.entity.LivingEntity;

import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.AERIAL_SPIRIT;
import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.ARCANE_SPIRIT;

public class AerialRiteType extends MalumRiteType {
	public AerialRiteType() {
		super("aerial_rite", ARCANE_SPIRIT, AERIAL_SPIRIT, AERIAL_SPIRIT);
	}

	@Override
	public MalumRiteEffect getNaturalRiteEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.ZEPHYRS_COURAGE, AERIAL_SPIRIT);
	}

	@Override
	public MalumRiteEffect getCorruptedEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.AETHERS_CHARM, AERIAL_SPIRIT);
	}
}
