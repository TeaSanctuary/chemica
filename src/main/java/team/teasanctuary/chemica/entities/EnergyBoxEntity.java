package team.teasanctuary.chemica.entities;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import team.teasanctuary.chemica.registry.Blocks;

public class EnergyBoxEntity extends MachineBlockWithEnergy {

    private final int transferRate = 60;

    public EnergyBoxEntity() {
        super(Blocks.ENERGY_BOX_ENTITY, 500000, true, 2);
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    // energy
                    case 0: return energy.getAmount();
                    // capacity
                    case 1: return energy.getCapacity();
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    // energy
                    case 0:
                        energy.setEnergy(value);
                        break;
                    // capacity
                    case 1:
                        energy.setCapacity(value);
                        break;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void tick() {
        super.tick();

        if (!world.isClient) {
            ItemStack chargable = getInvStack(0);
            ItemStack dischargable = getInvStack(1);

            if (!chargable.isEmpty()) {
                Item item = chargable.getItem();

                if (item instanceof IItemEnergyStorageHolder) {
                    if (((IItemEnergyStorageHolder) item).getEnergyStorage(chargable).getAmount() < ((IItemEnergyStorageHolder) item).getEnergyStorage(chargable).getCapacity())
                        energy.to(((IItemEnergyStorageHolder) item).getEnergyStorage(chargable), transferRate);
                }
            }

            if (!dischargable.isEmpty()) {
                Item item = dischargable.getItem();

                if (item instanceof IItemEnergyStorageHolder) {
                    if (((IItemEnergyStorageHolder) item).getEnergyStorage(dischargable).getAmount() > 0) {
                        ((IItemEnergyStorageHolder) item).getEnergyStorage(dischargable).to(this.getEnergyStorage(), transferRate);
                    }
                }
            }
        }
    }
}
