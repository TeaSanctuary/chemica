package team.teasanctuary.chemica;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.math.BlockPos;
import team.teasanctuary.chemica.blocks.EnergyBoxBlock;
import team.teasanctuary.chemica.blocks.CrankBlock;
import team.teasanctuary.chemica.blocks.CrusherBlock;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.entities.EnergyBoxEntity;
import team.teasanctuary.chemica.entities.CrankBlockEntity;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;
import team.teasanctuary.chemica.entities.SolidFuelGeneratorEntity;
import team.teasanctuary.chemica.gui.EnergyBoxController;
import team.teasanctuary.chemica.gui.CrusherBlockController;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.BlockContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.gui.SolidFuelGeneratorController;
import team.teasanctuary.chemica.gui.TesterItemController;
import team.teasanctuary.chemica.items.BatteryItem;
import team.teasanctuary.chemica.items.ScrewdriverItem;
import team.teasanctuary.chemica.items.TesterItem;
import team.teasanctuary.chemica.recipes.CrusherRecipe;
import team.teasanctuary.chemica.recipes.GeneratorRecipe;

public class ModMain implements ModInitializer {
	public static final EnergyBoxBlock ENERGY_BOX_BLOCK = new EnergyBoxBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
	public static final CrusherBlock CRUSHER_BLOCK = new CrusherBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
	public static final CrankBlock CRANK_BLOCK = new CrankBlock(FabricBlockSettings.of(Material.WOOD).hardness(1.f).nonOpaque().build());
	public static final SolidFuelGeneratorBlock SOLID_FUEL_GENERATOR_BLOCK = new SolidFuelGeneratorBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
	public static BlockEntityType<EnergyBoxEntity> ENERGY_BOX_ENTITY;
	public static BlockEntityType<CrusherBlockEntity> CRUSHER_BLOCK_ENTITY;
	public static BlockEntityType<CrankBlockEntity> CRANK_BLOCK_ENTITY;
	public static BlockEntityType<SolidFuelGeneratorEntity> SOLID_FUEL_GENERATOR_ENTITY;

	public static final ScrewdriverItem SCREWDRIVER_ITEM = new ScrewdriverItem(new Item.Settings().maxCount(1));
	public static final BatteryItem BATTERY_ITEM = new BatteryItem(new Item.Settings().maxCount(1));
	public static final TesterItem TESTER_ITEM = new TesterItem(new Item.Settings().maxCount(1));

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

	public static final ItemGroup CHEMICA_GENERAL = FabricItemGroupBuilder.create(
			new Identifier("chemica", "general"))
			.icon(() -> new ItemStack(ENERGY_BOX_BLOCK))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(ENERGY_BOX_BLOCK));
				stacks.add(new ItemStack(CRUSHER_BLOCK));
				stacks.add(new ItemStack(SCREWDRIVER_ITEM));
				stacks.add(new ItemStack(BATTERY_ITEM));
				stacks.add(new ItemStack(TESTER_ITEM));
				stacks.add(new ItemStack(CRANK_BLOCK));
				stacks.add(new ItemStack(SOLID_FUEL_GENERATOR_BLOCK));
			})
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, ScrewdriverItem.ID, SCREWDRIVER_ITEM);
		Registry.register(Registry.ITEM, BatteryItem.ID, BATTERY_ITEM);
		Registry.register(Registry.ITEM, TesterItem.ID, TESTER_ITEM);

		Registry.register(Registry.BLOCK, SolidFuelGeneratorBlock.ID, SOLID_FUEL_GENERATOR_BLOCK);
		Registry.register(Registry.ITEM, SolidFuelGeneratorBlock.ID, new BlockItem(SOLID_FUEL_GENERATOR_BLOCK, new Item.Settings().maxCount(1)));
		SOLID_FUEL_GENERATOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SolidFuelGeneratorBlock.ID, BlockEntityType.Builder.create(SolidFuelGeneratorEntity::new, SOLID_FUEL_GENERATOR_BLOCK).build(null));

		Registry.register(Registry.BLOCK, CrusherBlock.ID, CRUSHER_BLOCK);
		Registry.register(Registry.ITEM, CrusherBlock.ID, new BlockItem(CRUSHER_BLOCK, new Item.Settings().maxCount(1).group(ItemGroup.MISC)));
		CRUSHER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, CrusherBlock.ID, BlockEntityType.Builder.create(CrusherBlockEntity::new, CRUSHER_BLOCK).build(null));

		Registry.register(Registry.BLOCK, CrankBlock.ID, CRANK_BLOCK);
		Registry.register(Registry.ITEM, CrankBlock.ID, new BlockItem(CRANK_BLOCK, new Item.Settings().group(CHEMICA_GENERAL)));
		CRANK_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, CrankBlock.ID, BlockEntityType.Builder.create(CrankBlockEntity::new, CRANK_BLOCK).build(null));

		Registry.register(Registry.BLOCK, EnergyBoxBlock.ID, ENERGY_BOX_BLOCK);
		Registry.register(Registry.ITEM, EnergyBoxBlock.ID, new BlockItem(ENERGY_BOX_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
		ENERGY_BOX_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EnergyBoxBlock.ID, BlockEntityType.Builder.create(EnergyBoxEntity::new, ENERGY_BOX_BLOCK).build(null));

		Registry.register(Registry.RECIPE_TYPE, CrusherRecipe.ID, CRUSHER_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, CrusherRecipe.ID, CrusherRecipe.SERIALIZER);

		Registry.register(Registry.RECIPE_TYPE, GeneratorRecipe.ID, GENERATOR_RECIPE);
		Registry.register(Registry.RECIPE_SERIALIZER, GeneratorRecipe.ID, GeneratorRecipe.SERIALIZER);

		ContainerProviderRegistry.INSTANCE.registerFactory(EnergyBoxBlock.ID, (syncId, id, player, buf) -> new EnergyBoxController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(CrusherBlock.ID, (syncId, id, player, buf) -> new CrusherBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(TesterItem.ID, (syncId, id, player, buf) -> new TesterItemController(syncId, player.inventory, BlockContext.create(player.world, player.getBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(SolidFuelGeneratorBlock.ID, (syncId, id, player, buf) -> new SolidFuelGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

		ServerSidePacketRegistry.INSTANCE.register(ClientMain.CRUSHER_CRUSH_BUTTON_PACKET_ID, (ctx, data) -> {
			BlockPos pos = data.readBlockPos();
			ctx.getTaskQueue().execute(() -> {
				if (ctx.getPlayer().world.canSetBlock(pos)) {
					BlockEntity be = ctx.getPlayer().world.getBlockEntity(pos);
					if (be instanceof CrusherBlockEntity) {
						//((CrusherBlockEntity) be).crush();
					}
				}
			});
		});
	}
}
