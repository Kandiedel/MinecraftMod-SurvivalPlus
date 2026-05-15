package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CompassElement extends HudElement {

    private static final String[] DIRECTIONS = {"S", "SW", "W", "NW", "N", "NE", "E", "SE"};

    @Override
    public boolean isEnabled() {
        return ModConfig.get().showCompass;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;

        int color = ModConfig.get().textColor | 0xFF000000;
        boolean shadow = ModConfig.get().useTextShadow;

        String facing = withBold(getDirection(client.player.getYaw()), ModConfig.get().valueTextBold);

        int textWidth = getWidth(client, facing);
        int textHeight = client.textRenderer.fontHeight;

        int x = getPercentX(context, ModConfig.get().compassX, textWidth);
        int y = getPercentY(context, ModConfig.get().compassY, textHeight);

        context.drawText(client.textRenderer, facing, x, y, color, shadow);
    }

    private String getDirection(float yaw) {
        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;
        int index = Math.round(yaw / 45f) % 8;
        return DIRECTIONS[index];
    }
}