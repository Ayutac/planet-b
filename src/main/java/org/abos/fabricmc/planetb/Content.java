package org.abos.fabricmc.planetb;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Content {

    public static final String REINFORCED_INDUSTRIAL_MACHINE_CASING_STR = "reinforced_industrial_machine_casing";
    public static final Block REINFORCED_INDUSTRIAL_MACHINE_CASING = new Block(FabricBlockSettings.of(Material.METAL, MapColor.GRAY).strength(10f, 100f).sounds(BlockSoundGroup.NETHERITE));

    public static final String MOON_ROCK_STR = "moon_rock";
    public static final Block MOON_ROCK = new Block(FabricBlockSettings.of(Material.STONE, MapColor.WHITE).requiresTool().strength(1.5f, 6f));

    public static void init() {
        initBlocks();
    }

    public static void initBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(PlanetB.MOD_ID, REINFORCED_INDUSTRIAL_MACHINE_CASING_STR), REINFORCED_INDUSTRIAL_MACHINE_CASING);
        Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, REINFORCED_INDUSTRIAL_MACHINE_CASING_STR), new BlockItem(REINFORCED_INDUSTRIAL_MACHINE_CASING, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(PlanetB.MOD_ID, MOON_ROCK_STR), MOON_ROCK);
        Registry.register(Registry.ITEM, new Identifier(PlanetB.MOD_ID, MOON_ROCK_STR), new BlockItem(MOON_ROCK, new FabricItemSettings().group(ItemGroup.MISC)));
    }

    private Content() {/* No instantiation. */}

}
