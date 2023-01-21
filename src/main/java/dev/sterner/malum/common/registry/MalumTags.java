package dev.sterner.malum.common.registry;

import dev.sterner.malum.Malum;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MalumTags {
	public static TagKey<Item> SCYTHE = TagKey.of(RegistryKeys.ITEM, new Identifier(Malum.MODID, "scythe"));
	public static TagKey<Item> SOUL_HUNTER_WEAPON = TagKey.of(RegistryKeys.ITEM, new Identifier(Malum.MODID, "soul_hunter_weapon"));

	public static void init() {

	}
}
