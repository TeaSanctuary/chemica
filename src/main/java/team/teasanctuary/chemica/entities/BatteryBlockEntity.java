package team.teasanctuary.chemica.entities;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.IEnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;

public class BatteryBlockEntity extends BlockEntity implements IEnergyStorage, PropertyDelegateHolder {

    private int energy = 10000;
    private final int energyCapacity = 20000;

    public BatteryBlockEntity() {
        super(ModMain.BATTERY_BLOCK_ENTITY);
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    // energy
                    case 0: return energy;
                    // capacity
                    case 1: return energyCapacity;
                }

                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    @Override
    public int getAmount() {
        return energy;
    }

    @Override
    public int getCapacity() {
        return energyCapacity;
    }

    @Override
    public int extract(int n, boolean sim) {
        if (n > 0) {
            if (n > energy)
                n = energy;
            if (!sim)
                energy -= n;
        }
        return n;
    }

    @Override
    public int recieve(int n, boolean sim) {
        if (n > 0) {
            final int r = energyCapacity - energy;
            if (n > r)
                n = r;
            if (!sim)
                energy += n;
        }
        return n;
    }
}
