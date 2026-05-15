package de.kandiedel.survivalPlus.client.hud.elements;

import de.kandiedel.survivalPlus.client.hud.HudElement;
import de.kandiedel.survivalPlus.config.ModConfig;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class TargetBlockElement extends HudElement {
    @Override
    public boolean isEnabled() {
        return ModConfig.get().showTargetBlock;
    }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        HitResult hit = client.crosshairTarget;
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockState state = client.world.getBlockState(blockHit.getBlockPos());

        int color = ModConfig.get().textColor | 0xFF000000;
        boolean shadow = ModConfig.get().useTextShadow;

        String nameText = withBold(state.getBlock().getName().getString(), ModConfig.get().valueTextBold);
        String coordText = withBold(String.format("%d %d %d",
                blockHit.getBlockPos().getX(),
                blockHit.getBlockPos().getY(),
                blockHit.getBlockPos().getZ()
        ), ModConfig.get().valueTextBold);

        float scale = 0.75f;
        int maxWidth = Math.max(getWidth(client, nameText), getWidth(client, coordText));

        int elementWidth = Math.round(maxWidth * scale);
        int elementHeight = Math.round((client.textRenderer.fontHeight * 2) * scale);

        float x = getPercentX(context, ModConfig.get().targetBlockX, elementWidth);
        float y = getPercentY(context, ModConfig.get().targetBlockY, elementHeight);

        context.getMatrices().pushMatrix();
        context.getMatrices().translate(x, y);
        context.getMatrices().scale(scale, scale);

        context.drawText(client.textRenderer, nameText, 0, 0, color, shadow);
        context.drawText(client.textRenderer, coordText, 0, client.textRenderer.fontHeight, color, shadow);

        context.getMatrices().popMatrix();
    }
}