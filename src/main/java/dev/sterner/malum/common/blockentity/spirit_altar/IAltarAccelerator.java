package dev.sterner.malum.common.blockentity.spirit_altar;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public interface IAltarAccelerator {
    public AltarAcceleratorType getAcceleratorType();
    public default boolean canAccelerate()
    {
        return true;
    }
    public float getAcceleration();

    default void addParticles(BlockPos altarPos, Vec3d altarentity) {

    }
    public default void addParticles(Color color, Color endColor, float alpha, BlockPos altarPos, Vec3d altarItemPos)
    {

    }

    public record AltarAcceleratorType(int maximumEntries, String type) {
    }
}
