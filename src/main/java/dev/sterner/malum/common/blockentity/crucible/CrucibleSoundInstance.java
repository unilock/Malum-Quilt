package dev.sterner.malum.common.blockentity.crucible;

import com.sammy.lodestone.systems.sound.LodestoneBlockEntitySoundInstance;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.random.RandomGenerator;

public class CrucibleSoundInstance extends LodestoneBlockEntitySoundInstance<SpiritCrucibleCoreBlockEntity> {
    public CrucibleSoundInstance(SpiritCrucibleCoreBlockEntity blockEntity, float volume, float pitch, RandomGenerator randomSource) {
        super(blockEntity, MalumSoundRegistry.CRUCIBLE_LOOP, volume, pitch, randomSource);
        this.x = blockEntity.getPos().getX() + 0.5f;
        this.y = blockEntity.getPos().getY() + 0.5f;
        this.z = blockEntity.getPos().getZ() + 0.5f;
    }

    @Override
    public void tick() {
        if (blockEntity.focusingRecipe != null || blockEntity.repairRecipe != null) {
            super.tick();
            return;
        }
        setDone();
    }

    public static void playSound(SpiritCrucibleCoreBlockEntity tileEntity) {
        MinecraftClient.getInstance().getSoundManager().playNextTick(new CrucibleSoundInstance(tileEntity, 1, 1, RandomGenerator.createLegacy()));
    }
}
