package dev.sterner.malum.common.rite.effect;

public abstract class EntityAffectingRiteEffect extends MalumRiteEffect {
	public EntityAffectingRiteEffect() {
		super();
	}

	@Override
	public int getRiteEffectTickRate() {
		return BASE_TICK_RATE*2;
	}

	@Override
	public int getRiteEffectRadius() {
		return BASE_RADIUS*2;
	}
}
