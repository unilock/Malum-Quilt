package dev.sterner.malum.common.spirit;

import com.sammy.lodestone.helpers.ItemHelper;
import com.sammy.lodestone.setup.LodestoneParticleRegistry;
import com.sammy.lodestone.setup.LodestoneScreenParticleRegistry;
import com.sammy.lodestone.systems.easing.Easing;
import com.sammy.lodestone.systems.particle.ScreenParticleBuilder;
import com.sammy.lodestone.systems.particle.SimpleParticleEffect;
import com.sammy.lodestone.systems.particle.WorldParticleBuilder;
import com.sammy.lodestone.systems.particle.data.ColorParticleData;
import com.sammy.lodestone.systems.particle.data.GenericParticleData;
import com.sammy.lodestone.systems.particle.data.SpinParticleData;
import com.sammy.lodestone.systems.particle.screen.LodestoneScreenParticleTextureSheet;
import com.sammy.lodestone.systems.particle.screen.base.ScreenParticle;
import dev.sterner.malum.Malum;
import dev.sterner.malum.MalumConfig;
import dev.sterner.malum.common.component.MalumComponents;
import dev.sterner.malum.common.entity.spirit.PlayerBoundItemEntity;
import dev.sterner.malum.common.recipe.SpiritWithCount;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import dev.sterner.malum.common.registry.MalumEnchantmentRegistry;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static net.minecraft.entity.EquipmentSlot.MAINHAND;
import static net.minecraft.util.math.MathHelper.nextFloat;

public final class SpiritHelper {
	public static void createSpiritsFromSoul(MalumEntitySpiritData data, World World, Vec3d position, LivingEntity attacker) {
		List<ItemStack> spirits = getSpiritItemStacks(data, attacker, ItemStack.EMPTY, 2);
		createSpiritEntities(spirits, data.totalSpirits, World, position, attacker);
	}

	public static void createSpiritsFromWeapon(LivingEntity target, LivingEntity attacker, ItemStack harvestStack) {
		List<ItemStack> spirits = getSpiritItemStacks(target, attacker, harvestStack, 1);
		createSpiritEntities(spirits, target, attacker);
	}

	public static void createSpiritEntities(LivingEntity target, LivingEntity attacker) {
		List<ItemStack> spirits = getSpiritItemStacks(target);
		if (!spirits.isEmpty()) {
			createSpiritEntities(spirits, target, attacker);
		}
	}

	public static void createSpiritEntities(LivingEntity target) {
		List<ItemStack> spirits = getSpiritItemStacks(target);
		if (!spirits.isEmpty()) {
			createSpiritEntities(spirits, target, null);
		}
	}

	public static void createSpiritEntities(List<ItemStack> spirits, LivingEntity target, LivingEntity attacker) {
		if (spirits.isEmpty()) {
			return;
		}
		MalumEntitySpiritData data = getEntitySpiritData(target);

		if (data.spiritItem != null) {
			MalumComponents.SPIRIT_COMPONENT.maybeGet(target).ifPresent(e -> {
				e.soulsToApplyToDrops = spirits;
				if (attacker != null) {
					e.killerUUID = attacker.getUuid();
				}
			});
		} else {
			createSpiritEntities(spirits, spirits.stream().mapToInt(ItemStack::getCount).sum(), target.world, target.getPos().add(0, target.getStandingEyeHeight() / 2f, 0), attacker);
		}
	}

	public static void createSpiritEntities(Collection<ItemStack> spirits, LivingEntity target, LivingEntity attacker) {
		createSpiritEntities(spirits, target, 1, attacker);
	}

	public static void createSpiritEntities(Collection<ItemStack> spirits, LivingEntity target, float speedMultiplier, LivingEntity attacker) {
		if (spirits.isEmpty()) {
			return;
		}
		createSpiritEntities(spirits, spirits.stream().mapToInt(ItemStack::getCount).sum(), target.world, target.getPos().add(0, target.getStandingEyeHeight() / 2f, 0), speedMultiplier, attacker);
	}

