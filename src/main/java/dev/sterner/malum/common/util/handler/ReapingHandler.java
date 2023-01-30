package dev.sterner.malum.common.util.handler;

import com.sammy.lodestone.helpers.ItemHelper;
import dev.sterner.malum.MalumConfig;
import dev.sterner.malum.common.component.MalumComponents;
import dev.sterner.malum.common.reaping.MalumReapingDropsData;
import dev.sterner.malum.common.reaping.ReapingDataReloadListener;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ReapingHandler {

	public static void tryCreateReapingDrops(LivingEntity target, DamageSource damageSource) {
		LivingEntity attacker = null;
		if (damageSource.getAttacker() instanceof LivingEntity directAttacker) {
			attacker = directAttacker;
		}
		if (attacker == null) {
			attacker = target.getAttacker();
		}
		if (MalumConfig.AWARD_CODEX_ON_KILL) {
			if (target.getGroup().equals(EntityGroup.UNDEAD) && attacker instanceof PlayerEntity player) {
				MalumComponents.PLAYER_COMPONENT.maybeGet(player).ifPresent(c -> {
					if (!c.obtainedEncyclopedia) {
						c.obtainedEncyclopedia = true;
						SpiritHelper.createSpiritEntities(List.of(MalumObjects.ENCYCLOPEDIA_ARCANA.getDefaultStack()), target, 1.25f, player);
					}
				});
			}
		}
		List<MalumReapingDropsData> data = ReapingDataReloadListener.REAPING_DATA.get(target.getType().getBuiltInRegistryHolder().getRegistryKey().getValue());
		if (data != null) {
			var capability = MalumComponents.SPIRIT_COMPONENT.get(target);
			float multiplier = capability.exposedSoul > 0 ? 1 : 0.35f;
			for (MalumReapingDropsData dropData : data) {
				World world = target.world;
				var random = world.random;
				if (random.nextFloat() < dropData.chance * multiplier) {
					Ingredient ingredient = dropData.drop;
					ItemStack stack = ItemHelper.copyWithNewCount(ingredient.getMatchingStacks()[random.nextInt(ingredient.getMatchingStacks().length)], MathHelper.nextInt(random, dropData.min, dropData.max));
					ItemEntity itemEntity = new ItemEntity(world, target.getX(), target.getY(), target.getZ(), stack);
					itemEntity.setToDefaultPickupDelay();
					itemEntity.setVelocity(MathHelper.nextFloat(random, -0.1F, 0.1F), MathHelper.nextFloat(random, 0.25f, 0.5f), MathHelper.nextFloat(random, -0.1F, 0.1F));
					world.spawnEntity(itemEntity);
				}
			}
		}
	}


}
