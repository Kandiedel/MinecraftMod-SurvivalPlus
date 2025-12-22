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

        String nameText = "§7" + state.getBlock().getName().getString();
        String coordText = String.format("§8%d %d %d",
                blockHit.getBlockPos().getX(),
                blockHit.getBlockPos().getY(),
                blockHit.getBlockPos().getZ()
        );

        float scale = 0.75f;
        int screenWidth = client.getWindow().getScaledWidth();

        int maxWidth = Math.max(getWidth(client, nameText), getWidth(client, coordText));

        float x = screenWidth - (maxWidth * scale) - MARGIN;
        float y = MARGIN;

        context.getMatrices().pushMatrix();
        context.getMatrices().translate(x, y);
        context.getMatrices().scale(scale, scale);

        context.drawText(client.textRenderer, nameText, 0, 0, COLOR_WHITE, true);
        context.drawText(client.textRenderer, coordText, 0, client.textRenderer.fontHeight, COLOR_WHITE, true);

        context.getMatrices().popMatrix();
    }
}