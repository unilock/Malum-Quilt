package dev.sterner.malum.common.item.nitrate;

import com.sammy.lodestone.setup.LodestoneScreenParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.screen.base.ScreenParticle;
import dev.sterner.malum.common.entity.nitrate.VividNitrateEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;

import static dev.sterner.malum.common.entity.nitrate.VividNitrateEntity.COLOR_FUNCTION;


public class VividNitrateItem extends AbstractNitrateItem{
    public VividNitrateItem(Settings settings) {
        super(settings, p -> new VividNitrateEntity(p, p.world));
    }

    @Override
    public void particleTick(ItemStack stack, float x, float y, ScreenParticle.RenderOrder renderOrder) {
        World world = MinecraftClient.getInstance().world;
        float partialTick = MinecraftClient.getInstance().getTickDelta();
        float gameTime = (float) (world.getTime() + partialTick + Math.sin(((world.getTime() + partialTick) * 0.1f)));
        Color firstColor = COLOR_FUNCTION.apply(new VividNitrateEntity.ColorFunctionData(world, 40f, 0, partialTick)).brighter();
        Color secondColor = COLOR_FUNCTION.apply(new VividNitrateEntity.ColorFunctionData(world, 40f, 0.125f, partialTick)).darker();
        ParticleBuilders.create(LodestoneScreenParticles.STAR)
            .setAlpha(0.04f, 0f)
            .setLifetime(6)
            .setScale((float) (1.5f + Math.sin(gameTime * 0.1f) * 0.125f), 0)
            .setColor(firstColor, secondColor)
            .setColorCoefficient(1.25f)
            .randomOffset(0.05f)
            .setSpinOffset(0.025f * gameTime % 6.28f)
            .setSpin(0, 1)
            .setSpinEasing(Easing.EXPO_IN_OUT)
            .setAlphaEasing(Easing.QUINTIC_IN)
            .overwriteRenderOrder(renderOrder)
            .centerOnStack(stack, -1, 4)
            .repeat(x, y, 1)
            .setScale((float) (1.4f - Math.sin(gameTime * 0.075f) * 0.125f), 0)
            .setColor(secondColor, firstColor)
            .setSpinOffset(0.785f-0.01f * gameTime % 6.28f)
            .repeat(x, y, 1);
    }
}
