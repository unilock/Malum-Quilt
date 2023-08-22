package dev.sterner.malum.common.network.packet.s2c.client.block;

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
import net.minecraft.util.math.Direction;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.awt.*;

public class BlockMistParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "block_mist_particle");

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        Color color = new Color(buf.readInt(), buf.readInt(), buf.readInt());
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());


        ClientWorld world = client.world;
        client.execute(() -> {
			WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
					.setTransparencyData(GenericParticleData.create(0.08f, 0.32f, 0).build())
					.setSpinData(SpinParticleData.create(0.2f).build())
					.setScaleData(GenericParticleData.create(0.15f, 0.2f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
					.setColorData(ColorParticleData.create(color, color).build())
					.setLifetime(15)
					.enableNoClip()
					.setRandomOffset(0.1f, 0f)
					.setRandomMotion(0.001f, 0.002f)
					.repeatSurroundBlock(world, pos, 6, Direction.UP, Direction.DOWN);

			WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
					.setTransparencyData(GenericParticleData.create(0.04f, 0.16f, 0).build())
					.setSpinData(SpinParticleData.create(0.1f).build())
					.setScaleData(GenericParticleData.create(0.35f, 0.4f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
					.setColorData(ColorParticleData.create(color, color).build())
					.setLifetime(20)
					.setRandomOffset(0.2f, 0)
					.enableNoClip()
					.setRandomMotion(0.001f, 0.002f)
					.repeatSurroundBlock(world, pos, 8, Direction.UP, Direction.DOWN);
        });
    }
}
