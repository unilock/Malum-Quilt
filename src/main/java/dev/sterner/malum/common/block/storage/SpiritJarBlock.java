package dev.sterner.malum.common.block.storage;

import com.sammy.lodestone.forge.ItemHandlerHelper;
import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.storage.SpiritJarBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class SpiritJarBlock<T extends SpiritJarBlockEntity> extends WaterLoggedEntityBlock<T> {

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

	@Override
	public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		System.out.println("StartBlockBreakJar");
		handleAttack(world, pos, player);
		super.onBlockBreakStart(state, world, pos, player);
	}

	public boolean handleAttack(World pWorld, BlockPos pPos, PlayerEntity pPlayer) {
		BlockEntity be = pWorld.getBlockEntity(pPos);
		if (be instanceof SpiritJarBlockEntity jar) {
			var jarHandler = jar.inventory.getValueUnsafer();
			ItemStack item = jarHandler.extractItemStack(0, pPlayer.isSneaking() ? 64 : 1, false);

			if (!item.isEmpty()) {
				ItemHandlerHelper.giveItemToPlayer(pPlayer, item, pPlayer.getInventory().selectedSlot);

				if (!pWorld.isClient()) {
					BlockHelper.updateAndNotifyState(pWorld, pPos);
				}
				return true;
			}
		}
		return false;
	}

	public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(5.5d, -0.5d, 5.5d, 10.5d, 2.0d, 10.5d),
                                                             Block.createCuboidShape(2.5d, 0.5d, 2.5d, 13.5d, 13.5d, 13.5d),
                                                             Block.createCuboidShape(4.5d, 13.5d, 4.5d, 11.5d, 14.5d, 11.5d),
                                                             Block.createCuboidShape(3.5d, 14.5d, 3.5d, 12.5d, 16.5d, 12.5d));

    public SpiritJarBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SpiritJarBlockEntity(pos, state);
    }

	//TODO remove
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		}

		return ((SpiritJarBlockEntity) world.getBlockEntity(pos)).onUse(player, hand);
	}
}