	public static void createSpiritEntities(MalumEntitySpiritData data, World World, Vec3d position, LivingEntity attacker) {
		createSpiritEntities(getSpiritItemStacks(data), data.totalSpirits, World, position, attacker);
	}

	public static void createSpiritEntities(Collection<ItemStack> spirits, float totalCount, World World, Vec3d position, @Nullable LivingEntity attacker) {
		createSpiritEntities(spirits, totalCount, World, position, 1f, attacker);
	}

	public static void createSpiritEntities(Collection<ItemStack> spirits, float totalCount, World World, Vec3d position, float speedMultiplier, @Nullable LivingEntity attacker) {
		if (attacker == null) {
			attacker = World.getClosestPlayer(position.x, position.y, position.z, 8, e -> true);
		}
		float speed = (0.15f + 0.25f / (totalCount + 1)) * speedMultiplier;
		RandomGenerator random = World.random;
		for (ItemStack stack : spirits) {
			int count = stack.getCount();
			if (count == 0) {
				continue;
			}
			for (int j = 0; j < count; j++) {
				if (MalumConfig.NO_FANCY_SPIRITS) {
					ItemEntity itemEntity = new ItemEntity(World, position.x, position.y, position.z, stack);
					itemEntity.setToDefaultPickupDelay();
					itemEntity.setVelocity(MathHelper.nextFloat(random, -0.1F, 0.1F), MathHelper.nextFloat(random, 0.25f, 0.5f), MathHelper.nextFloat(random, -0.1F, 0.1F));
					World.spawnEntity(itemEntity);
					continue;
				}
				PlayerBoundItemEntity entity = new PlayerBoundItemEntity(World, attacker == null ? null : attacker.getUuid(), ItemHelper.copyWithNewCount(stack, 1),
						position.x,
						position.y,
						position.z,
						nextFloat(Malum.RANDOM, -speed, speed),
						nextFloat(Malum.RANDOM, 0.05f, 0.06f),
						nextFloat(Malum.RANDOM, -speed, speed));
				World.spawnEntity(entity);
			}
		}
		World.playSound(null, position.x, position.y, position.z, MalumSoundRegistry.SPIRIT_HARVEST, SoundCategory.PLAYERS, 1.0F, 0.7f + random.nextFloat() * 0.4f);
	}


	public static MalumSpiritType getSpiritType(String spirit) {
		MalumSpiritType type = MalumSpiritTypeRegistry.SPIRITS.get(spirit);
		return type == null ? MalumSpiritTypeRegistry.SACRED_SPIRIT : type;
	}

	public static MalumEntitySpiritData getEntitySpiritData(LivingEntity entity) {
		Identifier key = Registries.ENTITY_TYPE.getId(entity.getType());
		if (SpiritDataReloadListener.HAS_NO_DATA.contains(key))
			return null;

		MalumEntitySpiritData spiritData = SpiritDataReloadListener.SPIRIT_DATA.get(key);
		if (spiritData != null)
			return spiritData;

		if (!entity.canUsePortals())
			return SpiritDataReloadListener.DEFAULT_BOSS_SPIRIT_DATA;

		if (!MalumConfig.USE_DEFAULT_SPIRIT_VALUES)
			return null;



		return switch (entity.getType().getSpawnGroup()) {
			case MONSTER -> SpiritDataReloadListener.DEFAULT_MONSTER_SPIRIT_DATA;
			case CREATURE -> SpiritDataReloadListener.DEFAULT_CREATURE_SPIRIT_DATA;
			case AMBIENT -> SpiritDataReloadListener.DEFAULT_AMBIENT_SPIRIT_DATA;
			case AXOLOTLS -> SpiritDataReloadListener.DEFAULT_AXOLOTL_SPIRIT_DATA;
			case UNDERGROUND_WATER_CREATURE -> SpiritDataReloadListener.DEFAULT_UNDERGROUND_WATER_CREATURE_SPIRIT_DATA;
			case WATER_CREATURE -> SpiritDataReloadListener.DEFAULT_WATER_CREATURE_SPIRIT_DATA;
			case WATER_AMBIENT -> SpiritDataReloadListener.DEFAULT_WATER_AMBIENT_SPIRIT_DATA;
			default -> null;
		};
	}

