package team.teasanctuary.chemica.api;

import net.minecraft.item.ItemStack;

public interface IItemEnergyStorageHolder {
    IEnergyStorage getEnergyStorage(ItemStack stack);
}
