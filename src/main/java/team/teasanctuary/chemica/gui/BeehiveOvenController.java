package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.WToggle;

public class BeehiveOvenController extends CottonCraftingController {
    public BeehiveOvenController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(ModMain.BEEHIVE_OVEN_RECIPE_TYPE, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel(9);
        setRootPanel(root);

        root.add(WItemSlot.of(blockInventory, 0), 4, 4);
        root.add(WItemSlot.outputOf(blockInventory, 1), 12, 4);

        WBar tempBar = new WBar(new Identifier("chemica", "textures/gui/temperature_bg.png"),
                new Identifier("chemica", "textures/gui/temperature_fg.png"),1, 2, WBar.Direction.UP);
        tempBar.withTooltip("chemica.tooltip.beehive_oven.temperatureTooltip");
        root.add(tempBar, 16, 0, 1, 8);

        WBar progressBar = new WBar(new Identifier("chemica", "textures/gui/progress_arrow_bg.png"),
                new Identifier("chemica", "textures/gui/progress_arrow_fg.png"),
                3, 4, WBar.Direction.RIGHT);
        root.add(progressBar, 6, 4, 5, 2);

        root.add(new WToggle(new Identifier("chemica", "textures/gui/toggle.png"), 32, 16, 16, 0), 0, 8);

        root.add(createPlayerInventoryPanel(), 0, 10);

        root.validate(this);
    }
}
