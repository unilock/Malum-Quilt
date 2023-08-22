package dev.sterner.malum.common.network.packet.s2c.server.entity;

import dev.sterner.malum.Malum;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;

public class SuccessfulSoulHarvestParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "successful_soul_harvest_particle");

    public static void send(PlayerEntity player, Color color, Color endColor, double posX, double posY, double posZ) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(color.getRed());
        buf.writeInt(color.getGreen());
        buf.writeInt(color.getBlue());
        buf.writeInt(endColor.getRed());
        buf.writeInt(endColor.getGreen());
        buf.writeInt(endColor.getBlue());
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }
}
