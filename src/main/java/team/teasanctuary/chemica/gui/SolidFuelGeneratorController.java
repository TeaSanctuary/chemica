package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.TranslatableText;
import team.teasanctuary.chemica.ClientMain;
import team.teasanctuary.chemica.ModMain;

public class SolidFuelGeneratorController extends SyncedGuiDescription {
    public SolidFuelGeneratorController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModMain.SOLID_FUEL_GENERATOR_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel(new TranslatableText("block.chemica.solid_fuel_generator"));
        root.add(label, 4, 0);

        // Slots
        root.add(new WItemSlot(blockInventory, 0, 1, 1, false), 2, 1);
        WSprite sprite = new WSprite(ClientMain.SECONDARY_PRODUCT_ARROW);
        root.add(sprite, 2, 2);
        root.add(new WItemSlot(blockInventory, 1, 1, 1, false), 2, 3);

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
