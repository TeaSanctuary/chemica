package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Materials {

    public static Item COPPER_INGOT;
    public static Item ALUMINUM_INGOT;
    public static Item BRONZE_INGOT;
    public static Item LEAD_INGOT;
    public static Item MAGNESIUM_INGOT;
    public static Item SILVER_INGOT;
    public static Item STEEL_INGOT;
    public static Item TIN_INGOT;
    public static Item ZINC_INGOT;

    public static Item ARGENTITE_ORE_DUST;
    public static Item CASSITERITE_ORE_DUST;
    public static Item COAL_ORE_DUST;
    public static Item CUPRITE_ORE_DUST;
    public static Item DIAMOND_ORE_DUST;
    public static Item GALENA_ORE_DUST;
    public static Item GOLD_ORE_DUST;
    public static Item IRON_ORE_DUST;
    public static Item MALACHITE_ORE_DUST;
    public static Item PYRITE_ORE_DUST;
    public static Item REDSTONE_ORE_DUST;
    public static Item RUTILE_ORE_DUST;
    public static Item SILVER_ORE_DUST;
    public static Item ZINCITE_ORE_DUST;
    public static Item SPHALERITE_ORE_DUST;
    public static Item CHALCOPYRITE_ORE_DUST;

    public static Item COKE_ITEM;

    public static Item WOODEN_GEAR;

    public static final ItemGroup CHEMICA_MATERIALS = FabricItemGroupBuilder.create(
            new Identifier("chemica", "materials"))
            .icon(() -> new ItemStack(COPPER_INGOT))
            .build();

    public static void init() {
        COPPER_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        ALUMINUM_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        BRONZE_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        LEAD_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        MAGNESIUM_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        SILVER_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        STEEL_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        TIN_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        ZINC_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        ARGENTITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        CASSITERITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        COAL_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        CUPRITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        DIAMOND_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        GALENA_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        GOLD_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        IRON_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        MALACHITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        PYRITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        REDSTONE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        RUTILE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        SILVER_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        ZINCITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        SPHALERITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        CHALCOPYRITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        COKE_ITEM = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        WOODEN_GEAR = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        register();
    }

    private static void register() {
        Registry.register(Registry.ITEM, new Identifier("chemica", "copper_ingot"), COPPER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "aluminum_ingot"), ALUMINUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "bronze_ingot"), BRONZE_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "lead_ingot"), LEAD_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "magnesium_ingot"), MAGNESIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "silver_ingot"), SILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "steel_ingot"), STEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "tin_ingot"), TIN_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "zinc_ingot"), ZINC_INGOT);

        Registry.register(Registry.ITEM, new Identifier("chemica", "argentite_ore_dust"), ARGENTITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "cassiterite_ore_dust"), CASSITERITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "coal_ore_dust"), COAL_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "cuprite_ore_dust"), CUPRITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "diamond_ore_dust"), DIAMOND_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "gold_ore_dust"), GOLD_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "iron_ore_dust"), IRON_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "malachite_ore_dust"), MALACHITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "pyrite_ore_dust"), PYRITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "redstone_ore_dust"), REDSTONE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "rutile_ore_dust"), RUTILE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "silver_ore_dust"), SILVER_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "zincite_ore_dust"), ZINCITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "sphalerite_ore_dust"), SPHALERITE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "galena_ore_dust"), GALENA_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "chalcopyrite_ore_dust"), CHALCOPYRITE_ORE_DUST);

        Registry.register(Registry.ITEM, new Identifier("chemica", "coke"), COKE_ITEM);

        Registry.register(Registry.ITEM, new Identifier("chemica", "wooden_gear"), WOODEN_GEAR);
    }

}
