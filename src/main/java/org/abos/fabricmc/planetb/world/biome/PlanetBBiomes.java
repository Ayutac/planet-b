package org.abos.fabricmc.planetb.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.gen.feature.ConfiguredFeatures;

public class PlanetBBiomes {

    public static void loadBiomes() {
        Registry.register(BuiltinRegistries.BIOME, PlanetBBiomeRegister.PLAINS_KEY.getValue(), PlanetBCreateBiome.PLAINS);
        BiomeModifications.addFeature(PlanetBBiomeRegister.SELECT_PLANET_B_BIOMES,
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(PlanetB.MOD_ID, ConfiguredFeatures.PLANET_MOON_ROCK_ORE_STR)));
    }

    public static void init() {
        PlanetB.LOGGER.info("Registering biomes for " + PlanetB.MOD_ID);
    }

    private PlanetBBiomes() {/* No instantiation. */}

}
