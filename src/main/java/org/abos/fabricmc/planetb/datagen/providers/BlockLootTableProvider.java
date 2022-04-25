package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import org.abos.fabricmc.planetb.Content;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {
    public BlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        addDrop(Content.PORTAL_FRAME);
        for (Content.Rock rock : Content.Rock.values()) {
            addDrop(rock.asBlock());
            addDrop(rock.asGlowingBlock());
        }
    }
}
