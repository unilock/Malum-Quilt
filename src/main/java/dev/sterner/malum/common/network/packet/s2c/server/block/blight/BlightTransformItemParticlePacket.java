package dev.sterner.malum.common.network.packet.s2c.server.block.blight;

import dev.sterner.malum.Malum;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.util.List;

public class BlightTransformItemParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "blight_transform_particles");

    public static void send(PlayerEntity player, List<String> spirits, double posX, double posY, double posZ) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(spirits.size());
        for (String string : spirits) {
            buf.writeString(string);
        }
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);
        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }
}
