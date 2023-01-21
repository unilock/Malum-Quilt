package dev.sterner.malum.common.network.packet.s2c.entity;

import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import dev.sterner.malum.Malum;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;

public class MajorEntityEffectParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "major_effect_particle");

    public static void send(PlayerEntity player, Color color, double posX, double posY, double posZ) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(color.getRed());
        buf.writeInt(color.getGreen());
        buf.writeInt(color.getBlue());
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }


    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();

        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for (int i = 0; i <= 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0f, 0.125f, 0)
                    .setLifetime(25)
                    .setSpin(0.025f * spinDirection, (0.2f + rand.nextFloat() * 0.05f) * spinDirection, 0)
                    .setSpinEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setScale(0.025f, 0.1f + rand.nextFloat() * 0.075f, 0.35f)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color.darker())
                    .enableNoClip()
                    .randomOffset(0.2f, 0.2f)
                    .setMotionCoefficient(0.95f)
                    .randomMotion(0.02f)
                    .repeat(world, posX, posY, posZ, 8);
            }
            ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                .setAlpha(0f, 0.06f, 0)
                .setLifetime(20)
                .setSpin(0.1f, 0.4f, 0)
                .setSpinEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                .setScale(0.15f, 0.4f, 0.35f)
                .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                .setColor(color, color.darker())
                .enableNoClip()
                .randomOffset(0.05f, 0.05f)
                .randomMotion(0.05f)
                .setMotionCoefficient(0.5f)
                .repeat(world, posX, posY, posZ, 12);

            ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                .setAlpha(0f, 0.06f, 0)
                .setLifetime(25)
                .setSpin(0.1f, 0.25f, 0)
                .setSpinEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                .setScale(0.15f, 0.45f, 0.35f)
                .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                .setColor(color.darker(), ColorHelper.darker(color, 3))
                .enableNoClip()
                .randomOffset(0.15f, 0.15f)
                .randomMotion(0.015f, 0.015f)
                .setMotionCoefficient(0.92f)
                .repeat(world, posX, posY, posZ, 20);
        });
    }
}
