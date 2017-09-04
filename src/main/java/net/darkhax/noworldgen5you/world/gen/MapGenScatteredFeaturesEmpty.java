package net.darkhax.noworldgen5you.world.gen;

import net.darkhax.noworldgen5you.WorldgenConfig;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class MapGenScatteredFeaturesEmpty extends MapGenScatteredFeature {

    private static boolean disableWitchHut = WorldgenConfig.isScateredStructureDisabled("witch_hut");
    private static boolean disableIgloo = WorldgenConfig.isScateredStructureDisabled("igloo");
    private static boolean disablePyramid = WorldgenConfig.isScateredStructureDisabled("pyramid");
    private static boolean disableJungleTemple = WorldgenConfig.isScateredStructureDisabled("jungle_temple");

    @Override
    public boolean canSpawnStructureAtCoords (int chunkX, int chunkZ) {

        final Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8));
        
        // witch hut
        if (disableWitchHut && biome == Biomes.SWAMPLAND) {
            
            return false;
        }
        
        // dessert pyramid
        else if (disablePyramid && (biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS)) {
            
            return false;
        }
        
        // igloo
        else if (disableIgloo && (biome == Biomes.ICE_PLAINS || biome == Biomes.COLD_TAIGA)) {
            
            return false;
        }
        
        // jungle temple
        else if (disableJungleTemple && (biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_HILLS)) {
            
            return false;
        }
        
        return super.canSpawnStructureAtCoords(chunkX, chunkZ);
    }
}