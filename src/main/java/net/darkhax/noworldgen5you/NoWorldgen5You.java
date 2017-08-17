package net.darkhax.noworldgen5you;

import org.apache.logging.log4j.Logger;

import net.darkhax.noworldgen5you.world.gen.MapGenEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenFortressEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenMineshaftEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenOceanMonumentEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenScatteredFeaturesEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenStrongholdEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenVillageEmpty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "noworldgen5you", name = "No Worldgen 5 You", version = "@VERSION@", acceptedMinecraftVersions = "[1.12,1.12.2)")
public class NoWorldgen5You {

    private static Logger log;

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        log = event.getModLog();
        WorldgenConfig.initConfig(event.getSuggestedConfigurationFile());
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onMapGen (InitMapGenEvent event) {

        final boolean isCustom = event.getType() == EventType.CUSTOM;

        if (!WorldgenConfig.isStructureDisabled(!isCustom ? event.getType().name().toLowerCase() : event.getOriginalGen().getClass().toString(), isCustom)) {

            return;
        }

        switch (event.getType()) {

            case CAVE:
                event.setNewGen(new MapGenEmpty());
                break;

            case CUSTOM:
                log.info("Attempting to replace {} with an empty map generator. If this causes a ClassCastException report it to the author of that mod and not the author of NoWorldgen5You");
                event.setNewGen(new MapGenEmpty());
                break;

            case MINESHAFT:
                event.setNewGen(new MapGenMineshaftEmpty());
                break;

            case NETHER_BRIDGE:
                event.setNewGen(new MapGenFortressEmpty());
                break;

            case NETHER_CAVE:
                event.setNewGen(new MapGenEmpty());
                break;

            case OCEAN_MONUMENT:
                event.setNewGen(new MapGenOceanMonumentEmpty());
                break;

            case RAVINE:
                event.setNewGen(new MapGenEmpty());
                break;

            case SCATTERED_FEATURE:
                event.setNewGen(new MapGenScatteredFeaturesEmpty());
                break;

            case STRONGHOLD:
                event.setNewGen(new MapGenStrongholdEmpty());
                break;

            case VILLAGE:
                event.setNewGen(new MapGenVillageEmpty());
                break;

            default:
                break;

        }
    }
}
