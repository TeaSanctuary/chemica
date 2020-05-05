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

    public static final ItemGroup CHEMICA_ORES = FabricItemGroupBuilder.create(
            new Identifier("chemica", "ores"))
            .icon(() -> new ItemStack(MALACHITE_ORE_BLOCK))
            .build();

    private static void register() {
        Registry.register(Registry.BLOCK, new Identifier("chemica", "malachite_ore"), MALACHITE_ORE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("chemica", "malachite_ore"), new BlockItem(MALACHITE_ORE_BLOCK, new Item.Settings().group(CHEMICA_ORES)));
    }

    public static void init() {

        MALACHITE_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.f).build());

        register();
    }

}
