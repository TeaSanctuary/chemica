package team.teasanctuary.chemica;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.gui.EnergyBoxController;
import team.teasanctuary.chemica.gui.CrusherBlockController;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.gui.SolidFuelGeneratorController;
import team.teasanctuary.chemica.gui.TesterItemController;
import team.teasanctuary.chemica.gui.BeehiveOvenController;
import team.teasanctuary.chemica.items.TesterItem;
import team.teasanctuary.chemica.recipes.BeehiveOvenRecipe;
import team.teasanctuary.chemica.recipes.CrusherRecipe;
import team.teasanctuary.chemica.recipes.GeneratorRecipe;
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

		Registry.register(Registry.RECIPE_TYPE, BeehiveOvenRecipe.ID, BEEHIVE_OVEN_RECIPE_TYPE);
		Registry.register(Registry.RECIPE_SERIALIZER, BeehiveOvenRecipe.ID, BeehiveOvenRecipe.SERIALIZER);

		ContainerProviderRegistry.INSTANCE.registerFactory(EnergyBoxBlock.ID, (syncId, id, player, buf) -> new EnergyBoxController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(CrusherBlock.ID, (syncId, id, player, buf) -> new CrusherBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(TesterItem.ID, (syncId, id, player, buf) -> new TesterItemController(syncId, player.inventory, BlockContext.create(player.world, player.getBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(SolidFuelGeneratorBlock.ID, (syncId, id, player, buf) -> new SolidFuelGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(BeehiveOvenControlBlock.ID, (syncId, id, player, buf) -> new BeehiveOvenController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	}
}
