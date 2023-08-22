package dev.sterner.malum.common.network.packet.s2c.client.entity;

import com.sammy.lodestone.setup.LodestoneParticleRegistry;
import com.sammy.lodestone.systems.particle.WorldParticleBuilder;
import com.sammy.lodestone.systems.particle.data.ColorParticleData;
import com.sammy.lodestone.systems.particle.data.GenericParticleData;
import com.sammy.lodestone.systems.particle.data.SpinParticleData;
import dev.sterner.malum.Malum;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.awt.*;

public class SuccessfulSoulHarvestParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "successful_soul_harvest_particle");

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        Color endColor = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();

        ClientWorld world = client.world;
        client.execute(() -> {
			WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
					.setTransparencyData(GenericParticleData.create(1.0f, 0).build())
					.setScaleData(GenericParticleData.create(0.4f, 0).build())
					.setColorData(ColorParticleData.create(color, endColor).build())
					.setSpinData(SpinParticleData.create(0.4f).build())
					.setLifetime(20)
					.setRandomOffset(0.5, 0).setRandomMotion(0, 0.125f)
					.addMotion(0, 0.28f, 0)
					.setGravity(1)
					.repeat(world, posX, posY, posZ, 40);

			WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
					.setTransparencyData(GenericParticleData.create(0.75f, 0).build())
					.setScaleData(GenericParticleData.create(0.2f, 0).build())
					.setColorData(ColorParticleData.create(color, endColor).build())
					.setSpinData(SpinParticleData.create(0.3f).build())
					.setLifetime(40)
					.setRandomOffset(0.5, 0.5).setRandomMotion(0.125f, 0.05)
					.addMotion(0, 0.15f, 0)
					.setGravity(1)
					.repeat(world, posX, posY, posZ, 30);
        });
    }
}
