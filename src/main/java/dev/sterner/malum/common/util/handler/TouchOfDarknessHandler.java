package dev.sterner.malum.common.util.handler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tessellator;
import com.sammy.lodestone.systems.rendering.ExtendedShader;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import dev.sterner.malum.common.block.weeping_well.PrimordialSoupBlock;
import dev.sterner.malum.common.registry.MalumDamageSourceRegistry;
import dev.sterner.malum.common.registry.MalumShaderRegistry;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import dev.sterner.malum.common.registry.MalumStatusEffectRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;

import java.util.function.Consumer;

public class TouchOfDarknessHandler {
	public static final float MAX_AFFLICTION = 100f;

	public int expectedAffliction;
	public int afflictionDuration;
	public float currentAffliction;
	public int rejection;
	public int timeSpentInGoop;

	public NbtCompound serializeNBT() {
		NbtCompound tag = new NbtCompound();
		tag.putInt("expectedAffliction", expectedAffliction);
		tag.putInt("afflictionDuration", afflictionDuration);
		tag.putFloat("currentAffliction", currentAffliction);
		tag.putInt("rejection", rejection);
		tag.putInt("timeSpentInGoop", timeSpentInGoop);
		return tag;
	}

	public void deserializeNBT(NbtCompound tag) {
		expectedAffliction = tag.getInt("expectedAffliction");
		afflictionDuration = tag.getInt("afflictionDuration");
		currentAffliction = tag.getFloat("currentAffliction");
		rejection = tag.getInt("rejection");
		timeSpentInGoop = tag.getInt("timeSpentInGoop");
	}

	public static void touchedByGoop(BlockState pState, LivingEntity livingEntity) {
		//while a living entity is in the primordial soup, we will bring their expected affliction to 100, slow their movement down.
		/*
		float intensity = 0.4f;
		TouchOfDarknessHandler touchOfDarknessHandler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
		if (touchOfDarknessHandler.isEntityRejected()) {
			return;
		}
		livingEntity.setVelocity(livingEntity.getVelocity().multiply(intensity, intensity, intensity));
		touchOfDarknessHandler.afflict(100);

		 */
	}

	public static double updateEntityGravity(LivingEntity livingEntity, double value) {
		/*
		TouchOfDarknessHandler handler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
		if (handler.timeSpentInGoop > 0) {
			double intensity = 1- Math.min(60, handler.timeSpentInGoop) / 60f;
			return value * intensity;
		}

		 */
		return value;
	}

	public static void entityTick(LivingEntity livingEntity) {
		/*
		TouchOfDarknessHandler handler = MalumLivingEntityDataCapability.getCapability(livingEntity).touchOfDarknessHandler;
		boolean isInTheGoop = livingEntity.world.getBlockState(livingEntity.getBlockPos()).getBlock() instanceof PrimordialSoupBlock;
		if (handler.afflictionDuration > 0) { // tick down the duration of touch of darkness.
			handler.afflictionDuration--;
			if (handler.afflictionDuration == 0) { //if it reaches zero, set expectedAffliction to 0, eventually ending the effect
				handler.expectedAffliction = 0;
			}
		}
		if (handler.currentAffliction <= handler.expectedAffliction) { //increment the affliction until it reaches the limit value
			float increase = 1.55f;
			if (handler.afflictionDuration < 20) { //increase in affliction strength decreases if effect is about to run out
				increase *= handler.afflictionDuration / 20f;
			}
			handler.currentAffliction = Math.min(MAX_AFFLICTION, handler.currentAffliction+increase);
			//if the entity's affliction reached the max, and the entity isn't close to actively being rejected, and is in the goop, they are rejected
			//rejection is set to 15, and the entity starts rapidly ascending as a result
			//we do this only on the server, and then communicate the rejection to the client using a packet
			if (!livingEntity.world.isClient()) {
				if (handler.currentAffliction >= MAX_AFFLICTION && (handler.rejection < 5 || handler.rejection > 25) && handler.timeSpentInGoop > 60) {
					handler.reject(livingEntity);
				}
			}
		}
		//if the expected affliction is lower than the current affliction, it diminishes.
		//current affliction diminishes faster if the expected value is 0
		if (handler.currentAffliction > handler.expectedAffliction) {
			handler.currentAffliction = Math.max(handler.currentAffliction - (handler.expectedAffliction == 0 ? 1.5f : 0.75f), 0);
		}
		//if we in the goop, we in the goop
		if (isInTheGoop) {
			handler.timeSpentInGoop++;
			boolean isPlayer = livingEntity instanceof PlayerEntity;
			if (isPlayer && livingEntity.world.getTime() % 6L == 0) {
				livingEntity.world.playSound(null, livingEntity.getBlockPos(), MalumSoundRegistry.SONG_OF_THE_VOID, SoundCategory.HOSTILE, 0.5f+handler.timeSpentInGoop*0.02f, 0.5f+handler.timeSpentInGoop*0.04f);
			}
			if (!isPlayer) {
				if (livingEntity.getVelocity().y > 0) {
					livingEntity.setVelocity(livingEntity.getVelocity().multiply(1, 0.5f, 1));
				}
			}
		}
		//if we ain't in the goop, we ain't in the goop.
		if (!isInTheGoop || handler.isEntityRejected()) {
			handler.timeSpentInGoop = 0;
		}
		//rejection grows while at max affliction, and already rejected.
		if (handler.currentAffliction >= TouchOfDarknessHandler.MAX_AFFLICTION && handler.isEntityRejected()) {
			handler.rejection += 2;
		}
		//rejection diminishes naturally
		handler.rejection = Math.max(handler.rejection - 1, 0);
		//handles throwing the entity out, if the entity is being rejected it's Y velocity is forced into a diminishing value
		if (handler.isEntityRejected()) {
			float intensity = (40 - handler.rejection) / 30f;
			livingEntity.setVelocity(livingEntity.getVelocity().x, Math.pow(1.1f * (intensity), 2), livingEntity.getVelocity().z);
			if (handler.rejection >= 40) {
				handler.rejection = 0;
			}
		}

		 */
	}

