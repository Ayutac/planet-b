package org.abos.fabricmc.planetb.worldgen;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;

public class ConfiguredFeatures {

    public static final String PLANET_MOON_ROCK_ORE_STR = "planet_moon_rock_ore";
    public static final ConfiguredFeature<?,?> PLANET_MOON_ROCK_ORE = new ConfiguredFeature<>
        (Feature.ORE, new OreFeatureConfig(
                new BlockMatchRuleTest(Blocks.BLACKSTONE),
                Content.MOON_ROCK.getDefaultState(),
                9
            ));

    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(PlanetB.MOD_ID, PLANET_MOON_ROCK_ORE_STR), PLANET_MOON_ROCK_ORE);

    }

    private ConfiguredFeatures() {/* No instantiation. */}

}
