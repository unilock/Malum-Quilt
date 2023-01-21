package dev.sterner.malum.common.item.equipment.trinket;

import dev.emi.trinkets.api.TrinketItem;
import dev.sterner.malum.api.interfaces.item.SpiritCollectActivity;

public class CurioHiddenBladeNecklace extends TrinketItem implements SpiritCollectActivity {
    public CurioHiddenBladeNecklace(Settings settings) {
        super(settings);
    }

    /*TODO forge event
    public void takeDamageEvent(LivingHurtEvent event, LivingEntity attacker, LivingEntity attacked, ItemStack stack) {
        if (attacked instanceof Player player && player.getCooldowns().isOnCooldown(ItemRegistry.NECKLACE_OF_THE_HIDDEN_BLADE.get())) {
            return;
        }
        float amount = event.getAmount();
        int amplifier = (int) Math.ceil(amount / 4f);
        if (amplifier >= 6) {
            amplifier *= amplifier / 6f;
        }
        MobEffect effect = MalumMobEffectRegistry.WICKED_INTENT.get();
        attacked.addEffect(new MobEffectInstance(effect, 40, amplifier - 1));
    }

     */
}
