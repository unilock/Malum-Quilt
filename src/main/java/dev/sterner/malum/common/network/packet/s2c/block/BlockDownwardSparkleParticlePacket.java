package dev.sterner.malum.common.network.packet.s2c.block;

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
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;

public class BlockDownwardSparkleParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "block_downward_sparkle_particle");

    public static void send(PlayerEntity player, Color color, BlockPos pos) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(color.getRed());
        buf.writeInt(color.getGreen());
        buf.writeInt(color.getBlue());
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }


    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());


        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for (int i = 0; i <= 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
                ParticleBuilders.create(LodestoneParticles.TWINKLE_PARTICLE)
                    .setAlpha(0, 0.8f, 0)
                    .setLifetime(25)
                    .setSpinOffset(spinOffset)
                    .setSpinCoefficient(2f)
                    .setSpin(0, 0.8f*spinDirection, 0.1f*spinDirection)
                    .setSpinEasing(Easing.CUBIC_IN, Easing.QUINTIC_OUT)
                    .setScale(0.05f, 0.1f, 0)
                    .setScaleCoefficient(0.8f)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT)
                    .setColor(ColorHelper.brighter(color, 2), color)
                    .enableNoClip()
                    .randomOffset(0.6f)
                    .setGravity(0.3f)
                    .disableNoClip()
                    .randomMotion(0.1f, 0.15f)
                    .repeat(world, pos.getX()+0.5f, pos.getY()+0.2f, pos.getZ()+0.5f, 1);
            }


            for (int i = 0; i < 2; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
                ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                    .setAlpha(0.05f, 0.08f, 0)
                    .setAlphaCoefficient(0.8f+rand.nextFloat()*0.4f)
                    .setAlphaEasing(Easing.SINE_IN, Easing.CIRC_IN)
                    .setLifetime(50+rand.nextInt(10))
                    .setSpinOffset(spinOffset)
                    .setSpin((0.1f+rand.nextFloat()*0.05f)*spinDirection)
                    .setScale(0.35f, 0.5f, 0)
                    .setScaleCoefficient(0.8f+rand.nextFloat()*0.4f)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .randomOffset(0.4f)
                    .enableNoClip()
                    .addMotion(0, -0.02f, 0)
                    .randomMotion(0.01f, 0.01f)
                    .repeatRandomFace(world, pos, 2);
            }
        });
    }
}
