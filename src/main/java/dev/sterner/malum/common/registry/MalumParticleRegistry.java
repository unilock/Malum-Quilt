package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import dev.sterner.malum.client.particles.spiritflame.SpiritFlameParticleType;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public interface MalumParticleRegistry {
	Map<ParticleType<?>, Identifier> PARTICLE_TYPES = new LinkedHashMap<>();

	DefaultParticleType SCYTHE_CUT_ATTACK_PARTICLE = register("scythe_cut_attack", FabricParticleTypes.simple(true));
	DefaultParticleType SCYTHE_SWEEP_ATTACK_PARTICLE = register("scythe_sweep_attack", FabricParticleTypes.simple(true));

	SpiritFlameParticleType SPIRIT_FLAME_PARTICLE = register("spirit_flame", new SpiritFlameParticleType());

	static SpiritFlameParticleType register(String name, SpiritFlameParticleType type) {
		PARTICLE_TYPES.put(type, Malum.id(name));
		return type;
	}

	static DefaultParticleType register(String name, DefaultParticleType type) {
		PARTICLE_TYPES.put(type, Malum.id(name));
		return type;
	}

	static void init() {
		PARTICLE_TYPES.keySet().forEach(particleType -> Registry.register(Registries.PARTICLE_TYPE, PARTICLE_TYPES.get(particleType), particleType));
	}

}
