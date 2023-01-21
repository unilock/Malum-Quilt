package dev.sterner.malum.common.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;

public class MalumDamageSourceRegistry {
	public static final String VOODOO_IDENTIFIER = "voodoo";
	public static final String SOUL_STRIKE_IDENTIFIER = "soul_strike";
	public static final String MAGEBANE_IDENTIFIER = "magebane";
	public static final String SCYTHE_SWEEP_IDENTIFIER = "scythe_sweep";

	public static final DamageSource VOODOO = new DamageSource(VOODOO_IDENTIFIER).setUsesMagic();
	public static final DamageSource SOUL_STRIKE = new DamageSource(SOUL_STRIKE_IDENTIFIER).setUsesMagic();

	public static DamageSource causeVoodooDamage(Entity attacker) {
		return new EntityDamageSource(VOODOO_IDENTIFIER, attacker).setUsesMagic();
	}

	public static DamageSource causeSoulStrikeDamage(Entity attacker) {
		return new EntityDamageSource(SOUL_STRIKE_IDENTIFIER, attacker).setUsesMagic();
	}

	public static DamageSource causeMagebaneDamage(Entity attacker) {
		return new EntityDamageSource(MAGEBANE_IDENTIFIER, attacker).setThorns().setUsesMagic();
	}

	public static DamageSource scytheSweepDamage(Entity attacker) {
		return new EntityDamageSource(SCYTHE_SWEEP_IDENTIFIER, attacker);
	}
}
