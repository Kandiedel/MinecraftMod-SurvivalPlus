package de.kandiedel.survivalPlus.client.durability;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class DurabilityManager {

    private static class ItemState {
        final ItemStack stack;
        final int damage;

        ItemState(ItemStack stack, int damage) {
            this.stack = stack;
            this.damage = damage;
        }
    }

    private static final Map<EquipmentSlot, ItemState> lastStateMap = new HashMap<>();

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(DurabilityManager::onClientTick);
    }

    private static void onClientTick(MinecraftClient client) {
        if (client.player == null) return;

        Map<EquipmentSlot, ItemState> currentStateMap = new HashMap<>();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = client.player.getEquippedStack(slot);

            if (stack.isEmpty() || !stack.isDamageable()) continue;

            int damage = stack.getDamage();
            int maxDamage = stack.getMaxDamage();
            float durabilityPercentage = (float) (maxDamage - damage) / maxDamage;

            currentStateMap.put(slot, new ItemState(stack, damage));

            if (durabilityPercentage <= 0.10f && durabilityPercentage > 0.0f) {
                ItemState lastState = lastStateMap.get(slot);

                boolean isSameItem = lastState != null && lastState.stack.getItem() == stack.getItem();

                boolean realisticDamageIncrease = lastState != null && (damage - lastState.damage) > 0 && (damage - lastState.damage) < 10;

                boolean tookDamageWhileUnder5Percent = isSameItem && realisticDamageIncrease;

                if (tookDamageWhileUnder5Percent) {

                    client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ARROW_HIT_PLAYER, 1.0f, 2.5f));

                    Text warningText = Text.literal("§eWarning! ")
                            .append(stack.getName())
                            .append(Text.literal(" §eis almost broken!"));

                    client.player.sendMessage(warningText, true);


                }
            }
        }

        lastStateMap.clear();
        lastStateMap.putAll(currentStateMap);
    }
}