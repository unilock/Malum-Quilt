package dev.sterner.malum.common.network.packet.s2c.server.block.blight;

import dev.sterner.malum.Malum;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class BlightMistParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "blight_mist_particles");

    public static void send(PlayerEntity player, BlockPos pos) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }
}
