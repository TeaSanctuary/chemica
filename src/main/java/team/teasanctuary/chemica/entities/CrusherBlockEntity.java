package team.teasanctuary.chemica.entities;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.util.Tickable;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.ImplementedInventory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class CrusherBlockEntity extends BlockEntity implements Inventory, PropertyDelegateHolder, Tickable {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private int step = 100;
    private int maxSteps = 500;
    private int steps = 0;

    public CrusherBlockEntity() {
        super(ModMain.CRUSHER_BLOCK_ENTITY);
    }

    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag,items);
        step = tag.getInt("step");
        steps = tag.getInt("steps");
        maxSteps = tag.getInt("maxSteps");
    }

    public void crush() {
        System.out.println("steps: " + steps);
        if (steps + step >= maxSteps) {
            steps = maxSteps;
        } else {
            this.steps += step;
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        tag.putInt("steps", steps);
        tag.putInt("step", step);
        tag.putInt("maxSteps", maxSteps);
        return super.toTag(tag);
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch(index) {
                    case 0: return steps;
                    case 1: return maxSteps;
                }

                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public int getInvSize() {
        return items.size();
    }

    @Override
    public boolean isInvEmpty() {
        return items.size() > 0;
    }

    @Override
    public ItemStack getInvStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack takeInvStack(int slot, int amount) {
        ItemStack stack = getInvStack(slot);

        if (stack.getCount() - amount <= 0) {
            getItems().set(slot, ItemStack.EMPTY);
        } else {
            stack.setCount(amount);
        }

        return stack;
    }

    @Override
    public ItemStack removeInvStack(int slot) {
        getItems().set(slot, ItemStack.EMPTY);

        return getInvStack(slot);
    }

    @Override
    public void setInvStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public void tick() {
        if (steps >= maxSteps) {
            this.steps = 0;

            this.items.set(0, ItemStack.EMPTY);
            markDirty();

            this.items.set(1, new ItemStack(Items.APPLE, 1));
            markDirty();
        }
    }
}
