package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.api.WToggle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BeehiveOvenGuiDescription extends SyncedGuiDescription {

    public static final int PROPERTY_DELEGATE_SIZE = 5;

    public BeehiveOvenGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModMain.BEEHIVE_OVEN_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context, PROPERTY_DELEGATE_SIZE));

        WGridPanel root = new WGridPanel(1);
        setRootPanel(root);

        root.add(WItemSlot.of(blockInventory, 0), 36, 36);
        root.add(WItemSlot.outputOf(blockInventory, 1), 108, 36);

        WToggle completeToggle = new WToggle(new Identifier("chemica", "textures/gui/toggle.png"), 32, 16, 16, 0);
        completeToggle.withTooltips("chemica.tooltip.beehive_oven.not_complete", "chemica.tooltip.beehive_oven.complete");
        root.add(completeToggle, 0, 72);

        WBar tempBar = new WBar(new Identifier("chemica", "textures/gui/temperature_bg.png"),
                new Identifier("chemica", "textures/gui/temperature_fg.png"),1, 2, WBar.Direction.UP);
        tempBar.withTooltip("chemica.tooltip.beehive_oven.temperatureTooltip");
        root.add(tempBar, 152, 12, 9, 66);

        WBar progressBar = new WBar(new Identifier("chemica", "textures/gui/fire_bar_bg.png"),
                new Identifier("chemica", "textures/gui/fire_bar_fg.png"),
                3, 4, WBar.Direction.UP);
        root.add(progressBar, 72, 36, 18, 18);


        root.add(createPlayerInventoryPanel(), 0, 90);

        root.validate(this);
    }
}
