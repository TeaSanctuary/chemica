package team.teasanctuary.chemica.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import team.teasanctuary.chemica.entities.CrankBlockEntity;

public class CrankBlock extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier("chemica", "crank");

    public CrankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CrankBlockEntity();
    }
}
