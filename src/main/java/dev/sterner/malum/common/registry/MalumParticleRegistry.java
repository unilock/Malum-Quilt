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
public class MalumParticleRegistry {
	private static final Map<ParticleType<?>, Identifier> PARTICLE_TYPES = new LinkedHashMap<>();

	public static final DefaultParticleType SCYTHE_CUT_ATTACK_PARTICLE = registerr("scythe_cut_attack", FabricParticleTypes.simple(true));
	public static final DefaultParticleType SCYTHE_SWEEP_ATTACK_PARTICLE = registerr("scythe_sweep_attack", FabricParticleTypes.simple(true));

	public static final SpiritFlameParticleType SPIRIT_FLAME_PARTICLE = registerr("spirit_flame", new SpiritFlameParticleType());

	private static SpiritFlameParticleType registerr(String name, SpiritFlameParticleType type) {
		PARTICLE_TYPES.put(type, Malum.id(name));
		return type;
	}

	private static DefaultParticleType registerr(String name, DefaultParticleType type) {
		PARTICLE_TYPES.put(type, Malum.id(name));
		return type;
	}
/*
	private static <T extends ParticleEffect> DefaultParticleType register(String name, ParticleType<T> type) {
		PARTICLE_TYPES.put(type, Malum.id(name));
		return type;


	}

 */

	public static void init() {
		PARTICLE_TYPES.keySet().forEach(particleType -> Registry.register(Registries.PARTICLE_TYPE, PARTICLE_TYPES.get(particleType), particleType));
	}

}
