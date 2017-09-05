package net.darkhax.noworldgen5you;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.darkhax.noworldgen5you.world.gen.MapGenScatteredFeaturesEmpty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;

public class WorldgenConfig {

    private static Configuration config;
    public static final Map<String, Boolean> settings = new HashMap<>();

    public static Configuration initConfig (File file) {

        config = new Configuration(file);

        config.setCategoryComment("map_structures", "Allows for various types of map generators to be disabled.");

        for (final InitMapGenEvent.EventType type : InitMapGenEvent.EventType.values()) {

            if (type != InitMapGenEvent.EventType.CUSTOM) {

                isStructureDisabled(type.name().toLowerCase());
            }
        }

        config.setCategoryComment("scattered_structures", "This category requires the scattered map generator from the map_structures category to be disabled.");

        for (final MapGenScatteredFeaturesEmpty.Type type : MapGenScatteredFeaturesEmpty.Type.values()) {

            isScateredStructureDisabled(type.name().toLowerCase());
        }

        config.setCategoryComment("map_populates", "Allows for various types of chunk populators to be disabled.");

        for (final Populate.EventType type : Populate.EventType.values()) {

            if (type != Populate.EventType.CUSTOM) {

                isPopulateDisabled(type.name().toLowerCase());
            }
        }

        syncConfigData();
        return config;
    }

    public static void syncConfigData () {

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean isStructureDisabled (String type) {

        final boolean result = config.getBoolean("disable_" + type, "map_structures", false, "Should " + type + " generation be disabled?");
        return result;
    }

    public static boolean isScateredStructureDisabled (String type) {

        final boolean result = config.getBoolean("disable_" + type, "scattered_structures", false, "Should " + type + " generation be disabled?");
        return result;
    }

    public static boolean isPopulateDisabled (String type) {

        final boolean result = config.getBoolean("disable_" + type, "map_populates", false, "Should " + type + " generation be disabled?");
        return result;
    }
}