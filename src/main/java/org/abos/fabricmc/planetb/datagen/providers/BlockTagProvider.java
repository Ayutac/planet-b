package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import org.abos.fabricmc.planetb.Content;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public BlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        FabricTagBuilder<Block> pickaxe = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
        pickaxe.add(Content.PORTAL_FRAME);
        for (Content.Rock rock : Content.Rock.values()) {
            pickaxe.add(rock.asBlock());
            pickaxe.add(rock.asGlowingBlock());
        }
    }
}
