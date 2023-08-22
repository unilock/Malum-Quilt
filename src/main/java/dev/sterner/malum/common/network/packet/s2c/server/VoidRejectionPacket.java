package dev.sterner.malum.common.network.packet.s2c.server;

import dev.sterner.malum.Malum;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class VoidRejectionPacket {
	public static final Identifier ID = new Identifier(Malum.MODID, "void_rejection");

	public static void send(PlayerEntity player, int entityId) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeInt(entityId);
		ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
	}
}
