package dev.sterner.malum.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.sterner.malum.common.registry.MalumSpiritAffinityRegistry;
import dev.sterner.malum.common.spirit.MalumSpiritAffinity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class MalumPlayerComponent implements AutoSyncedComponent {
    private final LivingEntity obj;
    public UUID targetedSoulUUID;
    public int targetedSoulId;
    public int soulFetchCooldown;

    public MalumSpiritAffinity affinity;

    public float soulWard;
    public float soulWardProgress;

	public int soulsShattered;
	public boolean obtainedEncyclopedia;

    public MalumPlayerComponent(LivingEntity player) {
        obj = player;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        affinity = MalumSpiritAffinityRegistry.AFFINITIES.get(tag.getString("affinity"));

        if (tag.contains("targetedSoulUUID")) {
            targetedSoulUUID = tag.getUuid("targetedSoulUUID");
        }
        targetedSoulId = tag.getInt("targetedSoulId");
        soulFetchCooldown = tag.getInt("soulFetchCooldown");

        soulWard = tag.getFloat("soulWard");
        soulWardProgress = tag.getFloat("soulWardProgress");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        if (targetedSoulUUID != null) {
            tag.putUuid("targetedSoulUUID", targetedSoulUUID);
        }
        tag.putInt("targetedSoulId", targetedSoulId);
        tag.putInt("soulFetchCooldown", soulFetchCooldown);

        if (affinity != null) {
            tag.putString("affinity", affinity.identifier);
        }
        tag.putFloat("soulWard", soulWard);
        tag.putFloat("soulWardProgress", soulWardProgress);
    }
}
