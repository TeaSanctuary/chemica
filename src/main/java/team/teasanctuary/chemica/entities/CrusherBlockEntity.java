package team.teasanctuary.chemica.entities;

import net.minecraft.container.PropertyDelegate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.ICrankable;
import team.teasanctuary.chemica.api.MachineBlockWithEnergy;
import team.teasanctuary.chemica.recipes.CrusherRecipe;
import team.teasanctuary.chemica.registry.Blocks;

public class CrusherBlockEntity extends MachineBlockWithEnergy implements ICrankable {
    private ItemStack output = ItemStack.EMPTY;
    private boolean crushingInProgress = false;

    public CrusherBlockEntity() {
        super(Blocks.CRUSHER_BLOCK_ENTITY, 0, true, 2);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("in_progress", crushingInProgress);

        CompoundTag output_tag = new CompoundTag();
        output.toTag(output_tag);
        tag.put("output", output_tag);

        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);

        crushingInProgress = tag.getBoolean("in_progress");
        output = ItemStack.fromTag(tag.getCompound("output"));
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch(index) {
                    case 0: return energy.getAmount();
                    case 1: return energy.getCapacity();
                }

                return 0;
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: energy.setEnergy(0);
                    break;
                    case 1: energy.setCapacity(value);
                    break;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void tick() {
        if (!world.isClient) {
            if (!output.isEmpty() && crushingInProgress) {
                if (energy.getAmount() >= energy.getCapacity()) {
                    crushingInProgress = false;
                    energy.setReceive(false);
                    energy.setEnergy(0);

                    ItemStack outputStack = getInvStack(1);
                    if (!outputStack.isEmpty()
                            && outputStack.isItemEqualIgnoreDamage(output)
                            && outputStack.getCount() < outputStack.getMaxCount()) {
                        outputStack.increment(output.getCount());
                    } else if (outputStack.isEmpty()) {
                        setInvStack(1, output.copy());
                    }
                    output = ItemStack.EMPTY;
                }
            }

            ItemStack from = getInvStack(0);
            if (!from.isEmpty()) {
                CrusherRecipe recipe = world.getRecipeManager().getFirstMatch(ModMain.CRUSHER_RECIPE, this, this.world).orElse(null);

                if (recipe != null) {
                    if (output.isEmpty()) {
                        if (canRecieveOutput(recipe)) {
                            energy.setReceive(true);
                            energy.setCapacity(recipe.getTicks());
                            energy.setEnergy(0);
                            output = recipe.getOutput();
                        }
                    } else if (!crushingInProgress
                            && output.isItemEqualIgnoreDamage(recipe.getOutput())
                            && energy.getAmount() > 0) {
                        getInvStack(0).decrement(1);
                        crushingInProgress = true;
                    }
                }
            }
        }
    }

    private boolean canRecieveOutput(CrusherRecipe recipe) {
        if (!this.items.get(0).isEmpty() && recipe != null) {
            ItemStack result = recipe.getOutput();
            if (result.isEmpty()) return false;

            ItemStack output = getInvStack(1);
            if (output.isEmpty()) return true;
            if (!output.isItemEqualIgnoreDamage(result)) return false;
            if (getInvMaxStackAmount() > output.getCount() && output.getCount() < output.getMaxCount()) return true;
            return output.getCount() < result.getMaxCount();
        }
        return false;
    }
}
