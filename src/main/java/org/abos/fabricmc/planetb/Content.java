package org.abos.fabricmc.planetb;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import reborncore.common.misc.TagConvertible;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Content {

    public static final String PORTAL_FRAME_STR = "portal_frame";
    public static final Block PORTAL_FRAME = new Block(FabricBlockSettings.of(Material.METAL, MapColor.GRAY).strength(10f, 100f).sounds(BlockSoundGroup.NETHERITE));
    private static Item PORTAL_FRAME_ITEM;

    private static ItemGroup ITEM_GROUP = null;

    public static final TagKey<Item> MUNDANE_ROCKS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "mundane_rocks"));
    public static final TagKey<Item> GLOWING_ROCKS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "glowing_rocks"));
    public static final TagKey<Item> ROCKS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "rocks"));
    public static final TagKey<Item> ROCK_SLABS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "rock_slabs"));
    public static final TagKey<Item> ROCK_STAIRS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "rock_stairs"));
    public static final TagKey<Item> ROCK_WALLS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "rock_walls"));
    public static final TagKey<Item> ROCK_DECORATIONS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "rock_decorations"));

    public static AbstractBlock.Settings createRockSettings(MapColor color) {
        return FabricBlockSettings.of(Material.STONE, color).requiresTool().strength(1.5f, 6f);
    }

    public static BlockItem registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(PlanetB.MOD_ID, name), block);
        BlockItem item = new BlockItem(block, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, name), item);
        return item;
    }

    public enum Rock implements ItemConvertible, TagConvertible<Item> {
        JUPITER(MapColor.TERRACOTTA_GREEN, 4),
        MARS(MapColor.TERRACOTTA_ORANGE, 7),
        MERCURY(MapColor.GOLD, 14),
        MOON(MapColor.WHITE, 7),
        NEPTUNE(MapColor.TERRACOTTA_CYAN, 1),
        PLUTO(MapColor.TERRACOTTA_BLUE, 0),
        SATURN(MapColor.ORANGE, 3),
        URANUS(MapColor.CYAN, 2),
        VENUS(MapColor.RED, 10);

        private final String name;
        private final Block block;
        private final Item item;
        private final Block glowingBlock;
        private final Item glowingItem;
        private final Block stairs;
        private final Block slab;
        private final Block wall;
        private final TagKey<Item> tag;

        Rock(final MapColor color, final int glowLightLevel) {
            name = name().toLowerCase(Locale.ROOT);
            block = new OreBlock(createRockSettings(color));
            item = registerBlock(name + "_rock", block);
            glowingBlock = new OreBlock(createRockSettings(color).luminance(blockState -> glowLightLevel));
            glowingItem = registerBlock(name + "_rock_glowing", glowingBlock);
            stairs = new StairsBlock(block.getDefaultState(), createRockSettings(color));
            registerBlock(name + "_rock_stairs", stairs);
            slab = new SlabBlock(createRockSettings(color));
            registerBlock(name + "_rock_slab", slab);
            wall = new WallBlock(createRockSettings(color));
            registerBlock(name + "_rock_wall", wall);
            tag = TagKey.of(Registry.ITEM_KEY, new Identifier("c", name + "_rocks"));
        }

        /**
         * @return the lowercase {@link #name()} of this enum entry
         */
        public String getName() {
            return name;
        }

        public Block asBlock() {
            return block;
        }

        @Override
        public Item asItem() {
            return item;
        }

        public Block asGlowingBlock() {
            return glowingBlock;
        }

        public Item asGlowingItem() {
            return glowingItem;
        }

        public Block getStairs() {
            return stairs;
        }

        public Block getSlab() {
            return slab;
        }

        public Block getWall() {
            return wall;
        }

        @Override
        public TagKey<Item> asTag() {
            return tag;
        }
    }

    public static final TagKey<Item> DUSTS_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier(PlanetB.MOD_ID, "dusts"));

    public enum Dust implements ItemConvertible, TagConvertible<Item> {
        JUPITER,
        MARS,
        MERCURY,
        MOON,
        NEPTUNE,
        PLUTO,
        SATURN,
        URANUS,
        VENUS;

        private final String name;
        private final Item item;
        private final TagKey<Item> tag;

        Dust() {
            name = name().toLowerCase(Locale.ROOT);
            item = new Item(new FabricItemSettings().group(ItemGroup.MISC));
            Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, name + "_dust"), item);
            tag = TagKey.of(Registry.ITEM_KEY, new Identifier("c", name + "_dusts"));
        }

        /**
         * @return the lowercase {@link #name()} of this enum entry
         */
        public String getName() {
            return name;
        }

        @Override
        public Item asItem() {
            return item;
        }

        @Override
        public TagKey<Item> asTag() {
            return tag;
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
        PlanetB.LOGGER.info("Initializing items...");
        initBlocks();
        getItemGroup(); // ensures enum loading
    }

    private static void initBlocks() {
        PORTAL_FRAME_ITEM = registerBlock(PORTAL_FRAME_STR, PORTAL_FRAME);
    }

    public static ItemGroup getItemGroup() {
        if (ITEM_GROUP != null)
            return ITEM_GROUP;
        List<ItemStack> itemList = new LinkedList<>();
        itemList.add(new ItemStack(PORTAL_FRAME_ITEM));
        Dust.createRock2DustMap().forEach((rock,dust) -> {
            itemList.add(new ItemStack(rock));
            itemList.add(new ItemStack(rock.asGlowingItem()));
            itemList.add(new ItemStack(rock.getStairs()));
            itemList.add(new ItemStack(rock.getSlab()));
            itemList.add(new ItemStack(rock.getWall()));
            itemList.add(new ItemStack(dust));
        });
        ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(PlanetB.MOD_ID,PlanetB.MOD_ID))
                .icon(() -> new ItemStack(Dust.MOON))
                .appendItems(list -> list.addAll(itemList))
                .build();
        return ITEM_GROUP;
    }

    private Content() {/* No instantiation. */}

}
