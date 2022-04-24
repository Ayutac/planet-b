package org.abos.fabricmc.planetb.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.abos.fabricmc.planetb.world.gen.feature.TreeFeatures;

public class PlanetBCreateBiome {

    public static final Biome PLAINS = createPlains();

    public static BiomeEffects createEffects() {
        return new BiomeEffects.Builder()
                .waterColor(0x000333).waterFogColor(0x000000)
                .skyColor(0x333ccc).fogColor(0x333666)
                .foliageColor(0x5580aa).grassColor(0x33aaaa)
                .moodSound(BiomeMoodSound.CAVE)
                .build();
    }

    private static Biome createPlains() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // DefaultBiomeFeatures.addFarmAnimals(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultGrass(generationSettings);
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
        //generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(TreeFeatures.RUBBER_TREE_PLACED_FEATURE).orElseThrow().);
        // DefaultBiomeFeatures.addSeagrassOnStone(generationSettings); doesn't seem to work
        DefaultBiomeFeatures.addAncientDebris(generationSettings);

        return new Biome.Builder()
                .category(Biome.Category.NONE)
                .temperature(0.3f).temperatureModifier(Biome.TemperatureModifier.NONE)
                .downfall(0.2f).precipitation(Biome.Precipitation.RAIN)
                .effects(createEffects())
                .generationSettings(generationSettings.build())
                .spawnSettings(spawnSettings.build())
                .build();
    }

    private PlanetBCreateBiome() {/* No instantiation. */}
}
