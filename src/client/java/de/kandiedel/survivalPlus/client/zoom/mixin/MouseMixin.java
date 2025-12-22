package de.kandiedel.survivalPlus.client.zoom.mixin;

import de.kandiedel.survivalPlus.client.zoom.ZoomManager;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (ZoomManager.isZooming()) {
            ZoomManager.onScroll(vertical);
            ci.cancel();
        }
    }

    @ModifyArg(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"), index = 0)
    private double adjustMouseSensitivityX(double original) {
        return original * ZoomManager.getMouseMultiplier();
    }

    @ModifyArg(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"), index = 1)
    private double adjustMouseSensitivityY(double original) {
        return original * ZoomManager.getMouseMultiplier();
    }
}