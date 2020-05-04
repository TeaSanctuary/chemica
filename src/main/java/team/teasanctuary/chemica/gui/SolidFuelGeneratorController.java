package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.minecraft.container.BlockContext;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.TranslatableText;
import team.teasanctuary.chemica.ClientMain;

public class SolidFuelGeneratorController extends CottonCraftingController {
    public SolidFuelGeneratorController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel(new TranslatableText("block.chemica.solid_fuel_generator"));
        label.setAlignment(Alignment.CENTER);
        root.add(label, 4, 0);

        // Slots
        root.add(new WItemSlot(blockInventory, 0, 1, 1, false, true), 2, 1);
        WSprite sprite = new WSprite(ClientMain.SECONDARY_PRODUCT_ARROW);
        root.add(sprite, 2, 2);
        root.add(new WItemSlot(blockInventory, 1, 1, 1, false, false), 2, 3);

        WBar fireBar = new WBar(ClientMain.FIRE_BAR_BG_TEXTURE, ClientMain.FIRE_BAR_TEXTURE,2, 3);
        root.add(fireBar, 4, 2);

        // Bars
        WBar energyBar = new WBar(ClientMain.ENERGY_BAR_BG_TEXTURE, ClientMain.ENERGY_BAR_TEXTURE, 0, 1, WBar.Direction.UP);
        energyBar.withTooltip("chemica.tooltip.battery_block.energyTooltip");
        root.add(energyBar, 6, 1, 1, 3);

        root.add(createPlayerInventoryPanel(), 0, 5);

        root.validate(this);
    }
}
