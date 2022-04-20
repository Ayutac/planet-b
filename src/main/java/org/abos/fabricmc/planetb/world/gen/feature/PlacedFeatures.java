package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.Arrays;

public class PlacedFeatures {

    public final static PlacedFeature PLANET_MOON_ROCK_ORE = new PlacedFeature(
            RegistryEntry.of(ConfiguredFeatures.PLANET_MOON_ROCK_ORE),
            Arrays.asList(
                    CountPlacementModifier.of(20),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));

    public static void init() {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(PlanetB.MOD_ID, ConfiguredFeatures.PLANET_MOON_ROCK_ORE_STR), PLANET_MOON_ROCK_ORE);
    }

    private PlacedFeatures() {/* No instantiation. */}
}
