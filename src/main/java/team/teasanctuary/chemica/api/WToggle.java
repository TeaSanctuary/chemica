package team.teasanctuary.chemica.api;

import blue.endless.jankson.annotation.Nullable;
import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.util.Identifier;

public class WToggle extends WWidget {
    protected PropertyDelegate properties;
    private final Identifier imageID;
    private final int elementWidth, elementHeight;
    private final float uvOffsetX;

    private final int state;

    public WToggle(Identifier imageID, int textureWidth, int elementWidth, int elementHeight, int initialState) {
        this.imageID = imageID;
        this.elementWidth = elementWidth;
        this.elementHeight = elementHeight;
        this.uvOffsetX = (float) elementWidth / textureWidth;
        this.state = initialState;
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
    public void paintBackground(int x, int y) {
        ScreenDrawing.texturedRect(x, y, elementWidth, elementHeight, imageID, uvOffsetX * (getState() ? 1 : 0), .0f, uvOffsetX * (getState() ? 2 : 1), 1.f, -1);
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
}