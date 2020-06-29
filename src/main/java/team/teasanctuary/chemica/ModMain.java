package team.teasanctuary.chemica;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.gui.*;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.items.TesterItem;
import team.teasanctuary.chemica.recipes.BeehiveOvenRecipe;
import team.teasanctuary.chemica.recipes.CrusherRecipe;
import team.teasanctuary.chemica.recipes.GeneratorRecipe;
import team.teasanctuary.chemica.recipes.StoneAlloySmelterRecipe;
import team.teasanctuary.chemica.registry.*;

public class ModMain implements ModInitializer {

	public static final RecipeType<CrusherRecipe> CRUSHER_RECIPE = new RecipeType<CrusherRecipe>() {
		@Override
		public String toString () {
			return CrusherRecipe.ID.toString();
		}
	};

	public static final RecipeType<GeneratorRecipe> GENERATOR_RECIPE = new RecipeType<GeneratorRecipe>() {
		@Override
		public String toString () {
			return GeneratorRecipe.ID.toString();
		}
	};

	public static final RecipeType<BeehiveOvenRecipe> BEEHIVE_OVEN_RECIPE_TYPE = new RecipeType<BeehiveOvenRecipe>() {
		@Override
		public String toString () {
			return BeehiveOvenRecipe.ID.toString();
		}
	};

	public static final RecipeType<StoneAlloySmelterRecipe> STONE_ALLOY_SMELTER_RECIPE = new RecipeType<StoneAlloySmelterRecipe>() {
		@Override
		public String toString () {
			return StoneAlloySmelterRecipe.ID.toString();
		}
	};

	public static final ItemGroup CHEMICA_GENERAL = FabricItemGroupBuilder.create(
			new Identifier("chemica", "general"))
			.icon(() -> new ItemStack(Blocks.ENERGY_BOX_BLOCK))
			.build();

	public static ScreenHandlerType BEEHIVE_OVEN_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType CRUSHER_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType ENERGY_BOX_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType SOLID_FUEL_GENERATOR_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType STONE_ALLOY_SMELTER_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType TESTER_ITEM_SCREEN_HANDLER_TYPE;

	@Override
	public void onInitialize() {
		Ores.init();
		Blocks.init();
		Items.init();
		Materials.init();
		Generation.init();

		Registry.register(Registry.RECIPE_TYPE, CrusherRecipe.ID, CRUSHER_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, CrusherRecipe.ID, CrusherRecipe.SERIALIZER);

		Registry.register(Registry.RECIPE_TYPE, GeneratorRecipe.ID, GENERATOR_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, GeneratorRecipe.ID, GeneratorRecipe.SERIALIZER);

		Registry.register(Registry.RECIPE_TYPE, BeehiveOvenRecipe.ID, BEEHIVE_OVEN_RECIPE_TYPE);
		Registry.register(Registry.RECIPE_SERIALIZER, BeehiveOvenRecipe.ID, BeehiveOvenRecipe.SERIALIZER);

		Registry.register(Registry.RECIPE_TYPE, StoneAlloySmelterRecipe.ID, STONE_ALLOY_SMELTER_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, StoneAlloySmelterRecipe.ID, StoneAlloySmelterRecipe.SERIALIZER);

		BEEHIVE_OVEN_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(BeehiveOvenControlBlock.ID, (syncId, inventory) -> new BeehiveOvenController(syncId, inventory, ScreenHandlerContext.EMPTY));
		CRUSHER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(CrusherBlock.ID, (syncId, inventory) -> new CrusherBlockController(syncId, inventory, ScreenHandlerContext.EMPTY));
		ENERGY_BOX_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(EnergyBoxBlock.ID, (syncId, inventory) -> new EnergyBoxController(syncId, inventory, ScreenHandlerContext.EMPTY));
		SOLID_FUEL_GENERATOR_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(SolidFuelGeneratorBlock.ID, (syncId, inventory) -> new SolidFuelGeneratorController(syncId, inventory, ScreenHandlerContext.EMPTY));
		STONE_ALLOY_SMELTER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(StoneAlloySmelterBlock.ID, (syncId, inventory) -> new StoneAlloySmelterController(syncId, inventory, ScreenHandlerContext.EMPTY));
		TESTER_ITEM_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(TesterItem.ID, (syncId, inventory) -> new TesterItemController(syncId, inventory, ScreenHandlerContext.EMPTY));
	}
}
