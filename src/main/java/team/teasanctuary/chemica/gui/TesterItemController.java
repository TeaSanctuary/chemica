package team.teasanctuary.chemica.gui;


import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerContext;
import team.teasanctuary.chemica.ModMain;

public class TesterItemController extends SyncedGuiDescription {
    public TesterItemController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModMain.TESTER_ITEM_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WLabel label = new WLabel("Tester");
        root.add(label, 4, 0);

        WItemSlot slot = WItemSlot.of(blockInventory, 0);
        root.add(slot, 4, 1);

        root.add(createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
}
