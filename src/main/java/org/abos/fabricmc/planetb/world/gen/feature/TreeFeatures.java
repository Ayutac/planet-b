package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;
import org.abos.fabricmc.planetb.PlanetB;
import techreborn.init.TRContent;
import techreborn.world.WorldGenerator;

import java.util.Arrays;
import java.util.List;

public class TreeFeatures {

    public static final PlacedFeature RUBBER_TREE_PLACED_FEATURE = new PlacedFeature(getEntry(BuiltinRegistries.CONFIGURED_FEATURE, WorldGenerator.RUBBER_TREE_FEATURE),
            List.of(
                    SurfaceWaterDepthFilterPlacementModifier.of(0),
                    RarityFilterPlacementModifier.of(15),
                    BiomePlacementModifier.of(),
                    PlacedFeatures.wouldSurvive(TRContent.RUBBER_SAPLING)
            ));

    public static  <T> RegistryEntry<T> getEntry(Registry<T> registry, T value) {
        return registry.getEntry(registry.getKey(value).orElseThrow()).orElseThrow();
    }

    public static void init() {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(PlanetB.MOD_ID, "rubber_tree/plains"), RUBBER_TREE_PLACED_FEATURE);
    }

    private TreeFeatures() {/* No instantiation. */}

}
