package dev.sterner.malum.common.rite.effect;

import dev.sterner.malum.common.blockentity.totem.TotemBaseBlockEntity;
import net.minecraft.util.math.BlockPos;

public abstract class BlockAffectingRiteEffect extends MalumRiteEffect {

	public BlockAffectingRiteEffect() {
		super();
	}

	@Override
	public BlockPos getRiteEffectCenter(TotemBaseBlockEntity totemBase) {
		return totemBase.getPos().offset(totemBase.direction, getRiteEffectRadius() + 1);
	}

	@Override
	public int getRiteEffectTickRate() {
		return BASE_TICK_RATE*5;
	}
}
