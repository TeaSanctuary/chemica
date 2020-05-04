package team.teasanctuary.chemica;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import team.teasanctuary.chemica.blocks.EnergyBoxBlock;
import team.teasanctuary.chemica.blocks.CrusherBlock;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.gui.BatteryBlockController;
import team.teasanctuary.chemica.gui.CrusherBlockController;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.gui.SolidFuelGeneratorController;
import team.teasanctuary.chemica.gui.TesterItemController;
import team.teasanctuary.chemica.items.TesterItem;

public class ClientMain implements ClientModInitializer {

    public static final Identifier FIRE_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar_bg.png");
    public static final Identifier FIRE_BAR_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar.png");
    public static final Identifier ENERGY_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/energybar.png");
    public static final Identifier ENERGY_BAR_TEXTURE = new Identifier("chemica", "textures/gui/energychemica.png");
    public static final Identifier SECONDARY_PRODUCT_ARROW = new Identifier("chemica", "textures/gui/secondary_product_arrow.png");
    public static final Identifier CRUSHER_CRUSH_BUTTON_PACKET_ID = new Identifier("chemica", "crusher_crush_button_pressed");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModMain.CRANK_BLOCK, RenderLayer.getCutout());

        ScreenProviderRegistry.INSTANCE.registerFactory(TesterItem.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<TesterItemController>(new TesterItemController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(EnergyBoxBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<BatteryBlockController>(new BatteryBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(CrusherBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<CrusherBlockController>(new CrusherBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(SolidFuelGeneratorBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<SolidFuelGeneratorController>(new SolidFuelGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
    }
}
