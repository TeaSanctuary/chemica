package team.teasanctuary.chemica.entities;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.ICrankable;
import team.teasanctuary.chemica.api.IEnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;

public class CrankBlockEntity extends BlockEntity {
    public CrankBlockEntity() {
        super(ModMain.CRANK_BLOCK_ENTITY);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return super.toTag(tag);
    }

    public ICrankable getCrankable() {
        if (world.isClient) return null;
        BlockPos p = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        return (ICrankable) world.getBlockEntity(p);
    }

    public ActionResult onPush() {
        ICrankable crankable = getCrankable();
        if (crankable == null) return ActionResult.FAIL;
        if (!(crankable instanceof IEnergyStorageHolder)) return ActionResult.PASS;

        System.out.println("crank");

        IEnergyStorage e = ((IEnergyStorageHolder) crankable).getEnergyStorage();

        e.recieve(1, false);
        return ActionResult.SUCCESS;
    }
}
