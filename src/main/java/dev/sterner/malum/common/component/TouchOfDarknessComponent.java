package dev.sterner.malum.common.component;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tessellator;
import com.sammy.lodestone.systems.easing.Easing;
import com.sammy.lodestone.systems.rendering.ExtendedShader;
import com.sammy.lodestone.systems.rendering.VFXBuilders;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import dev.sterner.malum.common.block.weeping_well.PrimordialSoupBlock;
import dev.sterner.malum.common.network.packet.s2c.server.VoidRejectionPacket;
import dev.sterner.malum.common.network.packet.s2c.server.block.VoidConduitParticlePacket;
import dev.sterner.malum.common.registry.*;
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
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.util.function.Consumer;

public class TouchOfDarknessComponent implements AutoSyncedComponent, ServerTickingComponent {
	public LivingEntity livingEntity;
	public static final float MAX_AFFLICTION = 100f;

	public int expectedAffliction;
	public int afflictionDuration;
	public float currentAffliction;
	public int rejection;
	public int timeSpentInGoop;

	public TouchOfDarknessComponent(LivingEntity livingEntity){
		this.livingEntity = livingEntity;
	}

	@Override
	public void serverTick() {
		boolean isInTheGoop = livingEntity.world.getBlockState(livingEntity.getBlockPos()).getBlock() instanceof PrimordialSoupBlock;
		if (afflictionDuration > 0) { // tick down the duration of touch of darkness.
			afflictionDuration--;
			if (afflictionDuration == 0) { //if it reaches zero, set expectedAffliction to 0, eventually ending the effect
				expectedAffliction = 0;
				MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
			}
		}
		if (currentAffliction <= expectedAffliction) { //increment the affliction until it reaches the limit value
			float increase = 1.55f;
			if (afflictionDuration < 20) { //increase in affliction strength decreases if effect is about to run out
				increase *= afflictionDuration / 20f;
				MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
			}
			currentAffliction = Math.min(MAX_AFFLICTION, currentAffliction+increase);
			//if the entity's affliction reached the max, and the entity isn't close to actively being rejected, and is in the goop, they are rejected
			//rejection is set to 15, and the entity starts rapidly ascending as a result
			//we do this only on the server, and then communicate the rejection to the client using a packet
			if (!livingEntity.world.isClient()) {
				if (currentAffliction >= MAX_AFFLICTION && (rejection < 5 || rejection > 25) && timeSpentInGoop > 60) {
					reject(livingEntity);
				}
			}
		}
		//if the expected affliction is lower than the current affliction, it diminishes.
		//current affliction diminishes faster if the expected value is 0
		if (currentAffliction > expectedAffliction) {
			currentAffliction = Math.max(currentAffliction - (expectedAffliction == 0 ? 1.5f : 0.75f), 0);
		}
		//if we in the goop, we in the goop
		if (isInTheGoop) {
			timeSpentInGoop++;
			MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
			boolean isPlayer = livingEntity instanceof PlayerEntity;
			if (isPlayer && livingEntity.world.getTime() % 6L == 0) {
				livingEntity.world.playSound(null, livingEntity.getBlockPos(), MalumSoundRegistry.SONG_OF_THE_VOID, SoundCategory.HOSTILE, 0.5f+timeSpentInGoop*0.02f, 0.5f+timeSpentInGoop*0.04f);
			}
			if (!isPlayer) {
				if (livingEntity.getVelocity().y > 0) {
					livingEntity.setVelocity(livingEntity.getVelocity().multiply(1, 0.5f, 1));
				}
			}
		}
		//if we ain't in the goop, we ain't in the goop.
		if (!isInTheGoop || isEntityRejected()) {
			timeSpentInGoop = 0;
			MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
		}
		//rejection grows while at max affliction, and already rejected.
		if (currentAffliction >= MAX_AFFLICTION && isEntityRejected()) {
			rejection += 2;
			MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
		}
		//rejection diminishes naturally
		rejection = Math.max(rejection - 1, 0);
		//handles throwing the entity out, if the entity is being rejected it's Y velocity is forced into a diminishing value
		if (isEntityRejected()) {
			float intensity = (40 - rejection) / 30f;
			livingEntity.setVelocity(livingEntity.getVelocity().x, Math.pow(1.1f * (intensity), 2), livingEntity.getVelocity().z);
			livingEntity.velocityModified = true;
			if (rejection >= 40) {
				rejection = 0;
				MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.sync(livingEntity);
			}
		}
	}

