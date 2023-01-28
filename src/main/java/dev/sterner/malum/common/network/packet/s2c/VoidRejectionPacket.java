package dev.sterner.malum.common.network.packet.s2c;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.component.MalumComponents;
import dev.sterner.malum.common.entity.nitrate.EthericNitrateEntity;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class VoidRejectionPacket {
	public static final Identifier ID = new Identifier(Malum.MODID, "void_rejection");

	public static void send(PlayerEntity player, int entityId) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeInt(entityId);
		ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
	}


	public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
		int entityId = buf.readInt();
		client.execute(() -> {
			if (MinecraftClient.getInstance().world != null) {
				Entity entity = MinecraftClient.getInstance().world.getEntityById(entityId);
				if (entity instanceof LivingEntity livingEntity) {
					MalumComponents.TOUCH_OF_DARKNESS_COMPONENT.maybeGet(livingEntity).ifPresent(c -> c.reject(livingEntity));
				}
			}
		});
	}
}
