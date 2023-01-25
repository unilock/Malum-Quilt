package dev.sterner.malum.common.entity.spirit;

import com.sammy.lodestone.helpers.ItemHelper;
import dev.sterner.malum.common.entity.FloatingItemEntity;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.registry.MalumEntityRegistry;
import dev.sterner.malum.common.util.handler.SpiritHarvestHandler;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class PlayerBoundItemEntity extends FloatingItemEntity {
    public UUID ownerUUID;
    public LivingEntity owner;

    public PlayerBoundItemEntity(World world) {
        super(MalumEntityRegistry.NATURAL_SPIRIT, world);
        maxAge = 4000;
    }

    public PlayerBoundItemEntity(World world, UUID ownerUUID, ItemStack stack, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        this(world);
        setOwner(ownerUUID);
        setItem(stack);
        setPos(posX, posY, posZ);
        setVelocity(velX, velY, velZ);
        maxAge = 800;
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    public void setOwner(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
        updateOwner();
    }

    public void updateOwner() {
        if (!world.isClient) {
            owner = (LivingEntity) ((ServerWorld) world).getEntity(ownerUUID);
        }
    }

    @Override
    public void spawnParticles(double x, double y, double z) {
        Vec3d motion = getVelocity();
        Vec3d norm = motion.normalize().multiply(0.05f);
        float extraAlpha = (float) motion.length();
        float cycles = 2;
        for (int i = 0; i < cycles; i++) {
            double lerpX = MathHelper.lerp(i / cycles, x - motion.x, x);
            double lerpY = MathHelper.lerp(i / cycles, y - motion.y, y);
            double lerpZ = MathHelper.lerp(i / cycles, z - motion.z, z);
            SpiritHelper.spawnSpiritParticles(world, lerpX, lerpY, lerpZ, 0.55f+extraAlpha, norm, color, endColor);
        }
    }

    @Override
    public void move() {
        float friction = 0.94f;
        setVelocity(getVelocity().multiply(friction, friction, friction));
        if (owner == null || !owner.isAlive()) {
            if (world.getTime() % 40L == 0) {
                PlayerEntity playerEntity = world.getClosestPlayer(this, 50);
                if (playerEntity != null) {
                    setOwner(playerEntity.getUuid());
                }
            }
            return;
        }
        Vec3d desiredLocation = owner.getPos().add(0, owner.getHeight() / 3, 0);
        float distance = (float) squaredDistanceTo(desiredLocation);
        float velocity = windUp < 0.25f ? 0 : Math.min(windUp-0.25f, 0.8f)*5f;
        moveTime++;
        Vec3d desiredMotion = desiredLocation.subtract(getPos()).normalize().multiply(velocity, velocity, velocity);
        float easing = 0.01f;
        float xMotion = (float) MathHelper.lerp(easing, getVelocity().x, desiredMotion.x);
        float yMotion = (float) MathHelper.lerp(easing, getVelocity().y, desiredMotion.y);
        float zMotion = (float) MathHelper.lerp(easing, getVelocity().z, desiredMotion.z);
        Vec3d resultingMotion = new Vec3d(xMotion, yMotion, zMotion);
        setVelocity(resultingMotion);

        if (distance < 0.4f) {
            if (isAlive()) {
                ItemStack stack = getItem();
                if (stack.getItem() instanceof MalumSpiritItem) {
                    SpiritHarvestHandler.pickupSpirit(stack, owner);
                }
                else {
                    ItemHelper.giveItemToEntity(stack, owner);
                }
                remove(RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        if (ownerUUID != null) {
            compound.putUuid("ownerUUID", ownerUUID);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        if (compound.contains("ownerUUID")) {
            setOwner(compound.getUuid("ownerUUID"));
        }
    }
}
