package dev.sterner.malum.compat.emi;

import com.google.common.collect.Maps;
import com.sammy.lodestone.systems.recipe.IngredientWithCount;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.sterner.malum.Malum;
import dev.sterner.malum.common.recipe.SpiritTransmutationRecipe;
import dev.sterner.malum.common.recipe.SpiritWithCount;
import dev.sterner.malum.common.registry.MalumObjects;
import dev.sterner.malum.common.registry.MalumRecipeTypeRegistry;
import dev.sterner.malum.common.registry.MalumRiteRegistry;
import dev.sterner.malum.compat.emi.recipe.SpiritFocusingEmiRecipe;
import dev.sterner.malum.compat.emi.recipe.SpiritInfusionEmiRecipe;
import dev.sterner.malum.compat.emi.recipe.SpiritRepairEmiRecipe;
import dev.sterner.malum.compat.emi.recipe.SpiritRiteEmiRecipe;
import dev.sterner.malum.compat.emi.recipe.SpiritTransmutationEmiRecipe;
import dev.sterner.malum.compat.emi.wrapper.SpiritTransmuationRecipeWrapper;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class EMIPlugin implements EmiPlugin
{
	private static final EmiStack SPIRIT_INFUSION_WORKSTATION = EmiStack.of(MalumObjects.SPIRIT_ALTAR.asItem());
	public static final EmiRecipeCategory SPIRIT_INFUSION = new EmiRecipeCategory(
			Malum.id("spirit_infusion"),
			SPIRIT_INFUSION_WORKSTATION
	);

	private static final EmiStack SPIRIT_FOCUSING_WORKSTATION = EmiStack.of(MalumObjects.SPIRIT_CRUCIBLE.asItem());
	public static final EmiRecipeCategory SPIRIT_FOCUSING = new EmiRecipeCategory(
			Malum.id("spirit_focusing"),
			SPIRIT_FOCUSING_WORKSTATION
	);

	private static final EmiStack SPIRIT_TRANSMUTATION_WORKSTATION = EmiStack.of(MalumObjects.SOULWOOD_TOTEM_BASE.asItem());
	public static final EmiRecipeCategory SPIRIT_TRANSMUTATION = new EmiRecipeCategory(
			Malum.id("spirit_transmutation"),
			SPIRIT_TRANSMUTATION_WORKSTATION
	);

	private static final EmiStack SPIRIT_RITE_WORKSTATION = EmiStack.of(MalumObjects.RUNEWOOD_TOTEM_BASE.asItem());
	public static final EmiRecipeCategory SPIRIT_RITE = new EmiRecipeCategory(
			Malum.id("spirit_rite"),
			SPIRIT_RITE_WORKSTATION
	);

	private static final EmiStack SPIRIT_REPAIR_WORKSTATION = EmiStack.of(MalumObjects.TWISTED_TABLET.asItem());
	public static final EmiRecipeCategory SPIRIT_REPAIR = new EmiRecipeCategory(
			Malum.id("spirit_repair"),
			SPIRIT_REPAIR_WORKSTATION
	);

	private <C extends Inventory, R extends Recipe<C>, E extends EmiRecipe> void registerRecipeTypeCategory(EmiRegistry registry, EmiRecipeCategory category, EmiStack workstation)
	{
		registry.addCategory(category);
		registry.addWorkstation(category, workstation);
	}

	private <C extends Inventory, R extends Recipe<C>, E extends EmiRecipe> void registerRecipeType(EmiRegistry registry, EmiRecipeCategory category, EmiStack workstation, RecipeType<R> type, Function<R, E> builder)
	{
		registerRecipeTypeCategory(registry, category, workstation);
		registry.getRecipeManager().listAllOfType(type).forEach((recipe) -> registry.addRecipe(builder.apply(recipe)));
	}

	@Override
	public void register(EmiRegistry registry)
	{
		this.registerRecipeType(registry, SPIRIT_INFUSION, SPIRIT_INFUSION_WORKSTATION, MalumRecipeTypeRegistry.SPIRIT_INFUSION, SpiritInfusionEmiRecipe::new);
		this.registerRecipeType(registry, SPIRIT_FOCUSING, SPIRIT_FOCUSING_WORKSTATION, MalumRecipeTypeRegistry.SPIRIT_FOCUSING, SpiritFocusingEmiRecipe::new);

		this.registerRecipeTypeCategory(registry, SPIRIT_TRANSMUTATION, SPIRIT_TRANSMUTATION_WORKSTATION);
		List<SpiritTransmutationRecipe> transmutation = registry.getRecipeManager().listAllOfType(MalumRecipeTypeRegistry.SPIRIT_TRANSMUTATION);
		List<SpiritTransmutationRecipe> leftovers = Lists.newArrayList();
		Map<String, List<SpiritTransmutationRecipe>> groups = Maps.newLinkedHashMap();
		transmutation.forEach((recipe) ->
		{
			if(recipe.group != null)
			{
				List<SpiritTransmutationRecipe> group = groups.computeIfAbsent(recipe.group, (k) -> Lists.newArrayList());
				group.add(recipe);
			}
			else
			{
				leftovers.add(recipe);
			}
		});
		groups.values().stream()
				.map(SpiritTransmuationRecipeWrapper::new)
				.forEach((recipe) -> registry.addRecipe(new SpiritTransmutationEmiRecipe(recipe)));
		leftovers.stream()
				.map(List::of)
				.map(SpiritTransmuationRecipeWrapper::new)
				.forEach((recipe) -> registry.addRecipe(new SpiritTransmutationEmiRecipe(recipe)));

		this.registerRecipeTypeCategory(registry, SPIRIT_RITE, SPIRIT_RITE_WORKSTATION);
		MalumRiteRegistry.RITES.forEach((rite) -> registry.addRecipe(new SpiritRiteEmiRecipe(rite)));

		this.registerRecipeType(registry, SPIRIT_REPAIR, SPIRIT_REPAIR_WORKSTATION, MalumRecipeTypeRegistry.SPIRIT_REPAIR, SpiritRepairEmiRecipe::new);
	}

	public static void addItems(WidgetHolder widgets, int left, int top, boolean vertical, List<EmiIngredient> ingredients)
	{
		int slots = ingredients.size();
		if(vertical)
		{
			top -= 10 * (slots - 1);
		}
		else
		{
			left -= 10 * (slots - 1);
		}
		for(int i = 0; i < slots; i++)
		{
			int offset = i * 20;
			int offsetLeft = left + 1 + (vertical ? 0 : offset);
			int offsetTop = top + 1 + (vertical ? offset : 0);
			widgets.addSlot(ingredients.get(i), offsetLeft, offsetTop).drawBack(false);
		}
	}

	public static EmiIngredient convertIngredientWithCount(IngredientWithCount ingredient)
	{
		return EmiIngredient.of(ingredient.ingredient, ingredient.count);
	}

	public static List<EmiIngredient> convertIngredientWithCounts(List<IngredientWithCount> ingredients)
	{
		return ingredients.stream().map(EMIPlugin::convertIngredientWithCount).toList();
	}

	public static EmiIngredient convertSpiritWithCount(SpiritWithCount spirit)
	{
		return EmiIngredient.of(Ingredient.ofItems(spirit.type.getSplinterItem()), spirit.count);
	}

	public static List<EmiIngredient> convertSpiritWithCounts(List<SpiritWithCount> spirits)
	{
		return spirits.stream().map(EMIPlugin::convertSpiritWithCount).toList();
	}
}
