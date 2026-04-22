package de.kandiedel.survivalPlus.client.enchantment;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class EnchantmentManager {

    public static void init() {
        registerEnchantmentColorModifier();
    }

    private static void registerEnchantmentColorModifier() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, lines) -> {

            ItemEnchantmentsComponent enchantments = itemStack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

            if (enchantments.isEmpty()) return;

            boolean headerAdded = false;

            for (int i = 0; i < lines.size(); i++) {
                Text line = lines.get(i);
                String lineString = line.getString();

                for (var entry : enchantments.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    int level = entry.getIntValue();
                    int maxLevel = enchantment.value().getMaxLevel();

                    Text enchantmentText = Enchantment.getName(enchantment, level);

                    if (lineString.equals(enchantmentText.getString())) {

                        if (!headerAdded) {
                            lines.add(i, Text.literal("Enchantments:").formatted(Formatting.GRAY));
                            headerAdded = true;
                            i += 1;
                        }

                        MutableText coloredText;
                        if (level >= maxLevel) {
                            coloredText = Text.literal("  ").append(enchantmentText.copy().formatted(Formatting.BOLD, Formatting.GOLD));
                        } else {
                            coloredText = Text.literal("  ").append(enchantmentText.copy().formatted(Formatting.LIGHT_PURPLE));
                        }

                        lines.set(i, coloredText);

                        break;
                    }
                }
            }
        });
    }
}