	public static int getEntitySpiritCount(LivingEntity entity) {
		MalumEntitySpiritData bundle = getEntitySpiritData(entity);
		if (bundle == null) {
			return 0;
		}
		return bundle.totalSpirits;
	}

	public static List<ItemStack> getSpiritItemStacks(LivingEntity entity, LivingEntity attacker, ItemStack harvestStack, float spoilsMultiplier) {
		return getSpiritItemStacks(getEntitySpiritData(entity), attacker, harvestStack, spoilsMultiplier);
	}

	public static List<ItemStack> getSpiritItemStacks(MalumEntitySpiritData data, LivingEntity attacker, ItemStack harvestStack, float spoilsMultiplier) {
		List<ItemStack> spirits = getSpiritItemStacks(data);
		if (spirits.isEmpty()) {
			return spirits;
		}
		int spiritBonus = 0;
		if (attacker.getAttributeInstance(MalumAttributeRegistry.SPIRIT_SPOILS) != null) {
			spiritBonus += attacker.getAttributeValue(MalumAttributeRegistry.SPIRIT_SPOILS);
		}
		if (!harvestStack.isEmpty()) {
			int spiritPlunder = EnchantmentHelper.getLevel(MalumEnchantmentRegistry.SPIRIT_PLUNDER, harvestStack);

			if (spiritPlunder > 0) {
				harvestStack.damage(spiritPlunder, attacker, (e) -> e.sendEquipmentBreakStatus(MAINHAND));
			}
			spiritBonus += spiritPlunder;


		}
		for (int i = 0; i < spiritBonus * spoilsMultiplier; i++) {
			int random = attacker.world.random.nextInt(spirits.size());
			spirits.get(random).increment(1);
		}
		return spirits;
	}

	public static List<ItemStack> getSpiritItemStacks(LivingEntity entity) {
		return getSpiritItemStacks(getEntitySpiritData(entity));
	}

	public static List<ItemStack> getSpiritItemStacks(MalumEntitySpiritData data) {
		List<ItemStack> spirits = new ArrayList<>();
		if (data == null) {
			return spirits;
		}
		for (SpiritWithCount spiritWithCount : data.dataEntries) {
			spirits.add(new ItemStack(spiritWithCount.type.getSplinterItem(), spiritWithCount.count));
		}
		return spirits;
	}

	public static void spawnSpiritGlimmerParticles(World World, double x, double y, double z, Color color, Color endColor) {
		spawnSpiritGlimmerParticles(World, x, y, z, 1, Vec3d.ZERO, color, endColor);
	}

	public static void spawnSpiritGlimmerParticles(World world, double x, double y, double z, float alphaMultiplier, Vec3d extraVelocity, Color color, Color endColor) {
		RandomGenerator rand = world.getRandom();
		WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
				.setTransparencyData(GenericParticleData.create(0.4f * alphaMultiplier, 0f).build())
				.setScaleData(GenericParticleData.create(0.25f + rand.nextFloat() * 0.1f, 0).build())
				.setColorData(ColorParticleData.create(color, endColor).build())
				.setLifetime(5 + rand.nextInt(4))
				.setRandomOffset(0.05f)
				.enableNoClip()
				.addMotion(extraVelocity.x, extraVelocity.y, extraVelocity.z)
				.setRandomMotion(0.02f, 0.02f)
				.setDiscardFunction(SimpleParticleEffect.ParticleDiscardFunctionType.INVISIBLE)
				.spawn(world, x, y, z);

		spawnSpiritParticles(world, x, y, z, 1, extraVelocity, color, endColor);
	}

