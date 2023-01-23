package dev.sterner.malum.common.block.obelisk;

import com.sammy.lodestone.systems.block.LodestoneEntityBlock;
import dev.sterner.malum.common.blockentity.obelisk.BrilliantObeliskBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BrillianceObeliskCoreBlock extends ObeliskCoreBlock<BrilliantObeliskBlockEntity> {
	public BrillianceObeliskCoreBlock(AbstractBlock.Settings properties) {
		super(properties);
		setBlockEntity(MalumBlockEntityRegistry.BRILLIANT_OBELISK);
	}

	@Override
	public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new BrilliantObeliskBlockEntity(pos, state);
	}
}
