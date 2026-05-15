package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsElement extends HudElement {

    @Override
    public boolean isEnabled() {
        return ModConfig.get().showFps;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        int valueColor = ModConfig.get().textColor | 0xFF000000;
        int labelColor = ModConfig.get().fpsLabelColor | 0xFF000000;
        boolean shadow = ModConfig.get().useTextShadow;

        String labelText = withBold("FPS: ", ModConfig.get().fpsLabelBold);
        String valueText = withBold(String.valueOf(client.getCurrentFps()), ModConfig.get().valueTextBold);
        String fullText = labelText + valueText;

        int textWidth = getWidth(client, fullText);
        int textHeight = client.textRenderer.fontHeight;

        int x = getPercentX(context, ModConfig.get().fpsX, textWidth);
        int y = getPercentY(context, ModConfig.get().fpsY, textHeight);

        context.drawText(client.textRenderer, labelText, x, y, labelColor, shadow);
        context.drawText(client.textRenderer, valueText, x + getWidth(client, labelText), y, valueColor, shadow);
    }
}