package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.random.AtomicSimpleRandom;
import net.minecraft.world.gen.random.ChunkRandom;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.biome.PlanetBBiomes;

public class PlanetFeatures {

    public static final StructureGeneratorFactory<StructurePoolFeatureConfig> PLANET_GENERATOR_FACTORY = context -> {
        ChunkRandom chunkRandom = new ChunkRandom(new AtomicSimpleRandom(0L));
        chunkRandom.setCarverSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), Math.max(chunkRandom.nextInt(100), 45), context.chunkPos().getStartZ());
        return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockPos, true, true);
    };

    public static StructureFeature<DefaultFeatureConfig> MARS_STRUCT = new StructureFeature(DefaultFeatureConfig.CODEC, PLANET_GENERATOR_FACTORY);
    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> MARS_CONF = MARS_STRUCT.configure(DefaultFeatureConfig.DEFAULT, PlanetBBiomes.ALL);

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(PlanetB.MOD_ID, "mars"), MARS_STRUCT);
    }

    private PlanetFeatures() {/* No instantiation. */}

}
