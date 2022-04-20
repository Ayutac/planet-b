package org.abos.fabricmc.planetb.world.biome;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import org.abos.fabricmc.planetb.PlanetB;

public class PlanetBBiomes {

    public static void loadBiomes() {
        Registry.register(BuiltinRegistries.BIOME, PlanetBBiomeRegister.PLAINS_KEY.getValue(), PlanetBCreateBiome.PLAINS);
    }

    public static void init() {
        PlanetB.LOGGER.info("Registering biomes for " + PlanetB.MOD_ID);
    }

    private PlanetBBiomes() {/* No instantiation. */}

}
