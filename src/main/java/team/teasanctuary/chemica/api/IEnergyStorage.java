package team.teasanctuary.chemica.api;

public interface IEnergyStorage {

    int getAmount();
    int getCapacity();

    int extract(int n, boolean sim);
    int receive(int n, boolean sim);

    boolean canReceive();
    void setReceive(boolean v);

    default int to(IEnergyStorage dest, final int amount) {
        if (amount > 0) {
            int sim = extract(amount, true);
            if (sim > 0) {
                sim = dest.receive(sim, true);
                if (sim > 0) {
                    return dest.receive(extract(sim, false), false);
                }
            }
        }
        return 0;
    }

}
