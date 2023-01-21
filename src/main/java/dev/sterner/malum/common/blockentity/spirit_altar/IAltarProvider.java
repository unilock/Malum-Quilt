package dev.sterner.malum.common.blockentity.spirit_altar;

import com.sammy.lodestone.systems.blockentity.LodestoneBlockEntityInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public interface IAltarProvider {
    LodestoneBlockEntityInventory getInventoryForAltar();
    Vec3d getItemPosForAltar();
    BlockPos getBlockPosForAltar();
}
