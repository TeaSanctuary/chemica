package team.teasanctuary.chemica.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import team.teasanctuary.chemica.registry.Items;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceFuelMixin {

    @ModifyVariable(at = @At("RETURN"), method = "createFuelTimeMap")
    private static Map<Item, Integer> createFuelTimeMap(Map<Item, Integer> map) {
        map.put(Items.COKE_ITEM, 8000);
        return map;
    }

}
