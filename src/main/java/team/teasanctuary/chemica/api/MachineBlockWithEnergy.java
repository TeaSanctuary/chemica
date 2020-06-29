package team.teasanctuary.chemica.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;

public abstract class MachineBlockWithEnergy extends MachineBlockEntity implements IEnergyStorageHolder {

    public MachineBlockWithEnergy(BlockEntityType<?> type, int energyCap, boolean canReceiveEnergy, int invSize) {
        super(type, invSize);
        energy = new EnergyStorage(energyCap, canReceiveEnergy);
    }

    protected final EnergyStorage energy;

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        energy.saveToNBT(tag);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        energy.writeFromNBT(tag);
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energy;
    }

}