	public boolean isEntityRejected() {
		return rejection >= 10;
	}

	public void touchedByGoop(BlockState pState, LivingEntity livingEntity) {
		//while a living entity is in the primordial soup, we will bring their expected affliction to 100, slow their movement down.

		float intensity = 0.4f;
		var comp = MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.get(livingEntity);
		if (comp.isEntityRejected()) {
			return;
		}
		livingEntity.setVelocity(livingEntity.getVelocity().multiply(intensity, intensity, intensity));
		afflict(100);
	}

	public double updateEntityGravity(LivingEntity livingEntity, double value) {
		if (timeSpentInGoop > 0) {
			double intensity = 1- Math.min(60, timeSpentInGoop) / 60f;
			return value * intensity;
		}
		return value;
	}

	public void afflict(int expectedAffliction) {
		if (this.expectedAffliction > expectedAffliction) {
			return;
		}
		this.expectedAffliction = expectedAffliction;
		this.afflictionDuration = 60;
	}

	public void reject(LivingEntity livingEntity) {
		if (!livingEntity.getType().isIn(MalumTagRegistry.SURVIVES_REJECTION)) {
			livingEntity.remove(Entity.RemovalReason.DISCARDED);
			return;
		}
		expectedAffliction = 0;
		currentAffliction += 40f;
		afflictionDuration = 0;
		rejection = 10;
		if (!livingEntity.world.isClient) {
			PlayerLookup.tracking(livingEntity).forEach(track -> VoidConduitParticlePacket.send(track, livingEntity.getX(), livingEntity.getY()+livingEntity.getHeight()/2f, livingEntity.getZ()));
			PlayerLookup.tracking(livingEntity).forEach(track -> VoidRejectionPacket.send(track, livingEntity.getId()));
			livingEntity.damage(new DamageSource(MalumDamageSourceRegistry.GUARANTEED_SOUL_SHATTER), 4);
			livingEntity.world.playSound(null, livingEntity.getBlockPos(), MalumSoundRegistry.VOID_REJECTION, SoundCategory.HOSTILE, 2f, MathHelper.nextFloat(livingEntity.getRandom(), 0.85f, 1.35f));
		}
		livingEntity.addStatusEffect(new StatusEffectInstance(MalumStatusEffectRegistry.REJECTED, 400, 0));
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		expectedAffliction = tag.getInt("expectedAffliction");
		afflictionDuration = tag.getInt("afflictionDuration");
		currentAffliction = tag.getFloat("currentAffliction");
		rejection = tag.getInt("rejection");
		timeSpentInGoop = tag.getInt("timeSpentInGoop");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putInt("expectedAffliction", expectedAffliction);
		tag.putInt("afflictionDuration", afflictionDuration);
		tag.putFloat("currentAffliction", currentAffliction);
		tag.putInt("rejection", rejection);
		tag.putInt("timeSpentInGoop", timeSpentInGoop);
	}

	public static class ClientOnly {
		private static final Tessellator INSTANCE = new Tessellator();

		public static void renderDarknessVignette(MatrixStack poseStack) {
			MinecraftClient minecraft = MinecraftClient.getInstance();
			PlayerEntity player = minecraft.player;
			if(player != null){
				TouchOfDarknessComponent touchOfDarknessComponent = MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.get(player);
				if (touchOfDarknessComponent.currentAffliction == 0f) {
					return;
				}

				int screenWidth = minecraft.getWindow().getScaledWidth();
				int screenHeight = minecraft.getWindow().getScaledHeight();

				float effectStrength = Easing.SINE_IN_OUT.ease(touchOfDarknessComponent.currentAffliction / MAX_AFFLICTION, 0, 1, 1);
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
}
