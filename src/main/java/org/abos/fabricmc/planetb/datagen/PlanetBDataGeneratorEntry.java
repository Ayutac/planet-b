package org.abos.fabricmc.planetb.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.abos.fabricmc.planetb.datagen.providers.BlockLootTableProvider;
import org.abos.fabricmc.planetb.datagen.providers.BlockTagProvider;
import org.abos.fabricmc.planetb.datagen.providers.ModelProvider;

public class PlanetBDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(BlockTagProvider::new);
        // tags always first!
        fabricDataGenerator.addProvider(BlockLootTableProvider::new);
        fabricDataGenerator.addProvider(ModelProvider::new);
    }
}
