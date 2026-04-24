package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class DurabilityWarningElement extends HudElement {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;

        ItemStack mostDamagedItem = null;
        float lowestPercentage = 1.0f;

        EquipmentSlot[] handSlots = {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
        for (EquipmentSlot slot : handSlots) {
            ItemStack stack = client.player.getEquippedStack(slot);
            if (!stack.isEmpty() && stack.isDamageable()) {
                float durabilityPercentage = (float) (stack.getMaxDamage() - stack.getDamage()) / stack.getMaxDamage();
                if (durabilityPercentage <= 0.10f && durabilityPercentage < lowestPercentage) {
                    lowestPercentage = durabilityPercentage;
                    mostDamagedItem = stack;
                }
            }
        }

        if (mostDamagedItem != null) {
            int currentDurability = mostDamagedItem.getMaxDamage() - mostDamagedItem.getDamage();
            String text = currentDurability + " / " + mostDamagedItem.getMaxDamage();

            int x = (context.getScaledWindowWidth() / 2) + 91 + 7;

            int y = client.getWindow().getScaledHeight() - MARGIN - client.textRenderer.fontHeight;

            context.drawText(client.textRenderer, text, x, y, 0xFFFFFF00, true);
        }
    }
}