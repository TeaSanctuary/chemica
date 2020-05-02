package team.teasanctuary.chemica.api;

public class EnergyStorage implements IEnergyStorage {

    private int energy = 0;
    private final int capacity;
    private boolean receive = false;

    public EnergyStorage(int cap, boolean canRecieve) {
        this.capacity = cap;
        this.receive = canRecieve;
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
