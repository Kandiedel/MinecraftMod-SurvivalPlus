package de.kandiedel.survivalPlus.client.fullbright;

import de.kandiedel.survivalPlus.client.SurvivalPlusClient;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class FullbrightManager {

    private static KeyBinding toggleKey;

    public static void init() {
        registerKeyBinding();
        ClientTickEvents.END_CLIENT_TICK.register(FullbrightManager::onTick);
    }

    private static void registerKeyBinding() {
        toggleKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.survivalplus.toggle_gamma",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_H,
                        SurvivalPlusClient.KEY_CATEGORY
                )
        );
    }

    private static void onTick(MinecraftClient client) {
        if (client.player == null) return;

        while (toggleKey.wasPressed()) {
            toggleFullbright(client);
        }

        if (ModConfig.get().isFullbrightEnabled) {
            client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false, false));
        }
    }

    private static void toggleFullbright(MinecraftClient client) {
        boolean newState = !ModConfig.get().isFullbrightEnabled;
        ModConfig.get().isFullbrightEnabled = newState;
        ModConfig.save();

        if (newState) {
            client.player.sendMessage(Text.translatable("msg.survivalplus.gamma_enabled"), true);
        } else {
            client.player.sendMessage(Text.translatable("msg.survivalplus.gamma_disabled"), true);
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}