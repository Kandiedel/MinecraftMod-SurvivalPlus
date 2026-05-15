package de.kandiedel.survivalPlus.client;

import de.kandiedel.survivalPlus.client.enchantment.EnchantmentManager;
import de.kandiedel.survivalPlus.client.fullbright.FullbrightManager;
import de.kandiedel.survivalPlus.client.hud.HudManager;
import de.kandiedel.survivalPlus.client.durability.DurabilityManager;
import de.kandiedel.survivalPlus.client.zoom.ZoomManager;
import de.kandiedel.survivalPlus.client.config.ConfigScreenBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class SurvivalPlusClient implements ClientModInitializer {

    public static final String MOD_ID = "survivalplus";
    public static final String CLOTH_CONFIG_ID = "cloth-config2";

    public static final KeyBinding.Category KEY_CATEGORY =
            KeyBinding.Category.create(Identifier.of(MOD_ID, "general"));

    public static KeyBinding settingsKey;

    @Override
    public void onInitializeClient() {
        HudManager.init();
        FullbrightManager.init();
        ZoomManager.init();
        EnchantmentManager.init();
        DurabilityManager.init();

        settingsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.survivalplus.settings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_BACKSLASH,
                SurvivalPlusClient.KEY_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (settingsKey.wasPressed()) {
                if (client.currentScreen == null) {
                    openSettingsScreen(client);
                }
            }
        });
    }

    private static void openSettingsScreen(MinecraftClient client) {
        if (!FabricLoader.getInstance().isModLoaded(CLOTH_CONFIG_ID)) {
            showMissingClothConfigMessage(client);
            return;
        }

        Screen configScreen = ConfigScreenBuilder.buildScreen(null);
        client.setScreen(configScreen);
    }

    private static void showMissingClothConfigMessage(MinecraftClient client) {
        if (client.player != null) {
            client.player.sendMessage(Text.translatable("msg.survivalplus.clothapi").formatted(Formatting.YELLOW), false);
        }
    }
}