package dev.sterner.malum.common.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;

public interface MalumDamageSourceRegistry {
	String VOODOO_IDENTIFIER = "voodoo";
	String SOUL_STRIKE_IDENTIFIER = "soul_strike";
	String MAGEBANE_IDENTIFIER = "magebane";
	String SCYTHE_SWEEP_IDENTIFIER = "scythe_sweep";

	DamageSource VOODOO = new DamageSource(VOODOO_IDENTIFIER).setUsesMagic();
	DamageSource SOUL_STRIKE = new DamageSource(SOUL_STRIKE_IDENTIFIER).setUsesMagic();

	static DamageSource causeVoodooDamage(Entity attacker) {
		return new EntityDamageSource(VOODOO_IDENTIFIER, attacker).setUsesMagic();
	}

	static DamageSource causeSoulStrikeDamage(Entity attacker) {
		return new EntityDamageSource(SOUL_STRIKE_IDENTIFIER, attacker).setUsesMagic();
	}

	static DamageSource causeMagebaneDamage(Entity attacker) {
		return new EntityDamageSource(MAGEBANE_IDENTIFIER, attacker).setThorns().setUsesMagic();
	}

	static DamageSource scytheSweepDamage(Entity attacker) {
		return new EntityDamageSource(SCYTHE_SWEEP_IDENTIFIER, attacker);
	}
}
