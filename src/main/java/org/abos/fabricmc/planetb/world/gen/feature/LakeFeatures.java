package org.abos.fabricmc.planetb.world.gen.feature;

import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.random.AtomicSimpleRandom;
import net.minecraft.world.gen.random.ChunkRandom;
import org.abos.fabricmc.planetb.PlanetB;
import org.abos.fabricmc.planetb.world.gen.PlanetBConfig;

public class LakeFeatures {

    public static final StructureGeneratorFactory<StructurePoolFeatureConfig> LAKE_GENERATOR_FACTORY = context -> {
        ChunkRandom chunkRandom = new ChunkRandom(new AtomicSimpleRandom(0L));
        chunkRandom.setCarverSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), Math.max(chunkRandom.nextInt(60), 5), context.chunkPos().getStartZ());
        return StructurePoolBasedGenerator.generate(context, PoolStructurePiece::new, blockPos, false, false);
    };

    @SuppressWarnings("all")
    public static StructureFeature<PlanetBConfig> LAKE = new StructureFeature(PlanetBConfig.CODEC, LAKE_GENERATOR_FACTORY);

    public static void init() {
        Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(PlanetB.MOD_ID, "lake"), LAKE);
        StructureFeature.STRUCTURE_TO_GENERATION_STEP.put(LAKE, GenerationStep.Feature.UNDERGROUND_DECORATION);
    }

    private LakeFeatures() {/* No instantiation. */}

}
