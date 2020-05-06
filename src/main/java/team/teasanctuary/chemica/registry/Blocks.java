package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.entities.*;

public class Blocks {
    public static EnergyBoxBlock ENERGY_BOX_BLOCK;
    public static CrusherBlock CRUSHER_BLOCK = new CrusherBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
    public static CrankBlock CRANK_BLOCK = new CrankBlock(FabricBlockSettings.of(Material.WOOD).hardness(1.f).nonOpaque().build());
    public static SolidFuelGeneratorBlock SOLID_FUEL_GENERATOR_BLOCK = new SolidFuelGeneratorBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
    public static ConduitBlock CONDUIT_BLOCK;
    public static BeehiveOvenBricksBlock BEEHIVE_OVEN_BRICKS_BLOCK;
    public static BeehiveOvenControlBlock BEEHIVE_OVEN_CONTROL_BLOCK;

    public static BlockEntityType<EnergyBoxEntity> ENERGY_BOX_ENTITY;
    public static BlockEntityType<CrusherBlockEntity> CRUSHER_BLOCK_ENTITY;
    public static BlockEntityType<CrankBlockEntity> CRANK_BLOCK_ENTITY;
    public static BlockEntityType<SolidFuelGeneratorEntity> SOLID_FUEL_GENERATOR_ENTITY;
    public static BlockEntityType<ConduitBlockEntity> CONDUIT_BLOCK_ENTITY;
    public static BlockEntityType<BeehiveOvenControlBlockEntity> BEEHIVE_OVEN_CONTROL_BLOCK_ENTITY;

    public static void init() {
        ENERGY_BOX_BLOCK = new EnergyBoxBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CRUSHER_BLOCK = new CrusherBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        CRANK_BLOCK = new CrankBlock(FabricBlockSettings.of(Material.WOOD).hardness(1.f).nonOpaque().build());
        SOLID_FUEL_GENERATOR_BLOCK = new SolidFuelGeneratorBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CONDUIT_BLOCK = new ConduitBlock(FabricBlockSettings.of(Material.METAL).hardness(1.f).nonOpaque().build());
        BEEHIVE_OVEN_BRICKS_BLOCK = new BeehiveOvenBricksBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        BEEHIVE_OVEN_CONTROL_BLOCK = new BeehiveOvenControlBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());

        register();
    }

    private static void register() {
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

        Registry.register(Registry.BLOCK, BeehiveOvenBricksBlock.ID, BEEHIVE_OVEN_BRICKS_BLOCK);
        Registry.register(Registry.ITEM, BeehiveOvenBricksBlock.ID, new BlockItem(BEEHIVE_OVEN_BRICKS_BLOCK, new Item.Settings().group(ModMain.CHEMICA_GENERAL)));

        Registry.register(Registry.BLOCK, BeehiveOvenControlBlock.ID, BEEHIVE_OVEN_CONTROL_BLOCK);
        Registry.register(Registry.ITEM, BeehiveOvenControlBlock.ID, new BlockItem(BEEHIVE_OVEN_CONTROL_BLOCK, new Item.Settings().group(ModMain.CHEMICA_GENERAL)));
        BEEHIVE_OVEN_CONTROL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BeehiveOvenControlBlock.ID,
                BlockEntityType.Builder.create(BeehiveOvenControlBlockEntity::new, BEEHIVE_OVEN_CONTROL_BLOCK).build(null));
    }
}
