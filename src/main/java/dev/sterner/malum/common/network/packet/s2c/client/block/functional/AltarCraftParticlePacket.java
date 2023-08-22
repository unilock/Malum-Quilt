package dev.sterner.malum.common.network.packet.s2c.client.block.functional;

import com.sammy.lodestone.setup.LodestoneParticleRegistry;
import com.sammy.lodestone.systems.particle.WorldParticleBuilder;
import com.sammy.lodestone.systems.particle.data.ColorParticleData;
import com.sammy.lodestone.systems.particle.data.GenericParticleData;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AltarCraftParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "altar_craft_particles");
    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        int strings = buf.readInt();
        List<String> spirits = new ArrayList<>();
        for (int i = 0; i < strings; i++) {
            spirits.add(buf.readString());
        }
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();

        ClientWorld world = client.world;
        client.execute(() -> {
            List<MalumSpiritType> types = new ArrayList<>();
            for (String string : spirits) {
                types.add(SpiritHelper.getSpiritType(string));
            }
            for (MalumSpiritType type : types) {
                Color color = type.getColor();
                Color endColor = type.getEndColor();
				WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0.6f, 0f).build())
						.setScaleData(GenericParticleData.create(0.15f, 0).build())
						.setColorData(ColorParticleData.create(color, endColor).build())
						.setLifetime(80)
						.setRandomOffset(0.1f)
						.addMotion(0, 0.26f, 0)
						.setRandomMotion(0.03f, 0.04f)
						.setGravity(1)
						.repeat(world, posX, posY, posZ, 32);

				WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0.2f, 0f).build())
						.setScaleData(GenericParticleData.create(0.4f, 0).build())
						.setColorData(ColorParticleData.create(color, endColor).build())
						.setLifetime(60)
						.setRandomOffset(0.25f, 0.1f)
						.setRandomMotion(0.004f, 0.004f)
						.enableNoClip()
						.repeat(world, posX, posY, posZ, 12);

				WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0.05f, 0f).build())
						.setScaleData(GenericParticleData.create(0.2f, 0).build())
						.setColorData(ColorParticleData.create(color, endColor).build())
						.setLifetime(30)
						.setRandomOffset(0.05f, 0.05f)
						.setRandomMotion(0.02f, 0.02f)
						.enableNoClip()
						.repeat(world, posX, posY, posZ, 8);
            }
        });
    }
}
