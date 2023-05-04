package dev.sterner.malum.common.event;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

import static dev.sterner.malum.Malum.MODID;
import static dev.sterner.malum.common.registry.MalumObjects.*;

public class MalumItemGroupEvents {
	public static final ItemGroup MALUM = QuiltItemGroup.builder(new Identifier(MODID, MODID)).icon(() -> new ItemStack(SPIRIT_ALTAR)).build();
	public static final ItemGroup MALUM_ARCANE_ROCKS = QuiltItemGroup.builder(new Identifier(MODID, "malum_shaped_stones")).icon(() -> new ItemStack(TAINTED_ROCK)).build();
	public static final ItemGroup MALUM_NATURAL_WONDERS = QuiltItemGroup.builder(new Identifier(MODID, "malum_natural_wonders")).icon(() -> new ItemStack(RUNEWOOD_SAPLING)).build();
	public static final ItemGroup MALUM_SPIRITS = QuiltItemGroup.builder(new Identifier(MODID, "malum_spirits")).icon(() -> new ItemStack(ARCANE_SPIRIT)).build();
	public static final ItemGroup MALUM_METALLURGIC_MAGIC = QuiltItemGroup.builder(new Identifier(MODID, "malum_metallurgic_magic")).icon(() -> new ItemStack(ALCHEMICAL_IMPETUS)).build();
}
