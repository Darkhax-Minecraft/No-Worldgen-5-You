package net.darkhax.noworldgen5you;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;

public class WorldgenConfig {

    private static Configuration config;
	public static final Map<String, Boolean> settings = new HashMap<>();

    public static Configuration initConfig (File file) {

        config = new Configuration(file);
        return config;
    }

    public static void syncConfigData () {

        if (config.hasChanged()) {
            config.save();
        }
    }
    
    public static boolean isStructureDisabled(String type, boolean isCustom) {
    	
    	boolean result = config.getBoolean("disable_" + type, "map_structures", false, "Should " + type + " generation be disabled?" + (isCustom ? " Note that this is not a vanilla structure!" : ""));
    	syncConfigData();
    	return result;
    }
}