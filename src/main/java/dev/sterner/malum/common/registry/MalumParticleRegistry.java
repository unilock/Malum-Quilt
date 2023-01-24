package dev.sterner.malum.common.registry;

import com.sammy.lodestone.helpers.DataHelper;
import dev.sterner.malum.client.particles.cut.ScytheAttackParticle;
import dev.sterner.malum.client.particles.spiritflame.SpiritFlameParticleType;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public interface MalumParticleRegistry {
	DefaultParticleType SCYTHE_CUT_ATTACK_PARTICLE = FabricParticleTypes.simple(true);
	DefaultParticleType SCYTHE_SWEEP_ATTACK_PARTICLE = FabricParticleTypes.simple(true);

	SpiritFlameParticleType SPIRIT_FLAME_PARTICLE = new SpiritFlameParticleType();

	static void init() {
		initParticles(bind(Registries.PARTICLE_TYPE));
	}

	static void registerFactories() {
		ParticleFactoryRegistry.getInstance().register(SCYTHE_CUT_ATTACK_PARTICLE, ScytheAttackParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SCYTHE_SWEEP_ATTACK_PARTICLE, ScytheAttackParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SPIRIT_FLAME_PARTICLE, SpiritFlameParticleType.Factory::new);
	}
	// shamelessly stolen from Botania
	static void initParticles(BiConsumer<ParticleType<?>, Identifier> registry) {
		registry.accept(SCYTHE_CUT_ATTACK_PARTICLE, DataHelper.prefix("scythe_cut_attack"));
		registry.accept(SCYTHE_SWEEP_ATTACK_PARTICLE, DataHelper.prefix("scythe_sweep_attack"));
		registry.accept(SPIRIT_FLAME_PARTICLE, DataHelper.prefix("spirit_flame"));
	}
	// guess where this one comes from
	static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
		return (t, id) -> Registry.register(registry, id, t);
	}
}
