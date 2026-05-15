package de.kandiedel.survivalPlus.client.config;

import de.kandiedel.survivalPlus.config.ModConfig;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Arrays;

public class ConfigScreenBuilder {

    public static Screen buildScreen(Screen parent) {
        ModConfig.ConfigData currentConfig = ModConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("SurvivalPlus Settings"))
                .setSavingRunnable(() -> {
                    ModConfig.save();
                });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // --- Kategorie 1: HUD ---
        ConfigCategory hudCategory = builder.getOrCreateCategory(Text.literal("HUD"));

        hudCategory.addEntry(entryBuilder.startTextDescription(
                Text.literal("§e§l§nNote:§7 Default positions look best on GUI Scale 3\n" +
                        "You might need to change some positions if you have a different GUI Scale.")
        ).build());

        addSubCategory(hudCategory, entryBuilder, "General",
                entryBuilder.startBooleanToggle(Text.literal("Enable HUD"), currentConfig.isHudEnabled)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.isHudEnabled = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "FPS Element",
                entryBuilder.startBooleanToggle(Text.literal("Show FPS"), currentConfig.showFps)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.showFps = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("FPS Position X (%)"), currentConfig.fpsX, 0, 100)
                        .setDefaultValue(2)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.fpsX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("FPS Position Y (%)"), currentConfig.fpsY, 0, 100)
                        .setDefaultValue(2)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.fpsY = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "Coordinates Element",
                entryBuilder.startBooleanToggle(Text.literal("Show Coordinates"), currentConfig.showCoordinates)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.showCoordinates = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Coordinates Position X (%)"), currentConfig.coordinatesX, 0, 100)
                        .setDefaultValue(2)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.coordinatesX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Coordinates Position Y (%)"), currentConfig.coordinatesY, 0, 100)
                        .setDefaultValue(6)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.coordinatesY = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Show Coordinate Decimals"), currentConfig.showCoordinateDecimals)
                        .setDefaultValue(false)
                        .setTooltip(Text.literal("Shows exact coordinates with decimal places instead of block positions"))
                        .setSaveConsumer(newValue -> currentConfig.showCoordinateDecimals = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "Biome Element",
                entryBuilder.startBooleanToggle(Text.literal("Show Biome"), currentConfig.showBiome)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.showBiome = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Biome Position X (%)"), currentConfig.biomeX, 0, 100)
                        .setDefaultValue(2)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.biomeX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Biome Position Y (%)"), currentConfig.biomeY, 0, 100)
                        .setDefaultValue(96)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.biomeY = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "Compass Element",
                entryBuilder.startBooleanToggle(Text.literal("Show Compass"), currentConfig.showCompass)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.showCompass = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Compass Position X (%)"), currentConfig.compassX, 0, 100)
                        .setDefaultValue(50)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.compassX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Compass Position Y (%)"), currentConfig.compassY, 0, 100)
                        .setDefaultValue(88)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.compassY = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "Target Block Element",
                entryBuilder.startBooleanToggle(Text.literal("Show Target Block"), currentConfig.showTargetBlock)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.showTargetBlock = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Target Block Position X (%)"), currentConfig.targetBlockX, 0, 100)
                        .setDefaultValue(98)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.targetBlockX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Target Block Position Y (%)"), currentConfig.targetBlockY, 0, 100)
                        .setDefaultValue(2)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.targetBlockY = newValue)
                        .build()
        );

        addSubCategory(hudCategory, entryBuilder, "Durability Element",
                entryBuilder.startBooleanToggle(Text.literal("Show Durability Display"), currentConfig.showDurabilityHud)
                        .setDefaultValue(true)
                        .setTooltip(Text.literal("Shows the current durability as current / max"))
                        .setSaveConsumer(newValue -> currentConfig.showDurabilityHud = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Durability Position X (%)"), currentConfig.durabilityHudX, 0, 100)
                        .setDefaultValue(60)
                        .setTooltip(Text.literal("0 = left, 50 = center, 100 = right"))
                        .setSaveConsumer(newValue -> currentConfig.durabilityHudX = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Durability Position Y (%)"), currentConfig.durabilityHudY, 0, 100)
                        .setDefaultValue(96)
                        .setTooltip(Text.literal("0 = top, 50 = center, 100 = bottom"))
                        .setSaveConsumer(newValue -> currentConfig.durabilityHudY = newValue)
                        .build(),

                entryBuilder.startIntSlider(Text.literal("Durability Text Alignment"), currentConfig.durabilityHudAlignment, 0, 2)
                        .setDefaultValue(0)
                        .setTooltip(Text.literal("0 = grows right, 1 = centered, 2 = grows left"))
                        .setSaveConsumer(newValue -> currentConfig.durabilityHudAlignment = newValue)
                        .build()
        );

        // --- Kategorie 2: Design & Colors ---
        ConfigCategory designCategory = builder.getOrCreateCategory(Text.literal("Design & Colors"));

        addSubCategory(designCategory, entryBuilder, "Value Text",
                entryBuilder.startColorField(Text.literal("Value Text Color"), currentConfig.textColor)
                        .setDefaultValue(0xAAAAAA)
                        .setSaveConsumer(newValue -> currentConfig.textColor = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Value Text Bold"), currentConfig.valueTextBold)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> currentConfig.valueTextBold = newValue)
                        .build()
        );

        addSubCategory(designCategory, entryBuilder, "FPS Label",
                entryBuilder.startColorField(Text.literal("FPS Label Color"), currentConfig.fpsLabelColor)
                        .setDefaultValue(0xFFFFFF)
                        .setSaveConsumer(newValue -> currentConfig.fpsLabelColor = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("FPS Label Bold"), currentConfig.fpsLabelBold)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.fpsLabelBold = newValue)
                        .build()
        );

        addSubCategory(designCategory, entryBuilder, "Coordinates Labels",
                entryBuilder.startColorField(Text.literal("X Label Color"), currentConfig.xLabelColor)
                        .setDefaultValue(0xFF5555)
                        .setSaveConsumer(newValue -> currentConfig.xLabelColor = newValue)
                        .build(),

                entryBuilder.startColorField(Text.literal("Y Label Color"), currentConfig.yLabelColor)
                        .setDefaultValue(0x55FF55)
                        .setSaveConsumer(newValue -> currentConfig.yLabelColor = newValue)
                        .build(),

                entryBuilder.startColorField(Text.literal("Z Label Color"), currentConfig.zLabelColor)
                        .setDefaultValue(0x5555FF)
                        .setSaveConsumer(newValue -> currentConfig.zLabelColor = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Coordinates Label Bold"), currentConfig.coordinatesLabelBold)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.coordinatesLabelBold = newValue)
                        .build()
        );

        addSubCategory(designCategory, entryBuilder, "Biome Label",
                entryBuilder.startColorField(Text.literal("Biome Label Color"), currentConfig.biomeLabelColor)
                        .setDefaultValue(0xFFFF55)
                        .setSaveConsumer(newValue -> currentConfig.biomeLabelColor = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Biome Label Bold"), currentConfig.biomeLabelBold)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.biomeLabelBold = newValue)
                        .build()
        );

        addSubCategory(designCategory, entryBuilder, "Durability Text",
                entryBuilder.startColorField(Text.literal("Durability Text Color"), currentConfig.durabilityTextColor)
                        .setDefaultValue(0xFFFF55)
                        .setTooltip(Text.literal("Color of the durability display text"))
                        .setSaveConsumer(newValue -> currentConfig.durabilityTextColor = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Durability Text Bold"), currentConfig.durabilityTextBold)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> currentConfig.durabilityTextBold = newValue)
                        .build()
        );

        addSubCategory(designCategory, entryBuilder, "General Text",
                entryBuilder.startBooleanToggle(Text.literal("Use Text Shadow"), currentConfig.useTextShadow)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> currentConfig.useTextShadow = newValue)
                        .build()
        );

        // --- Kategorie 3: Extras (Durability / Zoom / Fullbright) ---
        ConfigCategory extrasCategory = builder.getOrCreateCategory(Text.literal("Extra Features"));

        addSubCategory(extrasCategory, entryBuilder, "Durability",
                entryBuilder.startIntSlider(Text.literal("Durability Warn Threshold"), currentConfig.durabilityWarningThreshold, 1, 100)
                        .setDefaultValue(10)
                        .setTooltip(Text.literal("Warns when durability falls below this value"))
                        .setSaveConsumer(newValue -> currentConfig.durabilityWarningThreshold = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Play Sound"), currentConfig.playDurabilitySound)
                        .setDefaultValue(true)
                        .setTooltip(Text.literal("Plays a warning sound when durability gets low"))
                        .setSaveConsumer(newValue -> currentConfig.playDurabilitySound = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Show Actionbar"), currentConfig.showDurabilityActionbar)
                        .setDefaultValue(true)
                        .setTooltip(Text.literal("Shows a warning message in the actionbar when durability gets low"))
                        .setSaveConsumer(newValue -> currentConfig.showDurabilityActionbar = newValue)
                        .build()
        );

        addSubCategory(extrasCategory, entryBuilder, "Zoom",
                entryBuilder.startDoubleField(Text.literal("Zoom Level"), currentConfig.zoomLevel)
                        .setDefaultValue(4.0)
                        .setSaveConsumer(newValue -> currentConfig.zoomLevel = newValue)
                        .build(),

                entryBuilder.startBooleanToggle(Text.literal("Smooth Zoom"), currentConfig.smoothZoom)
                        .setDefaultValue(true)
                        .setTooltip(Text.literal("Animates zoom in and out smoothly"))
                        .setSaveConsumer(newValue -> currentConfig.smoothZoom = newValue)
                        .build()
        );

        return builder.build();
    }

    @SafeVarargs
    private static void addSubCategory(
            ConfigCategory category,
            ConfigEntryBuilder entryBuilder,
            String title,
            AbstractConfigListEntry<?>... entries
    ) {
        category.addEntry(entryBuilder.startSubCategory(Text.literal(title), Arrays.asList(entries)).build());
    }
}