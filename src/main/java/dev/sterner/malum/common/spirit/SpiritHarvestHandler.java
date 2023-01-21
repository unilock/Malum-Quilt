package dev.sterner.malum.common.spirit;

import com.sammy.lodestone.helpers.ItemHelper;
import dev.emi.trinkets.api.TrinketsApi;
import dev.sterner.malum.api.interfaces.item.SpiritCollectActivity;
import dev.sterner.malum.common.component.MalumComponents;
import dev.sterner.malum.common.component.SpiritLivingEntityComponent;
import dev.sterner.malum.common.entity.boomerang.ScytheBoomerangEntity;
import dev.sterner.malum.common.item.spirit.SpiritPouchItem;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import dev.sterner.malum.common.registry.MalumDamageSourceRegistry;
import dev.sterner.malum.common.registry.MalumTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class SpiritHarvestHandler {
	public static void exposeSoul(DamageSource source, float amount, LivingEntity target) {
		if (amount == 0) {
			return;
		}
		if (source.getAttacker() instanceof LivingEntity attacker) {
			ItemStack stack = attacker.getMainHandStack();
			if (source.getSource() instanceof ScytheBoomerangEntity) {
				stack = ((ScytheBoomerangEntity) source.getSource()).scythe;
			}
			if (stack.isIn(MalumTags.SOUL_HUNTER_WEAPON)) {
				MalumComponents.SPIRIT_COMPONENT.get(target).exposedSoul = 200;
			}
		}
	}

	public static void shatterSoul(DamageSource source, LivingEntity target) {
		LivingEntity attacker = null;
		if (source.getSource().getName().equals(MalumDamageSourceRegistry.SOUL_STRIKE_IDENTIFIER)) {
			SpiritHelper.createSpiritEntities(SpiritHelper.getSpiritItemStacks(target), target, null);
			return;
		}
		if (source.getAttacker() instanceof LivingEntity directAttacker) {
			attacker = directAttacker;
		}
		if (attacker == null) {
			attacker = target.getAttacker();
		}
		if (attacker != null) {
			ItemStack stack = attacker.getMainHandStack();
			if (source.getSource() instanceof ScytheBoomerangEntity scytheBoomerang) {
				stack = scytheBoomerang.scythe;
			}
			if (!(target instanceof PlayerEntity)) {
				SpiritLivingEntityComponent component = MalumComponents.SPIRIT_COMPONENT.get(target);
				if (component.exposedSoul > 0 && !component.isSoulless() && (!false || (false && !component.isSpawnerSpawned()))) {
					SpiritHelper.createSpiritsFromWeapon(target, attacker, stack);
					component.setSoulless(true);
				}
			}
		}
	}

	public static void pickupSpirit(ItemStack stack, LivingEntity collector) {
		if (collector instanceof PlayerEntity playerEntity) {
			TrinketsApi.getTrinketComponent(playerEntity).orElseThrow().forEach((slot, trinket) -> {
				EntityAttributeInstance instance = playerEntity.getAttributeInstance(MalumAttributeRegistry.ARCANE_RESONANCE);
				if (trinket.getItem() instanceof SpiritCollectActivity spiritCollectActivity) {
					spiritCollectActivity.collect(stack, playerEntity, slot, trinket, instance != null ? instance.getValue() : 0);
				}
			});
			for (DefaultedList<ItemStack> playerInventory : playerEntity.getInventory().combinedInventory) {
				for (ItemStack item : playerInventory) {
					if (item.getItem() instanceof SpiritPouchItem) {
						DefaultedList<ItemStack> stacks = DefaultedList.ofSize(27, ItemStack.EMPTY);
						NbtCompound nbt = item.getOrCreateNbt();
						if (nbt != null) {
							Inventories.readNbt(nbt, stacks);
						}
						SimpleInventory inventory = new SimpleInventory(stacks.delegate.toArray(ItemStack[]::new));

						ItemStack newStack = inventory.addStack(stack);
						if (!newStack.isEmpty()) {
							ItemHelper.giveItemToEntity(newStack, collector);
						}

						Inventories.writeNbt(nbt, inventory.stacks);

						World world = playerEntity.world;
						world.playSound(null, playerEntity.getX(), playerEntity.getY() + 0.5, playerEntity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((world.random.nextFloat() - world.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
						return;
					}
				}
			}
		}
		ItemHelper.giveItemToEntity(stack, collector);
	}
}
