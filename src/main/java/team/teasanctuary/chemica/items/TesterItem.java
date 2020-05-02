package team.teasanctuary.chemica.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class TesterItem extends Item {
    public static final Identifier ID = new Identifier("chemica", "tester");

    public TesterItem(Settings settings) {
        super(settings);
    }
}
