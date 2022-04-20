package org.abos.fabricmc.planetb.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.abos.fabricmc.planetb.PlanetB;

public class PlanetBBiomeRegister {

    public static final RegistryKey<Biome> PLAINS_KEY = registerKey("plains");

    private static RegistryKey<Biome> registerKey(String name){
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(PlanetB.MOD_ID, name));
    }

    private PlanetBBiomeRegister() {/* No instantiation */}
}
