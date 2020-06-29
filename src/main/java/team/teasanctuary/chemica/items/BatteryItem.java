package team.teasanctuary.chemica.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import team.teasanctuary.chemica.api.*;

import java.util.List;

public class BatteryItem extends Item implements IItemEnergyStorageHolder {
    public static final Identifier ID = new Identifier("chemica", "battery");
    public BatteryItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        BatteryItem item = (BatteryItem) stack.getItem();
        IEnergyStorage e = item.getEnergyStorage(stack);
        int amount = e.getAmount();
        int capacity = e.getCapacity();
        tooltip.add(new TranslatableText("chemica.tooltip.battery.stored", amount, capacity));
    }

    @Override
    public IEnergyStorage getEnergyStorage(ItemStack stack) {
        EnergyStorage energy = new EnergyStorage(5000, true);
        Runnable listener = () -> {
            energy.saveToNBT(stack.getOrCreateTag());
        };

        if (!stack.hasTag() || !stack.getTag().contains("energy")) listener.run();
        energy.writeFromNBT(stack.getTag());
        return energy;
    }
}
