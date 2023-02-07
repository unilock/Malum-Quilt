package dev.sterner.malum.common.item.spirit;

import dev.sterner.malum.api.interfaces.item.SoulContainerItem;
import dev.sterner.malum.common.component.MalumComponents;
import dev.sterner.malum.common.entity.spirit.SoulEntity;
import dev.sterner.malum.common.spirit.MalumEntitySpiritData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulStaveItem extends Item implements SoulContainerItem {
    public SoulStaveItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack pStack) {
        return UseAction.BOW;
    }

    @Override
    public String getTranslationKey(ItemStack pStack) {
        if (pStack.hasNbt() && pStack.getNbt().contains(MalumEntitySpiritData.SOUL_DATA)) {
            return "item.malum.filled_soulwood_stave";
        }
        return super.getTranslationKey(pStack);
    }

    @Override
    public void appendTooltip(ItemStack pStack, @Nullable World pWorld, List<Text> pTooltipComponents, TooltipContext pIsAdvanced) {
        if (pStack.hasNbt()) {
            NbtCompound tag = pStack.getNbt();
            if (tag.contains(MalumEntitySpiritData.SOUL_DATA)) {
                MalumEntitySpiritData data = MalumEntitySpiritData.load(tag);
                pTooltipComponents.add(Text.translatable("malum.spirit.description.stored_soul").formatted(Formatting.GRAY));
                pTooltipComponents.addAll(data.createTooltip());
            }
        }
        super.appendTooltip(pStack, pWorld, pTooltipComponents, pIsAdvanced);
    }


	@Override
    public TypedActionResult<ItemStack> interactWithSoul(PlayerEntity player, Hand hand, SoulEntity soul) {
        ItemStack stack = player.getStackInHand(hand);
        ItemStack otherStack = player.getStackInHand(hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND);
        if (otherStack.getItem() instanceof SoulContainerItem) {
            return TypedActionResult.fail(stack);
        }
        if (!soul.spiritData.equals(MalumEntitySpiritData.EMPTY) && (!stack.getOrCreateNbt().contains(MalumEntitySpiritData.SOUL_DATA))) {
            soul.spiritData.saveTo(stack.getOrCreateNbt());
            soul.discard();
            player.swingHand(hand, true);
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.fail(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            MalumComponents.PLAYER_COMPONENT.maybeGet(user).ifPresent(c -> {
                if (c.targetedSoulUUID != null) {
                    LivingEntity entity = (LivingEntity) serverWorld.getEntity(c.targetedSoulUUID);
                    if (entity != null && entity.isAlive()) {
                        user.setCurrentHand(hand);
                    }
                } else {
                    fetchSoul(user, hand);
                }
            });
        }
        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(stack.getItem(), 40);
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
