package net.darkhax.noworldgen5you.world.gen;

import net.darkhax.noworldgen5you.WorldgenConfig;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class MapGenScatteredFeaturesEmpty extends MapGenScatteredFeature {

    public static enum Type {
        
        WITCH_HUT,
        IGLOO,
        DESSERT_PYRAMID,
        JUNGLE_TEMPLE;
    }

    @Override
    public boolean canSpawnStructureAtCoords (int chunkX, int chunkZ) {

        final Biome biome = this.world.getBiomeProvider().getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8));
        
        // witch hut
        if (WorldgenConfig.isScateredStructureDisabled(Type.WITCH_HUT.name().toLowerCase()) && biome == Biomes.SWAMPLAND) {
            
            return false;
        }
        
        // dessert pyramid
        else if (WorldgenConfig.isScateredStructureDisabled(Type.DESSERT_PYRAMID.name().toLowerCase()) && (biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS)) {
            
            return false;
        }
        
        // igloo
        else if (WorldgenConfig.isScateredStructureDisabled(Type.IGLOO.name().toLowerCase()) && (biome == Biomes.ICE_PLAINS || biome == Biomes.COLD_TAIGA)) {
            
            return false;
        }
        
        // jungle temple
        else if (WorldgenConfig.isScateredStructureDisabled(Type.JUNGLE_TEMPLE.name().toLowerCase()) && (biome == Biomes.JUNGLE || biome == Biomes.JUNGLE_HILLS)) {
            
            return false;
        }
        
        return super.canSpawnStructureAtCoords(chunkX, chunkZ);
    }
}