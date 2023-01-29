package dev.sterner.malum.common.component;

import com.sammy.lodestone.systems.worldgen.LodestoneBlockFiller;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import dev.sterner.malum.common.block.blight.BlightedSoilBlock;
import dev.sterner.malum.common.network.packet.s2c.block.blight.BlightMistParticlePacket;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import dev.sterner.malum.common.world.gen.feature.SoulwoodTreeFeature;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.util.Map;

public class MalumWorldComponent implements AutoSyncedComponent, ServerTickingComponent {
	public World world;
	public int blightTimer, intensity, rate, times;
	public BlockPos sourcePos;
	public Map<Integer, Double> noiseValues;

	public boolean discarded;

	public MalumWorldComponent(World world){
		this.world = world;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		discarded = tag.getBoolean("discarded");
		blightTimer = tag.getInt("blightTimer");
		intensity = tag.getInt("intensity");
		rate = tag.getInt("rate");
		times = tag.getInt("times");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("discarded", discarded);
		tag.putInt("blightTimer", blightTimer);
		tag.putInt("intensity", intensity);
		tag.putInt("rate", rate);
		tag.putInt("times", times);
	}

	public void end(){
		discarded = true;
		MalumComponents.MALUM_WORLD_COMPONENT.sync(world);
	}

	@Override
	public void serverTick() {
		if (times == 0) {
			end();
			return;
		}
		if (blightTimer == 0) {
			blightTimer = rate;
			times--;
			createBlight((ServerWorld) world);
			intensity+=2;
		} else {
			blightTimer--;
		}
	}
	public void createBlight(ServerWorld world) {
		LodestoneBlockFiller filler = new LodestoneBlockFiller(false);
		if (noiseValues == null) {
			noiseValues = SoulwoodTreeFeature.generateBlight(world, filler, sourcePos, intensity);
		} else {
			SoulwoodTreeFeature.generateBlight(world, filler, noiseValues, sourcePos, intensity);
		}
		filler.getEntries().entrySet().stream().filter(e -> e.getValue().getState().getBlock() instanceof BlightedSoilBlock).map(Map.Entry::getKey)
				.forEach(p -> PlayerLookup.tracking(world, world.getWorldChunk(p).getPos()).forEach(track -> BlightMistParticlePacket.send(track, p)));

		world.playSound(null, sourcePos, MalumSoundRegistry.MAJOR_BLIGHT_MOTIF, SoundCategory.BLOCKS, 1f, 1.8f);
	}
}
