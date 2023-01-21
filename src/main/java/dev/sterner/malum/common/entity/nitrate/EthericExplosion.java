package dev.sterner.malum.common.entity.nitrate;

import dev.sterner.malum.common.registry.MalumDamageSourceRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EthericExplosion extends Explosion {
    public EthericExplosion(World pLevel, @Nullable Entity pSource, double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius, List<BlockPos> pPositions) {
        super(pLevel, pSource, pToBlowX, pToBlowY, pToBlowZ, pRadius, pPositions);
    }

    public EthericExplosion(World pLevel, @Nullable Entity pSource, double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius, boolean pFire, DestructionType pBlockInteraction, List<BlockPos> pPositions) {
        super(pLevel, pSource, pToBlowX, pToBlowY, pToBlowZ, pRadius, pFire, pBlockInteraction, pPositions);
    }

    public EthericExplosion(World pLevel, @Nullable Entity pSource, double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius, boolean pFire, DestructionType pBlockInteraction) {
        super(pLevel, pSource, pToBlowX, pToBlowY, pToBlowZ, pRadius, pFire, pBlockInteraction);
    }

    public EthericExplosion(World pLevel, @Nullable Entity pSource, @Nullable DamageSource pDamageSource, @Nullable ExplosionBehavior pDamageCalculator, double pToBlowX, double pToBlowY, double pToBlowZ, float pRadius, boolean pFire, DestructionType pBlockInteraction) {
        super(pLevel, pSource, pDamageSource, pDamageCalculator, pToBlowX, pToBlowY, pToBlowZ, pRadius, pFire, pBlockInteraction);
    }

    @Override
    public DamageSource getDamageSource() {
        if (getDamageSource() != null) {
            return MalumDamageSourceRegistry.SOUL_STRIKE;
        }
        return MalumDamageSourceRegistry.causeSoulStrikeDamage(getDamageSource().getSource());
    }

    public static EthericExplosion explode(World level, @Nullable Entity pEntity, double pX, double pY, double pZ, float pExplosionRadius, DestructionType pMode) {
        return explode(level, pEntity, null, null, pX, pY, pZ, pExplosionRadius, false, pMode);
    }

    public static EthericExplosion explode(World level, @Nullable Entity pEntity, double pX, double pY, double pZ, float pExplosionRadius, boolean pCausesFire, DestructionType pMode) {
        return explode(level, pEntity, null, null, pX, pY, pZ, pExplosionRadius, pCausesFire, pMode);
    }

    public static EthericExplosion explode(World level, @Nullable Entity pExploder, @Nullable DamageSource pDamageSource, @Nullable ExplosionBehavior pContext, double pX, double pY, double pZ, float pSize, boolean pCausesFire, DestructionType pMode) {
        EthericExplosion explosion = new EthericExplosion(level, pExploder, pDamageSource, pContext, pX, pY, pZ, pSize, pCausesFire, pMode);
        if (!level.isClient) {
            explosion.collectBlocksAndDamageEntities();
        }
        explosion.affectWorld(true);
        return explosion;
    }
}
