package team.teasanctuary.chemica.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Materials {

    public static Item COPPER_INGOT;


    public static void init() {
        COPPER_INGOT = new Item(new Item.Settings());
    }

    private static void register() {
        Registry.register(Registry.ITEM, new Identifier("chemica", "copper_ingot"), COPPER_INGOT);
    }

}