	public void afflict(int expectedAffliction) {
		if (this.expectedAffliction > expectedAffliction) {
			return;
		}
		this.expectedAffliction = expectedAffliction;
		this.afflictionDuration = 60;
	}

	public void reject(LivingEntity livingEntity) {
		if (!(livingEntity instanceof PlayerEntity)) {
			livingEntity.remove(Entity.RemovalReason.DISCARDED);
			return;
		}
		expectedAffliction = 0;
		currentAffliction += 40f;
		afflictionDuration = 0;
		rejection = 10;
		if (!livingEntity.world.isClient) {
			//PacketRegistry.MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> livingEntity), new VoidRejectionPacket(livingEntity.getId()));
			//PacketRegistry.MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> livingEntity), new VoidConduitParticlePacket(livingEntity.getX(), livingEntity.getY()+livingEntity.getHeight()/2f, livingEntity.getZ()));
			livingEntity.damage(new DamageSource(MalumDamageSourceRegistry.GUARANTEED_SOUL_SHATTER), 4);
			livingEntity.world.playSound(null, livingEntity.getBlockPos(), MalumSoundRegistry.VOID_REJECTION, SoundCategory.HOSTILE, 2f, MathHelper.nextFloat(livingEntity.getRandom(), 0.85f, 1.35f));
		}
		livingEntity.addStatusEffect(new StatusEffectInstance(MalumStatusEffectRegistry.REJECTED, 400, 0));
	}

	public boolean isEntityRejected() {
		return rejection >= 10;
	}

	public static class ClientOnly {
		private static final Tessellator INSTANCE = new Tessellator();

		public static void renderDarknessVignette(MatrixStack poseStack) {
			MinecraftClient minecraft = MinecraftClient.getInstance();
			PlayerEntity player = minecraft.player;
			//TODO TouchOfDarknessHandler darknessHandler = MalumLivingEntityDataCapability.getCapability(player).touchOfDarknessHandler;
			/*
			if (darknessHandler.currentAffliction == 0f) {
				return;
			}

			 */
			int screenWidth = minecraft.getWindow().getScaledWidth();
			int screenHeight = minecraft.getWindow().getScaledHeight();

			float effectStrength = Easing.SINE_IN_OUT.ease(MAX_AFFLICTION, 0, 1, 1);//TODO float effectStrength = Easing.SINE_IN_OUT.ease(darknessHandler.currentAffliction / MAX_AFFLICTION, 0, 1, 1);
			float alpha = Math.min(1, effectStrength * 5);
			float zoom = 0.5f + Math.min(0.35f, effectStrength);
			float intensity = 1f + (effectStrength > 0.5f ? (effectStrength - 0.5f) * 2.5f : 0);

			ExtendedShader shaderInstance = (ExtendedShader) MalumShaderRegistry.TOUCH_OF_DARKNESS.getInstance().get();
			shaderInstance.getUniformOrDefault("Speed").setFloat(1000f);
			Consumer<Float> setZoom = f -> shaderInstance.getUniformOrDefault("Zoom").setFloat(f);
			Consumer<Float> setIntensity = f -> shaderInstance.getUniformOrDefault("Intensity").setFloat(f);
			VFXBuilders.ScreenVFXBuilder builder = VFXBuilders.createScreen()
					.setPosColorTexDefaultFormat()
					.setPositionWithWidth(0, 0, screenWidth, screenHeight)
					.overrideBufferBuilder(INSTANCE.getBufferBuilder())
					.setColor(0, 0, 0)
					.setAlpha(alpha)
					.setShader(MalumShaderRegistry.TOUCH_OF_DARKNESS.getInstance());

			poseStack.push();
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();

			setZoom.accept(zoom);
			setIntensity.accept(intensity);
			builder.draw(poseStack);

			setZoom.accept(zoom * 1.25f + 0.15f);
			setIntensity.accept(intensity * 0.8f + 0.5f);
			builder.setAlpha(0.5f * alpha).draw(poseStack);

			RenderSystem.disableBlend();
			poseStack.pop();

			shaderInstance.setUniformDefaults();
		}
	}
}
