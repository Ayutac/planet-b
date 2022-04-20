package org.abos.fabricmc.planetb;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetB implements ModInitializer {

	public static final String MOD_ID = "planet-b";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing...");
		Content.init();
		LOGGER.info("Initializing done! Good travels!");
	}
}