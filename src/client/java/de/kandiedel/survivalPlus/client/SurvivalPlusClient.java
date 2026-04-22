package de.kandiedel.survivalPlus.client;

import de.kandiedel.survivalPlus.client.enchantment.EnchantmentManager;
import de.kandiedel.survivalPlus.client.fullbright.FullbrightManager;
import de.kandiedel.survivalPlus.client.hud.HudManager;
import de.kandiedel.survivalPlus.client.zoom.ZoomManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class SurvivalPlusClient implements ClientModInitializer {

    public static final KeyBinding.Category KEY_CATEGORY = KeyBinding.Category.create(Identifier.of("survivalplus", "general"));

    @Override
    public void onInitializeClient() {
        HudManager.init();
        FullbrightManager.init();
        ZoomManager.init();
        EnchantmentManager.init();
    }
}
