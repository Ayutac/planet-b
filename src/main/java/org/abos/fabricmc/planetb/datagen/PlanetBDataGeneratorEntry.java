package org.abos.fabricmc.planetb.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.datagen.providers.*;

public class PlanetBDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(BlockTagProvider::new);
        fabricDataGenerator.addProvider(ItemTagProvider::new);
        // tags always first!
        Content.getItemGroup(); // make sure item group is populated
        fabricDataGenerator.addProvider(RecipeProvider::new);
        fabricDataGenerator.addProvider(BlockLootTableProvider::new);
        fabricDataGenerator.addProvider(ModelProvider::new);
    }
}
