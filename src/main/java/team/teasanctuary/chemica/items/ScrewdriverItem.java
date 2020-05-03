package team.teasanctuary.chemica.items;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import team.teasanctuary.chemica.api.IEnergyStorage;
import team.teasanctuary.chemica.api.IEnergyStorageHolder;

public class ScrewdriverItem extends Item {
    public static final Identifier ID = new Identifier("chemica", "screwdriver");

    public ScrewdriverItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();

        BlockEntity be = context.getWorld().getBlockEntity(pos);

        if (be instanceof IEnergyStorageHolder) {
            PlayerEntity p = context.getPlayer();
            IEnergyStorage e = ((IEnergyStorageHolder) be).getEnergyStorage();
            p.addChatMessage(new LiteralText("Contains: " + e.getAmount() + "/" + e.getCapacity() + " J"), true);
        }

        return ActionResult.SUCCESS;
    }
}
