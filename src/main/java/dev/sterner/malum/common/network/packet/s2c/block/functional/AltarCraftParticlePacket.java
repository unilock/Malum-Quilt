package dev.sterner.malum.common.network.packet.s2c.block.functional;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AltarCraftParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "altar_craft_particles");
    public static void send(PlayerEntity player, List<String> spirits, Vec3d vec3d) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        for (String s : spirits){
            buf.writeString(s);
        }
        buf.writeDouble(vec3d.x);
        buf.writeDouble(vec3d.y);
        buf.writeDouble(vec3d.z);

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }


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
                ParticleBuilders.create(LodestoneParticles.TWINKLE_PARTICLE)
                    .setAlpha(0.6f, 0f)
                    .setLifetime(80)
                    .setScale(0.15f, 0)
                    .setColor(color, endColor)
                    .randomOffset(0.1f)
                    .addMotion(0, 0.26f, 0)
                    .randomMotion(0.03f, 0.04f)
                    .setGravity(1)
                    .repeat(world, posX, posY, posZ, 32);

                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.2f, 0f)
                    .setLifetime(60)
                    .setScale(0.4f, 0)
                    .setColor(color, endColor)
                    .randomOffset(0.25f, 0.1f)
                    .randomMotion(0.004f, 0.004f)
                    .enableNoClip()
                    .repeat(world, posX, posY, posZ, 12);

                ParticleBuilders.create(LodestoneParticles.SPARKLE_PARTICLE)
                    .setAlpha(0.05f, 0f)
                    .setLifetime(30)
                    .setScale(0.2f, 0)
                    .setColor(color, endColor)
                    .randomOffset(0.05f, 0.05f)
                    .randomMotion(0.02f, 0.02f)
                    .enableNoClip()
                    .repeat(world, posX, posY, posZ, 8);
            }
        });
    }
}
