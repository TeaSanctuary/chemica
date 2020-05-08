package team.teasanctuary.chemica.entities;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.ImplementedInventory;
import team.teasanctuary.chemica.blocks.BeehiveOvenControlBlock;
import team.teasanctuary.chemica.recipes.BeehiveOvenRecipe;
import team.teasanctuary.chemica.registry.Blocks;

public class BeehiveOvenControlBlockEntity extends BlockEntity implements Tickable, ImplementedInventory, PropertyDelegateHolder {
    protected final DefaultedList<ItemStack> items;
    private boolean isComplete;
    private Direction direction;

    private boolean isBurning = false;
    private int temperature = 20;
    private int recipeBurnTime = 0;
    private int burnTime = 0;
    private int threshold = 0;
    private ItemStack output = ItemStack.EMPTY;

    private int tempTimer = 0;

    private static final int tempTransitionSpeed = 1;
    private static final int maxTemperature = 1200;

    private static final Identifier IGNORE_BLOCK = new Identifier("minecraft", "empty");
    private static final Identifier[][][] STRUCTURE = {
            {
                    {
                            IGNORE_BLOCK, Blocks.FIREPROOF_BRICKS_BLOCK_ID, IGNORE_BLOCK
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, IGNORE_BLOCK, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    }
            },
            {
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, new Identifier("minecraft", "air"), Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    }
            },
            {
                    {
                            IGNORE_BLOCK, Blocks.FIREPROOF_BRICKS_BLOCK_ID, IGNORE_BLOCK
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    },
                    {
                            Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID, Blocks.FIREPROOF_BRICKS_BLOCK_ID
                    }
            }
    };

    public BeehiveOvenControlBlockEntity() {
        super(Blocks.BEEHIVE_OVEN_CONTROL_BLOCK_ENTITY);
        items = DefaultedList.ofSize(2, ItemStack.EMPTY);
        isComplete = false;
        this.direction = Direction.NORTH;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        isBurning = tag.getBoolean("isBurning");
        temperature = tag.getInt("temp");
        recipeBurnTime = tag.getInt("recipeBurnTime");
        burnTime = tag.getInt("burnTime");
        threshold = tag.getInt("threshold");
        tempTimer = tag.getInt("tempT");
        output = ItemStack.fromTag(tag);
        Inventories.fromTag(tag, items);

        super.fromTag(tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("isBurning", isBurning);
        tag.putInt("temp", temperature);
        tag.putInt("recipeBurnTime", recipeBurnTime);
        tag.putInt("burnTime", burnTime);
        tag.putInt("threshold", threshold);
        tag.putInt("tempT", tempTimer);
        output.toTag(tag);
        Inventories.toTag(tag, items);

        return super.toTag(tag);
    }

    @Override
    public void tick() {
        if (!hasWorld())
            return;

        if (!getWorld().isClient) {
            if (isBurning) {
                checkStructureIntegrity();
                isBurning = isComplete;
            }

            tempTimer++;
            if (!isBurning) {
                if (temperature > 20) {
                    tempTimer %= 20 * 4;
                    if (tempTimer == 0)
                        --temperature;
                }

                if (burnTime > 0)
                    burnTime = 0;
            }

            if (isBurning && temperature < maxTemperature) {
                if (tempTimer % 10 == 0)
                    ++temperature;
            }
            world.setBlockState(pos, getCachedState().with(BeehiveOvenControlBlock.SMOKING, (temperature >= 100)));

            ItemStack from = getInvStack(0);
            if (isBurning) {
                if (!isComplete)
                    burnTime = 0;
                else
                    --burnTime;

                if (burnTime <= 0) {
                    isBurning = false;
                    burnTime = 0;
                    recipeBurnTime = 0;

                    if (temperature >= threshold) {
                        if (!getInvStack(1).isEmpty() && getInvStack(1).isItemEqualIgnoreDamage(output))
                            this.items.get(1).increment(output.getCount());
                        else if (getInvStack(1).isEmpty()) {
                            ItemStack out = output.copy();
                            out.setCount(output.getCount());
                            setInvStack(1, out);
                        }
                        output = ItemStack.EMPTY;
                    }
                    world.setBlockState(pos, getCachedState().with(BeehiveOvenControlBlock.LIT, false));
                } else
                    world.setBlockState(pos, getCachedState().with(BeehiveOvenControlBlock.LIT, true));
            }

            if (!from.isEmpty() && !isBurning && isComplete) {
                BeehiveOvenRecipe recipe = world.getRecipeManager().getFirstMatch(ModMain.BEEHIVE_OVEN_RECIPE_TYPE, this, this.world).orElse(null);
                if (recipe == null) {
                    int bt = AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(from.getItem(), -1);
                    if (bt != -1) {
                        Identifier itemID = Registry.ITEM.getId(from.getItem());
                        recipe = new BeehiveOvenRecipe(new Identifier("chemica", "fallback_" + itemID.getNamespace() + "_" + itemID.getPath()),
                                Ingredient.ofStacks(from), ItemStack.EMPTY, bt, maxTemperature + 1);
                    }
                }

                if (recipe != null && canAcceptOutput(recipe)) {
                    isBurning = true;
                    recipeBurnTime = recipe.getBurnTime();
                    burnTime = recipeBurnTime;
                    threshold = recipe.getThreshold();
                    output = recipe.getOutput();
                    from.decrement(1);
                }
            }
        }
    }

    private boolean canAcceptOutput(BeehiveOvenRecipe recipe) {
        if (!this.items.get(0).isEmpty() && recipe != null) {
            ItemStack result = recipe.getOutput();
            if (result.isEmpty()) return true;

            ItemStack output = getInvStack(1);
            if (output.isEmpty()) return true;
            if (!output.isItemEqualIgnoreDamage(result)) return false;
            if (getInvMaxStackAmount() > output.getCount() && output.getCount() < output.getMaxCount()) return true;
            return output.getCount() < result.getMaxCount();
        }
        return false;
    }

    public void checkStructure() {
        if (!isBurning && hasWorld()) {
            checkStructureIntegrity();
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public boolean getBurning() {
        return isBurning;
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    if (tempTimer % 10 == 0) // check for integrity every 500 ms
                        checkStructure();
                    return (isComplete) ? 1 : 0;
                case 1: return temperature;
                case 2: return maxTemperature;
                case 3: return burnTime;
                case 4: return recipeBurnTime;
                default: return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    isComplete = (value > 0); // FIXME: why
                    break;
                case 1:
                    temperature = value;
                    break;
                case 2:
                    break;
                case 3:
                    burnTime = value;
                    break;
                case 4:
                    recipeBurnTime = value;
                    break;
                default:
                    break;
            }
        }

        @Override
        public int size() {
            return 5;
        }
    };

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    private void checkStructureIntegrity() {
        assert(hasWorld());
        World world = getWorld();
        BlockPos pos = getPos();
        BlockPos xMod = new BlockPos(pos); // right relative to the block
        BlockPos yMod = new BlockPos(0, -1, 0);
        BlockPos zMod = new BlockPos(pos); // back relative to the block
        BlockPos origin = new BlockPos(pos); // top front left corner of the structure relative to the control block
        BlockState state = world.getBlockState(pos);
        direction = state.get(BeehiveOvenControlBlock.FACING);

        switch (direction) {
            case NORTH:
                origin = new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ());

                xMod = new BlockPos(-1, 0, 0);
                zMod = new BlockPos(0, 0, 1);
                break;
            case EAST:
                origin = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 1);

                xMod = new BlockPos(0, 0, -1);
                zMod = new BlockPos(-1, 0, 0);
                break;
            case SOUTH:
                origin = new BlockPos(pos.getX() - 1, pos.getY() + 2, pos.getZ());

                xMod = new BlockPos(1, 0, 0);
                zMod = new BlockPos(0, 0, -1);
                break;
            case WEST:
                origin = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() - 1);

                xMod = new BlockPos(0, 0, 1);
                zMod = new BlockPos(1, 0, 0);
                break;
        }

        boolean tempIsComplete = true;
        BlockPos prevOrigin = origin;
        for (int x = 0; x < 3 && tempIsComplete; x++, origin = origin.add(xMod)) {
            origin = new BlockPos(origin.getX(), prevOrigin.getY(), origin.getZ());
            for (int y = 0; y < 3 && tempIsComplete; y++, origin = origin.add(yMod)) {
                switch (direction) {
                    case NORTH:
                    case SOUTH:
                        origin = new BlockPos(origin.getX(), origin.getY(), prevOrigin.getZ());
                        break;
                    case EAST:
                    case WEST:
                        origin = new BlockPos(prevOrigin.getX(), origin.getY(), origin.getZ());
                        break;
                }
                for (int z = 0; z < 3 && tempIsComplete; z++, origin = origin.add(zMod)) {
                    Identifier worldId = Registry.BLOCK.getId(world.getBlockState(origin).getBlock());
                    Identifier structureId = STRUCTURE[z][y][x];
                    if (!structureId.toString().equals(IGNORE_BLOCK.toString()) && !worldId.toString().equals(structureId.toString()))
                        tempIsComplete = false;
                }
            }
        }

        this.isComplete = tempIsComplete;
    }
}
