package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import team.teasanctuary.chemica.ClientMain;
import team.teasanctuary.chemica.ModMain;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;

public class CrusherBlockController extends SyncedGuiDescription {
    public CrusherBlockController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModMain.CRUSHER_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel(3);
        setRootPanel(root);
        //root.setSize(126, 50);

        WLabel label = new WLabel("Crusher");

        root.add(label, (root.getWidth() / 2) * 3, 0);

        WItemSlot sourceSlot = WItemSlot.of(blockInventory, 0);
        root.add(sourceSlot, 4 * 3, 5);

        WItemSlot destSlot = WItemSlot.of(blockInventory, 1);
        root.add(destSlot, root.getWidth() - (16 * 3), 5);

        WBar progressBar = new WBar((Texture) null, (Texture) null, 0, 1, WBar.Direction.RIGHT);
        progressBar.setProperties(propertyDelegate);
        root.add(progressBar, 6 * 3, 7, 17, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 14);

        root.validate(this);
    }
}
