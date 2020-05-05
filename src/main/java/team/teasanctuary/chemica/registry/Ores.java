package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Ores {

    public static Block MALACHITE_ORE_BLOCK;
    public static Block ARGENTITE_ORE_BLOCK;
    public static Block CHALCOPYRITE_ORE_BLOCK;
    public static Block GALENA_ORE_BLOCK;
    public static Block CUPRITE_ORE_BLOCK;
    public static Block PYRITE_ORE_BLOCK;
    public static Block RUTILE_ORE_BLOCK;
    public static Block SILVER_ORE_BLOCK;
    public static Block SPHALERITE_ORE_BLOCK;
    public static Block ZINCITE_ORE_BLOCK;
    public static Block CASSITERITE_ORE_BLOCK;

    public static final ItemGroup CHEMICA_ORES = FabricItemGroupBuilder.create(
            new Identifier("chemica", "ores"))
            .icon(() -> new ItemStack(MALACHITE_ORE_BLOCK))
            .build();

    private static void register() {
        Registry.register(Registry.BLOCK, new Identifier("chemica", "malachite_ore"), MALACHITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "malachite_ore"), new BlockItem(MALACHITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "argentite_ore"), ARGENTITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "argentite_ore"), new BlockItem(ARGENTITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "chalcopyrite_ore"), CHALCOPYRITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "chalcopyrite_ore"), new BlockItem(CHALCOPYRITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "galena_ore"), GALENA_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "galena_ore"), new BlockItem(GALENA_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "cuprite_ore"), CUPRITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "cuprite_ore"), new BlockItem(CUPRITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "rutile_ore"), RUTILE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "rutile_ore"), new BlockItem(RUTILE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "pyrite_ore"), PYRITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "pyrite_ore"), new BlockItem(PYRITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "silver_ore"), SILVER_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "silver_ore"), new BlockItem(SILVER_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "sphalerite_ore"), SPHALERITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "sphalerite_ore"), new BlockItem(SPHALERITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "zincite_ore"), ZINCITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "zincite_ore"), new BlockItem(ZINCITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));

        Registry.register(Registry.BLOCK, new Identifier("chemica", "cassiterite_ore"), CASSITERITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "cassiterite_ore"), new BlockItem(CASSITERITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));
    }

    public static void init() {

        MALACHITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(2.f).build());
        ARGENTITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        CHALCOPYRITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        GALENA_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        CUPRITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.f).build());
        PYRITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        RUTILE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(5.f).build());
        SILVER_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        SPHALERITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.f).build());
        ZINCITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.f).build());
        CASSITERITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.f).build());

        register();
    }

}
