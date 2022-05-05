package org.abos.fabricmc.planetb;

import net.fabricmc.api.ModInitializer;
import org.abos.fabricmc.planetb.world.biome.PlanetBBiomes;
import org.abos.fabricmc.planetb.world.dimension.PlanetBDimension;
import org.abos.fabricmc.planetb.world.gen.feature.OreFeatures;
import org.abos.fabricmc.planetb.world.gen.feature.SatelliteFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetB implements ModInitializer {

	public static final String MOD_ID = "planet-b";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_ID + "...");

		Content.init();
		OreFeatures.init();
		SatelliteFeatures.init();
		PlanetBBiomes.init();
		PlanetBDimension.init();

		PlanetBBiomes.loadBiomes();

		LOGGER.info("Initializing of " + MOD_ID + " done!");
	}

}
