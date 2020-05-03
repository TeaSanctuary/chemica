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

public class CrusherRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier("chemica", "crusher_recipe");
    public static final Serializer SERIALIZER = new Serializer();

    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;
    private final int ticks;

    public CrusherRecipe(Identifier id, Ingredient input, ItemStack output, int ticks) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.ticks = ticks;
    }

    @Override
    public String toString() {
        return "CrusherRecipe [input=" + this.input + ", output=" + this.output + ", id=" + this.id + ", ticks=" + this.ticks + "]";
    }

    @Override
    public boolean matches(Inventory inv, World worldIn) {
        return this.input.test(inv.getInvStack(0));
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.output.copy();
    }

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

    public int getTicks() {
        return this.ticks;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModMain.CRUSHER_RECIPE;
    }

    private static class Serializer implements RecipeSerializer<CrusherRecipe> {
        Serializer() {
        }

        @Override
        public CrusherRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));
            ItemStack output = getItemStack(JsonHelper.getObject(json, "result"));
            int ticks = JsonHelper.getInt(json, "ticks");
            return new CrusherRecipe(id, input, output, ticks);
        }

        @Override
        public CrusherRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            int ticks = buf.readVarInt();
            return new CrusherRecipe(id, input, output, ticks);
        }

        @Override
        public void write(PacketByteBuf buf, CrusherRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
            buf.writeVarInt(recipe.ticks);
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
