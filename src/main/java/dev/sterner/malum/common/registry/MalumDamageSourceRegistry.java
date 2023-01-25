package dev.sterner.malum.common.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;

public interface MalumDamageSourceRegistry {
	String VOODOO_IDENTIFIER = "voodoo";
	String MAGEBANE_IDENTIFIER = "magebane";
	String SCYTHE_SWEEP_IDENTIFIER = "scythe_sweep";
	String GUARANTEED_SOUL_SHATTER = "soul_strike";

	DamageSource VOODOO = new DamageSource(VOODOO_IDENTIFIER).setUsesMagic();
	DamageSource SOUL_STRIKE = new DamageSource(GUARANTEED_SOUL_SHATTER).setUsesMagic();


    static DamageSource causeVoodooDamage(Entity attacker) {
		return new EntityDamageSource(VOODOO_IDENTIFIER, attacker).setUsesMagic();
	}

	static DamageSource causeSoulStrikeDamage(Entity attacker) {
		return new EntityDamageSource(GUARANTEED_SOUL_SHATTER, attacker).setUsesMagic();
	}

	static DamageSource causeMagebaneDamage(Entity attacker) {
		return new EntityDamageSource(MAGEBANE_IDENTIFIER, attacker).setThorns().setUsesMagic();
	}

	static DamageSource scytheSweepDamage(Entity attacker) {
		return new EntityDamageSource(SCYTHE_SWEEP_IDENTIFIER, attacker);
	}
}
