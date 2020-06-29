package team.teasanctuary.chemica;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import team.teasanctuary.chemica.blocks.BeehiveOvenControlBlock;
import team.teasanctuary.chemica.blocks.EnergyBoxBlock;
import team.teasanctuary.chemica.blocks.CrusherBlock;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.blocks.StoneAlloySmelterBlock;
import team.teasanctuary.chemica.gui.*;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.items.TesterItem;
import team.teasanctuary.chemica.registry.Blocks;

public class ClientMain implements ClientModInitializer {

    public static final Identifier FIRE_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar_bg.png");
    public static final Identifier FIRE_BAR_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar_fg.png");
    public static final Identifier ENERGY_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/energybar.png");
    public static final Identifier ENERGY_BAR_TEXTURE = new Identifier("chemica", "textures/gui/energychemica.png");
    public static final Identifier SECONDARY_PRODUCT_ARROW = new Identifier("chemica", "textures/gui/secondary_product_arrow.png");
    public static final Identifier PROGRESS_ARROW_TEXTURE = new Identifier("chemica", "textures/gui/progress_arrow_fg.png");
    public static final Identifier PROGRESS_ARROW_BG_TEXTURE = new Identifier("chemica", "textures/gui/progress_arrow_bg.png");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.CRANK_BLOCK, RenderLayer.getCutout());

        ScreenRegistry.<BeehiveOvenController, CottonInventoryScreen<BeehiveOvenController>>register(ModMain.BEEHIVE_OVEN_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<BeehiveOvenController>(gui, inventory.player, title));
        ScreenRegistry.<CrusherBlockController, CottonInventoryScreen<CrusherBlockController>>register(ModMain.CRUSHER_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<CrusherBlockController>(gui, inventory.player, title));
        ScreenRegistry.<EnergyBoxController, CottonInventoryScreen<EnergyBoxController>>register(ModMain.ENERGY_BOX_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<EnergyBoxController>(gui, inventory.player, title));
        ScreenRegistry.<SolidFuelGeneratorController, CottonInventoryScreen<SolidFuelGeneratorController>>register(ModMain.SOLID_FUEL_GENERATOR_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<SolidFuelGeneratorController>(gui, inventory.player, title));
        ScreenRegistry.<StoneAlloySmelterController, CottonInventoryScreen<StoneAlloySmelterController>>register(ModMain.STONE_ALLOY_SMELTER_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<StoneAlloySmelterController>(gui, inventory.player, title));
        ScreenRegistry.<TesterItemController, CottonInventoryScreen<TesterItemController>>register(ModMain.TESTER_ITEM_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new CottonInventoryScreen<TesterItemController>(gui, inventory.player, title));

    }
}
