package org.hqpots.bolt.utils;

import java.util.*;
import org.bukkit.plugin.*;
import java.io.*;
import org.bukkit.configuration.file.*;

public class ConfigManager
{
    public static Map<String, FileConfiguration> configs;
    
    static {
        ConfigManager.configs = new HashMap<String, FileConfiguration>();
    }
    
    public static boolean isFileLoaded(final String fileName) {
        return ConfigManager.configs.containsKey(fileName);
    }
    
    public static void load(final Plugin plugin, final String fileName) {
        final File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            try {
                plugin.saveResource(fileName, false);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!isFileLoaded(fileName)) {
            ConfigManager.configs.put(fileName, (FileConfiguration)YamlConfiguration.loadConfiguration(file));
        }
    }
    
    public static FileConfiguration get(final String fileName) {
        if (isFileLoaded(fileName)) {
            return ConfigManager.configs.get(fileName);
        }
        return null;
    }
    
    public static boolean update(final String fileName, final String path, final Object value) {
        if (isFileLoaded(fileName) && !ConfigManager.configs.get(fileName).contains(path)) {
            ConfigManager.configs.get(fileName).set(path, value);
            return true;
        }
        return false;
    }
    
    public static void set(final String fileName, final String path, final Object value) {
        if (isFileLoaded(fileName)) {
            ConfigManager.configs.get(fileName).set(path, value);
        }
    }
    
    public void addComment(final String fileName, final String path, final String... comments) {
        if (isFileLoaded(fileName)) {
            for (final String comment : comments) {
                if (!ConfigManager.configs.get(fileName).contains(path)) {
                    ConfigManager.configs.get(fileName).set("_COMMENT_" + comments.length, (Object)(" " + comment));
                }
            }
        }
    }
    
    public static void remove(final String fileName, final String path) {
        if (isFileLoaded(fileName)) {
            ConfigManager.configs.get(fileName).set(path, (Object)null);
        }
    }
    
    public static boolean contains(final String fileName, final String path) {
        return isFileLoaded(fileName) && ConfigManager.configs.get(fileName).contains(path);
    }
    
    public static void reload(final Plugin plugin, final String fileName) {
        final File file = new File(plugin.getDataFolder(), fileName);
        if (isFileLoaded(fileName)) {
            try {
                ConfigManager.configs.get(fileName).load(file);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void save(final Plugin plugin, final String fileName) {
        final File file = new File(plugin.getDataFolder(), fileName);
        if (isFileLoaded(fileName)) {
            try {
                ConfigManager.configs.get(fileName).save(file);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
