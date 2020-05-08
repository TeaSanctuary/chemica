package team.teasanctuary.chemica.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.IEnergyProvider;
import team.teasanctuary.chemica.api.MachineBlock;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;
import team.teasanctuary.chemica.entities.SolidFuelGeneratorEntity;

import java.util.Random;

public class SolidFuelGeneratorBlock extends MachineBlock {
    public static final Identifier ID = new Identifier("chemica", "solid_fuel_generator");

    public static final BooleanProperty BURNING = BooleanProperty.of("burning");

    public SolidFuelGeneratorBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(BURNING, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new SolidFuelGeneratorEntity();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BURNING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof SolidFuelGeneratorEntity) {
            ContainerProviderRegistry.INSTANCE.openContainer(SolidFuelGeneratorBlock.ID, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }

        Inventory i = (Inventory) world.getBlockEntity(pos);
        System.out.println("The first slot holds "
                + i.getInvStack(0) + " and the second slot holds " + i.getInvStack(1));

        player.swingHand(Hand.MAIN_HAND);

        return ActionResult.SUCCESS;
    }

    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SolidFuelGeneratorEntity) {
                ItemScatterer.spawn(world, (BlockPos)pos, (Inventory)((SolidFuelGeneratorEntity)blockEntity));
                world.updateHorizontalAdjacent(pos, this);
            }

            super.onBlockRemoved(state, world, pos, newState, moved);
        }
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean)state.get(BURNING)) {
            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY();
            double f = (double)pos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = (Direction)state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52D;
            double h = random.nextDouble() * 0.6D - 0.3D;
            double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52D : h;
            double j = random.nextDouble() * 6.0D / 16.0D;
            double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52D : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
        }
    }
}
