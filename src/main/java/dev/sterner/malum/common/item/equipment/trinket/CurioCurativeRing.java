package dev.sterner.malum.common.item.equipment.trinket;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.sterner.malum.api.interfaces.item.SpiritCollectActivity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class CurioCurativeRing extends TrinketItem implements SpiritCollectActivity {
    public CurioCurativeRing(Settings settings) {
        super(settings);
    }

    @Override
    public void collect(ItemStack spirit, LivingEntity livingEntity, SlotReference slot, ItemStack trinket, double arcaneResonance) {
        livingEntity.heal(livingEntity.getMaxHealth() * 0.1f+(float)(arcaneResonance*0.05f));
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        map.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "Curio health boost", 4f, EntityAttributeModifier.Operation.ADDITION));
        return map;
    }
}
