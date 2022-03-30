package eu.flowtex.bingo;

import eu.flowtex.bingo.commands.*;
import eu.flowtex.bingo.listeners.*;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.Config;
import eu.flowtex.bingo.manager.BoardManager;
import eu.flowtex.bingo.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static Timer timer;
    private static GameManager bingoManager;
    public static Config config;
    private ArrayList<UUID> buildMode;
    private Main main;
    public String prefix = getConfig().getString("general.prefix");
    public String colorcode = getConfig().getString("general.colorcode");
    private boolean reset;
    private File customConfigFile;
    private FileConfiguration customConfig;
    List<String> list = new ArrayList<String>();

    public void onLoad() {
        this.saveDefaultConfig();
        if (this.getConfig().getBoolean("general.reset")) {
            final File propertiesFile = new File(Bukkit.getWorldContainer(), "server.properties");
            this.reset = true;
            try {
                final FileInputStream stream = new FileInputStream(propertiesFile);
                try {
                    final Properties properties = new Properties();
                    properties.load(stream);
                    final File world = new File(Bukkit.getWorldContainer(), properties.getProperty("level-name"));
                    FileUtils.deleteDirectory(world);
                    final File nether = new File(Bukkit.getWorldContainer(), "world_nether");
                    FileUtils.deleteDirectory(nether);
                    world.mkdirs();
                    new File(world, "data").mkdirs();
                    new File(world, "datapacks").mkdirs();
                    new File(world, "playerdata").mkdirs();
                    new File(world, "poi").mkdirs();
                    new File(world, "region").mkdirs();
                    new File(nether, "data").mkdirs();
                    new File(nether, "datapacks").mkdirs();
                    new File(nether, "playerdata").mkdirs();
                    new File(nether, "poi").mkdirs();
                    new File(nether, "region").mkdirs();
                    stream.close();
                } catch (Throwable t) {
                    try {
                        stream.close();
                    } catch (Throwable t2) {
                        t.addSuppressed(t2);
                    }
                    throw t;
                }
            } catch (IOException ex) {
            }
            this.getConfig().set("general.reset", (Object) false);
            this.saveConfig();
        }
    }

    public void onEnable() {
        Main.instance = this;
        Main.timer = new Timer();
        Main.bingoManager = new GameManager();
        BoardManager.startScoreboard();
        this.createCustomConfig();
        this.registerListeners();
        this.saveDefaultConfig();
        this.bootMSG();
        this.registerCommands();
        this.buildMode = new ArrayList<UUID>();
        //World spawn = Bukkit.createWorld(new WorldCreator("spawn"));
        Bukkit.getWorlds().forEach(world -> {
            world.setGameRuleValue("spawnRadius", "0");
            world.setGameRuleValue("announceAdvancements", "false");
        });
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void bootMSG() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        Plugin getBingoPlugin = getServer().getPluginManager().getPlugin("Bingo");
        String ServerVersion = Bukkit.getVersion();
        String PluginVersion = getDescription().getVersion();
        console.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------------------------------------");
        console.sendMessage("");
        console.sendMessage(ChatColor.LIGHT_PURPLE + " ____                                __     ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "/\\  _`\\    __                       /\\ \\    ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "\\ \\ \\L\\ \\ /\\_\\   ___      __     ___\\ \\ \\   ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + " \\ \\ _ <'\\/\\ \\ /' _ `\\  /'_ `\\  / __`\\ \\ \\  ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "  \\ \\ \\L\\ \\ \\ \\/\\ \\/\\ \\/\\ \\L\\ \\/\\ \\L\\ \\ \\_\\ ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "   \\ \\____/ \\ \\_\\ \\_\\ \\_\\ \\____ \\ \\____/\\/\\_\\");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "    \\/___/   \\/_/\\/_/\\/_/\\/___L\\ \\/___/  \\/_/");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "                           /\\____/           ");
        console.sendMessage(ChatColor.LIGHT_PURPLE + "                           \\_/__/");
        console.sendMessage("");
        console.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------------------------------------");
        console.sendMessage("Bingo! was activated!");
        console.sendMessage("");
        console.sendMessage("Bingo Gamemode Plugin created by VelixCC Development Team");
        console.sendMessage("Concept by BlackRoseee");
        console.sendMessage("Authors: yNikk, borekking");
        console.sendMessage("");
        console.sendMessage("Plugin Version: " + PluginVersion);
        console.sendMessage("Server Version: " + ServerVersion);
        if (getServer().getPluginManager().getPlugin("CloudNet-Bridge") != null) {
            console.sendMessage("Successfully synced up with CloudNet");
        } else console.sendMessage("Could not sync up with CloudNet: CloudNet is not installed");
        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            console.sendMessage("Successfully synced up with ProtocolLib");
        } else {
            console.sendMessage("Could not sync up with ProtocolLib: ProtocolLib is not installed");
            console.sendMessage("The plugin is now shut down due to a requirement which is not satisfied");
            getServer().getPluginManager().disablePlugin(getBingoPlugin);
        }
        console.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------------------------------------");
    }

    public void registerListeners() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener) new BlockBreakListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new BlockPlaceListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new EntitySpawnListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new InventoryClickListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerChatListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerDamageListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerDropListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerFoodListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerInteractListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerInventoryListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerJoinListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerPickupListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerPvPListener(), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerQuitListener(), (Plugin) this);
    }

    public void registerCommands() {
        this.getCommand("reset").setExecutor((CommandExecutor) new ResetCommand());
        this.getCommand("items").setExecutor((CommandExecutor) new ItemsCommand());
        this.getCommand("top").setExecutor((CommandExecutor) new TopCommand());
        this.getCommand("bingo").setExecutor((CommandExecutor) new BingoCommand());
        this.getCommand("gm").setExecutor((CommandExecutor) new GamemodeCommand());
        this.getCommand("build").setExecutor((CommandExecutor) new BuildCommand());
    }

    public void onDisable() {
    }

    public static Main getInstance() {
        return Main.instance;
    }

    public static Timer getTimer() {
        return Main.timer;
    }

    public static GameManager getBingoManager() {
        return Main.bingoManager;
    }

    public boolean reset() {
        return this.reset;
    }

    public ArrayList<UUID> getBuildMode() {
        return this.buildMode;
    }
}
