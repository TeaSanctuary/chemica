package team.teasanctuary.chemica.recipes;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.World;
import team.teasanctuary.chemica.ModMain;

import static net.minecraft.recipe.ShapedRecipe.getItemStack;

public class BeehiveOvenRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier("chemica", "beehive_oven_recipe");
    public static final BeehiveOvenRecipe.Serializer SERIALIZER = new BeehiveOvenRecipe.Serializer();

    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;
    private final int burnTime;
    private final int threshold;

    public BeehiveOvenRecipe(Identifier id, Ingredient input, ItemStack output, int burnTime, int threshold) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.burnTime = burnTime;
        this.threshold = threshold;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        return this.input.test(inv.getInvStack(0));
    }

    public int getBurnTime() { return burnTime; }

    public int getThreshold() { return threshold; }

    @Override
    public ItemStack craft(Inventory inv) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output;
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
        return ModMain.BEEHIVE_OVEN_RECIPE_TYPE;
    }

    private static class Serializer implements RecipeSerializer<BeehiveOvenRecipe> {
        Serializer() {
        }

        @Override
        public BeehiveOvenRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));
            ItemStack output = getItemStack(JsonHelper.getObject(json, "result"));
            int burnTime = JsonHelper.getInt(json, "burnTime");
            int threshold = JsonHelper.getInt(json, "threshold");
            return new BeehiveOvenRecipe(id, input, output, burnTime, threshold);
        }

        @Override
        public BeehiveOvenRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            int burnTime = buf.readVarInt();
            int threshold = buf.readVarInt();
            return new BeehiveOvenRecipe(id, input, output, burnTime, threshold);
        }

        @Override
        public void write(PacketByteBuf buf, BeehiveOvenRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
            buf.writeVarInt(recipe.burnTime);
            buf.writeVarInt(recipe.threshold);
        }
    }
}
