package dev.sterner.malum.common.network.packet.s2c.client;

import dev.sterner.malum.Malum;
import dev.sterner.malum.common.component.MalumComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketSender;

public class VoidRejectionPacket {
	public static final Identifier ID = new Identifier(Malum.MODID, "void_rejection");

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