	public static void spawnSpiritParticles(World World, double x, double y, double z, Color color, Color endColor) {
		spawnSpiritParticles(World, x, y, z, 1, Vec3d.ZERO, color, endColor);
	}

	public static void spawnSpiritParticles(World world, double x, double y, double z, float alphaMultiplier, Vec3d extraVelocity, Color color, Color endColor) {
		RandomGenerator rand = world.getRandom();
		WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
				.setTransparencyData(GenericParticleData.create(0.275f * alphaMultiplier, 0f).build())
				.setLifetime(15 + rand.nextInt(4))
				.setSpinData(SpinParticleData.create(nextFloat(rand, 0.05f, 0.1f)).build())
				.setScaleData(GenericParticleData.create(0.05f + rand.nextFloat() * 0.025f, 0).build())
				.setColorData(ColorParticleData.create(color, endColor).setCoefficient(1.25f).build())
				.setRandomOffset(0.02f)
				.enableNoClip()
				.addMotion(extraVelocity.x, extraVelocity.y, extraVelocity.z)
				.setRandomMotion(0.01f, 0.01f)
				.setDiscardFunction(SimpleParticleEffect.ParticleDiscardFunctionType.INVISIBLE)
				.repeat(world, x, y, z, 1)
				.setTransparencyData(GenericParticleData.create(0.2f * alphaMultiplier, 0f).build())
				.setLifetime(10 + rand.nextInt(2))
				.setScaleData(GenericParticleData.create(0.15f + rand.nextFloat() * 0.05f, 0f).build())
				.setRandomMotion(0.01f, 0.01f)
				.repeat(world, x, y, z, 1);

	}

	public static void spawnSoulParticles(World World, double x, double y, double z, Color color, Color endColor) {
		spawnSoulParticles(World, x, y, z, 1, 1, Vec3d.ZERO, color, endColor);
	}

