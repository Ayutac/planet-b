package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

    public static final String ANCIENT_DEBRIS_STR = "ancient_debris_ore";
    public static final ConfiguredFeature<?,?> ANCIENT_DEBRIS_CONF = new ConfiguredFeature<>(Feature.SCATTERED_ORE, new OreFeatureConfig(List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.ANCIENT_DEBRIS.getDefaultState())),
            2, 1f
    ));
    public static final PlacedFeature ANCIENT_DEBRIS_PLACE = new PlacedFeature(RegistryEntry.of(ANCIENT_DEBRIS_CONF), Arrays.asList(
            CountPlacementModifier.of(8),
            SquarePlacementModifier.of(),
            PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE));

    public static final String PACKED_ICE_STR = "packed_ice_ore";
    public static final ConfiguredFeature<?,?> PACKED_ICE_CONF = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.PACKED_ICE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.PACKED_ICE.getDefaultState())),
            10
    ));
    public static final PlacedFeature PACKED_ICE_PLACE_UPPER = new PlacedFeature(RegistryEntry.of(PACKED_ICE_CONF), Arrays.asList(
            CountPlacementModifier.of(4),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.fixed(22), YOffset.fixed(56))));
    public static final PlacedFeature PACKED_ICE_PLACE_BELT = new PlacedFeature(RegistryEntry.of(PACKED_ICE_CONF), Arrays.asList(
            CountPlacementModifier.of(12),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(64), YOffset.fixed(24))));
    public static final PlacedFeature PACKED_ICE_PLACE_LOWER = new PlacedFeature(RegistryEntry.of(PACKED_ICE_CONF), Arrays.asList(
            CountPlacementModifier.of(8),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(4), YOffset.aboveBottom(72))));

    private static List<PlacementModifier> createOrePlacementModifiers(Ore ore) {
        return Arrays.asList(
                CountPlacementModifier.of(ore.veinsPerChunk),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.trapezoid(ore.minYOffset, ore.maxYOffset));
    }

    public enum Ore {
        JUPITER(14, 10, YOffset.aboveBottom(56), YOffset.aboveBottom(72)), // peak 64 above
        MARS(28, 5, YOffset.fixed(24), YOffset.fixed(54)), // peak 39
        MERCURY(16, 8, YOffset.fixed(32), YOffset.fixed(48)), // peak 40
        MOON(32, 5, YOffset.fixed(32), YOffset.fixed(64)), // peak 48
        NEPTUNE(12, 10, YOffset.aboveBottom(20), YOffset.aboveBottom(36)), // peak 28 above
        PLUTO(8, 8, YOffset.aboveBottom(8), YOffset.aboveBottom(24)), // peak 16 above
        SATURN(16, 8, YOffset.aboveBottom(44), YOffset.aboveBottom(60)), // peak 52 above
        URANUS(12, 10, YOffset.aboveBottom(32), YOffset.aboveBottom(48)), // peak 40 above
        VENUS(16, 8, YOffset.fixed(22), YOffset.fixed(40)); // peak 31

        private String fullName;
        private int veinsPerChunk;
        private YOffset minYOffset;
        private YOffset maxYOffset;
        private ConfiguredFeature<?,?> configuredFeature;
        private PlacedFeature placedFeature;

        Ore(int size, int veinsPerChunk, YOffset minYOffset, YOffset maxYOffset) {
            if (size < 0)
                size = 0;
            if (veinsPerChunk < 0)
                veinsPerChunk = 0;
            this.veinsPerChunk = veinsPerChunk;
            this.minYOffset = minYOffset;
            this.maxYOffset = maxYOffset;
            fullName = name().toLowerCase(Locale.ROOT) + "_ore";
            BlockState defaultBlockState = Content.Rock.valueOf(name()).asBlock().getDefaultState();
            configuredFeature = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(List.of(
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, defaultBlockState),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, defaultBlockState)),
                    size
            ));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PlanetB.id(fullName), configuredFeature);
            placedFeature = new PlacedFeature(RegistryEntry.of(configuredFeature), createOrePlacementModifiers(this));
            Registry.register(BuiltinRegistries.PLACED_FEATURE, PlanetB.id(fullName), placedFeature);
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
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PlanetB.id(ANCIENT_DEBRIS_STR), ANCIENT_DEBRIS_CONF);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PlanetB.id(ANCIENT_DEBRIS_STR), ANCIENT_DEBRIS_PLACE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PlanetB.id(PACKED_ICE_STR), PACKED_ICE_CONF);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PlanetB.id(PACKED_ICE_STR + "upper"), PACKED_ICE_PLACE_UPPER);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PlanetB.id(PACKED_ICE_STR + "belt"), PACKED_ICE_PLACE_BELT);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, PlanetB.id(PACKED_ICE_STR + "lower"), PACKED_ICE_PLACE_LOWER);
    }

    private OreFeatures() {/* No instantiation. */}

}
