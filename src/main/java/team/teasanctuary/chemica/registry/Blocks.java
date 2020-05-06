package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.CustomStairsBlock;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.blocks.ConduitBlock;
import team.teasanctuary.chemica.entities.*;

public class Blocks {
    public static EnergyBoxBlock ENERGY_BOX_BLOCK;
    public static CrusherBlock CRUSHER_BLOCK;
    public static CrankBlock CRANK_BLOCK;
    public static SolidFuelGeneratorBlock SOLID_FUEL_GENERATOR_BLOCK;
    public static ConduitBlock CONDUIT_BLOCK;
    public static BeehiveOvenControlBlock BEEHIVE_OVEN_CONTROL_BLOCK;
    public static StoneAlloySmelterBlock STONE_ALLOY_SMELTER_BLOCK;

    public static Block FIREPROOF_BRICKS_BLOCK;
    public static SlabBlock FIREPROOF_BRICK_SLAB;
    public static StairsBlock FIREPROOF_BRICK_STAIRS;
    public static WallBlock FIREPROOF_BRICK_WALL;

    public static Block BAUXITE_BLOCK;

    public static Block SANDSTONE_BRICKS_BLOCK;

    public static BlockEntityType<EnergyBoxEntity> ENERGY_BOX_ENTITY;
    public static BlockEntityType<CrusherBlockEntity> CRUSHER_BLOCK_ENTITY;
    public static BlockEntityType<CrankBlockEntity> CRANK_BLOCK_ENTITY;
    public static BlockEntityType<SolidFuelGeneratorEntity> SOLID_FUEL_GENERATOR_ENTITY;
    public static BlockEntityType<ConduitBlockEntity> CONDUIT_BLOCK_ENTITY;
    public static BlockEntityType<BeehiveOvenControlBlockEntity> BEEHIVE_OVEN_CONTROL_BLOCK_ENTITY;
    public static BlockEntityType<StoneAlloySmelterEntity> STONE_ALLOY_SMELTER_ENTITY;

    public static final Identifier BAUXITE_BLOCK_ID = new Identifier("chemica", "bauxite");

    public static final Identifier FIREPROOF_BRICKS_BLOCK_ID = new Identifier("chemica", "fireproof_bricks");
    public static final Identifier FIREPROOF_BRICK_SLAB_ID = new Identifier("chemica", "fireproof_brick_slab");
    public static final Identifier FIREPROOF_BRICK_STAIRS_ID = new Identifier("chemica", "fireproof_brick_stairs");
    public static final Identifier FIREPROOF_BRICK_WALL_ID = new Identifier("chemica", "fireproof_brick_wall");

    public static final Identifier SANDSTONE_BRICKS_BLOCK_ID = new Identifier("chemica", "sandstone_bricks");

    public static final ItemGroup CHEMICA_BUILDING_BLOCKS = FabricItemGroupBuilder.create(
            new Identifier("chemica", "building_blocks"))
            .icon(() -> new ItemStack(SANDSTONE_BRICKS_BLOCK))
            .build();

