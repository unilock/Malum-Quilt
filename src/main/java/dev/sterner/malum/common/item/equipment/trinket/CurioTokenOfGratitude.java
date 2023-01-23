package dev.sterner.malum.common.item.equipment.trinket;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class CurioTokenOfGratitude extends TrinketItem {
    public CurioTokenOfGratitude(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        map.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "Curio armor toughness boost", 1, EntityAttributeModifier.Operation.ADDITION));
        map.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "Curio armor boost", 1, EntityAttributeModifier.Operation.ADDITION));
        return map;
    }

    /*TODO forge event
    public static void accelerateEating(LivingEntityUseItemEvent.Start event) {
        if (CurioHelper.hasCurioEquipped(event.getEntity(), ItemRegistry.RING_OF_DESPERATE_VORACITY.get())) {
            if (event.getItem().is(GROSS_FOODS)) {
                event.setDuration((int) (event.getDuration() * 0.5f));
            }
        }
    }

    public static void finishEating(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof PlayerEntity player) {
            ItemStack stack = event.getResultStack();
            if (CurioHelper.hasCurioEquipped(player, ItemRegistry.RING_OF_DESPERATE_VORACITY.get())) {
                if (stack.isIn(GROSS_FOODS)) {
                    StatusEffectInstance gluttony = player.getStatusEffect(MalumStatusEffectRegistry.GLUTTONY);
                    if (gluttony != null) {
                        player.getHungerManager().add(1, 0.25f*(gluttony.getAmplifier()+1));
                    }
                    player.getHungerManager().add(2, 1f);
                    StatusEffectInstance hunger = player.getStatusEffect(StatusEffects.HUNGER);
                    if (hunger != null) {
                        EntityHelper.shortenEffect(hunger, player, 150);
                    }
                }
            }
        }
    }

     */
}
