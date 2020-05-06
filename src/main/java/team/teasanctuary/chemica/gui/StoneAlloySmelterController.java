package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;
import team.teasanctuary.chemica.ClientMain;

public class StoneAlloySmelterController extends CottonCraftingController {
    public StoneAlloySmelterController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel(new TranslatableText("block.chemica.stone_alloy_smelter"));
        label.setAlignment(Alignment.CENTER);
        root.add(label, 4, 0);

        // Slots
        root.add(new WItemSlot(blockInventory, 0, 1, 1, false, true), 1, 1);
        root.add(new WItemSlot(blockInventory, 1, 1, 1, false, true), 3, 1);

        root.add(new WItemSlot(blockInventory, 2, 1, 1, false, true), 2, 3);

        WBar fireBar = new WBar(ClientMain.FIRE_BAR_BG_TEXTURE, ClientMain.FIRE_BAR_TEXTURE,0, 1);
        root.add(fireBar, 2, 2);

        root.add(new WItemSlot(blockInventory, 3, 1, 1, true, true), 7, 2);
//
        // Bars
        WBar energyBar = new WBar(ClientMain.PROGRESS_ARROW_BG_TEXTURE, ClientMain.PROGRESS_ARROW_TEXTURE, 2, 3, WBar.Direction.RIGHT);
        energyBar.withTooltip("chemica.tooltip.battery_block.energyTooltip");
        root.add(energyBar, 4, 2, 2, 1);

        root.add(createPlayerInventoryPanel(), 0, 5);

        root.validate(this);
    }
}
