package dev.sterner.malum.common.blockentity.obelisk;

import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.multiblock.MultiBlockStructure;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import dev.sterner.malum.common.blockentity.spirit_altar.IAltarAccelerator;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import dev.sterner.malum.common.registry.MalumObjects;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.function.Supplier;

public class RunewoodObeliskBlockEntity extends ObeliskCoreBlockEntity implements IAltarAccelerator {
	public static final AltarAcceleratorType OBELISK = new AltarAcceleratorType(4, "obelisk");
	public static final Supplier<MultiBlockStructure> STRUCTURE = () ->  (MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumObjects.RUNEWOOD_OBELISK_COMPONENT.getDefaultState())));

	public RunewoodObeliskBlockEntity(BlockPos pos, BlockState state) {
		super(MalumBlockEntityRegistry.RUNEWOOD_OBELISK, STRUCTURE.get(), pos, state);
	}

	@Override
	public AltarAcceleratorType getAcceleratorType() {
		return OBELISK;
	}

	@Override
	public float getAcceleration() {
		return 1f;
	}

	@Override
	public void addParticles(Color color, Color endColor, float alpha, BlockPos altarPos, Vec3d altarItemPos) {
		Vec3d startPos = BlockHelper.fromBlockPos(pos).add(0.5f, 2.15f, 0.5f);
		ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
				.setAlpha(alpha, 0f)
				.setLifetime(35)
				.setScale(0.2f + world.random.nextFloat() * 0.1f, 0)
				.randomOffset(0.1f)
				.randomMotion(0.01f, 0.01f)
				.setColor(color, endColor)
				.setSpin(0.1f + world.random.nextFloat() * 0.2f)
				.randomMotion(0.0025f, 0.0025f)
				.enableNoClip()
				.repeat(world, startPos.x, startPos.y, startPos.z, 1);

		ParticleBuilders.create(LodestoneParticles.SPARKLE_PARTICLE)
				.setAlpha(alpha, 0f)
				.setLifetime(25)
				.setScale(0.5f, 0)
				.randomOffset(0.1, 0.1)
				.randomMotion(0.02f, 0.02f)
				.setColor(color, endColor)
				.randomMotion(0.0025f, 0.0025f)
				.enableNoClip()
				.repeat(world, startPos.x, startPos.y, startPos.z, 2);

	}
}
