package dev.sterner.malum.common.rite;

import dev.sterner.malum.common.registry.MalumStatusEffectRegistry;
import dev.sterner.malum.common.rite.effect.AuraRiteEffect;
import dev.sterner.malum.common.rite.effect.MalumRiteEffect;
import net.minecraft.entity.LivingEntity;

import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.AQUEOUS_SPIRIT;
import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.ARCANE_SPIRIT;

public class AqueousRiteType extends MalumRiteType {
	public AqueousRiteType() {
		super("aqueous_rite", ARCANE_SPIRIT, AQUEOUS_SPIRIT, AQUEOUS_SPIRIT);
	}

	@Override
	public MalumRiteEffect getNaturalRiteEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.POSEIDONS_GRASP, AQUEOUS_SPIRIT);
	}

	@Override
	public MalumRiteEffect getCorruptedEffect() {
		return new AuraRiteEffect(LivingEntity.class, MalumStatusEffectRegistry.ANGLERS_LURE, AQUEOUS_SPIRIT);
	}
}
