package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.blocks.*;
import team.teasanctuary.chemica.entities.*;

public class Blocks {
    public static EnergyBoxBlock ENERGY_BOX_BLOCK;
    public static CrusherBlock CRUSHER_BLOCK;
    public static CrankBlock CRANK_BLOCK;
    public static SolidFuelGeneratorBlock SOLID_FUEL_GENERATOR_BLOCK;
    public static ConduitBlock CONDUIT_BLOCK;
    public static StoneAlloySmelterBlock STONE_ALLOY_SMELTER_BLOCK;
    public static Block BAUXITE_BLOCK;

    public static BlockEntityType<EnergyBoxEntity> ENERGY_BOX_ENTITY;
    public static BlockEntityType<CrusherBlockEntity> CRUSHER_BLOCK_ENTITY;
    public static BlockEntityType<CrankBlockEntity> CRANK_BLOCK_ENTITY;
    public static BlockEntityType<SolidFuelGeneratorEntity> SOLID_FUEL_GENERATOR_ENTITY;
    public static BlockEntityType<ConduitBlockEntity> CONDUIT_BLOCK_ENTITY;
    public static BlockEntityType<StoneAlloySmelterEntity> STONE_ALLOY_SMELTER_ENTITY;

    public static void init() {
        ENERGY_BOX_BLOCK = new EnergyBoxBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CRUSHER_BLOCK = new CrusherBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        CRANK_BLOCK = new CrankBlock(FabricBlockSettings.of(Material.WOOD).hardness(1.f).nonOpaque().build());
        SOLID_FUEL_GENERATOR_BLOCK = new SolidFuelGeneratorBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).build());
        CONDUIT_BLOCK = new ConduitBlock(FabricBlockSettings.of(Material.METAL).hardness(1.f).nonOpaque().build());
        STONE_ALLOY_SMELTER_BLOCK = new StoneAlloySmelterBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        BAUXITE_BLOCK = new Block(FabricBlockSettings.of(Material.SAND).hardness(1.f).build());

        register();
    }

    private static void register() {
        Registry.register(Registry.BLOCK, new Identifier("chemica", "bauxite"), BAUXITE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "bauxite"), new BlockItem(BAUXITE_BLOCK, new Item.Settings().maxCount(1).group(ModMain.CHEMICA_GENERAL)));

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
    }
}
