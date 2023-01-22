package dev.sterner.malum.common.blockentity.crucible;

import com.sammy.lodestone.helpers.BlockHelper;
import com.sammy.lodestone.helpers.DataHelper;
import com.sammy.lodestone.setup.LodestoneParticles;
import com.sammy.lodestone.systems.blockentity.LodestoneBlockEntityInventory;
import com.sammy.lodestone.systems.multiblock.MultiBlockCoreEntity;
import com.sammy.lodestone.systems.multiblock.MultiBlockStructure;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
import dev.sterner.malum.common.blockentity.tablet.ITabletTracker;
import dev.sterner.malum.common.blockentity.tablet.TwistedTabletBlockEntity;
import dev.sterner.malum.common.item.impedus.ImpetusItem;
import dev.sterner.malum.common.item.spirit.MalumSpiritItem;
import dev.sterner.malum.common.network.packet.s2c.block.functional.AltarConsumeParticlePacket;
import dev.sterner.malum.common.network.packet.s2c.block.functional.AltarCraftParticlePacket;
import dev.sterner.malum.common.recipe.SpiritFocusingRecipe;
import dev.sterner.malum.common.recipe.SpiritRepairRecipe;
import dev.sterner.malum.common.recipe.SpiritWithCount;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumSoundRegistry;
import dev.sterner.malum.common.spirit.SpiritHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.networking.api.PlayerLookup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SpiritCrucibleCoreBlockEntity extends MultiBlockCoreEntity implements IAccelerationTarget, ITabletTracker {
    public static final Supplier<MultiBlockStructure> STRUCTURE = () ->  MultiBlockStructure.of(new MultiBlockStructure.StructurePiece(0, 1, 0, MalumObjects.SPIRIT_CRUCIBLE_COMPONENT.getDefaultState()));

    public LodestoneBlockEntityInventory inventory;
    public LodestoneBlockEntityInventory spiritInventory;
    public SpiritFocusingRecipe focusingRecipe;
    public SpiritRepairRecipe repairRecipe;
    public float spiritAmount;
    public float spiritSpin;
    public float speed;
    public float damageChance;
    public int maxDamage;
    public float progress;
    public boolean isCrafting;

    public int queuedCracks;
    public int crackTimer;

    public List<BlockPos> tabletPositions = new ArrayList<>();
    public List<TwistedTabletBlockEntity> twistedTablets = new ArrayList<>();
    public TwistedTabletBlockEntity validTablet;
    public int tabletFetchCooldown;

    public List<BlockPos> acceleratorPositions = new ArrayList<>();
    public List<ICrucibleAccelerator> accelerators = new ArrayList<>();

    public SpiritCrucibleCoreBlockEntity(BlockEntityType<? extends SpiritCrucibleCoreBlockEntity> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
        inventory = new LodestoneBlockEntityInventory(1, 1, t -> !(t.getItem() instanceof MalumSpiritItem)) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                needsSync = true;
                BlockHelper.updateAndNotifyState(world, pos);
            }
        };
        spiritInventory = new LodestoneBlockEntityInventory(4, 64) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                needsSync = true;
                spiritAmount = Math.max(1, MathHelper.lerp(0.15f, spiritAmount, nonEmptyItemAmount + 1));
                BlockHelper.updateAndNotifyState(world, pos);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                if (!(stack.getItem() instanceof MalumSpiritItem spiritItem))
                    return false;

                for (int i = 0; i < size(); i++) {
                    if (i != slot) {
                        ItemStack stackInSlot = getStack(i);
                        if (!stackInSlot.isEmpty() && stackInSlot.getItem() == spiritItem)
                            return false;
                    }
                }
                return true;
            }
        };
    }

    public SpiritCrucibleCoreBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntityRegistry.SPIRIT_CRUCIBLE, STRUCTURE.get(), pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound compound) {
        if (spiritAmount != 0) {
            compound.putFloat("spiritAmount", spiritAmount);
        }
        if (progress != 0) {
            compound.putFloat("progress", progress);
        }
        if (speed != 0) {
            compound.putFloat("speed", speed);
        }
        if (damageChance != 0) {
            compound.putFloat("damageChance", damageChance);
        }
        if (maxDamage != 0) {
            compound.putInt("maxDamage", maxDamage);
        }
        if (queuedCracks != 0) {
            compound.putInt("queuedCracks", queuedCracks);
        }

        inventory.save(compound);
        spiritInventory.save(compound, "spiritInventory");

        saveTwistedTabletData(compound);
        saveAcceleratorData(compound);
    }

    @Override
    public void readNbt(NbtCompound compound) {
        spiritAmount = compound.getFloat("spiritAmount");
        progress = compound.getFloat("progress");
        speed = compound.getFloat("speed");
        damageChance = compound.getFloat("damageChance");
        maxDamage = compound.getInt("maxDamage");
        queuedCracks = compound.getInt("queuedCracks");

        inventory.readNbt(compound);
        spiritInventory.readNbt(compound, "spiritInventory");

        loadTwistedTabletData(world, compound);
        loadAcceleratorData(world, compound);
        super.readNbt(compound);
    }

    @Override
    public ActionResult onUse(PlayerEntity player, Hand hand) {
        if (world.isClient()) {
            return ActionResult.CONSUME;
        }
        if (hand.equals(Hand.MAIN_HAND)) {
            fetchTablets(world, pos.up());
            ItemStack heldStack = player.getMainHandStack();
            recalibrateAccelerators(world, pos);
            if (!(heldStack.getItem() instanceof MalumSpiritItem)) {
                ItemStack stack = inventory.interact(world, player, hand);
                if (!stack.isEmpty()) {
                    return ActionResult.SUCCESS;
                }
            }
            spiritInventory.interact(world, player, hand);
            if (heldStack.isEmpty()) {
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
        }
        return super.onUse(player, hand);
    }

    @Override
    public void onBreak(@Nullable PlayerEntity player) {
        inventory.dumpItems(world, pos);
        spiritInventory.dumpItems(world, pos);
        super.onBreak(player);
    }

    @Override
    public void fetchTablets(World world, BlockPos pos) {
        ITabletTracker.super.fetchTablets(world, pos);
        if (focusingRecipe == null && !getTablets().isEmpty()) {
            for (TwistedTabletBlockEntity tablet : getTablets()) {
                repairRecipe = SpiritRepairRecipe.getRecipe(world, inventory.getStack(0), tablet.inventory.getStack(0), spiritInventory.nonEmptyItemStacks);
                if (repairRecipe != null) {
                    validTablet = tablet;
                    break;
                }
            }
        } else {
            repairRecipe = null;
            validTablet = null;
        }
    }

    @Override
    public List<TwistedTabletBlockEntity> getTablets() {
        return twistedTablets;
    }

    @Override
    public List<BlockPos> getTabletPositions() {
        return tabletPositions;
    }

    @Override
    public boolean canBeAccelerated() {
        return focusingRecipe != null && !isRemoved();
    }

    @Override
    public List<ICrucibleAccelerator> getAccelerators() {
        return accelerators;
    }

    @Override
    public List<BlockPos> getAcceleratorPositions() {
        return acceleratorPositions;
    }

    @Override
    public int getLookupRange() {
        return 4;
    }

    @Override
    public void init() {
        if (world.isClient() && focusingRecipe == null && repairRecipe == null) {
            CrucibleSoundInstance.playSound(this);
        }
        fetchTablets(world, pos.up());
        focusingRecipe = SpiritFocusingRecipe.getRecipe(world, inventory.getStack(0), spiritInventory.nonEmptyItemStacks);
    }

    @Override
    public void tick() {
        super.tick();
        spiritAmount = Math.max(1, MathHelper.lerp(0.1f, spiritAmount, spiritInventory.nonEmptyItemAmount));
        if (queuedCracks > 0) {
            crackTimer++;
            if (crackTimer % 7 == 0) {
                float pitch = 0.95f + (crackTimer - 8) * 0.015f + world.random.nextFloat() * 0.05f;
                world.playSound(null, pos, MalumSoundRegistry.IMPETUS_CRACK, SoundCategory.BLOCKS, 0.7f, pitch);
                queuedCracks--;
                if (queuedCracks == 0) {
                    crackTimer = 0;
                }
            }
        }
        if (world.isClient) {
            spiritSpin += (1 + Math.cos(Math.sin(world.getTime() * 0.1f))) * (1 + speed * 0.1f);
            passiveParticles();
        } else {
            if (focusingRecipe != null) {
                if (!accelerators.isEmpty()) {
                    boolean canAccelerate = true;
                    for (ICrucibleAccelerator accelerator : accelerators) {
                        boolean canAcceleratorAccelerate = accelerator.canAccelerate();
                        if (!canAcceleratorAccelerate) {
                            canAccelerate = false;
                        }
                    }
                    if (!canAccelerate) {
                        recalibrateAccelerators(world, pos);
                        BlockHelper.updateAndNotifyState(world, pos);
                    }
                }
            } else if (speed > 0) {
                speed = 0f;
                damageChance = 0f;
                maxDamage = 0;
                BlockHelper.updateAndNotifyState(world, pos);
            }

            if (focusingRecipe != null) {
                isCrafting = true;
                progress += 1 + speed;
                if (progress >= focusingRecipe.time) {
                    craft();
                }
                return;
            } else if (repairRecipe != null && !getTablets().isEmpty()) {
                isCrafting = true;
                ItemStack damagedItem = inventory.getStack(0);

                int time = 400 + damagedItem.getDamage() * 5;
                progress++;
                if (!repairRecipe.repairMaterial.matches(validTablet.inventory.getStack(0))) {
                    fetchTablets(world, pos.up());
                }
                if (progress >= time) {
                    repair();
                }
            }
            if (focusingRecipe == null && repairRecipe == null) {
                progress = 0;
                isCrafting = false;
            }

            if (focusingRecipe == null) {
                tabletFetchCooldown--;
                if (tabletFetchCooldown <= 0) {
                    tabletFetchCooldown = 5;
                    fetchTablets(world, pos.up());
                }
            }
        }
    }

    public void repair() {
        Vec3d itemPos = getItemPos(this);
        Vec3d providedItemPos = validTablet.getItemPos();
        ItemStack damagedItem = inventory.getStack(0);
        ItemStack repairMaterial = validTablet.inventory.getStack(0);
        ItemStack result = SpiritRepairRecipe.getRepairRecipeOutput(damagedItem);
        result.setDamage(Math.max(0, result.getDamage() - (int) (result.getMaxDamage() * repairRecipe.durabilityPercentage)));
        inventory.setStackInSlot(0, result);

        if (repairRecipe.repairMaterial.getItem() instanceof MalumSpiritItem malumSpiritItem) {
            PlayerLookup.tracking(this).forEach(trackingPlayer -> AltarConsumeParticlePacket.send(trackingPlayer, repairMaterial, List.of(malumSpiritItem.type.identifier), providedItemPos.x, providedItemPos.y, providedItemPos.z, itemPos.x, itemPos.y, itemPos.z));
        } else {
            PlayerLookup.tracking(this).forEach(trackingPlayer -> AltarConsumeParticlePacket.send(trackingPlayer, repairMaterial, repairRecipe.spirits.stream().map(s -> s.type.identifier).collect(Collectors.toList()), providedItemPos.x, providedItemPos.y, providedItemPos.z, itemPos.x, itemPos.y, itemPos.z));
        }

        repairMaterial.decrement(repairRecipe.repairMaterial.getCount());
        validTablet.inventory.updateData();

        for (SpiritWithCount spirit : repairRecipe.spirits) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack spiritStack = spiritInventory.getStack(i);
                if (spirit.matches(spiritStack)) {
                    spiritStack.decrement(spirit.count);
                    break;
                }
            }
        }

        spiritInventory.updateData();
        if (!repairRecipe.spirits.isEmpty()) {
            PlayerLookup.tracking(this).forEach(trackingPlayer -> AltarCraftParticlePacket.send(trackingPlayer, repairRecipe.spirits.stream().map(s -> s.type.identifier).collect(Collectors.toList()), itemPos));
        } else if (repairRecipe.repairMaterial.getItem() instanceof MalumSpiritItem malumSpiritItem) {
            PlayerLookup.tracking(this).forEach(trackingPlayer -> AltarCraftParticlePacket.send(trackingPlayer, List.of(malumSpiritItem.type.identifier), itemPos));
        }
        repairRecipe = SpiritRepairRecipe.getRecipe(world, damagedItem, repairMaterial, spiritInventory.nonEmptyItemStacks);
        fetchTablets(world, pos.up());
        BlockHelper.updateAndNotifyState(world, validTablet.getPos());
        finishRecipe();
    }

    public void craft() {
        Vec3d itemPos = getItemPos(this);
        ItemStack stack = inventory.getStack(0);
        ItemStack outputStack = focusingRecipe.output.copy();
        if (focusingRecipe.durabilityCost != 0 && stack.isDamageable()) {
            int durabilityCost = focusingRecipe.durabilityCost;
            float chance = damageChance;
            while (durabilityCost < durabilityCost + maxDamage) {
                if (world.random.nextFloat() < chance) {
                    durabilityCost++;
                    chance *= chance;
                } else {
                    break;
                }
            }
            queuedCracks = durabilityCost;
            boolean success = stack.damage(durabilityCost, world.random, null);
            if (success && stack.getItem() instanceof ImpetusItem impetusItem) {
                inventory.setStackInSlot(0, impetusItem.getCrackedVariant().getDefaultStack());
            }
        }
        for (SpiritWithCount spirit : focusingRecipe.spirits) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack spiritStack = spiritInventory.getStack(i);
                if (spirit.matches(spiritStack)) {
                    spiritStack.decrement(spirit.count);
                    break;
                }
            }
        }
        spiritInventory.updateData();
        world.spawnEntity(new ItemEntity(world, itemPos.x, itemPos.y, itemPos.z, outputStack));
        PlayerLookup.tracking(this).forEach(trackingPlayer -> AltarCraftParticlePacket.send(trackingPlayer, focusingRecipe.spirits.stream().map(s -> s.type.identifier).collect(Collectors.toList()), itemPos));
        focusingRecipe = SpiritFocusingRecipe.getRecipe(world, stack, spiritInventory.nonEmptyItemStacks);
        finishRecipe();
    }

    public void finishRecipe() {
        world.playSound(null, pos, MalumSoundRegistry.CRUCIBLE_CRAFT, SoundCategory.BLOCKS, 1, 0.75f + world.random.nextFloat() * 0.5f);
        progress = 0;
        recalibrateAccelerators(world, pos);
        BlockHelper.updateAndNotifyState(world, pos);
    }

    @Override
    public Map<ICrucibleAccelerator.CrucibleAcceleratorType, Integer> recalibrateAccelerators(World world, BlockPos pos) {
        Map<ICrucibleAccelerator.CrucibleAcceleratorType, Integer> accelerators = IAccelerationTarget.super.recalibrateAccelerators(world, pos);
        speed = 0f;
        damageChance = 0f;
        maxDamage = 0;
        accelerators.forEach((e, c) -> {
            speed += e.getAcceleration(c);
            damageChance = Math.min(damageChance + e.getDamageChance(c), 1);
            maxDamage += e.getMaximumDamage(c);
        });
        return accelerators;
    }

    public static Vec3d getItemPos(SpiritCrucibleCoreBlockEntity blockEntity) {
        return BlockHelper.fromBlockPos(blockEntity.getPos()).add(blockEntity.itemOffset());
    }

    public Vec3d itemOffset() {
        return new Vec3d(0.5f, 1.6f, 0.5f);
    }

    public Vec3d spiritOffset(int index, float partialTicks) {
        float distance = 0.75f + (float) Math.sin((spiritSpin + partialTicks) / 20f) * 0.025f;
        float height = 1.75f;
        return DataHelper.rotatingRadialOffset(new Vec3d(0.5f, height, 0.5f), distance, index, spiritAmount, (long) (spiritSpin + partialTicks), 360);
    }

    public void passiveParticles() {
        Vec3d itemPos = getItemPos(this);
        //passive spirit particles
        int spiritsRendered = 0;
        if (!spiritInventory.isEmpty()) {
            for (int i = 0; i < spiritInventory.slotCount; i++) {
                ItemStack item = spiritInventory.getStack(i);
                if (item.getItem() instanceof MalumSpiritItem spiritSplinterItem) {
                    Vec3d offset = spiritOffset(spiritsRendered++, 0);
                    Color color = spiritSplinterItem.type.getSplinterItem().getColor();
                    Color endColor = spiritSplinterItem.type.getSplinterItem().getEndColor();
                    double x = getPos().getX() + offset.getX();
                    double y = getPos().getY() + offset.getY();
                    double z = getPos().getZ() + offset.getZ();
                    SpiritHelper.spawnSpiritGlimmerParticles(world, x, y, z, color, endColor);
                }
            }
        }
        //spirit particles shot out from the twisted tablet
        if (repairRecipe != null) {
            TwistedTabletBlockEntity tabletBlockEntity = validTablet;

            List<Color> colors = new ArrayList<>();
            List<Color> endColors = new ArrayList<>();
            if (repairRecipe.repairMaterial.getItem() instanceof MalumSpiritItem spiritItem) {
                colors.add(spiritItem.type.getSplinterItem().getColor());
                endColors.add(spiritItem.type.getSplinterItem().getEndColor());
            } else if (!spiritInventory.isEmpty()) {
                for (int i = 0; i < spiritInventory.slotCount; i++) {
                    ItemStack item = spiritInventory.getStack(i);
                    if (item.getItem() instanceof MalumSpiritItem spiritItem) {
                        colors.add(spiritItem.type.getSplinterItem().getColor());
                        endColors.add(spiritItem.type.getSplinterItem().getEndColor());
                    }
                }
            }
            for (int i = 0; i < colors.size(); i++) {
                Color color = colors.get(i);
                Color endColor = endColors.get(i);
                Vec3d tabletItemPos = tabletBlockEntity.getItemPos();
                Vec3d velocity = tabletItemPos.subtract(itemPos).normalize().multiply(-0.1f);

                starParticles(itemPos, color, endColor);

                ParticleBuilders.create(LodestoneParticles.STAR_PARTICLE)
                    .setAlpha(0.24f / colors.size(), 0f)
                    .setLifetime(15)
                    .setScale(0.45f + world.random.nextFloat() * 0.15f, 0)
                    .randomOffset(0.05)
                    .setSpinOffset((-0.075f * world.getTime()) % 6.28f)
                    .setColor(color, endColor)
                    .enableNoClip()
                    .repeat(world, tabletItemPos.x, tabletItemPos.y, tabletItemPos.z, 1);

                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.4f / colors.size(), 0f)
                    .setLifetime((int) (10 + world.random.nextInt(8) + Math.sin((0.5 * world.getTime()) % 6.28f)))
                    .setScale(0.2f + world.random.nextFloat() * 0.15f, 0)
                    .randomOffset(0.05)
                    .setSpinOffset((0.075f * world.getTime() % 6.28f))
                    .setSpin(0.1f + world.random.nextFloat() * 0.05f)
                    .setColor(color.brighter(), endColor)
                    .setAlphaCoefficient(0.5f)
                    .setColorCoefficient(0.75f)
                    .setMotion(velocity.x, velocity.y, velocity.z)
                    .enableNoClip()
                    .repeat(world, tabletItemPos.x, tabletItemPos.y, tabletItemPos.z, 1);
            }
        }
        if (focusingRecipe != null || repairRecipe != null && !(validTablet.inventory.getStack(0).getItem() instanceof MalumSpiritItem)) {
            focusingParticles(itemPos);
        }
    }

    public void focusingParticles(Vec3d itemPos) {
        int spiritsRendered = 0;
        for (int i = 0; i < spiritInventory.slotCount; i++) {
            ItemStack item = spiritInventory.getStack(i);
            for (ICrucibleAccelerator accelerator : accelerators) {
                if (accelerator != null) {
                    accelerator.addParticles(pos, itemPos);
                }
            }
            if (item.getItem() instanceof MalumSpiritItem spiritSplinterItem) {
                Vec3d offset = spiritOffset(spiritsRendered++, 0);
                Color color = spiritSplinterItem.type.getColor();
                Color endColor = spiritSplinterItem.type.getEndColor();
                double x = getPos().getX() + offset.getX();
                double y = getPos().getY() + offset.getY();
                double z = getPos().getZ() + offset.getZ();
                Vec3d velocity = new Vec3d(x, y, z).subtract(itemPos).normalize().multiply(-0.03f);
                if (repairRecipe == null) {
                    for (ICrucibleAccelerator accelerator : accelerators) {
                        if (accelerator != null) {
                            accelerator.addParticles(color, endColor, 0.08f / spiritInventory.nonEmptyItemAmount, pos, itemPos);
                        }
                    }
                }
                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.30f, 0f)
                    .setLifetime(40)
                    .setScale(0.2f, 0)
                    .randomOffset(0.02f)
                    .randomMotion(0.01f, 0.01f)
                    .setColor(color, endColor)
                    .setColorCoefficient(0.75f)
                    .randomMotion(0.0025f, 0.0025f)
                    .addMotion(velocity.x, velocity.y, velocity.z)
                    .enableNoClip()
                    .repeat(world, x, y, z, 1);

                ParticleBuilders.create(LodestoneParticles.WISP_PARTICLE)
                    .setAlpha(0.12f / spiritInventory.nonEmptyItemAmount, 0f)
                    .setLifetime(25)
                    .setScale(0.2f + world.random.nextFloat() * 0.1f, 0)
                    .randomOffset(0.05)
                    .setSpinOffset((0.075f * world.getTime() % 6.28f))
                    .setColor(color, endColor)
                    .enableNoClip()
                    .repeat(world, itemPos.x, itemPos.y, itemPos.z, 1);

                starParticles(itemPos, color, endColor);
            }
        }
    }

    public void starParticles(Vec3d itemPos, Color color, Color endColor) {
        ParticleBuilders.create(LodestoneParticles.STAR_PARTICLE)
            .setAlpha(0.07f / spiritInventory.nonEmptyItemAmount, 0.16f / spiritInventory.nonEmptyItemAmount, 0f)
            .setScaleEasing(Easing.SINE_IN, Easing.SINE_OUT)
            .setLifetime(25)
            .setScale(0.2f, 0.45f + world.random.nextFloat() * 0.1f, 0)
            .setScaleEasing(Easing.QUINTIC_IN, Easing.CUBIC_IN_OUT)
            .setSpin(0, 0.2f, 0)
            .setSpinEasing(Easing.CUBIC_IN, Easing.EXPO_IN)
            .randomOffset(0.1)
            .randomMotion(0.02f)
            .setColor(color, endColor)
            .setColorEasing(Easing.BOUNCE_IN_OUT)
            .setColorCoefficient(0.5f)
            .randomMotion(0.0025f, 0.0025f)
            .enableNoClip()
            .overwriteRemovalProtocol(SimpleParticleEffect.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
            .repeat(world, itemPos.x, itemPos.y, itemPos.z, 1);
    }
}
