package org.abos.fabricmc.planetb.world.biome;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.function.Predicate;

public class PlanetBBiomeRegister {

    public static final RegistryKey<Biome> PLAINS_KEY = registerKey("plains");
    public static final RegistryKey<Biome> FOREST_KEY = registerKey("forest");

    public static final Predicate<BiomeSelectionContext> SELECT_PLANET_B_BIOMES = BiomeSelectors.includeByKey(
            PLAINS_KEY, FOREST_KEY);

    private static RegistryKey<Biome> registerKey(String name){
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(PlanetB.MOD_ID, name));
    }

    private PlanetBBiomeRegister() {/* No instantiation */}
}
