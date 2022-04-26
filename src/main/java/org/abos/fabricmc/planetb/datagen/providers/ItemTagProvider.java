package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import org.abos.fabricmc.planetb.Content;

import java.util.List;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        FabricTagBuilder<Item> mundaneRocks = getOrCreateTagBuilder(Content.MUNDANE_ROCKS_TAG);
        FabricTagBuilder<Item> glowingRocks = getOrCreateTagBuilder(Content.GLOWING_ROCKS_TAG);
        FabricTagBuilder<Item> rockSlabs = getOrCreateTagBuilder(Content.ROCK_SLABS_TAG);
        FabricTagBuilder<Item> rockStairs = getOrCreateTagBuilder(Content.ROCK_STAIRS_TAG);
        FabricTagBuilder<Item> rockWalls = getOrCreateTagBuilder(Content.ROCK_WALLS_TAG);
        FabricTagBuilder<Item> slabs = getOrCreateTagBuilder(ItemTags.SLABS);
        FabricTagBuilder<Item> stairs = getOrCreateTagBuilder(ItemTags.STAIRS);
        FabricTagBuilder<Item> walls = getOrCreateTagBuilder(ItemTags.WALLS);
        for (Content.Rock rock : Content.Rock.values()) {
            getOrCreateTagBuilder(rock.asTag()).add(rock.asItem(), rock.asGlowingItem());
            mundaneRocks.add(rock.asItem());
            glowingRocks.add(rock.asGlowingItem());
            rockSlabs.add(rock.getSlab().asItem());
            slabs.add(rock.getSlab().asItem());
            rockStairs.add(rock.getStairs().asItem());
            stairs.add(rock.getStairs().asItem());
            rockWalls.add(rock.getWall().asItem());
            walls.add(rock.getWall().asItem());
        }
        getOrCreateTagBuilder(Content.ROCKS_TAG).addTag(Content.MUNDANE_ROCKS_TAG).addTag(Content.GLOWING_ROCKS_TAG);
        getOrCreateTagBuilder(Content.ROCK_DECORATIONS_TAG).addTag(Content.ROCK_SLABS_TAG).addTag(Content.ROCK_STAIRS_TAG).addTag(Content.ROCK_WALLS_TAG);

        Item[] mercuryStuff = new Item[] {
                Content.Rock.MERCURY.asItem(),
                Content.Rock.MERCURY.asGlowingItem(),
                Content.Rock.MERCURY.getSlab().asItem(),
                Content.Rock.MERCURY.getStairs().asItem(),
                Content.Rock.MERCURY.getWall().asItem(),
                Content.Dust.MERCURY.asItem()};
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(mercuryStuff);
        getOrCreateTagBuilder(ItemTags.IGNORED_BY_PIGLIN_BABIES).add(mercuryStuff);

        FabricTagBuilder<Item> dusts = getOrCreateTagBuilder(Content.DUSTS_TAG);
        for (Content.Dust dust : Content.Dust.values()) {
            getOrCreateTagBuilder(dust.asTag()).add(dust.asItem());
            dusts.add(dust.asItem());
        }
    }
}
