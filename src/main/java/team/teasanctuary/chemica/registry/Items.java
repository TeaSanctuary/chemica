package team.teasanctuary.chemica.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import team.teasanctuary.chemica.items.BatteryItem;
import team.teasanctuary.chemica.items.ScrewdriverItem;
import team.teasanctuary.chemica.items.TesterItem;

public class Items {

    public static ScrewdriverItem SCREWDRIVER_ITEM;
    public static BatteryItem BATTERY_ITEM;
    public static TesterItem TESTER_ITEM;

    public static void init() {
        SCREWDRIVER_ITEM = new ScrewdriverItem(new Item.Settings().maxCount(1));
        BATTERY_ITEM = new BatteryItem(new Item.Settings().maxCount(1));
        TESTER_ITEM = new TesterItem(new Item.Settings().maxCount(1));

        register();
    }

    private static void register() {
        Registry.register(Registry.ITEM, ScrewdriverItem.ID, SCREWDRIVER_ITEM);
        Registry.register(Registry.ITEM, BatteryItem.ID, BATTERY_ITEM);
        Registry.register(Registry.ITEM, TesterItem.ID, TESTER_ITEM);
    }

}
