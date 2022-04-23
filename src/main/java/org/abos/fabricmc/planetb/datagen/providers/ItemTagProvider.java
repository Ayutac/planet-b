package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import org.abos.fabricmc.planetb.Content;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        FabricTagBuilder<Item> mundaneRocks = getOrCreateTagBuilder(Content.MUNDANE_ROCKS_TAG);
        FabricTagBuilder<Item> glowingRocks = getOrCreateTagBuilder(Content.GLOWING_ROCKS_TAG);
        for (Content.Rock rock : Content.Rock.values()) {
            getOrCreateTagBuilder(rock.asTag()).add(rock.asItem(), rock.asGlowingItem());
            mundaneRocks.add(rock.asItem());
            glowingRocks.add(rock.asGlowingItem());
        }
        getOrCreateTagBuilder(Content.ROCKS_TAG).addTag(Content.MUNDANE_ROCKS_TAG).addTag(Content.GLOWING_ROCKS_TAG);

        FabricTagBuilder<Item> dusts = getOrCreateTagBuilder(Content.DUSTS_TAG);
        for (Content.Dust dust : Content.Dust.values()) {
            getOrCreateTagBuilder(dust.asTag()).add(dust.asItem());
            dusts.add(dust.asItem());
        }
    }
}
