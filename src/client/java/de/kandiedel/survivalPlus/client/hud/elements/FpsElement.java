package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsElement extends HudElement {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        context.drawText(client.textRenderer, String.format("§f§lFPS: §7%d", client.getCurrentFps()), MARGIN, MARGIN, COLOR_WHITE, true);
    }
}