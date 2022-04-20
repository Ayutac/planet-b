package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.Locale;

public class ConfiguredFeatures {

    public enum Ore {
        JUPITER(14),
        MARS(28),
        MERCURY(16),
        MOON(32),
        NEPTUNE(12),
        PLUTO(8),
        SATURN(16),
        URANUS(12),
        VENUS(16);

        private String fullName;
        private ConfiguredFeature<?,?> feature;

        Ore(int size) {
            fullName = name().toLowerCase(Locale.ROOT) + "_ore";
            feature = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES, Content.Rock.valueOf(name()).asBlock().getDefaultState(), size
            ));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(PlanetB.MOD_ID, fullName), feature);
        }

        public String getFullName() {
            return fullName;
        }

        public ConfiguredFeature<?,?> asFeature() {
            return feature;
        }

    }

    public static void init() {
        //noinspection ResultOfMethodCallIgnored
        Ore.values(); // force loading
    }

    private ConfiguredFeatures() {/* No instantiation. */}

}
