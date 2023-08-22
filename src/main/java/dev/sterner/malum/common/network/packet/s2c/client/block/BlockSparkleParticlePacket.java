package dev.sterner.malum.common.network.packet.s2c.client.block;

import com.sammy.lodestone.helpers.ColorHelper;
import com.sammy.lodestone.setup.LodestoneParticleRegistry;
import com.sammy.lodestone.systems.easing.Easing;
import com.sammy.lodestone.systems.particle.SimpleParticleEffect;
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

public class BlockSparkleParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "block_sparkle_particle");

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());


        ClientWorld world = client.world;
        client.execute(() -> {
            var rand = world.random;
            for (int i = 0; i < 5; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
				WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0, 0.8f, 0).build())
						.setSpinData(SpinParticleData.create(0.7f*spinDirection, 0).setCoefficient(1.25f).setSpinOffset(spinOffset).setEasing(Easing.CUBIC_IN).build())
						.setScaleData(GenericParticleData.create(0.05f, 0.1f, 0).setCoefficient(0.8f).setEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT).build())
						.setColorData(ColorParticleData.create(ColorHelper.brighter(color, 2), color).build())
						.setLifetime(20)
						.enableNoClip()
						.setRandomOffset(0.6f)
						.setGravity(1.1f)
						.addMotion(0, 0.28f+rand.nextFloat()*0.15f, 0)
						.disableNoClip()
						.setRandomMotion(0.1f, 0.15f)
						.setDiscardFunction(SimpleParticleEffect.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
						.repeat(world, pos.getX()+0.5f, pos.getY()+0.2f, pos.getZ()+0.5f, 1);
            }
            for (int i = 0; i < 2; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
				WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
						.setTransparencyData(GenericParticleData.create(0.05f, 0.08f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
						.setSpinData(SpinParticleData.create((0.125f+rand.nextFloat()*0.075f)*spinDirection).setSpinOffset(spinOffset).build())
						.setScaleData(GenericParticleData.create(0.35f, 0.5f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
						.setColorData(ColorParticleData.create(color, color).build())
						.setLifetime(50+rand.nextInt(10))
						.setRandomOffset(0.4f)
						.enableNoClip()
						.setRandomMotion(0.01f, 0.01f)
						.setDiscardFunction(SimpleParticleEffect.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
						.repeatSurroundBlock(world, pos, 1);
            }
        });
    }
}
