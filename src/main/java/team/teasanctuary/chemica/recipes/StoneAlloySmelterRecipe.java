package team.teasanctuary.chemica.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.teasanctuary.chemica.ModMain;

public class StoneAlloySmelterRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier("chemica", "stone_alloy_smelter_recipe");
    public static final Serializer SERIALIZER = new Serializer();

    private final Identifier id;
    private final ItemStack input;
    private final ItemStack input2;
    private final ItemStack output;
    private int cookTime;

    public StoneAlloySmelterRecipe(Identifier id, ItemStack input, ItemStack input2, ItemStack output, int cookTime) {
        this.id = id;
        this.input = input;
        this.input2 = input2;
        this.output = output;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(Inventory inv, World worldIn) {
        return input != null && !this.input.isEmpty() && inv.getInvStack(0).getItem() == this.input.getItem() && inv.getInvStack(0).getCount() == this.input.getCount()
                && this.input2 != null && !this.input2.isEmpty() && inv.getInvStack(1).getItem() == this.input2.getItem() && inv.getInvStack(1).getCount() == this.input2.getCount();
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.output.copy();
    }

    public ItemStack getInput() { return input; }
    public ItemStack getInput2() { return input2; }
    public int getCookTime() { return cookTime; }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModMain.STONE_ALLOY_SMELTER_RECIPE;
    }

    private static class Serializer implements RecipeSerializer<StoneAlloySmelterRecipe> {
        Serializer() {
        }

        @Override
        public StoneAlloySmelterRecipe read(Identifier id, JsonObject json) {
            ItemStack input = getItemStack(JsonHelper.getObject(json, "ingredient"));
            ItemStack input2 = getItemStack(JsonHelper.getObject(json, "ingredient2"));
            ItemStack output = getItemStack(JsonHelper.getObject(json, "result"));
            int cookTime = JsonHelper.getInt(json, "cookTime");
            return new StoneAlloySmelterRecipe(id, input, input2, output, cookTime);
        }

        @Override
        public StoneAlloySmelterRecipe read(Identifier id, PacketByteBuf buf) {
            ItemStack input = buf.readItemStack();
            ItemStack input2 = buf.readItemStack();
            ItemStack output = buf.readItemStack();
            int cookTime = buf.readVarInt();
            return new StoneAlloySmelterRecipe(id, input, input2, output, cookTime);
        }

        @Override
        public void write(PacketByteBuf buf, StoneAlloySmelterRecipe recipe) {
            buf.writeItemStack(recipe.input);
            buf.writeItemStack(recipe.input2);
            buf.writeItemStack(recipe.output);
            buf.writeVarInt(recipe.cookTime);
        }

        public static ItemStack getItemStack(JsonObject json) {
            String string = JsonHelper.getString(json, "item");
            Item item = Registry.ITEM.getOrEmpty(new Identifier(string)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + string + "'"));
            if (json.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            } else {
                int i = JsonHelper.getInt(json, "count", 1);
                return new ItemStack(item, i);
            }
        }

    }

}
