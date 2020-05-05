package team.teasanctuary.chemica.entities;

import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.*;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.item.ItemStack;
import team.teasanctuary.chemica.recipes.CrusherRecipe;
import team.teasanctuary.chemica.registry.Blocks;

public class CrusherBlockEntity extends MachineBlockEntity implements ICrankable {

    public CrusherBlockEntity() {
        super(Blocks.CRUSHER_BLOCK_ENTITY, 100, true, 2);
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
            ItemStack from = getInvStack(0);

            if (!from.isEmpty()) {
                CrusherRecipe recipe = world.getRecipeManager().getFirstMatch(ModMain.CRUSHER_RECIPE, this, this.world).orElse(null);

                if (recipe != null) {
                    energy.setRecieve(true);
                    boolean canRecieve = canRecieveOutput(recipe);
                    if (energy.getAmount() >= recipe.getTicks() && canRecieve) {
                        ItemStack input = this.items.get(0);
                        ItemStack output = this.items.get(1);
                        ItemStack result = recipe.getOutput();

                        if (output.isEmpty()) {
                            output = result.copy();
                            output.setCount(result.getCount());
                            this.items.set(1, output);
                        } else if(output.getItem() == result.getItem()) {
                            output.increment(result.getCount());
                        }

                        input.decrement(1);
                    }
                }

                return;
            }

            energy.setRecieve(false);
            energy.extract(energy.getCapacity(), false);
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
