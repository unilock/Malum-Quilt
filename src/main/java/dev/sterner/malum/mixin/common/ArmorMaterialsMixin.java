package dev.sterner.malum.mixin.common;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorMaterials.class)
public abstract class ArmorMaterialsMixin {
	@Shadow
	public abstract int getDurability(EquipmentSlot slot);
}
