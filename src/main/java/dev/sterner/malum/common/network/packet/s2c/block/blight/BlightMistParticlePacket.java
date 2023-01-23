package dev.sterner.malum.common.network.packet.s2c.block.blight;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.ParticleTextureSheets;
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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;

public class BlightMistParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "blight_mist_particles");

    public static void send(PlayerEntity player, BlockPos pos) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());

        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }


    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());

        ClientWorld world = client.world;
        client.execute(() -> {
            for (int i = 0; i < 3; i++) {
                float multiplier = MathHelper.nextFloat(world.random, 0.4f, 1f);
                float timeMultiplier = MathHelper.nextFloat(world.random, 0.9f, 1.4f);
                Color color = new Color((int)(31*multiplier), (int)(19*multiplier), (int)(31*multiplier));
                boolean spinDirection = world.random.nextBoolean();
                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.15f, 1f, 0)
                    .setLifetime((int) (45*timeMultiplier))
                    .setSpin(0.2f*(spinDirection ? 1 : -1))
                    .setScale(0.15f, 0.2f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .enableNoClip()
                    .randomOffset(0.1f, 0f)
                    .randomMotion(0.005f, 0.01f)
                    .overwriteRenderType(ParticleTextureSheets.TRANSPARENT)
                    .repeatSurroundBlock(world, pos, 2, Direction.UP);

                ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                    .setAlpha(0.25f, 0.55f, 0)
                    .setLifetime((int) (50*timeMultiplier))
                    .setSpin(0.1f*(spinDirection ? 1 : -1))
                    .setScale(0.35f, 0.4f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .randomOffset(0.2f, 0)
                    .enableNoClip()
                    .randomMotion(0.005f, 0.005f)
                    .overwriteRenderType(ParticleTextureSheets.TRANSPARENT)
                    .repeatSurroundBlock(world, pos, 2, Direction.UP);

                color = new Color((int)(80*multiplier), (int)(40*multiplier), (int)(80*multiplier));
                ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                    .setAlpha(0.02f, 0.15f, 0)
                    .setLifetime((int) (50*timeMultiplier))
                    .setSpin(0.1f*(spinDirection ? 1 : -1))
                    .setScale(0.35f, 0.4f, 0)
                    .setScaleEasing(Easing.QUINTIC_OUT, Easing.SINE_IN)
                    .setColor(color, color)
                    .randomOffset(0.2f, 0)
                    .enableNoClip()
                    .randomMotion(0.01f, 0.005f)
                    .repeatSurroundBlock(world, pos, 2, Direction.UP);
            }
        });
    }
}
