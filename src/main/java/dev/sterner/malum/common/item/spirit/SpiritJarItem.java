package dev.sterner.malum.common.item.spirit;

import dev.sterner.malum.common.spirit.MalumSpiritType;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritJarItem extends BlockItem {
    public SpiritJarItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public String getTranslationKey(ItemStack pStack) {
        if (pStack.hasNbt() && pStack.getNbt().contains("spirit")) {
            return "item.malum.filled_spirit_jar";
        }
        return super.getTranslationKey(pStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt() && stack.getNbt().contains("spirit")) {
            MalumSpiritType spirit = SpiritHelper.getSpiritType(stack.getNbt().getString("spirit"));
            int count = stack.getNbt().getInt("count");
            tooltip.add(Text.translatable("malum.spirit.description.stored_spirit").formatted(Formatting.GRAY));
            tooltip.add(spirit.getCountComponent(count));
        }
    }
}
