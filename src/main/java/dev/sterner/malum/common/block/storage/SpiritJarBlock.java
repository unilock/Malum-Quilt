package dev.sterner.malum.common.block.storage;

import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.storage.SpiritJarBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class SpiritJarBlock<T extends SpiritJarBlockEntity> extends WaterLoggedEntityBlock<T> {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        SpiritJarBlockEntity blockEntity = (SpiritJarBlockEntity) world.getBlockEntity(pos);

        //noinspection ConstantConditions
        return blockEntity.onUse(state, world, pos, player, hand, hit);
    }



    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? checkType(type, MalumBlockEntityRegistry.SPIRIT_JAR, (world1, pos, state1, blockEntity) -> blockEntity.clientTick(world1, pos, state1)) : null;
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
}
