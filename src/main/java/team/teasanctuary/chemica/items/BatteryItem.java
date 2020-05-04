package team.teasanctuary.chemica.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import team.teasanctuary.chemica.api.EnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;
import team.teasanctuary.chemica.api.ImplementedInventory;

import java.util.List;

public class BatteryItem extends Item implements IEnergyStorageHolder {
    public static final Identifier ID = new Identifier("chemica", "battery");

    private EnergyStorage energy = new EnergyStorage(5000, true);

    public BatteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public IEnergyStorage getEnergyStorage() {
        return energy;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("chemica.tooltip.battery.stored", energy.getAmount(), energy.getCapacity()));
    }
}
