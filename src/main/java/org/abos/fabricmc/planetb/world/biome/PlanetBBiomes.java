package org.abos.fabricmc.planetb.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
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
        BiomeModification ores = BiomeModifications.create(new Identifier(PlanetB.MOD_ID, PlanetB.MOD_ID));
        for (ConfiguredFeatures.Ore ore : ConfiguredFeatures.Ore.values()) {
            ores.add(ModificationPhase.REPLACEMENTS, PlanetBBiomeRegister.SELECT_PLANET_B_BIOMES,
                context -> context.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                        RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(PlanetB.MOD_ID, ore.getFullName()))));
        }
    }

    public static void init() {
        PlanetB.LOGGER.info("Registering biomes for " + PlanetB.MOD_ID);
    }

    private PlanetBBiomes() {/* No instantiation. */}

}
