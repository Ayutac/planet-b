package org.abos.fabricmc.planetb.world.biome;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.gen.feature.OreFeatures;
import org.abos.fabricmc.planetb.world.gen.feature.TreeFeatures;

import java.util.function.BiConsumer;

public class PlanetBBiomes {

    public static void loadBiomes() {
        Registry.register(BuiltinRegistries.BIOME, PlanetBBiomeRegister.PLAINS_KEY.getValue(), PlanetBCreateBiome.PLAINS);
        BiomeModifications.create(new Identifier(PlanetB.MOD_ID, "features"))
                .add(ModificationPhase.ADDITIONS, PlanetBBiomeRegister.SELECT_PLANET_B_BIOMES, oreModifier())
                .add(ModificationPhase.ADDITIONS, PlanetBBiomeRegister.SELECT_PLANET_B_BIOMES, treeModifier());
        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(PlanetBBiomeRegister.PLAINS_KEY), GenerationStep.Feature.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(TreeFeatures.RUBBER_TREE_PLACED_FEATURE).orElseThrow());
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

    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> treeModifier() {
        return (biomeSelectionContext, biomeModificationContext) ->
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, BuiltinRegistries.PLACED_FEATURE.getKey(TreeFeatures.RUBBER_TREE_PLACED_FEATURE).orElseThrow());
    }

    public static void init() {
        PlanetB.LOGGER.info("Registering biomes for " + PlanetB.MOD_ID);
    }

    private PlanetBBiomes() {/* No instantiation. */}

}
