package dev.sterner.malum.common.network.packet.s2c.block.blight;

import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.ParticleTextureSheets;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;
import java.util.ArrayList;
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
            var rand = world.random;
            List<MalumSpiritType> types = new ArrayList<>();
            for (String string : spirits) {
                types.add(SpiritHelper.getSpiritType(string));
            }
            for (MalumSpiritType type : types) {
                Color color = type.getColor();
                for (int i = 0; i < 3; i++) {
                    int spinDirection = (rand.nextBoolean() ? 1 : -1);
                    int spinOffset = rand.nextInt(360);
                    ParticleBuilders.create(LodestoneParticles.TWINKLE_PARTICLE)
                        .setAlpha(0.4f, 0.8f, 0)
                        .setLifetime(20)
                        .setSpinOffset(spinOffset)
                        .setSpinCoefficient(1.25f)
                        .setSpin(0.7f*spinDirection, 0)
                        .setSpinEasing(Easing.CUBIC_IN)
                        .setScale(0.075f, 0.15f, 0)
                        .setScaleCoefficient(0.8f)
                        .setScaleEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT)
                        .setColor(ColorHelper.brighter(color, 2), color)
                        .enableNoClip()
                        .randomOffset(0.6f)
                        .setGravity(1.1f)
                        .addMotion(0, 0.28f+rand.nextFloat()*0.15f, 0)
                        .disableNoClip()
                        .randomMotion(0.1f, 0.15f)
                        .overwriteRemovalProtocol(SimpleParticleEffect.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                        .repeat(world, posX, posY, posZ, 2);
                }
                int spinOffset = rand.nextInt(360);
                for (int i = 0; i < 3; i++) {
                    int spinDirection = (rand.nextBoolean() ? 1 : -1);
                    ParticleBuilders.create(LodestoneParticles.SPARKLE_PARTICLE)
                        .setAlpha(0.12f, 0.06f, 0)
                        .setAlphaEasing(Easing.SINE_IN, Easing.CIRC_IN)
                        .setLifetime(30)
                        .setSpinOffset(spinOffset)
                        .setSpin((0.125f+rand.nextFloat()*0.075f)*spinDirection)
                        .setScale(0.85f, 0.5f, 0)
                        .setScaleEasing(Easing.EXPO_OUT, Easing.SINE_IN)
                        .setColor(color.brighter(), color.darker())
                        .randomOffset(0.4f)
                        .enableNoClip()
                        .randomMotion(0.01f, 0.01f)
                        .overwriteRemovalProtocol(SimpleParticleEffect.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                        .repeat(world, posX, posY, posZ, 5);
                }
            }

            for (int i = 0; i < 3; i++) {
                float multiplier = MathHelper.nextFloat(world.random, 0.4f, 1f);
                float timeMultiplier = MathHelper.nextFloat(world.random, 0.9f, 1.4f);
                Color color = new Color((int)(31*multiplier), (int)(19*multiplier), (int)(31*multiplier));
                boolean spinDirection = world.random.nextBoolean();
                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.4f, 1f, 0)
                    .setLifetime((int) (45*timeMultiplier))
                    .setSpin(0.2f*(spinDirection ? 1 : -1))
                    .setScale(0.15f, 0.2f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .enableNoClip()
                    .randomOffset(0.1f, 0.1f)
                    .randomMotion(0.01f, 0.02f)
                    .addMotion(0, 0.01f, 0)
                    .overwriteRenderType(ParticleTextureSheets.TRANSPARENT)
                    .repeat(world, posX, posY, posZ, 2);

                ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                    .setAlpha(0.35f, 0.55f, 0)
                    .setLifetime((int) (50*timeMultiplier))
                    .setSpin(0.1f*(spinDirection ? 1 : -1))
                    .setScale(0.35f, 0.4f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .randomOffset(0.2f, 0)
                    .enableNoClip()
                    .randomMotion(0.015f, 0.015f)
                    .addMotion(0, 0.01f, 0)
                    .overwriteRenderType(ParticleTextureSheets.TRANSPARENT)
                    .repeat(world, posX, posY, posZ, 3);

                color = new Color((int)(80*multiplier), (int)(40*multiplier), (int)(80*multiplier));
                ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                    .setAlpha(0.1f, 0.15f, 0)
                    .setLifetime((int) (50*timeMultiplier))
                    .setSpin(0.1f*(spinDirection ? 1 : -1))
                    .setScale(0.35f, 0.4f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .randomOffset(0.2f, 0)
                    .enableNoClip()
                    .randomMotion(0.02f, 0.01f)
                    .addMotion(0, 0.01f, 0)
                    .repeat(world, posX, posY, posZ, 2);
            }
        });
    }
}
