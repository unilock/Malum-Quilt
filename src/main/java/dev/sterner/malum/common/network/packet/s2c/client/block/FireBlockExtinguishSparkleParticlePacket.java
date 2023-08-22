package dev.sterner.malum.common.network.packet.s2c.client.block;

import dev.sterner.malum.Malum;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.awt.*;

public class FireBlockExtinguishSparkleParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "block_extinguish_sparkle_particle");

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());

        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for(int i = 0; i < 8; ++i) {
                double d0 = pos.getX() + rand.nextFloat();
                double d1 = pos.getY() + rand.nextFloat();
                double d2 = pos.getZ() + rand.nextFloat();
                world.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        });
    }
}
