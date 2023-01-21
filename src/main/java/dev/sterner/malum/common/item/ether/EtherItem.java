package dev.sterner.malum.common.item.ether;

import com.sammy.lodestone.setup.LodestoneScreenParticles;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.screen.base.ScreenParticle;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.awt.*;

public class EtherItem extends AbstractEtherItem {
    public EtherItem(Block blockIn, Settings builder, boolean iridescent) {
        super(blockIn, builder, iridescent);
    }

    @Override
    public void particleTick(ItemStack stack, float x, float y, ScreenParticle.RenderOrder renderOrder) {
        World world = MinecraftClient.getInstance().world;
        float gameTime = world.getTime() + MinecraftClient.getInstance().getTickDelta();
        AbstractEtherItem etherItem = (AbstractEtherItem) stack.getItem();
        Color firstColor = new Color(etherItem.getFirstColor(stack));
        Color secondColor = new Color(etherItem.getSecondColor(stack));
        float alphaMultiplier = etherItem.iridescent ? 0.75f : 0.5f;
        ParticleBuilders.create(LodestoneScreenParticles.STAR)
            .setAlpha(0.09f*alphaMultiplier, 0f)
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
            .centerOnStack(stack, etherItem.iridescent ? -1 : 0, etherItem.iridescent ? 3 : 4)
            .repeat(x, y, 1)
            .setScale((float) (1.4f - Math.sin(gameTime * 0.075f) * 0.125f), 0)
            .setColor(secondColor, firstColor)
            .setSpinOffset(0.785f-0.01f * gameTime % 6.28f)
            .repeat(x, y, 1);
    }
}
