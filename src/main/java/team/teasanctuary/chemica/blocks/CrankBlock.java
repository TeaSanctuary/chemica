package team.teasanctuary.chemica.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.teasanctuary.chemica.api.EnergyStorage;
import team.teasanctuary.chemica.api.ICrankable;
import team.teasanctuary.chemica.api.IEnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;
import team.teasanctuary.chemica.entities.CrankBlockEntity;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;

public class CrankBlock extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier("chemica", "crank");

    public CrankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CrankBlockEntity();
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moved) {
        super.onBlockAdded(state, world, pos, oldState, moved);
        BlockEntity be = world.getBlockEntity(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));

        if (be instanceof ICrankable) {
            return;
        }

        world.breakBlock(pos, true);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        System.out.println("used crank");
        if (world.isClient) return ActionResult.SUCCESS;

        CrankBlockEntity be = (CrankBlockEntity) world.getBlockEntity(pos);
        assert be != null;
        return be.onPush();
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean moved) {
        super.neighborUpdate(state, world, pos, block, neighborPos, moved);
        if (neighborPos.equals(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()))) {
            world.breakBlock(pos, true);
        }
    }
}
