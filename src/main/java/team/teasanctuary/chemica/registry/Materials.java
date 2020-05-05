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
        Registry.register(Registry.ITEM, new Identifier("chemica", "wooden_gear"), WOODEN_GEAR);
    }

}
