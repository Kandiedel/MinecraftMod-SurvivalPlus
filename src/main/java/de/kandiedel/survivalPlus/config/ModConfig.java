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
    }
}