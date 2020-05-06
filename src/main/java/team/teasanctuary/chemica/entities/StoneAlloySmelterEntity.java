package team.teasanctuary.chemica.entities;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.ImplementedInventory;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.blocks.StoneAlloySmelterBlock;
import team.teasanctuary.chemica.recipes.GeneratorRecipe;
import team.teasanctuary.chemica.recipes.StoneAlloySmelterRecipe;
import team.teasanctuary.chemica.registry.Blocks;

public class StoneAlloySmelterEntity extends BlockEntity implements ImplementedInventory, PropertyDelegateHolder, Tickable {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private ItemStack output;
    private int recipeBurnTime = 0;
    private int burnTime = 0;
    private int cookTime = 0;
    private int cookTimeTotal;

    public boolean isBurning() { return burnTime > 0; }

    public StoneAlloySmelterEntity() {
        super(Blocks.STONE_ALLOY_SMELTER_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("recipeBurnTime", recipeBurnTime);
        tag.putInt("burnTime", burnTime);
        tag.putInt("cookTime", cookTime);
        tag.putInt("cookTimeTotal", cookTimeTotal);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        recipeBurnTime = tag.getInt("recipeBurnTime");
        burnTime = tag.getInt("burnTime");
        cookTime = tag.getInt("cookTime");
        cookTimeTotal = tag.getInt("cookTimeTotal");
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0: return burnTime;
                    case 1: return recipeBurnTime;
                    case 2: return cookTime;
                    case 3: return cookTimeTotal;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: burnTime = value;
                    break;
                    case 1: recipeBurnTime = value;
                    break;
                    case 2: cookTime = value;
                    break;
                    case 3: cookTimeTotal = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public void tick() {
        if (!world.isClient) {
            if (this.isBurning()) {
                --burnTime;
            }

            ItemStack fuel = getInvStack(2);

            if (!this.isBurning() && fuel.isEmpty()) {
                if (!this.isBurning() && this.cookTime > 0) {
                    this.cookTime = cookTimeTotal;
                }
            } else {
                StoneAlloySmelterRecipe recipe = this.world.getRecipeManager().getFirstMatch(ModMain.STONE_ALLOY_SMELTER_RECIPE, this, this.world).orElse(null);
                if (cookTime <= 0 && this.canRecieveOutput(recipe) && recipe != null) {
                    if (!this.isBurning()) {
                        this.burnTime = AbstractFurnaceBlockEntity.createFuelTimeMap().get(fuel.getItem());
                        this.recipeBurnTime = this.burnTime;
                    }
                    if (this.isBurning()) {
                        if (!fuel.isEmpty()) {
                            fuel.decrement(1);
                        } else {
                            setInvStack(2, ItemStack.EMPTY);
                        }

                        cookTime = 0;
                        cookTimeTotal = recipe.getCookTime();

                    }
                }

                if (this.isBurning() && this.canRecieveOutput(recipe) && recipe != null) {
                    if (this.cookTimeTotal > 0) {
                        ++this.cookTime;
                    }
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = 0;

                        ItemStack out = getInvStack(3);

                        ItemStack from = getInvStack(0);
                        ItemStack from2 = getInvStack(1);

                        ItemStack input1 = recipe.getInput();
                        ItemStack input2 = recipe.getInput2();

                        from.decrement(input1.getCount());
                        from2.decrement(input2.getCount());

                        if (out.isEmpty()) {
                            setInvStack(3, recipe.getOutput().copy());
                        } else if (out.getItem() == recipe.getOutput().getItem()) {
                            out.increment(recipe.getOutput().getCount());
                        }
                    }
                } else {
                    this.cookTime = 0;
                }
            }
        }
//            if (isBurning) {
//                --burnTime;
//                --cookTime;
//
//                if (cookTime <= 0 && !output.isEmpty()) {
//                    cookTime = 0;
//                    cookTimeTotal = 0;
//                    output = ItemStack.EMPTY;
//
//                    if (getInvStack(3).isEmpty()) {
//                        setInvStack(3, output.copy());
//                    } else {
//                        getInvStack(3).increment(output.getCount());
//                    }
//                }
//
//                if (burnTime <= 0) {
//                    isBurning = false;
//                    burnTime = 0;
//                    recipeBurnTime = 0;
//
//                    world.setBlockState(pos, getCachedState().with(StoneAlloySmelterBlock.BURNING, false));
//                }
//            }
//
//            ItemStack from = getInvStack(0);
//            ItemStack from2 = getInvStack(1);
//            ItemStack fuel = getInvStack(2);
//
//            if (!from.isEmpty() && !from2.isEmpty() && !isBurning && AbstractFurnaceBlockEntity.canUseAsFuel(fuel) && cookTime <= 0) {
//                StoneAlloySmelterRecipe recipe = world.getRecipeManager().getFirstMatch(ModMain.STONE_ALLOY_SMELTER_RECIPE, this, this.world).orElse(null);
//
//                if (recipe != null) {
//                    if (canRecieveOutput(recipe)) {
//                        burnTime = AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(fuel.getItem(), 0);
//                        recipeBurnTime = burnTime;
//                        cookTime = recipe.getCookTime();
//                        cookTimeTotal = cookTime;
//                        output = recipe.getOutput();
//                        isBurning = true;
//                        world.setBlockState(pos, getCachedState().with(StoneAlloySmelterBlock.BURNING, true));
//
//                        from.decrement(recipe.getInput().getCount());
//                        from2.decrement(recipe.getInput2().getCount());
//                        fuel.decrement(1);
//                    }
//                }
//            }
//        }
    }

    private boolean canRecieveOutput(StoneAlloySmelterRecipe recipe) {
        // TODO
        return true;
    }
}
