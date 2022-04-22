package org.abos.fabricmc.planetb.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.abos.fabricmc.planetb.datagen.providers.BlockLootTableProvider;

public class PlanetBDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(BlockLootTableProvider::new);

    }
}
