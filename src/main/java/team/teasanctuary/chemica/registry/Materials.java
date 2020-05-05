package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Materials {

    public static Item COPPER_INGOT;

    public static final ItemGroup CHEMICA_MATERIALS = FabricItemGroupBuilder.create(
            new Identifier("chemica", "materials"))
            .icon(() -> new ItemStack(COPPER_INGOT))
            .build();

    public static void init() {
        COPPER_INGOT = new Item(new Item.Settings().group(CHEMICA_MATERIALS));

        register();
    }

    private static void register() {
        Registry.register(Registry.ITEM, new Identifier("chemica", "copper_ingot"), COPPER_INGOT);
    }

}
