package dev.sterner.malum.common.registry;

import com.sammy.lodestone.systems.item.tools.magic.*;
import com.sammy.lodestone.systems.multiblock.MultiBlockItem;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.blockentity.FusionPlateBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCatalyzerCoreBlockEntity;
import dev.sterner.malum.common.blockentity.crucible.SpiritCrucibleCoreBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.BrilliantObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.obelisk.RunewoodObeliskBlockEntity;
import dev.sterner.malum.common.blockentity.storage.PlinthCoreBlockEntity;
import dev.sterner.malum.common.item.*;
import dev.sterner.malum.common.item.cosmetic.AncientWeaveItem;
import dev.sterner.malum.common.item.equipment.armor.SoulHunterArmorItem;
import dev.sterner.malum.common.item.equipment.armor.SoulStainedSteelArmorItem;
import dev.sterner.malum.common.item.equipment.trinket.*;
import dev.sterner.malum.common.item.ether.EtherBrazierItem;
import dev.sterner.malum.common.item.ether.EtherItem;
import dev.sterner.malum.common.item.ether.EtherSconceItem;
import dev.sterner.malum.common.item.ether.EtherTorchItem;
import dev.sterner.malum.common.item.food.HolyCaramelItem;
import dev.sterner.malum.common.item.food.HolySyrupItem;
import dev.sterner.malum.common.item.food.UnholySyrupItem;
import dev.sterner.malum.common.item.impedus.CrackedImpetusItem;
import dev.sterner.malum.common.item.impedus.ImpetusItem;
import dev.sterner.malum.common.item.nitrate.EthericNitrateItem;
import dev.sterner.malum.common.item.nitrate.VividNitrateItem;
import dev.sterner.malum.common.item.spirit.*;
import dev.sterner.malum.common.item.tools.MalumScytheItem;
import dev.sterner.malum.common.item.tools.magic.MagicScytheItem;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static dev.sterner.malum.common.item.ItemTiers.ItemTierEnum.SOUL_STAINED_STEEL;
import static net.minecraft.item.Items.GLASS_BOTTLE;

public interface MalumItemRegistry {
	Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
	Set<MalumScytheItem> SCYTHES = new ReferenceOpenHashSet<>();






	static QuiltItemSettings settings(){
		return new QuiltItemSettings();
	}

	static <T extends Item> T register(String id, T item) {
		ITEMS.put(new Identifier(Malum.MODID, id), item);
		return item;
	}

	static <T extends MalumScytheItem> T registerScytheItem(String id, T item) {
		SCYTHES.add(item);
		return register(id, item);
	}

	static void init() {
		ITEMS.forEach((id, item) -> Registry.register(Registries.ITEM, id, item));
	}
}
