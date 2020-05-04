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

public class GeneratorRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier("chemica", "generator_recipe");
    public static final GeneratorRecipe.Serializer SERIALIZER = new GeneratorRecipe.Serializer();

    private final Identifier id;
    private final Ingredient input;
    private final int burnTime;

    public GeneratorRecipe(Identifier id, Ingredient input, int output) {
        this.id = id;
        this.input = input;
        this.burnTime = output;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        return this.input.test(inv.getInvStack(0));
    }

    public int getBurnTime() { return burnTime; }

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
        return ItemStack.EMPTY;
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
        return ModMain.GENERATOR_RECIPE;
    }

    private static class Serializer implements RecipeSerializer<GeneratorRecipe> {
        Serializer() {
        }

        @Override
        public GeneratorRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));
            int output = JsonHelper.getInt(json, "burnTime");
            return new GeneratorRecipe(id, input, output);
        }

        @Override
        public GeneratorRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            int output = buf.readInt();
            return new GeneratorRecipe(id, input, output);
        }

        @Override
        public void write(PacketByteBuf buf, GeneratorRecipe recipe) {
            recipe.input.write(buf);
            buf.writeInt(recipe.burnTime);
        }
    }

}
