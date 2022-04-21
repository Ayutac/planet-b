package org.abos.fabricmc.planetb;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Locale;
import java.util.Map;

public class Content {

    public static final String PORTAL_FRAME_STR = "portal_frame";
    public static final Block PORTAL_FRAME = new Block(FabricBlockSettings.of(Material.METAL, MapColor.GRAY).strength(10f, 100f).sounds(BlockSoundGroup.NETHERITE));

    public enum Rock implements ItemConvertible {
        JUPITER(MapColor.TERRACOTTA_GREEN),
        MARS(MapColor.TERRACOTTA_ORANGE),
        MERCURY(MapColor.GOLD),
        MOON(MapColor.WHITE),
        NEPTUNE(MapColor.CYAN),
        PLUTO(MapColor.TERRACOTTA_BLUE),
        SATURN(MapColor.ORANGE),
        URANUS(MapColor.TERRACOTTA_PURPLE),
        VENUS(MapColor.RED);

        private Block block;
        private Item item;

        Rock(MapColor color) {
            block = new OreBlock(FabricBlockSettings.of(Material.STONE, color).requiresTool().strength(1.5f, 6f));
            item = registerBlock(name().toLowerCase() + "_rock", block);
        }

        public Block asBlock() {
            return block;
        }

        @Override
        public Item asItem() {
            return item;
        }
    }

    public enum Dust implements ItemConvertible {
        JUPITER,
        MARS,
        MERCURY,
        MOON,
        NEPTUNE,
        PLUTO,
        SATURN,
        URANUS,
        VENUS;

        private Item item;

        Dust() {
            item = new Item(new FabricItemSettings().group(ItemGroup.MISC));
            Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, name().toLowerCase(Locale.ROOT) + "_dust"), item);
        }

        @Override
        public Item asItem() {
            return item;
        }

        public static Map<Rock, Dust> createRock2DustMap() {
            var builder = new ImmutableMap.Builder<Rock, Dust>();
            for (Dust dust : Dust.values()) {
                builder.put(Rock.valueOf(dust.name()), dust);
            }
            return builder.build();
        }
    }

    public static void init() {
        initBlocks();
    }

    public static BlockItem registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(PlanetB.MOD_ID, name), block);
        BlockItem item = new BlockItem(block, new FabricItemSettings().group(ItemGroup.MISC));
        Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, name), item);
        return item;
    }

    public static void initBlocks() {
        registerBlock(PORTAL_FRAME_STR, PORTAL_FRAME);
        //noinspection ResultOfMethodCallIgnored
        Rock.values(); // force loading
        //noinspection ResultOfMethodCallIgnored
        Dust.values(); // force loading
    }

    private Content() {/* No instantiation. */}

}
