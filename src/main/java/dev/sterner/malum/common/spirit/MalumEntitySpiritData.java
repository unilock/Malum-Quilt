package dev.sterner.malum.common.spirit;

import com.google.gson.JsonSyntaxException;
import dev.sterner.malum.common.recipe.SpiritWithCount;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MalumEntitySpiritData {
	public static final String SOUL_DATA = "soul_data";
	public static final MalumEntitySpiritData EMPTY = new MalumEntitySpiritData(MalumSpiritTypeRegistry.SACRED_SPIRIT, new ArrayList<>(), null);
	public final MalumSpiritType primaryType;
	public final int totalSpirits;
	public final List<SpiritWithCount> dataEntries;
	@Nullable
	public final Ingredient spiritItem;

	public MalumEntitySpiritData(MalumSpiritType primaryType, List<SpiritWithCount> dataEntries, @Nullable Ingredient spiritItem) {
		this.primaryType = primaryType;
		this.totalSpirits = dataEntries.stream().mapToInt(d -> d.count).sum();
		this.dataEntries = dataEntries;
		this.spiritItem = spiritItem;
	}

	public List<Text> createTooltip() {
		return dataEntries.stream().map(SpiritWithCount::getComponent).collect(Collectors.toList());
	}

	public void saveTo(NbtCompound tag) {
		tag.put(SOUL_DATA, writeNbt());
	}

	public NbtCompound writeNbt() {
		NbtCompound tag = new NbtCompound();
		tag.putString("primaryType", primaryType.identifier);
		tag.putInt("dataAmount", dataEntries.size());
		for (int i = 0; i < dataEntries.size(); i++) {
			NbtCompound dataTag = dataEntries.get(i).writeNbt(new NbtCompound());
			tag.put("dataEntry" + i, dataTag);
		}
		if (spiritItem != null)
			tag.putString("spiritItem", spiritItem.toJson().toString());
		return tag;
	}

	public static MalumEntitySpiritData load(NbtCompound tag) {
		NbtCompound nbt = tag.getCompound(SOUL_DATA);


		String type = nbt.getString("primaryType");
		int dataAmount = nbt.getInt("dataAmount");
		if (dataAmount == 0) {
			return EMPTY;
		}
		List<SpiritWithCount> data = new ArrayList<>();
		for (int i = 0; i < dataAmount; i++) {
			data.add(SpiritWithCount.readNbt(nbt.getCompound("dataEntry" + i)));
		}
		Ingredient spiritItem = null;
		try {
			if (tag.contains("spiritItem", NbtElement.STRING_TYPE)) {
				spiritItem = Ingredient.fromJson(JsonHelper.deserialize(tag.getString("spiritItem")));
			}
		} catch (JsonSyntaxException ignored) {
			// NO-OP
		}
		return new MalumEntitySpiritData(SpiritHelper.getSpiritType(type), data, spiritItem);
	}


	public static Builder builder(MalumSpiritType type) {
		return builder(type, 1);
	}

	public static Builder builder(MalumSpiritType type, int count) {
		return new Builder(type).withSpirit(type, count);
	}

	public static class Builder {
		private final MalumSpiritType type;
		private final List<SpiritWithCount> spirits = new ArrayList<>();
		private Ingredient spiritItem = null;

		public Builder(MalumSpiritType type) {
			this.type = type;
		}

		public Builder withSpirit(MalumSpiritType spiritType) {
			return withSpirit(spiritType, 1);
		}

		public Builder withSpirit(MalumSpiritType spiritType, int count) {
			spirits.add(new SpiritWithCount(spiritType, count));
			return this;
		}

		public Builder withSpiritItem(Ingredient spiritItem) {
			this.spiritItem = spiritItem;
			return this;
		}

		public MalumEntitySpiritData build() {
			return new MalumEntitySpiritData(type, spirits, spiritItem);
		}
	}
}
