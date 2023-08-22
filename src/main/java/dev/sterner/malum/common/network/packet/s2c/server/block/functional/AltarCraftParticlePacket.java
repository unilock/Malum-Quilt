package dev.sterner.malum.common.network.packet.s2c.server.block.functional;

import dev.sterner.malum.Malum;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.util.List;

public class AltarCraftParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "altar_craft_particles");
    public static void send(PlayerEntity player, List<String> spirits, Vec3d vec3d) {
        PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(spirits.size());
        for (String s : spirits){
            buf.writeString(s);
        }
        buf.writeDouble(vec3d.x);
        buf.writeDouble(vec3d.y);
        buf.writeDouble(vec3d.z);

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }
}
