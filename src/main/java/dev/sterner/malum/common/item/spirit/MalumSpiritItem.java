package dev.sterner.malum.common.item.spirit;

import com.sammy.lodestone.systems.rendering.particle.screen.base.ScreenParticle;
import com.sammy.lodestone.systems.rendering.particle.screen.emitter.ItemParticleEmitter;
import dev.sterner.malum.api.interfaces.item.IFloatingGlowItem;
import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;



public class MalumSpiritItem extends Item implements IFloatingGlowItem, ItemParticleEmitter {
    public MalumSpiritType type;

    public MalumSpiritItem(Settings properties, MalumSpiritType type) {
        super(properties);
        this.type = type;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, java.util.List<Text> tooltip, TooltipContext context) {
        tooltip.add(type.getFlavourComponent(stack));
    }

    @Override
    public Color getColor() {
        return type.getColor();
    }

    @Override
    public Color getEndColor() {
        return type.getEndColor();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void particleTick(ItemStack stack, float x, float y, ScreenParticle.RenderOrder renderOrder) {
        SpiritHelper.spawnSpiritScreenParticles(type.getColor(), type.getEndColor(), stack, x, y, renderOrder);
    }
}
