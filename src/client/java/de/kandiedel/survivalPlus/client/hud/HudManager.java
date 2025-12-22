package de.kandiedel.survivalPlus.client.hud;

import de.kandiedel.survivalPlus.client.hud.elements.BiomeElement;
import de.kandiedel.survivalPlus.client.hud.elements.CompassElement;
import de.kandiedel.survivalPlus.client.hud.elements.CoordinatesElement;
import de.kandiedel.survivalPlus.client.hud.elements.TargetBlockElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    private static final List<HudElement> elements = new ArrayList<>();
    private static KeyBinding toggleKey;

    public static void init() {
        ModConfig.load();

        elements.add(new CoordinatesElement());
        elements.add(new BiomeElement());
        elements.add(new TargetBlockElement());
        elements.add(new CompassElement());

        registerKeyBinding();

        HudRenderCallback.EVENT.register(HudManager::render);
    }

    private static void registerKeyBinding() {
        toggleKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.survivalplus.toggle_hud",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_O,
                        KeyBinding.Category.create(Identifier.of("survivalplus", "general"))
                )
        );
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.getDebugHud().shouldShowDebugHud()) return;

        handleInput(client);

        if (!ModConfig.get().isHudEnabled) return;
        if (client.player == null || client.world == null) return;

        for (HudElement element : elements) {
            if (element.isEnabled()) {
                element.render(context, client);
            }
        }
    }

    private static void handleInput(MinecraftClient client) {
        while (toggleKey.wasPressed()) {
            ModConfig.get().isHudEnabled = !ModConfig.get().isHudEnabled;
            ModConfig.save();

            if (client.player != null) {
                Text status = ModConfig.get().isHudEnabled
                        ? Text.translatable("msg.survivalplus.enabled")
                        : Text.translatable("msg.survivalplus.disabled");
                client.player.sendMessage(status, true);
            }
        }
    }
}