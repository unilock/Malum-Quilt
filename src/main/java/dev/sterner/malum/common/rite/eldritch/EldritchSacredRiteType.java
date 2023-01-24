package dev.sterner.malum.common.rite.eldritch;


import dev.sterner.malum.common.blockentity.totem.TotemBaseBlockEntity;
import dev.sterner.malum.common.network.packet.s2c.block.BlockMistParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.entity.MajorEntityEffectParticlePacket;
import dev.sterner.malum.common.rite.MalumRiteType;
import dev.sterner.malum.common.rite.effect.BlockAffectingRiteEffect;
import dev.sterner.malum.common.rite.effect.EntityAffectingRiteEffect;
import dev.sterner.malum.common.rite.effect.MalumRiteEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.util.List;

import static dev.sterner.malum.common.registry.MalumSpiritTypeRegistry.*;

public class EldritchSacredRiteType extends MalumRiteType {
	public EldritchSacredRiteType() {
		super("greater_sacred_rite", ELDRITCH_SPIRIT, ARCANE_SPIRIT, SACRED_SPIRIT, SACRED_SPIRIT);
	}

	@Override
	public MalumRiteEffect getNaturalRiteEffect() {
		return new BlockAffectingRiteEffect() {
			@Override
			public int getRiteEffectRadius() {
				return BASE_RADIUS * 2;
			}

			@Override
			public BlockPos getRiteEffectCenter(TotemBaseBlockEntity totemBase) {
				return totemBase.getPos();
			}

			@SuppressWarnings("ConstantConditions")
			@Override
			public void riteEffect(TotemBaseBlockEntity totemBase) {
				World world = totemBase.getWorld();
				BlockPos pos = totemBase.getPos();
				getNearbyBlocks(totemBase, Fertilizable.class).forEach(p -> {
					if (world.random.nextFloat() <= 0.06f) {
						BlockState state = world.getBlockState(p);
						for (int i = 0; i < 5 + world.random.nextInt(3); i++) {
							state.randomTick((ServerWorld) world, p, world.random);
						}
						BlockPos particlePos = state.isOpaque() ? p : p.down();
						if(world instanceof ServerWorld serverWorld){
							PlayerLookup.tracking(serverWorld, serverWorld.getWorldChunk(pos).getPos()).forEach(track -> BlockMistParticlePacket.send(track, SACRED_SPIRIT.getColor(), particlePos));
						}
					}
				});
			}
		};
	}

	@Override
	public MalumRiteEffect getCorruptedEffect() {
		return new EntityAffectingRiteEffect() {

			@SuppressWarnings("ConstantConditions")
			@Override
			public void riteEffect(TotemBaseBlockEntity totemBase) {
				World world = totemBase.getWorld();
				List<AnimalEntity> entities = getNearbyEntities(totemBase, AnimalEntity.class, e -> e.canEat() && e.age == 0).toList(); //TODO: it'd be interesting to separate different entity types and then breed those respectively, this would allow you to have up to 30 cows, sheep and pigs rather than up to 30 animals
				if (entities.size() > 30) {
					return;
				}
				entities.forEach(e -> {
					if (world.random.nextFloat() <= 0.01f) {
						e.setLoveTicks(600);
						PlayerLookup.tracking(e).forEach(track -> MajorEntityEffectParticlePacket.send(track, SACRED_SPIRIT.getColor(), e.getX(), e.getY() + e.getHeight() / 2f, e.getZ()));
					}
				});
			}
		};
	}
}
