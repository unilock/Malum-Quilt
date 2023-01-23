package dev.sterner.malum.common.blockentity.storage;

import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.systems.blockentity.ItemHolderBlockEntity;
import com.sammy.lodestone.systems.blockentity.LodestoneBlockEntityInventory;
import dev.sterner.malum.common.blockentity.spirit_altar.IAltarProvider;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ItemPedestalBlockEntity extends ItemHolderBlockEntity implements IAltarProvider {
    public ItemPedestalBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntityRegistry.ITEM_PEDESTAL, pos, state);
        inventory = new LodestoneBlockEntityInventory(1, 64) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateAndNotifyState(world, pos);
            }
        };
    }

    public ItemPedestalBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public ItemStack getHeldItem() {
        return inventory.getStack(0);
    }


    @Override
    public LodestoneBlockEntityInventory getInventoryForAltar() {
        return inventory;
    }

    @Override
    public Vec3d getItemPosForAltar() {
        return getItemPos();
    }

    @Override
    public BlockPos getBlockPosForAltar() {
        return pos;
    }

    public Vec3d getItemPos() {
        return BlockHelper.fromBlockPos(getPos()).add(itemOffset());
    }

    public Vec3d itemOffset() {
        return new Vec3d(0.5f, 1.1f, 0.5f);
    }

    @Override
    public void tick() {
        if (world.isClient()) {
            if (inventory.getStack(0).getItem() instanceof MalumSpiritItem item) {
                Vec3d pos = getItemPos();
                double x = pos.x;
                double y = pos.y + Math.sin((world.getTime() ) / 20f) * 0.1f;
                double z = pos.z;
                SpiritHelper.spawnSpiritGlimmerParticles(world, x, y, z, item.type.getColor(), item.type.getEndColor());
            }
        }
    }
}
