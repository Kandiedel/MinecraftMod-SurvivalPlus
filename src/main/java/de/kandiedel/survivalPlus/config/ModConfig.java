package de.kandiedel.survivalPlus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("survivalplus.json").toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static ConfigData data = new ConfigData();

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                data = GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigData get() {
        return data;
    }

    public static class ConfigData {
        public boolean isHudEnabled = true;

        public boolean showCoordinates = true;
        public boolean showBiome = true;
        public boolean showTargetBlock = true;
        public boolean showCompass = true;
        public boolean showFps = true;
        public boolean showDurability = true;
        public boolean showDurabilityHud = true;

        public int fpsX = 0;
        public int fpsY = 0;

        public int coordinatesX = 0;
        public int coordinatesY = 3;
        public boolean showCoordinateDecimals = false;

        public int biomeX = 0;
        public int biomeY = 100;

        public int compassX = 50;
        public int compassY = 93;

        public int targetBlockX = 100;
        public int targetBlockY = 0;

        public int durabilityHudX = 62;
        public int durabilityHudY = 100;
        public int durabilityHudAlignment = 0;

        public int textColor = 0xAAAAAA;
        public int fpsLabelColor = 0xFFFFFF;
        public int xLabelColor = 0xFF5555;
        public int yLabelColor = 0x55FF55;
        public int zLabelColor = 0x5555FF;
        public int biomeLabelColor = 0xFFFF55;
        public int durabilityTextColor = 0xFFFF55;

        public boolean valueTextBold = false;
        public boolean fpsLabelBold = true;
        public boolean coordinatesLabelBold = true;
        public boolean biomeLabelBold = true;
        public boolean durabilityTextBold = false;

        public boolean useTextShadow = true;
        public boolean showBackground = true;

        public int hudOffsetX = 5;
        public int hudOffsetY = 5;

        public int durabilityWarningThreshold = 10;
        public boolean playDurabilitySound = true;
        public boolean showDurabilityActionbar = true;

        public boolean isFullbrightEnabled = false;
        public double zoomLevel = 4.0;
        public boolean cinematicZoom = true;
        public boolean smoothZoom = true;
    }
}