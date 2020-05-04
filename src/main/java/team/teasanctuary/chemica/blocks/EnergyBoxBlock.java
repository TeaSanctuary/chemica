package team.teasanctuary.chemica.blocks;

import team.teasanctuary.chemica.entities.EnergyBoxEntity;
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

public class EnergyBoxBlock extends Block implements BlockEntityProvider {
    public static final Identifier ID = new Identifier("chemica", "energy_box");

    public EnergyBoxBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new EnergyBoxEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof EnergyBoxEntity) {
            ContainerProviderRegistry.INSTANCE.openContainer(EnergyBoxBlock.ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        player.swingHand(Hand.MAIN_HAND);

        return ActionResult.SUCCESS;
    }
}
