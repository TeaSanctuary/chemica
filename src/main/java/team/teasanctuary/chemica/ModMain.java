package team.teasanctuary.chemica;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.gui.*;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.items.TesterItem;
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

		Registry.register(Registry.RECIPE_TYPE, StoneAlloySmelterRecipe.ID, STONE_ALLOY_SMELTER_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, StoneAlloySmelterRecipe.ID, StoneAlloySmelterRecipe.SERIALIZER);

		ContainerProviderRegistry.INSTANCE.registerFactory(EnergyBoxBlock.ID, (syncId, id, player, buf) -> new EnergyBoxController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(CrusherBlock.ID, (syncId, id, player, buf) -> new CrusherBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(TesterItem.ID, (syncId, id, player, buf) -> new TesterItemController(syncId, player.inventory, BlockContext.create(player.world, player.getBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(SolidFuelGeneratorBlock.ID, (syncId, id, player, buf) -> new SolidFuelGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(StoneAlloySmelterBlock.ID, (syncId, id, player, buf) -> new StoneAlloySmelterController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	}
}
