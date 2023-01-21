package dev.sterner.malum.common.item.equipment.trinket;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.sterner.malum.api.interfaces.item.SpiritCollectActivity;
import dev.sterner.malum.common.registry.MalumAttributeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class CurioMagebaneBelt extends TrinketItem implements SpiritCollectActivity {
    public CurioMagebaneBelt(Settings settings) {
        super(settings);
    }

    /* TODO forge event
    public void onSoulwardAbsorbDamage(LivingHurtEvent event, LivingEntity wardedEntity, ItemStack stack, float soulwardLost, float damageAbsorbed) {
        DamageSource source = event.getSource();
        if (source.getEntity() != null) {
            if (source instanceof EntityDamageSource entityDamageSource) {
                if (!entityDamageSource.isThorns()) {
                    source.getEntity().hurt(DamageSourceRegistry.causeMagebaneDamage(wardedEntity), damageAbsorbed);
                }
            }
        }
    }

     */

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        map.put(MalumAttributeRegistry.SOUL_WARD_RECOVERY_SPEED, new EntityAttributeModifier(uuid, "Soul Ward Recovery Rate", 3f, EntityAttributeModifier.Operation.ADDITION));
        return map;
    }
}
