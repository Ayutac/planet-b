package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.random.AtomicSimpleRandom;
import net.minecraft.world.gen.random.ChunkRandom;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.gen.PlanetBConfig;
import techreborn.init.ModFluids;
import techreborn.init.TRContent;

public class LakeFeatures {

    public static RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> LAKE_OIL;

    public static RegistryEntry<PlacedFeature> LAKE_OIL_UNDERGROUND;

    public static void init() {
        LAKE_OIL = ConfiguredFeatures.register("planet-b:lake_oil", Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(ModFluids.OIL.getBlock().getDefaultState()), BlockStateProvider.of(Blocks.STONE.getDefaultState())));
        LAKE_OIL_UNDERGROUND = PlacedFeatures.register("planet-b:lake_oil_underground", LAKE_OIL, new PlacementModifier[]{RarityFilterPlacementModifier.of(9), SquarePlacementModifier.of(), HeightRangePlacementModifier.of(UniformHeightProvider.create(YOffset.fixed(0), YOffset.getTop())), EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.bothOf(BlockPredicate.not(BlockPredicate.IS_AIR), BlockPredicate.insideWorldBounds(new BlockPos(0, -5, 0))), 32), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, -2147483648, -5), BiomePlacementModifier.of()});
    }

    private LakeFeatures() {/* No instantiation. */}

}
