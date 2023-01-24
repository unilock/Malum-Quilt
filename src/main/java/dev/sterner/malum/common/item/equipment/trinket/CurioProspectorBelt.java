package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import dev.sterner.malum.common.registry.MalumObjects;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;

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



    public static LootContext.Builder applyFortune(Entity source, LootContext.Builder builder) {
        if (source instanceof LivingEntity livingEntity) {
            if (TrinketsApi.getTrinketComponent(livingEntity).map(trinketComponent -> trinketComponent.isEquipped(MalumObjects.BELT_OF_THE_PROSPECTOR)).orElse(false)) {
                int fortuneBonus = 3;
                ItemStack diamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
                diamondPickaxe.addEnchantment(Enchantments.FORTUNE, fortuneBonus);
                return builder.parameter(LootContextParameters.TOOL, diamondPickaxe);
            }
        }
        return builder;
    }


}
