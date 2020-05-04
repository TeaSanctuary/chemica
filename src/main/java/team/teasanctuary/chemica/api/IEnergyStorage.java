package team.teasanctuary.chemica.api;

public interface IEnergyStorage {

    int getAmount();
    int getCapacity();

    int extract(int n, boolean sim);
    int recieve(int n, boolean sim);

    boolean canRecieve();
    void setRecieve(boolean v);

    default int to(IEnergyStorage dest, final int amount) {
        if (amount > 0) {
            int sim = extract(amount, true);
            if (sim > 0) {
                sim = dest.recieve(sim, true);
                if (sim > 0) {
                    return dest.recieve(extract(sim, false), false);
                }
            }
        }
        return 0;
    }

}
