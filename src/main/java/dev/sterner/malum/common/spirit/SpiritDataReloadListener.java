package dev.sterner.malum.common.spirit;


import com.google.gson.*;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.recipe.SpiritWithCount;
import dev.sterner.malum.common.registry.MalumSpiritTypeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import java.util.*;


public class SpiritDataReloadListener extends JsonDataLoader {
	public static final Map<Identifier, MalumEntitySpiritData> SPIRIT_DATA = new HashMap<>();
	public static final Set<Identifier> HAS_NO_DATA = new HashSet<>();

	public static final MalumEntitySpiritData DEFAULT_MONSTER_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.WICKED_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_CREATURE_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.SACRED_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_AMBIENT_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.AERIAL_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_WATER_CREATURE_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.AQUEOUS_SPIRIT)
			.withSpirit(MalumSpiritTypeRegistry.SACRED_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_WATER_AMBIENT_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.AQUEOUS_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_UNDERGROUND_WATER_CREATURE_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.AQUEOUS_SPIRIT)
			.withSpirit(MalumSpiritTypeRegistry.EARTHEN_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_AXOLOTL_SPIRIT_DATA = MalumEntitySpiritData // They're their own category
			.builder(MalumSpiritTypeRegistry.AQUEOUS_SPIRIT, 2)
			.withSpirit(MalumSpiritTypeRegistry.SACRED_SPIRIT)
			.build();
	public static final MalumEntitySpiritData DEFAULT_BOSS_SPIRIT_DATA = MalumEntitySpiritData
			.builder(MalumSpiritTypeRegistry.ELDRITCH_SPIRIT, 2)
			.build();

	private static final Gson GSON = (new GsonBuilder()).create();

	public SpiritDataReloadListener() {
		super(GSON, "spirit_data/entity");
	}

	@Override
	protected void apply(Map<Identifier, JsonElement> objectIn, ResourceManager resourceManagerIn, Profiler profilerIn) {
		SPIRIT_DATA.clear();
		HAS_NO_DATA.clear();
		for (JsonElement entry : objectIn.values()) {
			JsonObject object = entry.getAsJsonObject();
			String name = object.getAsJsonPrimitive("registry_name").getAsString();
			Identifier resourceLocation = new Identifier(name);
			if (!Registry.ENTITY_TYPE.containsId(resourceLocation)) {
				continue;
			}
			if (!object.has("primary_type")) {
				Malum.LOGGER.info("Entity with registry name: " + name + " lacks a primary spirit type. Skipping file.");
				continue;
			}
			String primaryType = object.getAsJsonPrimitive("primary_type").getAsString();
			boolean isEmpty = primaryType.equals("none");
			if (SPIRIT_DATA.containsKey(resourceLocation)) {
				if (isEmpty)
					Malum.LOGGER.info("Entity with registry name: " + name + " already has spirit data associated with it. Removing.");
				else
					Malum.LOGGER.info("Entity with registry name: " + name + " already has spirit data associated with it. Overwriting.");
			} else if (HAS_NO_DATA.contains(resourceLocation) && !isEmpty) {
				Malum.LOGGER.info("Entity with registry name: " + name + " already has empty spirit data associated with it. Overwriting.");
			}
			if (primaryType.equals("none")) {
				SPIRIT_DATA.remove(resourceLocation);
				HAS_NO_DATA.add(resourceLocation);
			} else {
				JsonArray array = object.getAsJsonArray("spirits");
				SPIRIT_DATA.put(resourceLocation, new MalumEntitySpiritData(SpiritHelper.getSpiritType(primaryType), getSpiritData(array), getSpiritItem(object)));
				HAS_NO_DATA.remove(resourceLocation);
			}
		}
	}

	private static List<SpiritWithCount> getSpiritData(JsonArray array) {
		List<SpiritWithCount> spiritData = new ArrayList<>();
		for (JsonElement spiritElement : array) {
			JsonObject spiritObject = spiritElement.getAsJsonObject();
			String spiritName = spiritObject.getAsJsonPrimitive("spirit").getAsString();
			int count = spiritObject.getAsJsonPrimitive("count").getAsInt();
			spiritData.add(new SpiritWithCount(SpiritHelper.getSpiritType(spiritName), count));
		}
		return spiritData;
	}

	private static Ingredient getSpiritItem(JsonObject object) {
		if (!object.has("spirit_item")) {
			return null;
		}

		try {
			return Ingredient.fromJson(object.get("spirit_item"));
		} catch (JsonParseException ignored) {
			return null;
		}
	}
}
