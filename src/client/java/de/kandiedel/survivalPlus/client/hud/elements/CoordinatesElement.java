package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordinatesElement extends HudElement {
    @Override
    public boolean isEnabled() {
        return ModConfig.get().showCoordinates;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;

        int valueColor = ModConfig.get().textColor | 0xFF000000;
        int xLabelColor = ModConfig.get().xLabelColor | 0xFF000000;
        int yLabelColor = ModConfig.get().yLabelColor | 0xFF000000;
        int zLabelColor = ModConfig.get().zLabelColor | 0xFF000000;
        boolean shadow = ModConfig.get().useTextShadow;

        String xValue;
        String yValue;
        String zValue;

        if (ModConfig.get().showCoordinateDecimals) {
            xValue = String.format("%.2f", client.player.getX());
            yValue = String.format("%.2f", client.player.getY());
            zValue = String.format("%.2f", client.player.getZ());
        } else {
            xValue = String.valueOf(client.player.getBlockPos().getX());
            yValue = String.valueOf(client.player.getBlockPos().getY());
            zValue = String.valueOf(client.player.getBlockPos().getZ());
        }

        String xLabel = withBold("X: ", ModConfig.get().coordinatesLabelBold);
        String yLabel = withBold("Y: ", ModConfig.get().coordinatesLabelBold);
        String zLabel = withBold("Z: ", ModConfig.get().coordinatesLabelBold);

        xValue = withBold(xValue, ModConfig.get().valueTextBold);
        yValue = withBold(yValue, ModConfig.get().valueTextBold);
        zValue = withBold(zValue, ModConfig.get().valueTextBold);

        int xLineWidth = getWidth(client, xLabel + xValue);
        int yLineWidth = getWidth(client, yLabel + yValue);
        int zLineWidth = getWidth(client, zLabel + zValue);

        int elementWidth = Math.max(xLineWidth, Math.max(yLineWidth, zLineWidth));
        int elementHeight = (int)(LINE_HEIGHT * 2.25) + client.textRenderer.fontHeight;

        int x = getPercentX(context, ModConfig.get().coordinatesX, elementWidth);
        int y = getPercentY(context, ModConfig.get().coordinatesY, elementHeight);

        int secondLineY = y + (int)(LINE_HEIGHT * 1.25);
        int thirdLineY = y + (int)(LINE_HEIGHT * 2.25);

        context.drawText(client.textRenderer, xLabel, x, y, xLabelColor, shadow);
        context.drawText(client.textRenderer, xValue, x + getWidth(client, xLabel), y, valueColor, shadow);

        context.drawText(client.textRenderer, yLabel, x, secondLineY, yLabelColor, shadow);
        context.drawText(client.textRenderer, yValue, x + getWidth(client, yLabel), secondLineY, valueColor, shadow);

        context.drawText(client.textRenderer, zLabel, x, thirdLineY, zLabelColor, shadow);
        context.drawText(client.textRenderer, zValue, x + getWidth(client, zLabel), thirdLineY, valueColor, shadow);
    }
}