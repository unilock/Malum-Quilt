package dev.sterner.malum.common.network.packet.s2c.server.block.functional;

import dev.sterner.malum.Malum;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.util.List;

public class AltarConsumeParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "altar_consume_particles");
    protected final ItemStack stack;
    protected final List<String> spirits;
    protected final double posX;
    protected final double posY;
    protected final double posZ;
    protected final double altarPosX;
    protected final double altarPosY;
    protected final double altarPosZ;

    public AltarConsumeParticlePacket(ItemStack stack, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
        this.stack = stack;
        this.spirits = spirits;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.altarPosX = altarPosX;
        this.altarPosY = altarPosY;
        this.altarPosZ = altarPosZ;
    }

    public static void send(PlayerEntity player, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeInt(spirits.size());
		for (String string : spirits) {
			buf.writeString(string);
		}
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
		buf.writeDouble(altarPosX);
		buf.writeDouble(altarPosY);
		buf.writeDouble(altarPosZ);
        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }
}
