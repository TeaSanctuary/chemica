package team.teasanctuary.chemica.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.api.EnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;

public class BatteryItem extends Item implements IEnergyStorageHolder {
    public static final Identifier ID = new Identifier("chemica", "battery");
    private EnergyStorage energy = new EnergyStorage(5000, true);

    public BatteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energy;
    }
}
