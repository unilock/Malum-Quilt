package dev.sterner.malum.common.network.packet.s2c.client.block;

import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneParticleRegistry;
import com.sammy.lodestone.systems.easing.Easing;
import com.sammy.lodestone.systems.particle.WorldParticleBuilder;
import com.sammy.lodestone.systems.particle.data.ColorParticleData;
import com.sammy.lodestone.systems.particle.data.GenericParticleData;
import com.sammy.lodestone.systems.particle.data.SpinParticleData;
import dev.sterner.malum.Malum;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.awt.*;

public class BlockDownwardSparkleParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "block_downward_sparkle_particle");

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());


        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for (int i = 0; i <= 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
				WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0, 0.8f, 0).build())
						.setSpinData(SpinParticleData.create(0, 0.8f * spinDirection, 0.1f * spinDirection).setCoefficient(2f).setSpinOffset(spinOffset).setEasing(Easing.CUBIC_IN, Easing.QUINTIC_OUT).build())
						.setScaleData(GenericParticleData.create(0.05f, 0.1f, 0).setCoefficient(0.8f).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
						.setColorData(ColorParticleData.create(ColorHelper.brighter(color, 2), color).build())
						.setLifetime(25)
						.enableNoClip()
						.setRandomOffset(0.6f)
						.setGravity(0.3f)
						.disableNoClip()
						.setRandomMotion(0.1f, 0.15f)
						.spawn(world, pos.getX() + 0.5f, pos.getY() + 0.2f, pos.getZ() + 0.5f);
            }


            for (int i = 0; i < 2; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
				WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0.05f, 0.08f, 0).setCoefficient(0.8f + rand.nextFloat() * 0.4f).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
						.setSpinData(SpinParticleData.create((0.1f + rand.nextFloat() * 0.05f) * spinDirection).setSpinOffset(spinOffset).build()).setScaleData(GenericParticleData.create(0.35f, 0.5f, 0).setCoefficient(0.8f + rand.nextFloat() * 0.4f).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
						.setColorData(ColorParticleData.create(color, color).build())
						.setScaleData(GenericParticleData.create(0.35f, 0.5f, 0.25f).setCoefficient(0.8f+rand.nextFloat()*0.4f).setEasing(Easing.QUINTIC_OUT, Easing.CIRC_IN).build())
						.setLifetime(50 + rand.nextInt(10))
						.setRandomOffset(0.4f)
						.enableNoClip()
						.addMotion(0, -0.02f, 0)
						.setRandomMotion(0.01f, 0.01f)
						.repeatSurroundBlock(world, pos, 2);
            }
        });
    }
}
