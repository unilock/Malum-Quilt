package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import dev.sterner.malum.common.registry.MalumItemRegistry;
import net.minecraft.entity.LivingEntity;

public class CurioDemolitionistRing extends TrinketItem {
    public CurioDemolitionistRing(Settings settings) {
        super(settings);
    }

    public static float increaseExplosionRadius(LivingEntity source, float original) {
        var v = TrinketsApi.getTrinketComponent(source);
        if(v.isPresent()){
            if(v.get().isEquipped(MalumItemRegistry.RING_OF_THE_DEMOLITIONIST)){
                return original + 1;
            }
        }
        return original;
    }
}
