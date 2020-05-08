package team.teasanctuary.chemica.entities;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.util.math.Direction;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;
import team.teasanctuary.chemica.api.MachineBlockWithEnergy;
import team.teasanctuary.chemica.blocks.BasicWireBlock;
import team.teasanctuary.chemica.registry.Blocks;

public class BasicWireBlockEntity extends MachineBlockWithEnergy {
    public BasicWireBlockEntity() {
        super(Blocks.BASIC_WIRE_BLOCK_ENTITY, 500, true, 0);
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return null;
    }

    @Override
    public void tick() {
//        if (getCachedState().get(BasicWireBlock.CONNECTED_DOWN)) {
////            BlockEntity be = world.getBlockEntity(pos.offset(Direction.DOWN));
////            if (be instanceof IEnergyStorageHolder) {
////                energy.to((((IEnergyStorageHolder) be).getEnergyStorage()), 64);
////            }
////        }
////
////        if (getCachedState().get(BasicWireBlock.CONNECTED_UP)) {
////            BlockEntity be = world.getBlockEntity(pos.offset(Direction.UP));
////            if (be instanceof IEnergyStorageHolder) {
////                energy.to((((IEnergyStorageHolder) be).getEnergyStorage()), 64);
////            }
////        }
////
////        if (getCachedState().get(BasicWireBlock.CONNECTED_EAST)) {
////            BlockEntity be = world.getBlockEntity(pos.offset(Direction.EAST));
////            if (be instanceof IEnergyStorageHolder) {
////                energy.to((((IEnergyStorageHolder) be).getEnergyStorage()), 64);
////            }
////        }
////
////        if (getCachedState().get(BasicWireBlock.CONNECTED_WEST)) {
////            BlockEntity be = world.getBlockEntity(pos.offset(Direction.WEST));
////            if (be instanceof IEnergyStorageHolder) {
////                energy.to((((IEnergyStorageHolder) be).getEnergyStorage()), 64);
////            }
////        }
////
////        if (getCachedState().get(BasicWireBlock.CONNECTED_NORTH)) {
////            BlockEntity be = world.getBlockEntity(pos.offset(Direction.NORTH));
////            if (be instanceof IEnergyStorageHolder) {
////                energy.to((((IEnergyStorageHolder) be).getEnergyStorage()), 64);
////            }
////        }
        energy.emitEnergy(this, world, pos, 64);
    }
}
