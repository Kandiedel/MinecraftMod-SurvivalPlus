package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class BiomeElement extends HudElement {
    @Override
    public boolean isEnabled() {
        return ModConfig.get().showBiome;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;

        int valueColor = ModConfig.get().textColor | 0xFF000000;
        int labelColor = ModConfig.get().biomeLabelColor | 0xFF000000;
        boolean shadow = ModConfig.get().useTextShadow;

        String biomeName = client.player.getEntityWorld()
                .getBiome(client.player.getBlockPos())
                .getKey()
                .map(key -> formatBiomeName(key.getValue().getPath()))
                .orElse("Unknown");

        String labelText = withBold("Biome: ", ModConfig.get().biomeLabelBold);
        String valueText = withBold(biomeName, ModConfig.get().valueTextBold);
        String fullText = labelText + valueText;

        int textWidth = getWidth(client, fullText);
        int textHeight = client.textRenderer.fontHeight;

        int x = getPercentX(context, ModConfig.get().biomeX, textWidth);
        int y = getPercentY(context, ModConfig.get().biomeY, textHeight);

        context.drawText(client.textRenderer, labelText, x, y, labelColor, shadow);
        context.drawText(client.textRenderer, valueText, x + getWidth(client, labelText), y, valueColor, shadow);
    }

    private String formatBiomeName(String path) {
        String[] words = path.split("_");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1))
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }
}