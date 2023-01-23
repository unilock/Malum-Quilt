package dev.sterner.malum.common.network.packet.s2c.block.functional;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AltarConsumeParticlePacket {
    public static final Identifier ID = new Identifier(Malum.MODID, "altar_consume_particles");
    protected final ItemStack stack;
    protected final List<String> spirits;
    protected final double posX;
    protected final double posY;
    protected final double posZ;
    protected final double altarPosX;
    protected final double altarPosY;
    protected final double altarPosZ;

    public AltarConsumeParticlePacket(ItemStack stack, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
        this.stack = stack;
        this.spirits = spirits;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.altarPosX = altarPosX;
        this.altarPosY = altarPosY;
        this.altarPosZ = altarPosZ;
    }

    public static void send(PlayerEntity player, ItemStack stack, List<String> spirits, double posX, double posY, double posZ, double altarPosX, double altarPosY, double altarPosZ) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeItemStack(stack);

        for (String s : spirits){
            buf.writeString(s);
        }

        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);

        buf.writeDouble(altarPosX);
        buf.writeDouble(altarPosY);
        buf.writeDouble(altarPosZ);
        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        ItemStack stack = buf.readItemStack();
        int strings = buf.readInt();
        List<String> spirits = new ArrayList<>();
        for (int i = 0; i < strings; i++) {
            spirits.add(buf.readString());
        }
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();
        double altarPosX = buf.readDouble();
        double altarPosY = buf.readDouble();
        double altarPosZ = buf.readDouble();

        ClientWorld world = client.world;
        client.execute(() -> {
            List<MalumSpiritType> types = new ArrayList<>();
            for (String string : spirits) {
                types.add(SpiritHelper.getSpiritType(string));
            }
            float alpha = 0.1f / types.size();
            for (MalumSpiritType type : types) {
                Color color = type.getColor();
                Color endColor = type.getEndColor();
                ParticleBuilders.create(LodestoneParticles.TWINKLE_PARTICLE)
                    .setAlpha(alpha * 2, 0f)
                    .setLifetime(60)
                    .setScale(0.4f, 0)
                    .setColor(color, endColor)
                    .randomOffset(0.25f, 0.1f)
                    .randomMotion(0.004f, 0.004f)
                    .enableNoClip()
                    .repeat(world, posX, posY, posZ, 12);

                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(alpha, 0f)
                    .setLifetime(30)
                    .setScale(0.2f, 0)
                    .setColor(color, endColor)
                    .randomOffset(0.05f, 0.05f)
                    .randomMotion(0.002f, 0.002f)
                    .enableNoClip()
                    .repeat(world, posX, posY, posZ, 8);

                Vec3d velocity = new Vec3d(posX, posY, posZ).subtract(altarPosX, altarPosY, altarPosZ).normalize().multiply(-0.05f);
                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(alpha, 0f)
                    .setLifetime(40)
                    .setScale(0.3f, 0)
                    .randomOffset(0.15f)
                    .randomMotion(0.005f, 0.005f)
                    .setColor(color, color.darker())
                    .addMotion(velocity.x, velocity.y, velocity.z)
                    .enableNoClip()
                    .repeat(world, posX, posY, posZ, 36);
            }
        });
    }
}
