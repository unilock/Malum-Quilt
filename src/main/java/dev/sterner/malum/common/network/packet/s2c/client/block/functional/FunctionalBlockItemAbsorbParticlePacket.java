package dev.sterner.malum.common.network.packet.s2c.client.block.functional;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.List;

public class FunctionalBlockItemAbsorbParticlePacket {
    protected final ItemStack stack;
    protected final List<String> spirits;
    protected final double posX;
    protected final double posY;
    protected final double posZ;
    protected final double altarPosX;
    protected final double altarPosY;
    protected final double altarPosZ;

    public FunctionalBlockItemAbsorbParticlePacket(ItemStack stack, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
        this.stack = stack;
        this.spirits = spirits;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.altarPosX = altarPosX;
        this.altarPosY = altarPosY;
        this.altarPosZ = altarPosZ;
    }

    public void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        client.execute(() -> {


        });
    }

}
