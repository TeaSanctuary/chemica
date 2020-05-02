package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Alignment;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.PacketByteBuf;
import team.teasanctuary.chemica.ClientMain;
import team.teasanctuary.chemica.entities.CrusherBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;

public class CrusherBlockController extends CottonCraftingController {
    public CrusherBlockController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel(3);
        setRootPanel(root);
        //root.setSize(126, 50);

        WLabel label = new WLabel("Crusher");
        label.setAlignment(Alignment.CENTER);

        root.add(label, (root.getWidth() / 2) * 3, 0);

        WItemSlot sourceSlot = WItemSlot.of(blockInventory, 0);
        root.add(sourceSlot, 4 * 3, 5);

        WItemSlot destSlot = WItemSlot.of(blockInventory, 1);
        root.add(destSlot, root.getWidth() - (16 * 3), 5);

        WBar progressBar = new WBar(null, null, 0, 1, WBar.Direction.RIGHT);
        progressBar.setProperties(propertyDelegate);
        root.add(progressBar, 6 * 3, 7, 17, 1);

        WButton button = new WButton(new TranslatableText("chemica.tooltip.crusher.crush_button_text"));
        button.setOnClick(() -> {
            context.run((world, pos) -> {
                PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
                data.writeBlockPos(pos);

                ClientSidePacketRegistry.INSTANCE.sendToServer(ClientMain.CRUSHER_CRUSH_BUTTON_PACKET_ID, data);
            });
        });

        root.add(button, 0, 12);

        root.add(this.createPlayerInventoryPanel(), 0, 20);

        root.validate(this);
    }
}
