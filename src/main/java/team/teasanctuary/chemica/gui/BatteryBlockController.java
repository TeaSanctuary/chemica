package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class BatteryBlockController extends CottonCraftingController {
    public BatteryBlockController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(90, 50);

        WLabel label = new WLabel("Battery Block");
        label.setAlignment(Alignment.CENTER);

        root.add(label, 2, 0);

//        WDynamicLabel label2 = new WDynamicLabel(() -> String.valueOf(propertyDelegate.get(0)));
//        label.setAlignment(Alignment.CENTER);
//
//        root.add(label2, 2, 2);

        WBar energyBar = new WBar(null,  new Identifier("chemica", "textures/fluids/redstone_still.png"), 0, 1, WBar.Direction.UP);
        energyBar.withTooltip("chemica.tooltip.battery_block.energyTooltip");
        energyBar.setProperties(propertyDelegate);

        root.add(energyBar, 2, 1, 1, 2);

        root.validate(this);
    }
}
