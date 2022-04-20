package org.abos.fabricmc.planetb.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import org.abos.fabricmc.planetb.PlanetB;

public class PlanetBDimension {

    public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
            Registry.DIMENSION_KEY,
            new Identifier(PlanetB.MOD_ID, PlanetB.MOD_ID)
    );

    public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(
            Registry.WORLD_KEY,
            DIMENSION_KEY.getValue()
    );

    public static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
            Registry.DIMENSION_TYPE_KEY,
            new Identifier(PlanetB.MOD_ID, PlanetB.MOD_ID + "_type")
    );

    public static void init() {
        PlanetB.LOGGER.info("Registering dimension for + " + PlanetB.MOD_ID);
    }
}
