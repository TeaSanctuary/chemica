package team.teasanctuary.chemica.api;

import blue.endless.jankson.annotation.Nullable;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WToggle extends WWidget {
    protected PropertyDelegate properties;
    private final Identifier imageID;
    private final int elementWidth, elementHeight;
    private final float uvOffsetX;
    private String[] toggleTooltip = new String[2];

    private final int state;

    public WToggle(Identifier imageID, int textureWidth, int elementWidth, int elementHeight, int state) {
        this.imageID = imageID;
        this.elementWidth = elementWidth;
        this.elementHeight = elementHeight;
        this.uvOffsetX = (float) elementWidth / textureWidth;
        this.state = state;
    }

    public boolean canResize() {
        return false;
    }

    public int getWidth() {
        return elementWidth;
    }

    public int getHeight() {
        return elementHeight;
    }

    public boolean getState() {
        return this.properties.get(state) == 1;
    }

    @Environment(EnvType.CLIENT)
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        boolean st = getState();
        ScreenDrawing.texturedRect(x, y, elementWidth, elementHeight, imageID, uvOffsetX * (st ? 1 : 0), .0f, uvOffsetX * (st ? 2 : 1), 1.f, -1);
    }

    public void createPeers(GuiDescription c) {
        if (this.properties == null) {
            this.properties = c.getPropertyDelegate();
        }

    }

    @Nullable
    public PropertyDelegate getProperties() {
        return this.properties;
    }

    public WToggle setProperties(PropertyDelegate properties) {
        this.properties = properties;
        return this;
    }

    public WToggle withTooltips(String falseTooltip, String trueTooltip) {
        this.toggleTooltip = new String[]{falseTooltip, trueTooltip};
        return this;
    }

    @Override
    public void addTooltip(TooltipBuilder builder) {
        String tooltipLabel = this.toggleTooltip[(getState()) ? 1 : 0];
        if (tooltipLabel == null)
            return;

        String translated = I18n.translate(tooltipLabel);

        builder.add(Text.of(translated));
    }


}