package dev.sterner.malum;

import dev.sterner.malum.common.registry.MalumParticleRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import java.util.function.Function;

public class MalumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {

	}
}
