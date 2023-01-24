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
import java.util.function.Supplier;

public class MalumSpiritType {
	private final Color color;

	private final Color endColor;
	public final String identifier;

	private final Supplier<Item> splinterItem;

	public MalumSpiritType(String identifier, Color color, Supplier<Item> splinterItem) {
		this.identifier = identifier;
		this.color = color;
		this.endColor = createEndColor(color);
		switch (identifier) {
			case "wicked" -> this.splinterItem = () -> MalumObjects.WICKED_SPIRIT;
			case "arcane" -> this.splinterItem = () -> MalumObjects.ARCANE_SPIRIT;
			case "eldritch" -> this.splinterItem = () -> MalumObjects.ELDRITCH_SPIRIT;
			case "aerial" -> this.splinterItem = () -> MalumObjects.AERIAL_SPIRIT;
			case "aqueous" -> this.splinterItem = () -> MalumObjects.AQUEOUS_SPIRIT;
			case "infernal" -> this.splinterItem = () -> MalumObjects.INFERNAL_SPIRIT;
			case "earthen" -> this.splinterItem = () -> MalumObjects.EARTHEN_SPIRIT;
			default -> this.splinterItem = () -> MalumObjects.SACRED_SPIRIT;
		}
	}

	public MalumSpiritType(String identifier, Color color, Color endColor, Supplier<Item> splinterItem) {
		this.identifier = identifier;
		this.color = color;
		this.endColor = endColor;
		switch (identifier) {
			case "wicked" -> this.splinterItem = () -> MalumObjects.WICKED_SPIRIT;
			case "arcane" -> this.splinterItem = () -> MalumObjects.ARCANE_SPIRIT;
			case "eldritch" -> this.splinterItem = () -> MalumObjects.ELDRITCH_SPIRIT;
			case "aerial" -> this.splinterItem = () -> MalumObjects.AERIAL_SPIRIT;
			case "aqueous" -> this.splinterItem = () -> MalumObjects.AQUEOUS_SPIRIT;
			case "infernal" -> this.splinterItem = () -> MalumObjects.INFERNAL_SPIRIT;
			case "earthen" -> this.splinterItem = () -> MalumObjects.EARTHEN_SPIRIT;
			default -> this.splinterItem = () -> MalumObjects.SACRED_SPIRIT;
		}
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
		return (MalumSpiritItem) splinterItem.get();
	}

	public Identifier getOverlayTexture() {
		return Malum.id("spirit/" + identifier + "_glow");
	}

	public BlockState getBlockState(boolean isCorrupt, BlockHitResult hit) {
		Block base = isCorrupt ? MalumObjects.SOULWOOD_TOTEM_POLE : MalumObjects.RUNEWOOD_TOTEM_POLE;
		return base.getDefaultState().with(Properties.HORIZONTAL_FACING, hit.getSide()).with(MalumSpiritTypeRegistry.SPIRIT_TYPE_PROPERTY, identifier);
	}
}
