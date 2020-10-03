package team.teasanctuary.chemica.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class BeehiveOvenGuiScreen extends CottonInventoryScreen<BeehiveOvenGuiDescription> {
    public BeehiveOvenGuiScreen(BeehiveOvenGuiDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
