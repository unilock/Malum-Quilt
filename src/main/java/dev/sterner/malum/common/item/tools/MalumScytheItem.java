package dev.sterner.malum.common.item.tools;

import dev.sterner.malum.common.entity.boomerang.ScytheBoomerangEntity;
import dev.sterner.malum.common.registry.MalumDamageSourceRegistry;
import dev.sterner.malum.common.registry.MalumParticleRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class MalumScytheItem extends SwordItem {
    public MalumScytheItem(ToolMaterial material, float damage, float speed , Settings settings) {
        super(material, (int) damage + 3, speed - 3.2f, settings);

    }

    /*TODO forge event
    @Override
    public void hurtEvent(LivingHurtEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        //TODO: convert this to a ToolAction, or something alike
        boolean canSweep = !CurioHelper.hasCurioEquipped(attacker, MalumObjects.NECKLACE_OF_THE_NARROW_EDGE) && !CurioHelper.hasCurioEquipped(attacker, MalumObjects.NECKLACE_OF_THE_HIDDEN_BLADE);
        if (attacker instanceof PlayerEntity player) {
            SoundEvent sound;
            if (canSweep) {
                spawnSweepParticles(player, MalumParticleRegistry.SCYTHE_SWEEP_ATTACK_PARTICLE);
                sound = SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP;
            } else {
                spawnSweepParticles(player, MalumParticleRegistry.SCYTHE_CUT_ATTACK_PARTICLE);
                sound = MalumSoundRegistry.SCYTHE_CUT;
            }
            attacker.world.playSound(null, target.getX(), target.getY(), target.getZ(), sound, attacker.getSoundCategory(), 1, 1);
        }

        if (!canSweep || event.getSource().isMagic() || event.getSource().getMsgId().equals(DamageSourceRegistry.SCYTHE_SWEEP_IDENTIFIER)) {
            return;
        }
        int world = EnchantmentHelper.getEquipmentWorld(Enchantments.SWEEPING, attacker);
        float damage = event.getAmount() * (0.5f + EnchantmentHelper.getSweepingMultiplier(attacker));
        target.world.getOtherEntities(attacker, target.getBoundingBox().expand(1 + world * 0.25f)).forEach(e -> {
            if (e instanceof LivingEntity livingEntity) {
                if (livingEntity.isAlive()) {
                    livingEntity.damage(new EntityDamageSource(MalumDamageSourceRegistry.SCYTHE_SWEEP_DAMAGE, attacker), damage);
                    livingEntity.takeKnockback(0.4F, MathHelper.sin(attacker.getYaw() * ((float) Math.PI / 180F)), (-MathHelper.cos(attacker.getYaw() * ((float) Math.PI / 180F))));
                }
            }
        });
    }

     */

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return true;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {//TODO remove
        if (attacker instanceof PlayerEntity) {
            spawnSweepParticles((PlayerEntity) attacker, MalumParticleRegistry.SCYTHE_SWEEP_ATTACK_PARTICLE);
            attacker.world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, attacker.getSoundCategory(), 1, 1);
        }

        target.damage(new EntityDamageSource(MalumDamageSourceRegistry.SCYTHE_SWEEP_IDENTIFIER, attacker), 1);
        return super.postHit(stack, target, attacker);
    }

    public void spawnSweepParticles(PlayerEntity player, DefaultParticleType type) {
        double d0 = (-MathHelper.sin(player.getYaw() * ((float) Math.PI / 180F)));
        double d1 = MathHelper.cos(player.getYaw() * ((float) Math.PI / 180F));
        if (player.world instanceof ServerWorld serverWorld) {
			System.out.println("Spawn Particle");
            serverWorld.spawnParticles(type, player.getX() + d0, player.getBodyY(0.5D), player.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }

    public static ItemStack getScytheItemStack(DamageSource source, LivingEntity attacker) {
        ItemStack stack = attacker.getMainHandStack();

        if (source.getSource() instanceof ScytheBoomerangEntity) {
            stack = ((ScytheBoomerangEntity) source.getSource()).scythe;
        }
        return stack.getItem() instanceof MalumScytheItem ? stack : ItemStack.EMPTY;
    }
}
