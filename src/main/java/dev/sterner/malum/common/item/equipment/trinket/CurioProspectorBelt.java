package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;

public class CurioProspectorBelt extends TrinketItem {
    public CurioProspectorBelt(Settings settings) {
        super(settings);
    }

    /*TODO forge event
    public static void processExplosion(ExplosionEvent.Detonate event) {
        LivingEntity exploder = event.getExplosion().getSourceMob();
        if (exploder != null && CurioHelper.hasCurioEquipped(exploder, MalumObjects.BELT_OF_THE_PROSPECTOR)) {
            event.getAffectedEntities().removeIf(e -> e instanceof ItemEntity itemEntity && itemEntity.getStack().isOf(ItemTagRegistry.PROSPECTORS_TREASURE));
        }
    }

     */


    /*
    public static LootContext.Builder applyFortune(Entity source, LootContext.Builder builder) {
        if (source instanceof LivingEntity livingEntity) {
            if (CurioHelper.hasCurioEquipped(livingEntity, MalumObjects.BELT_OF_THE_PROSPECTOR)) {
                int fortuneBonus = 3 + CuriosApi.getCuriosHelper().getCuriosHandler(livingEntity).map(h -> h.getFortuneWorld(null)).orElse(0);
                ItemStack diamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
                diamondPickaxe.addEnchantment(Enchantments.FORTUNE, fortuneBonus);
                return builder.parameter(LootContextParameters.TOOL, diamondPickaxe);
            }
        }
        return builder;
    }

     */
}
