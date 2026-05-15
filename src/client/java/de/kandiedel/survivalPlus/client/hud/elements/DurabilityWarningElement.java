package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class DurabilityWarningElement extends HudElement {

    @Override
    public boolean isEnabled() {
        return ModConfig.get().showDurabilityHud;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;

        ItemStack mostDamagedItem = null;
        float lowestPercentage = 1.0f;

        float warningThreshold = ModConfig.get().durabilityWarningThreshold / 100.0f;

        EquipmentSlot[] handSlots = {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
        for (EquipmentSlot slot : handSlots) {
            ItemStack stack = client.player.getEquippedStack(slot);
            if (!stack.isEmpty() && stack.isDamageable()) {
                float durabilityPercentage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();

                if (durabilityPercentage <= warningThreshold && durabilityPercentage < lowestPercentage) {
                    lowestPercentage = durabilityPercentage;
                    mostDamagedItem = stack;
                }
            }
        }

        if (mostDamagedItem != null) {
            int currentDurability = mostDamagedItem.getMaxDamage() - mostDamagedItem.getDamage();

            String text = withBold(
                    currentDurability + " / " + mostDamagedItem.getMaxDamage(),
                    ModConfig.get().durabilityTextBold
            );

            int color = ModConfig.get().durabilityTextColor | 0xFF000000;
            boolean shadow = ModConfig.get().useTextShadow;

            int textWidth = getWidth(client, text);
            int textHeight = client.textRenderer.fontHeight;

            int margin = MARGIN;
            int availableWidth = Math.max(0, context.getScaledWindowWidth() - (margin * 2));
            int anchorX = margin + Math.round(availableWidth * (ModConfig.get().durabilityHudX / 100.0f));

            int x;
            if (ModConfig.get().durabilityHudAlignment == 1) {
                x = anchorX - (textWidth / 2);
            } else if (ModConfig.get().durabilityHudAlignment == 2) {
                x = anchorX - textWidth;
            } else {
                x = anchorX;
            }

            int y = getPercentY(context, ModConfig.get().durabilityHudY, textHeight);

            x = Math.max(MARGIN, Math.min(x, context.getScaledWindowWidth() - textWidth - MARGIN));

            context.drawText(client.textRenderer, text, x, y, color, shadow);
        }
    }
}