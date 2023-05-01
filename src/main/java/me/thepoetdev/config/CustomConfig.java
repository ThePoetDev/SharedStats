package me.thepoetdev.config;

import me.thepoetdev.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CustomConfig {
    private static Main plugin;
    private static FileConfiguration config = null;
    private static File configFile = null;

    public static void registerCustomConfig(Main plugin){
        CustomConfig.plugin = plugin;
        saveDefaultConfig();
    }

    public static void reloadConfig() {
        if (configFile == null) configFile = new File(plugin.getDataFolder(), "recipes.yml");

        config = YamlConfiguration.loadConfiguration(configFile);

        InputStream defaultStream = plugin.getResource("recipes.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            config.setDefaults(defaultConfig);
        }
    }

    public static FileConfiguration getConfig() {
        if (config == null) reloadConfig();
        return config;
    }

    public static void saveConfig() {
        if (config == null || configFile == null) return;

        try {
            getConfig().save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
        }
    }

    public static void saveDefaultConfig() {
        if (configFile == null) configFile = new File(plugin.getDataFolder(), "recipes.yml");

        if (!configFile.exists()) plugin.saveResource("recipes.yml", false);

    }

}
