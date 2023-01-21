package dev.sterner.malum.common.item.equipment.armor;

import com.google.common.collect.ImmutableMultimap;
import com.sammy.lodestone.setup.LodestoneAttributeRegistry;
import com.sammy.lodestone.systems.item.LodestoneArmorItem;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class SoulHunterArmorItem extends LodestoneArmorItem {
    public SoulHunterArmorItem(EquipmentSlot slot, Settings builder) {
        super(ArmorTiers.ArmorTierEnum.SPIRIT_HUNTER, slot, builder);
    }

    @Override
    public ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> createExtraAttributes(EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = new ImmutableMultimap.Builder<>();
        UUID uuid = MODIFIERS[slot.getEntitySlotId()];
        builder.put(LodestoneAttributeRegistry.MAGIC_PROFICIENCY, new EntityAttributeModifier(uuid, "Magic Proficiency", 2f, EntityAttributeModifier.Operation.ADDITION));
        return builder;
    }
}
