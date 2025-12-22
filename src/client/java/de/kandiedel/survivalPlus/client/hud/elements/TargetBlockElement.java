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

        int screenWidth = client.getWindow().getScaledWidth();

        int maxWidth = Math.max(getWidth(client, nameText), getWidth(client, coordText));

        int x = screenWidth - maxWidth - MARGIN;
        int y = MARGIN;

        context.drawText(client.textRenderer, nameText, x, y, COLOR_WHITE, true);
        context.drawText(client.textRenderer, coordText, x, y + client.textRenderer.fontHeight, COLOR_WHITE, true);
    }
}