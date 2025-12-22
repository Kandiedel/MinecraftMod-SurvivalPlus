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

        int biomeY = client.getWindow().getScaledHeight() - MARGIN - client.textRenderer.fontHeight;

        String biomeName = client.player.getEntityWorld()
                .getBiome(client.player.getBlockPos())
                .getKey()
                .map(key -> formatBiomeName(key.getValue().getPath()))
                .orElse("Unknown");

        Text label = Text.translatable("hud.survivalplus.biome").append("§7" + biomeName);

        context.drawText(client.textRenderer, "§e§l" + label.getString(), MARGIN, biomeY, COLOR_WHITE, true);
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