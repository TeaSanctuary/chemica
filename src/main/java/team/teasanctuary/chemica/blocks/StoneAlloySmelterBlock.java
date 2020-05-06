package team.teasanctuary.chemica.blocks;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.teasanctuary.chemica.api.MachineBlock;
import team.teasanctuary.chemica.entities.SolidFuelGeneratorEntity;
import team.teasanctuary.chemica.entities.StoneAlloySmelterEntity;

public class StoneAlloySmelterBlock extends MachineBlock {
    public static final Identifier ID = new Identifier("chemica", "stone_alloy_smelter");

    public static final BooleanProperty BURNING = BooleanProperty.of("burning");

    public StoneAlloySmelterBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(BURNING, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof StoneAlloySmelterEntity) {
            ContainerProviderRegistry.INSTANCE.openContainer(StoneAlloySmelterBlock.ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        Inventory i = (Inventory) world.getBlockEntity(pos);
        System.out.println("The first slot holds "
                + i.getInvStack(0) + " and the second slot holds " + i.getInvStack(1));

        player.swingHand(Hand.MAIN_HAND);

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BURNING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new StoneAlloySmelterEntity();
    }
}
