package dev.sterner.malum.common.reaping;

import net.minecraft.recipe.Ingredient;

public class MalumReapingDropsData {

	public final Ingredient drop;
	public final float chance;
	public final int min;
	public final int max;

	public MalumReapingDropsData(Ingredient drop, float chance, int min, int max) {
		this.drop = drop;
		this.chance = chance;
		this.min = min;
		this.max = max;
	}
}
