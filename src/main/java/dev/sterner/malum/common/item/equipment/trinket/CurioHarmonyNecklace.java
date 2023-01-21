package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;

public class CurioHarmonyNecklace extends TrinketItem {
    public CurioHarmonyNecklace(Settings settings) {
        super(settings);
    }

    /*TODO forge event
    public static void preventDetection(LivingEvent.LivingVisibilityEvent event) {
        if (event.getLookingEntity() instanceof LivingEntity watcher) {
            LivingEntity target = event.getEntity();

            var v = TrinketsApi.getTrinketComponent(target);
            if(v.isPresent()){
                if (v.get().isEquipped(MalumItemRegistry.NECKLACE_OF_BLISSFUL_HARMONY)) {
                    MalumEntitySpiritData data = SpiritHelper.getEntitySpiritData(watcher);
                    float visibilityModifier = data == null ? 0.5f : 0.5f / (1+data.dataEntries.stream().map(s -> s.type.equals(MalumSpiritType.WICKED_SPIRIT) ? 1 : 0).count());
                    event.modifyVisibility(visibilityModifier);
                }
            }


        }
    }

     */
}
