package dev.sterner.malum.mixin;

import com.sammy.lodestone.forge.INBTSerializableCompound;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements INBTSerializableCompound {

	@Shadow
	public abstract NbtCompound toIdentifiedLocatedNbt();

	@Shadow
	public abstract void readNbt(NbtCompound nbt);

	@Override
	public NbtCompound serializeNBT() {
		return this.toIdentifiedLocatedNbt();
	}

	@Override
	public void deserializeNBT(NbtCompound nbt) {
		this.readNbt(nbt);
	}
}
