package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.items.BatteryItem;
import team.teasanctuary.chemica.items.ScrewdriverItem;
import team.teasanctuary.chemica.items.TesterItem;

public class Items {

    public static ScrewdriverItem SCREWDRIVER_ITEM;
    public static BatteryItem BATTERY_ITEM;
    public static TesterItem TESTER_ITEM;

    public static final ItemGroup CHEMICA_ITEMS = FabricItemGroupBuilder.create(
            new Identifier("chemica", "items"))
            .icon(() -> new ItemStack(TESTER_ITEM))
            .build();

    public static void init() {
        SCREWDRIVER_ITEM = new ScrewdriverItem(new Item.Settings().maxCount(1).group(CHEMICA_ITEMS));
        BATTERY_ITEM = new BatteryItem(new Item.Settings().maxCount(1).group(CHEMICA_ITEMS));
        TESTER_ITEM = new TesterItem(new Item.Settings().maxCount(1).group(CHEMICA_ITEMS));

        register();
    }

    private static void register() {
        Registry.register(Registry.ITEM, ScrewdriverItem.ID, SCREWDRIVER_ITEM);
        Registry.register(Registry.ITEM, BatteryItem.ID, BATTERY_ITEM);
        Registry.register(Registry.ITEM, TesterItem.ID, TESTER_ITEM);
    }

}
