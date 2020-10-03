package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Materials {

    public static Item COPPER_INGOT;
    public static Item ZINC_INGOT;

    public static Item COAL_ORE_DUST;
    public static Item DIAMOND_ORE_DUST;
    public static Item GOLD_ORE_DUST;
    public static Item IRON_ORE_DUST;
    public static Item REDSTONE_ORE_DUST;
    public static Item ZINCITE_ORE_DUST;

    public static Item COKE_ITEM;

    public static Item WOODEN_GEAR;

    public static final ItemGroup CHEMICA_MATERIALS = FabricItemGroupBuilder.create(
            new Identifier("chemica", "materials"))
            .icon(() -> new ItemStack(COPPER_INGOT))
            .build();

    public static void init() {
        COPPER_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        ZINC_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        COAL_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        DIAMOND_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        GOLD_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        IRON_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        REDSTONE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));
        ZINCITE_ORE_DUST = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        COKE_ITEM = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        WOODEN_GEAR = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        register();
    }

    private static void register() {
        Registry.register(Registry.ITEM, new Identifier("chemica", "copper_ingot"), COPPER_INGOT);
        Registry.register(Registry.ITEM, new Identifier("chemica", "zinc_ingot"), ZINC_INGOT);

        Registry.register(Registry.ITEM, new Identifier("chemica", "coal_ore_dust"), COAL_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "diamond_ore_dust"), DIAMOND_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "gold_ore_dust"), GOLD_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "iron_ore_dust"), IRON_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "redstone_ore_dust"), REDSTONE_ORE_DUST);
        Registry.register(Registry.ITEM, new Identifier("chemica", "zincite_ore_dust"), ZINCITE_ORE_DUST);

        Registry.register(Registry.ITEM, new Identifier("chemica", "coke"), COKE_ITEM);

        Registry.register(Registry.ITEM, new Identifier("chemica", "wooden_gear"), WOODEN_GEAR);
    }

}
