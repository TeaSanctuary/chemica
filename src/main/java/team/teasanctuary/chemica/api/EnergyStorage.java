package team.teasanctuary.chemica.api;

import net.minecraft.nbt.CompoundTag;

public class EnergyStorage implements IEnergyStorage {

    private int energy = 0;
    private int capacity;
    private boolean receive = false;

    public EnergyStorage(int cap, boolean canRecieve) {
        this.capacity = cap;
        this.receive = canRecieve;
    }

    public void saveToNBT(CompoundTag tag) {
        tag.putInt("energy", energy);
        tag.putInt("capacity", capacity);
        tag.putBoolean("receive", receive);
    }

    public void writeFromNBT(CompoundTag tag) {
        energy = tag.getInt("energy");
        capacity = tag.getInt("capacity");
        receive = tag.getBoolean("receive");
    }

    @Override
    public int getAmount() {
        return energy;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public boolean canRecieve() { return receive; }

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
        if (!receive) return -1;
        if (n > 0) {
            final int r = capacity - energy;
            if (n > r)
                n = r;
            if (!sim)
                energy += n;
        }
        return n;
    }
}
