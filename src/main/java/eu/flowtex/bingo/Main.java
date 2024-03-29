package eu.flowtex.bingo;

import com.google.common.reflect.ClassPath;
import eu.flowtex.bingo.commands.*;
import eu.flowtex.bingo.config.DefaultConfiguration;
import eu.flowtex.bingo.config.ItemsConfiguration;
import eu.flowtex.bingo.config.MessageConfiguration;
import eu.flowtex.bingo.config.ScoreboardConfiguration;
import eu.flowtex.bingo.listeners.*;
import eu.flowtex.bingo.manager.BoardManager;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
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
    private ArrayList<UUID> buildMode;
    private boolean reset;

    private DefaultConfiguration defaultConfiguration;
    private MessageConfiguration messageConfiguration;
    private ItemsConfiguration itemsConfiguration;
    private ScoreboardConfiguration scoreboardConfiguration;

    List<String> list = new ArrayList<String>();

    public void initialize() {
        this.saveDefaultConfig();
        if (this.getDefaultConfiguration().getConfig().getBoolean("general.reset")) {
            final File propertiesFile = new File(Bukkit.getWorldContainer(), "server.properties");
            this.reset = true;
            try {
                final FileInputStream resetWorld = new FileInputStream(propertiesFile);
                try {
                    final Properties properties = new Properties();
                    properties.load(resetWorld);
                    final File world = new File(Bukkit.getWorldContainer(), properties.getProperty("level-name"));
                    final File nether = new File(Bukkit.getWorldContainer(), "world_nether");
                    final File end = new File(Bukkit.getWorldContainer(), "world_end");

                    FileUtils.deleteDirectory(world);
                    FileUtils.deleteDirectory(nether);
                    FileUtils.deleteDirectory(end);
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
                    resetWorld.close();
                } catch (Throwable t) {
                    try {
                        resetWorld.close();
                    } catch (Throwable t2) {
                        t.addSuppressed(t2);
                    }
                    throw t;
                }
            } catch (IOException ex) {
            }
            this.getDefaultConfiguration().getConfig().set("general.reset", (Object) false);
            this.saveConfig();
        }
    }

    public void onEnable() {
        instance = this;
        this.defaultConfiguration = new DefaultConfiguration();
        this.messageConfiguration = new MessageConfiguration();
        this.scoreboardConfiguration = new ScoreboardConfiguration();
        this.bootMSG();
        this.buildMode = new ArrayList<UUID>();
        this.itemsConfiguration = new ItemsConfiguration();
        this.itemsConfiguration.read();
        registerListeners();
        registerCommands();
        //this.register("eu.flowtex.bingo.commands", "eu.flowtex.bingo.listeners");
        bingoManager = new GameManager();
        BoardManager.lobbyScoreboard();

        this.initialize();
        timer = new Timer();

        //World spawn = Bukkit.createWorld(new WorldCreator("spawn"));
        Bukkit.getWorlds().forEach(world -> {
            world.setGameRuleValue("spawnRadius", "0");
            if (this.getDefaultConfiguration().getConfig().getBoolean("general.hideAdvancements")) {
                world.setGameRuleValue("announceAdvancements", "false");
            } else {
                world.setGameRuleValue("announceAdvancements", "true");
            }
        });
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


    /**
    public void register(String commandPackageName, String... listenerPackageNameList) {
        try {
            for (String listenerPackageName : listenerPackageNameList) {
                for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader())
                        .getTopLevelClasses(listenerPackageName)) {
                    Class<?> currentClass = Class.forName(classInfo.getName());
                    if (Listener.class.isAssignableFrom(currentClass)) {
                        Bukkit.getPluginManager().registerEvents((Listener) currentClass.newInstance(), this);
                        System.out.println("REGISTERED EVENT: " + classInfo.getName());
                    }
                }
            }
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader())
                    .getTopLevelClasses(commandPackageName)) {
                Class<?> currentClass = Class.forName(classInfo.getName());
                currentClass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    */

    private void registerListeners() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener) new BlockPlaceListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new BlockBreakListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new CommandPreProcessListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new EntitySpawnListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new InventoryClickListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerChatListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerDamageListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerDropListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerFoodListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerInteractListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerJoinListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerPickupListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerPvPListener(this), (Plugin) this);
        pluginManager.registerEvents((Listener) new PlayerQuitListener(this), (Plugin) this);
    }

    private void registerCommands() {
        this.getCommand("bingo").setExecutor((CommandExecutor) new BingoCommand(this));
        this.getCommand("build").setExecutor((CommandExecutor) new BuildCommand(this));
        this.getCommand("gm").setExecutor((CommandExecutor) new GamemodeCommand(this));
        this.getCommand("items").setExecutor((CommandExecutor) new ItemsCommand(this));
        this.getCommand("ping").setExecutor((CommandExecutor) new PingCommand(this));
        this.getCommand("reset").setExecutor((CommandExecutor) new ResetCommand(this));
        this.getCommand("top").setExecutor((CommandExecutor) new TopCommand(this));
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

    public DefaultConfiguration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public MessageConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }

    public ScoreboardConfiguration getScoreboardConfiguration() {
        return scoreboardConfiguration;
    }
}
