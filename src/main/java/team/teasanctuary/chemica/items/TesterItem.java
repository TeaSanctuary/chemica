package team.teasanctuary.chemica.items;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import team.teasanctuary.chemica.api.ImplementedInventory;
import team.teasanctuary.chemica.blocks.EnergyBoxBlock;

public class TesterItem extends Item implements ImplementedInventory {
    public static final Identifier ID = new Identifier("chemica", "tester");

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public TesterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world != null && !world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(TesterItem.ID, user, (buffer) -> {
                buffer.writeBlockPos(user.getBlockPos());
            });
        }
        return super.use(world, user, hand);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
