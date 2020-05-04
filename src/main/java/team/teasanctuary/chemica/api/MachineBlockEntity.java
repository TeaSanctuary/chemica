package team.teasanctuary.chemica.api;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

public abstract class MachineBlockEntity extends BlockEntity implements Tickable, ImplementedInventory, PropertyDelegateHolder,
        BlockEntityClientSerializable, IEnergyStorageHolder {

    protected final DefaultedList<ItemStack> items;
    protected final EnergyStorage energy;

    public MachineBlockEntity(BlockEntityType<?> type, int energyCap, boolean canRecieveEnergy, int invSize) {
        super(type);
        energy = new EnergyStorage(energyCap, canRecieveEnergy);
        items = DefaultedList.ofSize(invSize, ItemStack.EMPTY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        energy.saveToNBT(tag);
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        energy.writeFromNBT(tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        fromTag(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return toTag(compoundTag);
    }

    @Override
    public void tick() {

    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energy;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
