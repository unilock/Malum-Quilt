package dev.sterner.malum.common.spirit;

import com.sammy.lodestone.helpers.ColorHelper;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;

import java.awt.*;

public class MalumSpiritType {
	private final Color color;

	private final Color endColor;
	public final String identifier;

	protected Item splinterItem;

	public MalumSpiritType(String identifier, Color color, Item splinterItem) {
		this.identifier = identifier;
		this.color = color;
		this.endColor = createEndColor(color);
		this.splinterItem = splinterItem;
	}

	public MalumSpiritType(String identifier, Color color, Color endColor, Item splinterItem) {
		this.identifier = identifier;
		this.color = color;
		this.endColor = endColor;
		this.splinterItem = splinterItem;
	}

	public Color getColor() {
		return color;
	}

	public Color getEndColor() {
		return endColor;
	}

	public Text getCountComponent(int count) {
		return Text.literal(" " + count + " ").append(Text.translatable(getDescription())).setStyle(Style.EMPTY.withColor(color.getRGB()));
	}

	public Text getFlavourComponent(ItemStack stack) {
		return Text.translatable(getFlavourText()).formatted(Formatting.ITALIC).setStyle(Style.EMPTY.withColor(ColorHelper.darker(color, 1, 0.75f).getRGB()));
	}

	public String getDescription() {
		return "malum.spirit.description." + identifier;
	}

	public String getFlavourText() {
		return "malum.spirit.flavour." + identifier;
	}

	public Color createEndColor(Color color) {
		return new Color(color.getGreen(), color.getBlue(), color.getRed());
	}

	public MalumSpiritItem getSplinterItem() {
		return (MalumSpiritItem) splinterItem;
	}

	public Identifier getOverlayTexture() {
		return Malum.id("spirit/" + identifier + "_glow");
	}

	public BlockState getBlockState(boolean isCorrupt, BlockHitResult hit) {
		Block base = isCorrupt ? MalumObjects.SOULWOOD_TOTEM_POLE : MalumObjects.RUNEWOOD_TOTEM_POLE;
		return base.getDefaultState().with(Properties.HORIZONTAL_FACING, hit.getSide()).with(MalumSpiritTypeRegistry.SPIRIT_TYPE_PROPERTY, identifier);
	}
}
