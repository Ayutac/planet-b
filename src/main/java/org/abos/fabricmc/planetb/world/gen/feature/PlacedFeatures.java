package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PlacedFeatures {

    public static List<PlacementModifier> createOrePlacementModifiers() {
        return Arrays.asList(
                CountPlacementModifier.of(20),
                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)));
    }

    public static void init() {
        for (ConfiguredFeatures.Ore ore : ConfiguredFeatures.Ore.values()) {
            Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(PlanetB.MOD_ID, ore.getFullName()),
                    new PlacedFeature(RegistryEntry.of(ore.asFeature()), createOrePlacementModifiers()));
        }
    }

    private PlacedFeatures() {/* No instantiation. */}
}
