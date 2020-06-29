package team.teasanctuary.chemica.api;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public abstract class MachineBlockEntity extends BlockEntity implements Tickable, ImplementedInventory, PropertyDelegateHolder,
        BlockEntityClientSerializable  {

    protected DefaultedList<ItemStack> items;

    public MachineBlockEntity(BlockEntityType<?> type, int invSize) {
        super(type);
        items = DefaultedList.ofSize(invSize, ItemStack.EMPTY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(getCachedState(), tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        fromTag(getCachedState(), compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return toTag(compoundTag);
    }

    @Override
    public void tick() {

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
