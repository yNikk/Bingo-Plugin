package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class Config {
    private static Main main;
    private File customConfigFile;
    private FileConfiguration customConfig;

    public static void main(Main main) {
        Config.main = main;
    }

    public void saveConfig() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        try {
            main.getConfig().save(this.customConfigFile);
        } catch (IOException var2) {
            console.sendMessage("ERROR: Could not save config to " + this.customConfigFile);
        }
    }

    public void createCustomConfig() {
        customConfigFile = new File(main.getDataFolder(), "config.yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        customConfig.options().copyDefaults(true);
        customConfig.addDefault("reset", "false");
        saveConfig();
    }
}
