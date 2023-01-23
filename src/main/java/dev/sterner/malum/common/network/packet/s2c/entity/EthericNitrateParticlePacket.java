package dev.sterner.malum.common.network.packet.s2c.entity;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.entity.nitrate.EthericNitrateEntity;
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

public class EthericNitrateParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "etheric_nitrate_particle");

    public static void send(PlayerEntity player, double posX, double posY, double posZ) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }


    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();

        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for (int i = 0; i < 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
                float motionMultiplier = (float) (1+Math.pow(rand.nextFloat(), 2));
                ParticleBuilders.create(LodestoneParticles.TWINKLE_PARTICLE)
                    .setAlpha(0.2f, 0.8f, 0)
                    .setLifetime(12)
                    .setSpinOffset(spinOffset)
                    .setSpinCoefficient(1.25f)
                    .setSpin(0.9f * spinDirection, 0)
                    .setSpinEasing(Easing.CUBIC_IN)
                    .setScale(0.075f, 0.25f, 0)
                    .setScaleCoefficient(0.8f)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT)
                    .setColor(EthericNitrateEntity.FIRST_COLOR.brighter(), EthericNitrateEntity.SECOND_COLOR.darker())
                    .enableNoClip()
                    .randomOffset(0.85f)
                    .setGravity(1.1f)
                    .addMotion(0, 0.3f + rand.nextFloat() * 0.15f * motionMultiplier, 0)
                    .disableNoClip()
                    .randomMotion(0.2f*motionMultiplier, 0.25f*motionMultiplier)
                    .overwriteRemovalProtocol(SimpleParticleEffect.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                    .repeat(world, posX, posY, posZ, 4);
            }
            int spinOffset = rand.nextInt(360);
            for (int i = 0; i < 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                float scaleMultiplier = (float) (1+Math.pow(rand.nextFloat(), 2)*0.5f);
                ParticleBuilders.create(LodestoneParticles.SPARKLE_PARTICLE)
                    .setAlpha(0.35f, 0.07f, 0)
                    .setAlphaEasing(Easing.SINE_IN, Easing.CIRC_IN)
                    .setLifetime(9)
                    .setSpinOffset(spinOffset)
                    .setSpin((0.125f + rand.nextFloat() * 0.075f) * spinDirection)
                    .setScale(0.8f*scaleMultiplier, 0.5f, 0)
                    .setScaleEasing(Easing.EXPO_OUT, Easing.SINE_IN)
                    .setColor(EthericNitrateEntity.FIRST_COLOR.brighter(), EthericNitrateEntity.SECOND_COLOR.darker())
                    .randomOffset(0.6f)
                    .enableNoClip()
                    .randomMotion(0.02f, 0.02f)
                    .overwriteRemovalProtocol(SimpleParticleEffect.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                    .repeat(world, posX, posY, posZ, 5);
            }
        });
    }
}
