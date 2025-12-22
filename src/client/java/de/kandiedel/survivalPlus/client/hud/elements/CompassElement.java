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

        String facing = getDirection(client.player.getYaw());

        int textWidth = getWidth(client, facing);
        int xCenter = (client.getWindow().getScaledWidth() - textWidth) / 2;
        int yAboveHotbar = client.getWindow().getScaledHeight() - 48;

        context.drawText(client.textRenderer, "§7" + facing, xCenter, yAboveHotbar, COLOR_WHITE, true);
    }

    private String getDirection(float yaw) {
        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;
        int index = Math.round(yaw / 45f) % 8;
        return DIRECTIONS[index];
    }
}