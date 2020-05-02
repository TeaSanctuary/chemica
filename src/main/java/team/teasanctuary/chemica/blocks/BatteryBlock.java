package team.teasanctuary.chemica.blocks;

import team.teasanctuary.chemica.entities.BatteryBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BatteryBlock extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier("chemica", "battery_block");

    public BatteryBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new BatteryBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof BatteryBlockEntity) {
            ContainerProviderRegistry.INSTANCE.openContainer(BatteryBlock.ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        player.swingHand(Hand.MAIN_HAND);

        return ActionResult.SUCCESS;
    }
}
