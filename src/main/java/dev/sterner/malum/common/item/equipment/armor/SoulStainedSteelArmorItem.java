package dev.sterner.malum.common.item.equipment.armor;

import com.google.common.collect.ImmutableMultimap;
import com.sammy.lodestone.systems.item.LodestoneArmorItem;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorMaterial;

import java.util.UUID;

public class SoulStainedSteelArmorItem extends LodestoneArmorItem {
    public SoulStainedSteelArmorItem(EquipmentSlot slot, Settings builder) {
        super(ArmorTiers.ArmorTierEnum.SOUL_STAINED_STEEL, slot, builder);
    }

    @Override
    public ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> createExtraAttributes(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = new ImmutableMultimap.Builder<>();
        UUID uuid = MODIFIERS[slot.getEntitySlotId()];
        builder.put(MalumAttributeRegistry.SOUL_WARD_CAP, new EntityAttributeModifier(uuid, "Soul Ward Cap", 3f, EntityAttributeModifier.Operation.ADDITION));
        return builder;
    }
}
