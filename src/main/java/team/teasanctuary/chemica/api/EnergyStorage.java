package team.teasanctuary.chemica.api;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyStorage implements IEnergyStorage {

    public int energy = 0;
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

    @Override
    public boolean canRecieve() { return receive; }

    public void setRecieve(boolean v) { receive = v; }
    public void setEnergy(int v) { energy = v; }
    public void setCapacity(int v) { capacity = v; }

    public void emitEnergy(IEnergyStorageHolder from, World world, BlockPos pos, int transferSize) {
        if (from.getEnergyStorage().getAmount() == 0) return;

        List<IEnergyStorageHolder> targets = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            BlockEntity be = world.getBlockEntity(pos.offset(direction));
            if (be instanceof IEnergyStorageHolder && !(be instanceof IEnergyProvider)) {
                targets.add((IEnergyStorageHolder) be);
            }
        }

        if (targets.size() == 0) return;
        if (transferSize % targets.size() != 0) return;

        AtomicInteger finalTransferSize = new AtomicInteger(transferSize / targets.size());
        targets.forEach(to -> from.getEnergyStorage().to(to.getEnergyStorage(), finalTransferSize.get()));
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
            final int r = capacity - energy;
            if (n > r)
                n = r;
            if (!sim)
                energy += n;
        }
        return n;
    }
}
