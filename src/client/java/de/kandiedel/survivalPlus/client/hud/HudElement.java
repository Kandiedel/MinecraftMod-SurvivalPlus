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

    protected int getPercentX(DrawContext context, int percent, int elementWidth) {
        int margin = MARGIN;
        int availableWidth = Math.max(0, context.getScaledWindowWidth() - elementWidth - (margin * 2));
        return margin + Math.round(availableWidth * (percent / 100.0f));
    }

    protected int getPercentY(DrawContext context, int percent, int elementHeight) {
        int margin = MARGIN;
        int availableHeight = Math.max(0, context.getScaledWindowHeight() - elementHeight - (margin * 2));
        return margin + Math.round(availableHeight * (percent / 100.0f));
    }

    protected String withBold(String text, boolean bold) {
        return bold ? "§l" + text + "§r" : text;
    }
}