package org.abos.fabricmc.planetb.world.dimension;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;
import techreborn.init.TRContent;

public class PlanetBDimension {

    public static final Identifier DIMENSION_ID = new Identifier(PlanetB.MOD_ID, PlanetB.MOD_ID);

    public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY, DIMENSION_ID);

    public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(
            Registry.WORLD_KEY,
            DIMENSION_KEY.getValue()
    );

    public static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
            Registry.DIMENSION_TYPE_KEY,
            new Identifier(PlanetB.MOD_ID, PlanetB.MOD_ID + "_type")
    );

    public static void init() {
        PlanetB.LOGGER.info("Initializing dimension for " + PlanetB.MOD_ID);
        CustomPortalBuilder.beginPortal()
                .frameBlock(Content.PORTAL_FRAME)
                .lightWithItem(TRContent.WRENCH)
                .onlyLightInOverworld()
                .destDimID(DIMENSION_ID)
                .tintColor(0x99c0cc)
                .registerPortal();
    }

    private PlanetBDimension() {/* No instantiation. */}
}