	public static void spawnSoulParticles(World world, double x, double y, double z, float alphaMultiplier, float scaleMultiplier, Vec3d extraVelocity, Color color, Color endColor) {
		RandomGenerator rand = world.getRandom();
		WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
				.setTransparencyData(GenericParticleData.create(0.1f * alphaMultiplier, 0).build())
				.setScaleData(GenericParticleData.create((0.2f + rand.nextFloat() * 0.2f) * scaleMultiplier, 0).setEasing(Easing.QUINTIC_IN).build())
				.setColorData(ColorParticleData.create(color, endColor).build())
				.setLifetime(8 + rand.nextInt(5))
				.setRandomOffset(0.05f)
				.enableNoClip()
				.addMotion(extraVelocity.x, extraVelocity.y, extraVelocity.z)
				.setRandomMotion(0.01f * scaleMultiplier, 0.01f * scaleMultiplier)
				.repeat(world, x, y, z, 1);

		WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
				.setTransparencyData(GenericParticleData.create(0, 0.05f * alphaMultiplier, 0f).setEasing(Easing.CUBIC_IN, Easing.CUBIC_OUT).build())
				.setSpinData(SpinParticleData.create(0.05f * world.getTime() % 6.28f, 0, nextFloat(rand, 0.05f, 0.4f)).setEasing(Easing.CUBIC_IN).build())
				.setScaleData(GenericParticleData.create((0.2f + rand.nextFloat() * 0.1f) * scaleMultiplier, 0.1f * scaleMultiplier).setEasing(Easing.QUINTIC_IN).build())
				.setColorData(ColorParticleData.create(color, endColor).build())
				.setLifetime(80 + rand.nextInt(10))
				.setRandomOffset(0.1f)
				.enableNoClip()
				.addMotion(extraVelocity.x, extraVelocity.y, extraVelocity.z)
				.setRandomMotion(0.01f * scaleMultiplier, 0.01f * scaleMultiplier)
				.repeat(world, x, y, z, 1)
				.setTransparencyData(GenericParticleData.create(0.12f * alphaMultiplier, 0f).build())
				.setSpinData(SpinParticleData.create(0, nextFloat(rand, 0.1f, 0.5f)).build())
				.setScaleData(GenericParticleData.create((0.15f + rand.nextFloat() * 0.1f) * scaleMultiplier, 0.1f * scaleMultiplier).build())
				.setLifetime(10 + rand.nextInt(5))
				.setRandomMotion(0.03f * scaleMultiplier, 0.03f * scaleMultiplier)
				.repeat(world, x, y, z, 2);

		WorldParticleBuilder.create(LodestoneParticleRegistry.STAR_PARTICLE)
				.setTransparencyData(GenericParticleData.create((rand.nextFloat() * 0.1f + 0.1f) * alphaMultiplier, 0f).build())
				.setSpinData(SpinParticleData.create(0.025f * world.getTime() % 6.28f, 0, nextFloat(rand, 0.05f, 0.4f)).build())
				.setScaleData(GenericParticleData.create((0.7f + rand.nextFloat() * 0.1f) * scaleMultiplier, 0.1f * scaleMultiplier).build())
				.setColorData(ColorParticleData.create(color, endColor).build())
				.setLifetime(20 + rand.nextInt(10))
				.setRandomOffset(0.01f)
				.enableNoClip()
				.addMotion(extraVelocity.x, extraVelocity.y, extraVelocity.z)
				.repeat(world, x, y, z, 1)
				.setTransparencyData(GenericParticleData.create((rand.nextFloat() * 0.05f + 0.05f) * alphaMultiplier, 0f).build())
				.setSpinData(SpinParticleData.create(0, nextFloat(rand, 0.1f, 0.5f)).build())
				.setScaleData(GenericParticleData.create((0.5f + rand.nextFloat() * 0.1f) * scaleMultiplier, 0.1f * scaleMultiplier).build())
				.setLifetime(10 + rand.nextInt(5));
	}

	public static void spawnSpiritScreenParticles(HashMap<LodestoneScreenParticleTextureSheet, ArrayList<ScreenParticle>> target, Color color, Color endColor, ItemStack stack, float pXPosition, float pYPosition) {
		RandomGenerator rand = MinecraftClient.getInstance().world.random;
		ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.SPARKLE, target)
				.setTransparencyData(GenericParticleData.create(0.04f, 0f).build())
				.setLifetime(10 + rand.nextInt(10))
				.setScaleData(GenericParticleData.create(0.8f + rand.nextFloat() * 0.1f, 0).build())
				.setColorData(ColorParticleData.create(color, endColor).setCoefficient(2f).build())
				.setRandomOffset(0.05f)
				.setRandomMotion(0.05f, 0.05f)
				.spawn(pXPosition, pYPosition);

		ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.WISP, target)
				.setTransparencyData(GenericParticleData.create(0.02f, 0f).build())
				.setSpinData(SpinParticleData.create(nextFloat(rand, 0.2f, 0.4f)).build())
				.setScaleData(GenericParticleData.create(0.6f + rand.nextFloat() * 0.4f, 0).build())
				.setColorData(ColorParticleData.create(color, endColor).setCoefficient(1.25f).build())
				.setLifetime(20 + rand.nextInt(8))
				.setRandomOffset(0.1f)
				.setRandomMotion(0.4f, 0.4f)
				.spawn(pXPosition, pYPosition)
				.setLifetime(10 + rand.nextInt(2))
				.setSpinData(SpinParticleData.create(nextFloat(rand, 0.05f, 0.1f)).build())
				.setScaleData(GenericParticleData.create(0.8f + rand.nextFloat() * 0.4f, 0f).build())
				.setRandomMotion(0.01f, 0.01f)
				.spawn(pXPosition, pYPosition);
	}
}
