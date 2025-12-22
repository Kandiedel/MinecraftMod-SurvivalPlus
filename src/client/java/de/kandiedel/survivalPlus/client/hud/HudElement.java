package de.kandiedel.survivalPlus.client.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class HudElement {
    protected static final int MARGIN = 5;
    protected static final int COLOR_WHITE = 0xFFFFFFFF;
    protected static final int LINE_HEIGHT = 10;

    public abstract boolean isEnabled();

    public abstract void render(DrawContext context, MinecraftClient client);

    protected int getWidth(MinecraftClient client, String text) {
        return client.textRenderer.getWidth(text);
    }
}