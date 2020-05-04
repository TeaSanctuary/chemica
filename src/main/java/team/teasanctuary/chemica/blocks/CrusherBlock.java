package team.teasanctuary.chemica.blocks;

import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.MachineBlock;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CrusherBlock extends MachineBlock {
    public static final Identifier ID = new Identifier("chemica", "crusher");

    public CrusherBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CrusherBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;


        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof CrusherBlockEntity) {
            ContainerProviderRegistry.INSTANCE.openContainer(CrusherBlock.ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        Inventory i = (Inventory) world.getBlockEntity(pos);
        System.out.println("The first slot holds "
                + i.getInvStack(0) + " and the second slot holds " + i.getInvStack(1));

        player.swingHand(Hand.MAIN_HAND);

        return ActionResult.SUCCESS;
    }
}