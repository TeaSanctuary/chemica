package team.teasanctuary.chemica.entities;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.BooleanProperty;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.MachineBlockEntity;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.recipes.GeneratorRecipe;

public class SolidFuelGeneratorEntity extends MachineBlockEntity {

    private int recipeBurnTime = 0;
    private int burnTime = 0;
    private boolean isBurning = false;

    public SolidFuelGeneratorEntity() {
        super(ModMain.SOLID_FUEL_GENERATOR_ENTITY, 100000, true, 2);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("recipeBurnTime", recipeBurnTime);
        tag.putInt("burnTime", burnTime);
        tag.putBoolean("isBurning", isBurning);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        recipeBurnTime = tag.getInt("recipeBurnTime");
        burnTime = tag.getInt("burnTime");
        isBurning = tag.getBoolean("isBurning");
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0: return energy.getAmount();
                case 1: return energy.getCapacity();
                case 2: return burnTime;
                case 3: return recipeBurnTime;
                default: return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: // Current Energy
                    energy.setEnergy(value);
                    break;
                case 1: // Max Energy
                    energy.setCapacity(value);
                    break;
                case 2: // Burn Time
                    burnTime = value;
                    break;
                case 3: // Burn Time Total
                    recipeBurnTime = value;
                    break;
                default:
                    break;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    @Override
    public void tick() {
        if (!world.isClient) {
            if (isBurning) {
                --burnTime;
                energy.recieve(30, false);
                if (burnTime <= 0) {
                    isBurning = false;
                    burnTime = 0;
                    recipeBurnTime = 0;
                    world.setBlockState(pos, getCachedState().with(SolidFuelGeneratorBlock.BURNING, false));
                }
            }

            ItemStack from = getInvStack(0);

            if (!from.isEmpty() && !isBurning) {
                GeneratorRecipe recipe = world.getRecipeManager().getFirstMatch(ModMain.GENERATOR_RECIPE, this, this.world).orElse(null);

                if (recipe != null) {
                    if (canRecieveOutput(recipe)) {
                        ItemStack input = getInvStack(0);
                        burnTime = recipe.getBurnTime();
                        recipeBurnTime = burnTime;
                        isBurning = true;
                        world.setBlockState(pos, getCachedState().with(SolidFuelGeneratorBlock.BURNING, true));

                        input.decrement(1);
                    }
                }
            }
            if (energy.getAmount() > 0) {
                energy.emitEnergy(this, world, pos, 30);
            }
        }
    }

    private boolean canRecieveOutput(GeneratorRecipe recipe) {
        return !(energy.getAmount() >= energy.getCapacity());
    }
}