    public static void init() {
        ENERGY_BOX_BLOCK = new EnergyBoxBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CRUSHER_BLOCK = new CrusherBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        CRANK_BLOCK = new CrankBlock(FabricBlockSettings.of(Material.WOOD).hardness(1.f).nonOpaque().build());
        SOLID_FUEL_GENERATOR_BLOCK = new SolidFuelGeneratorBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CONDUIT_BLOCK = new ConduitBlock(FabricBlockSettings.of(Material.METAL).hardness(1.f).nonOpaque().build());
        BEEHIVE_OVEN_CONTROL_BLOCK = new BeehiveOvenControlBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).lightLevel(13).build());
        STONE_ALLOY_SMELTER_BLOCK = new StoneAlloySmelterBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());

        FIREPROOF_BRICKS_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        FIREPROOF_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copy(FIREPROOF_BRICKS_BLOCK).build());
        FIREPROOF_BRICK_STAIRS = new CustomStairsBlock(FIREPROOF_BRICKS_BLOCK.getDefaultState(), FabricBlockSettings.copy(FIREPROOF_BRICKS_BLOCK).build());
        FIREPROOF_BRICK_WALL = new WallBlock(FabricBlockSettings.copy(FIREPROOF_BRICKS_BLOCK).build());

        BAUXITE_BLOCK = new Block(FabricBlockSettings.of(Material.SAND).hardness(1.f).build());

        SANDSTONE_BRICKS_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.f).build());

        register();
    }

    private static void register() {
        Registry.register(Registry.BLOCK, BAUXITE_BLOCK_ID, BAUXITE_BLOCK);
        Registry.register(Registry.ITEM, BAUXITE_BLOCK_ID, new BlockItem(BAUXITE_BLOCK, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, FIREPROOF_BRICKS_BLOCK_ID, FIREPROOF_BRICKS_BLOCK);
        Registry.register(Registry.ITEM, FIREPROOF_BRICKS_BLOCK_ID, new BlockItem(FIREPROOF_BRICKS_BLOCK, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, FIREPROOF_BRICK_SLAB_ID, FIREPROOF_BRICK_SLAB);
        Registry.register(Registry.ITEM, FIREPROOF_BRICK_SLAB_ID, new BlockItem(FIREPROOF_BRICK_SLAB, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, FIREPROOF_BRICK_STAIRS_ID, FIREPROOF_BRICK_STAIRS);
        Registry.register(Registry.ITEM, FIREPROOF_BRICK_STAIRS_ID, new BlockItem(FIREPROOF_BRICK_STAIRS, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, FIREPROOF_BRICK_WALL_ID, FIREPROOF_BRICK_WALL);
        Registry.register(Registry.ITEM, FIREPROOF_BRICK_WALL_ID, new BlockItem(FIREPROOF_BRICK_WALL, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, SANDSTONE_BRICKS_BLOCK_ID, SANDSTONE_BRICKS_BLOCK);
        Registry.register(Registry.ITEM, SANDSTONE_BRICKS_BLOCK_ID, new BlockItem(SANDSTONE_BRICKS_BLOCK, new Item.Settings().maxCount(64).group(CHEMICA_BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, StoneAlloySmelterBlock.ID, STONE_ALLOY_SMELTER_BLOCK);
        Registry.register(Registry.ITEM, StoneAlloySmelterBlock.ID, new BlockItem(STONE_ALLOY_SMELTER_BLOCK, new Item.Settings().maxCount(1).group(ModMain.CHEMICA_GENERAL)));
        STONE_ALLOY_SMELTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, StoneAlloySmelterBlock.ID,
                BlockEntityType.Builder.create(StoneAlloySmelterEntity::new, STONE_ALLOY_SMELTER_BLOCK).build(null));

        Registry.register(Registry.BLOCK, SolidFuelGeneratorBlock.ID, SOLID_FUEL_GENERATOR_BLOCK);
        Registry.register(Registry.ITEM, SolidFuelGeneratorBlock.ID, new BlockItem(SOLID_FUEL_GENERATOR_BLOCK, new Item.Settings().maxCount(1).group(ModMain.CHEMICA_GENERAL)));
        SOLID_FUEL_GENERATOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SolidFuelGeneratorBlock.ID,
                BlockEntityType.Builder.create(SolidFuelGeneratorEntity::new, SOLID_FUEL_GENERATOR_BLOCK).build(null));

        Registry.register(Registry.BLOCK, ConduitBlock.ID, CONDUIT_BLOCK);
        Registry.register(Registry.ITEM, ConduitBlock.ID, new BlockItem(CONDUIT_BLOCK, new Item.Settings().maxCount(64).group(ModMain.CHEMICA_GENERAL)));
        CONDUIT_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ConduitBlock.ID,
                BlockEntityType.Builder.create(ConduitBlockEntity::new, CONDUIT_BLOCK).build(null));

        Registry.register(Registry.BLOCK, CrusherBlock.ID, CRUSHER_BLOCK);
        Registry.register(Registry.ITEM, CrusherBlock.ID, new BlockItem(CRUSHER_BLOCK, new Item.Settings().maxCount(1).group(ModMain.CHEMICA_GENERAL)));
        CRUSHER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, CrusherBlock.ID,
                BlockEntityType.Builder.create(CrusherBlockEntity::new, CRUSHER_BLOCK).build(null));

        Registry.register(Registry.BLOCK, CrankBlock.ID, CRANK_BLOCK);
        Registry.register(Registry.ITEM, CrankBlock.ID, new BlockItem(CRANK_BLOCK, new Item.Settings().group(ModMain.CHEMICA_GENERAL)));
        CRANK_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, CrankBlock.ID,
                BlockEntityType.Builder.create(CrankBlockEntity::new, CRANK_BLOCK).build(null));

        Registry.register(Registry.BLOCK, EnergyBoxBlock.ID, ENERGY_BOX_BLOCK);
        Registry.register(Registry.ITEM, EnergyBoxBlock.ID, new BlockItem(ENERGY_BOX_BLOCK, new Item.Settings().group(ModMain.CHEMICA_GENERAL)));
        ENERGY_BOX_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, EnergyBoxBlock.ID,
                BlockEntityType.Builder.create(EnergyBoxEntity::new, ENERGY_BOX_BLOCK).build(null));

        Registry.register(Registry.BLOCK, BeehiveOvenControlBlock.ID, BEEHIVE_OVEN_CONTROL_BLOCK);
        Registry.register(Registry.ITEM, BeehiveOvenControlBlock.ID, new BlockItem(BEEHIVE_OVEN_CONTROL_BLOCK, new Item.Settings().group(ModMain.CHEMICA_GENERAL)));
        BEEHIVE_OVEN_CONTROL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BeehiveOvenControlBlock.ID,
                BlockEntityType.Builder.create(BeehiveOvenControlBlockEntity::new, BEEHIVE_OVEN_CONTROL_BLOCK).build(null));
    }
}
