package dev.sterner.malum.common.item.spirit;

import com.sammy.lodestone.systems.item.tools.LodestoneSwordItem;
import dev.emi.trinkets.api.TrinketsApi;
import dev.sterner.malum.api.interfaces.item.SpiritCollectActivity;
import dev.sterner.malum.common.registry.MalumItemRegistry;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.sammy.lodestone.setup.LodestoneAttributeRegistry.MAGIC_PROFICIENCY;


public class TyrvingItem extends LodestoneSwordItem {
    public TyrvingItem(ToolMaterial material, float damage, float speed, Item.Settings settings) {
        super(material, (int) damage, speed, settings);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return true;
    }

    /*TODO forge event
    public void hurtEvent(LivingHurtEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        if (event.getSource().isMagic()) {
            return;
        }
        if (attacker.level instanceof ServerLevel) {
            float spiritCount = SpiritHelper.getEntitySpiritCount(target) * 2f;
            if (target instanceof Player) {
                spiritCount = 4 * Math.max(1, (1 + target.getArmorValue() / 12f) * (1 + (1 - 1 / (float)target.getArmorValue())) / 12f);
            }

            if (target.isAlive()) {
                target.invulnerableTime = 0;
                target.hurt(DamageSourceRegistry.causeVoodooDamage(attacker), spiritCount);
            }
            attacker.level.playSound(null, target.blockPosition(), SoundRegistry.VOID_SLASH.get(), SoundSource.PLAYERS, 1, 1f + target.level.random.nextFloat() * 0.25f);
            MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> target), new MajorEntityEffectParticlePacket(SpiritTypeRegistry.ELDRITCH_SPIRIT.getColor(), target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ()));
        }
    }

     */

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int lastDamageTaken = (int) target.lastDamageTaken;
        target.lastDamageTaken = 0;
        target.damage(DamageSource.MAGIC, (float) (SpiritHelper.getSpiritItemStacks(target).stream()
                                                                                           .mapToInt(ItemStack::getCount)
                                                                                           .reduce(0, Integer::sum) + 0.5f * attacker.getAttributeValue(MAGIC_PROFICIENCY)));
        target.lastDamageTaken += lastDamageTaken;
        if ((TrinketsApi.getTrinketComponent(attacker).orElseThrow().isEquipped(MalumItemRegistry.NECKLACE_OF_THE_MYSTIC_MIRROR))) {
            TrinketsApi.getTrinketComponent(attacker).orElseThrow().forEach((slot, trinket) -> {
                if (trinket.getItem() instanceof SpiritCollectActivity spiritCollectActivity) {
                    spiritCollectActivity.collect(stack, attacker, slot, trinket, 1);//TODO arcaneResonance
                }
            });
        }
        return super.postHit(stack, target, attacker);
    }
}
