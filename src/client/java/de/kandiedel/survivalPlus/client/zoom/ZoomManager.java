package de.kandiedel.survivalPlus.client.zoom;

import de.kandiedel.survivalPlus.client.SurvivalPlusClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class ZoomManager {

    public static boolean isZooming = false;
    private static KeyBinding zoomKey;

    private static float currentZoomLevel = 1.0f;
    private static float lastTickZoomLevel = 1.0f;
    private static float targetZoomLevel = 1.0f;

    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 50.0f;
    private static final float SCROLL_SENSITIVITY = 1.2f;

    public static void init() {
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.survivalplus.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                SurvivalPlusClient.KEY_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            lastTickZoomLevel = currentZoomLevel;

            boolean wasZooming = isZooming;
            isZooming = zoomKey.isPressed();

            if (isZooming) {
                if (!wasZooming) {
                    targetZoomLevel = 4.0f;
                }
            } else {
                targetZoomLevel = 1.0f;
            }

            float speed = (currentZoomLevel > targetZoomLevel) ? 0.6f : 0.125f;

            currentZoomLevel = MathHelper.lerp(speed, currentZoomLevel, targetZoomLevel);

            if (Math.abs(currentZoomLevel - targetZoomLevel) < 0.001f) {
                currentZoomLevel = targetZoomLevel;
            }
        });
    }

    public static double getMouseMultiplier() {
        return 1.0 / currentZoomLevel;
    }

    public static void onScroll(double amount) {
        if (!isZooming) return;

        if (amount > 0) {
            targetZoomLevel *= SCROLL_SENSITIVITY;
        } else if (amount < 0) {
            targetZoomLevel /= SCROLL_SENSITIVITY;
        }

        targetZoomLevel = MathHelper.clamp(targetZoomLevel, MIN_ZOOM, MAX_ZOOM);
    }

    public static double changeFov(double fov, float tickDelta) {
        float interpolatedZoom = MathHelper.lerp(tickDelta, lastTickZoomLevel, currentZoomLevel);

        if (interpolatedZoom <= 1.0f) return fov;
        return fov / interpolatedZoom;
    }

    public static boolean isZooming() {
        return isZooming;
    }
}