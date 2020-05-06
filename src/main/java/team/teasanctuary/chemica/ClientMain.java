package team.teasanctuary.chemica;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import team.teasanctuary.chemica.blocks.BeehiveOvenControlBlock;
import team.teasanctuary.chemica.blocks.EnergyBoxBlock;
import team.teasanctuary.chemica.blocks.CrusherBlock;
import team.teasanctuary.chemica.blocks.SolidFuelGeneratorBlock;
import team.teasanctuary.chemica.gui.EnergyBoxController;
import team.teasanctuary.chemica.gui.CrusherBlockController;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.util.Identifier;
import team.teasanctuary.chemica.gui.SolidFuelGeneratorController;
import team.teasanctuary.chemica.gui.TesterItemController;
import team.teasanctuary.chemica.gui.BeehiveOvenController;
import team.teasanctuary.chemica.items.TesterItem;
import team.teasanctuary.chemica.registry.Blocks;

public class ClientMain implements ClientModInitializer {

    public static final Identifier FIRE_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar_bg.png");
    public static final Identifier FIRE_BAR_TEXTURE = new Identifier("chemica", "textures/gui/fire_bar.png");
    public static final Identifier ENERGY_BAR_BG_TEXTURE = new Identifier("chemica", "textures/gui/energybar.png");
    public static final Identifier ENERGY_BAR_TEXTURE = new Identifier("chemica", "textures/gui/energychemica.png");
    public static final Identifier SECONDARY_PRODUCT_ARROW = new Identifier("chemica", "textures/gui/secondary_product_arrow.png");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.CRANK_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.CONDUIT_BLOCK, RenderLayer.getCutout());

        ScreenProviderRegistry.INSTANCE.registerFactory(TesterItem.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<TesterItemController>(new TesterItemController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(EnergyBoxBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<EnergyBoxController>(new EnergyBoxController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(CrusherBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<CrusherBlockController>(new CrusherBlockController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(SolidFuelGeneratorBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<SolidFuelGeneratorController>(new SolidFuelGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(BeehiveOvenControlBlock.ID, (syncId, identifier, player, buf) -> new CottonInventoryScreen<BeehiveOvenController>(new BeehiveOvenController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
    }
}
