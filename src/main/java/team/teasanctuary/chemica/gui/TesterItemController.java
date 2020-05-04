package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;

public class TesterItemController extends CottonCraftingController {
    public TesterItemController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel("Tester");
        label.setAlignment(Alignment.CENTER);
        root.add(label, 4, 0);

        WItemSlot slot = WItemSlot.of(blockInventory, 0);
        root.add(slot, 4, 1);

        root.add(createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
}
