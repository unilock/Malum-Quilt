package dev.sterner.malum.common.blockentity.tablet;

import dev.sterner.malum.common.blockentity.storage.ItemStandBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class TwistedTabletBlockEntity extends ItemStandBlockEntity {
    public TwistedTabletBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntityRegistry.TWISTED_TABLET, pos, state);
    }

    @Override
    public Vec3d itemOffset() {
        Direction direction = getCachedState().get(Properties.FACING);
        Vec3d directionVector = new Vec3d(direction.getOffsetX(), direction.getOffsetY(), direction.getOffsetZ());
        return new Vec3d(0.5f + directionVector.getX() * 0.25f, 0.5f + directionVector.getY() * 0.4f, 0.5f + directionVector.getZ() * 0.25f);
    }

}
