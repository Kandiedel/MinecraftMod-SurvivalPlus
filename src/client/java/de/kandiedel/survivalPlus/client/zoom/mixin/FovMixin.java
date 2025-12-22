package de.kandiedel.survivalPlus.client.zoom.mixin;

import de.kandiedel.survivalPlus.client.zoom.ZoomManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class FovMixin {

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void modifyFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        float originalFov = cir.getReturnValue();

        double newFov = ZoomManager.changeFov(originalFov, tickDelta);

        cir.setReturnValue((float) newFov);
    }
}