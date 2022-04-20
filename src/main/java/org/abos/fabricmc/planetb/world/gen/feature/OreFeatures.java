package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OreFeatures {

    private static List<PlacementModifier> createOrePlacementModifiers(Ore ore) {
        return Arrays.asList(
                CountPlacementModifier.of(ore.veinsPerChunk),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)));
    }

    public enum Ore {
        JUPITER(14, 10),
        MARS(28, 5),
        MERCURY(16, 8),
        MOON(32, 5),
        NEPTUNE(12, 10),
        PLUTO(8, 8),
        SATURN(16, 8),
        URANUS(12, 10),
        VENUS(16, 8);

        private String fullName;
        private int veinsPerChunk;
        private ConfiguredFeature<?,?> configuredFeature;
        private PlacedFeature placedFeature;

        Ore(int size, int veinsPerChunk) {
            if (size < 0)
                size = 0;
            if (veinsPerChunk < 0)
                veinsPerChunk = 0;
            this.veinsPerChunk = veinsPerChunk;
            fullName = name().toLowerCase(Locale.ROOT) + "_ore";
            BlockState defaultBlockState = Content.Rock.valueOf(name()).asBlock().getDefaultState();
            configuredFeature = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(List.of(
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, defaultBlockState),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, defaultBlockState)),
                    size
            ));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(PlanetB.MOD_ID, fullName), configuredFeature);
            placedFeature = new PlacedFeature(RegistryEntry.of(configuredFeature), createOrePlacementModifiers(this));
            Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(PlanetB.MOD_ID, fullName), placedFeature);
        }

        public int getVeinsPerChunk() {
            return veinsPerChunk;
        }

        public RegistryKey<PlacedFeature> getPlacedFeatureRegistryKey() {
            return BuiltinRegistries.PLACED_FEATURE.getKey(placedFeature).orElseThrow();
        }

    }

    public static void init() {
        //noinspection ResultOfMethodCallIgnored
        Ore.values(); // force loading
    }

    private OreFeatures() {/* No instantiation. */}

}
