package dev.sterner.malum.common.block.obelisk;

import dev.sterner.malum.common.blockentity.obelisk.RunewoodObeliskBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.AbstractBlock;

public class RunewoodObeliskCoreBlock extends ObeliskCoreBlock<RunewoodObeliskBlockEntity> {
	public RunewoodObeliskCoreBlock(AbstractBlock.Settings properties) {
		super(properties, MalumBlockEntityRegistry.RUNEWOOD_OBELISK);
	}
}
