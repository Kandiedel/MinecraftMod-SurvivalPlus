package de.kandiedel.survivalPlus.client;

import de.kandiedel.survivalPlus.client.hud.HudManager;
import net.fabricmc.api.ClientModInitializer;

public class SurvivalPlusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudManager.init();
    }
}
