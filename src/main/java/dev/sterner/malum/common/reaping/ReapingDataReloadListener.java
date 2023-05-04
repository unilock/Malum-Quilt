package dev.sterner.malum.common.reaping;

import com.google.gson.*;
import dev.sterner.malum.Malum;
import net.minecraft.recipe.Ingredient;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReapingDataReloadListener extends JsonDataLoader {
	public static Map<Identifier, List<MalumReapingDropsData>> REAPING_DATA = new HashMap<>();
	private static final Gson GSON = (new GsonBuilder()).create();
	public ReapingDataReloadListener() {
		super(GSON, "reaping_data");
	}

	@Override
	protected void apply(Map<Identifier, JsonElement> objectIn, ResourceManager manager, Profiler profiler) {
		REAPING_DATA.clear();
		for (int i = 0; i < objectIn.size(); i++) {
			Identifier location = (Identifier) objectIn.keySet().toArray()[i];
			JsonObject object = objectIn.get(location).getAsJsonObject();
			String name = object.getAsJsonPrimitive("registry_name").getAsString();
			Identifier resourceLocation = new Identifier(name);
			if (!Registry.ENTITY_TYPE.containsId(resourceLocation)) {
				continue;
			}
			if (REAPING_DATA.containsKey(resourceLocation)) {
				Malum.LOGGER.info("entity with registry name: " + name + " already has reaping data associated with it. Overwriting.");
			}
			JsonArray drops = object.getAsJsonArray("drops");
			List<MalumReapingDropsData> dropsList = new ArrayList<>();
			for (JsonElement drop : drops) {
				JsonObject dropObject = drop.getAsJsonObject();
				if (!dropObject.has("ingredient")) {
					Malum.LOGGER.info("entity with registry name: " + name + " lacks a reaping ingredient. Skipping drops entry.");
					continue;
				}
				Ingredient dropIngredient = Ingredient.fromJson(dropObject.getAsJsonObject("ingredient"));
				float chance = dropObject.getAsJsonPrimitive("chance").getAsFloat();
				int min = dropObject.getAsJsonPrimitive("min").getAsInt();
				int max = dropObject.getAsJsonPrimitive("max").getAsInt();
				dropsList.add(new MalumReapingDropsData(dropIngredient, chance, min, max));
			}
			REAPING_DATA.put(resourceLocation, dropsList);
		}
	}
}
