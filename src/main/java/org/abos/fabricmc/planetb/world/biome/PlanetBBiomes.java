package org.abos.fabricmc.planetb.world.biome;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.gen.feature.OreFeatures;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class PlanetBBiomes {

    public static final RegistryKey<Biome> PLAINS_KEY = registerKey("plains");
    public static final RegistryKey<Biome> FOREST_KEY = registerKey("forest");
    public static final RegistryKey<Biome> MUSHROOM_FOREST_KEY = registerKey("mushroom_forest");

    public static final Predicate<BiomeSelectionContext> SELECT_PLANET_B_BIOMES = BiomeSelectors.includeByKey(
            PLAINS_KEY, FOREST_KEY, MUSHROOM_FOREST_KEY);

    private static RegistryKey<Biome> registerKey(String name){
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(PlanetB.MOD_ID, name));
    }

    public static void loadBiomes() {
        BiomeModifications.create(new Identifier(PlanetB.MOD_ID, "features"))
                .add(ModificationPhase.ADDITIONS, SELECT_PLANET_B_BIOMES, oreModifier());
    }

    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> oreModifier() {
        return (biomeSelectionContext, biomeModificationContext) -> {
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                    BuiltinRegistries.PLACED_FEATURE.getKey(OreFeatures.ANCIENT_DEBRIS_PLACE).orElseThrow());
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                    BuiltinRegistries.PLACED_FEATURE.getKey(OreFeatures.PACKED_ICE_PLACE_UPPER).orElseThrow());
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                    BuiltinRegistries.PLACED_FEATURE.getKey(OreFeatures.PACKED_ICE_PLACE_BELT).orElseThrow());
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                    BuiltinRegistries.PLACED_FEATURE.getKey(OreFeatures.PACKED_ICE_PLACE_LOWER).orElseThrow());
            for (OreFeatures.Ore ore : OreFeatures.Ore.values()) {
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ore.getPlacedFeatureRegistryKey());
            }
        };
    }

    public static void init() {
        PlanetB.LOGGER.info("Registering biomes for " + PlanetB.MOD_ID);
    }

    private PlanetBBiomes() {/* No instantiation. */}

}
