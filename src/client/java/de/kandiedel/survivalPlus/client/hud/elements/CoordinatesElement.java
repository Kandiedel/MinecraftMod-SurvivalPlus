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

        context.drawText(client.textRenderer, String.format("§c§lX: §7%.0f", client.player.getX()), MARGIN, MARGIN, COLOR_WHITE, true);
        context.drawText(client.textRenderer, String.format("§a§lY: §7%.0f", client.player.getY()), MARGIN, MARGIN + LINE_HEIGHT, COLOR_WHITE, true);
        context.drawText(client.textRenderer, String.format("§9§lZ: §7%.0f", client.player.getZ()), MARGIN, MARGIN + LINE_HEIGHT * 2, COLOR_WHITE, true);
    }
}