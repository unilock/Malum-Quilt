package dev.sterner.malum.common.entity.nitrate;

import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.ParticleTextureSheets;
import dev.sterner.malum.common.network.packet.s2c.entity.EthericNitrateParticlePacket;
import dev.sterner.malum.common.registry.MalumEntityRegistry;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.awt.*;

import static net.minecraft.util.math.MathHelper.nextFloat;

public class EthericNitrateEntity extends AbstractNitrateEntity{
    public static final Color FIRST_COLOR = MalumSpiritTypeRegistry.INFERNAL_SPIRIT.getColor();
    public static final Color SECOND_COLOR = new Color(178, 28, 73);

    public EthericNitrateEntity(World world) {
        super(MalumEntityRegistry.ETHERIC_NITRATE, world);
    }

    public EthericNitrateEntity(double x, double y, double z, World world) {
        super(MalumEntityRegistry.ETHERIC_NITRATE, x, y, z, world);
    }

    public EthericNitrateEntity(LivingEntity owner, World world) {
        super(MalumEntityRegistry.ETHERIC_NITRATE, owner, world);
    }

    @Override
    public void onExplode() {
        if (world instanceof ServerWorld serverWorld) {
            PlayerLookup.tracking(serverWorld, serverWorld.getWorldChunk(getBlockPos()).getPos()).forEach(track -> EthericNitrateParticlePacket.send(track, getX(), getY(), getZ()));
        }
    }

    @Override
    public void spawnParticles() {
        double ox = prevX, oy = prevY + getYOffset(0) + 0.25f, oz = prevZ;
        double x = getX(), y = getY() + getYOffset(0) + 0.25f, z = getZ();
        Vec3d motion = getVelocity();
        Vec3d norm = motion.normalize().multiply(0.1f);
        float extraAlpha = (float) motion.length();
        float cycles = 3;
        Color firstColor = FIRST_COLOR.brighter();
        var rand = world.getRandom();
        for (int i = 0; i < cycles; i++) {
            float pDelta = i / cycles;
            double lerpX = MathHelper.lerp(pDelta, ox, x)-motion.x/4f;
            double lerpY = MathHelper.lerp(pDelta, oy, y)-motion.y/4f;
            double lerpZ = MathHelper.lerp(pDelta, oz, z)-motion.z/4f;
            float alphaMultiplier = (0.35f + extraAlpha) * Math.min(1, windUp * 2);
            SpiritHelper.spawnSpiritParticles(world, lerpX, lerpY, lerpZ, alphaMultiplier, norm, firstColor, SECOND_COLOR);

            ParticleBuilders.create(LodestoneParticles.SMOKE_PARTICLE)
                .setAlpha(Math.min(1, 0.25f * alphaMultiplier), 0f)
                .setAlphaEasing(Easing.SINE_IN, Easing.SINE_OUT)
                .setLifetime(65 + rand.nextInt(15))
                .setSpin(nextFloat(rand, -0.1f, 0.1f))
                .setSpinOffset(rand.nextFloat() * 6.28f)
                .setScale(0.2f + rand.nextFloat() * 0.05f, 0.3f, 0f)
                .setColor(SECOND_COLOR, SECOND_SMOKE_COLOR)
                .setColorEasing(Easing.QUINTIC_OUT)
                .setColorCoefficient(1.25f)
                .randomOffset(0.02f)
                .enableNoClip()
                .addMotion(norm.x, norm.y, norm.z)
                .randomMotion(0.01f, 0.01f)
                .overwriteRenderType(ParticleTextureSheets.TRANSPARENT)
                .repeat(world, lerpX, lerpY, lerpZ, 1)
                .setColorCoefficient(2f)
                .repeat(world, lerpX, lerpY, lerpZ, 1);
        }
    }

    @Override
    public int getPierce() {
        return 3;
    }

    @Override
    public float getExplosionRadius() {
        return 2.75f;
    }
}
