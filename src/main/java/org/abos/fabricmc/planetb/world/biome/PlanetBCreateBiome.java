package org.abos.fabricmc.planetb.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class PlanetBCreateBiome {
    public static final Biome PLAINS = createPlains();

    private static Biome createPlains() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        return
                (new Biome.Builder())
                        .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE)
                        .temperature(0.4f)
                        .temperatureModifier(Biome.TemperatureModifier.NONE)
                        .downfall(0.4f)
                        .effects((new BiomeEffects.Builder()
                                .waterColor(0x3f76e4).waterFogColor(0x050533)
                                .fogColor(0xD8BFD8).skyColor(0xADD8E6)
                                .build()))
                        .generationSettings(generationSettings.build())
                        .spawnSettings(spawnSettings.build())
                        .build();
    }
}
