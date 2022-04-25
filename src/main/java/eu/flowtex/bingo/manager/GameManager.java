package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.Team;
import eu.flowtex.bingo.utils.TimeProvider;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class GameManager {
    public GameState gameState;
    private final SetupManager gameSettings;
    private final TeamManager teamManager;
    private final ItemManager itemManager;
    public BoardManager boardManager;
    private Main main;

    public GameManager() {
        this.gameState = GameState.LOBBY;
        this.gameSettings = new SetupManager(1, 9, SetupManager.ItemDifficulty.NORMAL);
        this.teamManager = new TeamManager();
        this.itemManager = new ItemManager();
        final BukkitRunnable r = new BukkitRunnable() {
            public void run() {
                if (Main.getInstance().reset()) {
                    final World w = Bukkit.getWorld("world");
                    final int spawnx = (int) w.getSpawnLocation().getX();
                    final int spawnz = (int) w.getSpawnLocation().getZ();
                    final int y = w.getHighestBlockYAt(spawnx, spawnz) + 4;
                }
            }
        };
        r.runTaskLater(Main.getInstance(), 20L);
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public void startTeamState() {
        this.gameState = GameState.TEAMJOIN;
        Bukkit.getOnlinePlayers().forEach(player -> {
            String msg0 = Main.getInstance().getGeneralConfig().getString("general.gamename").replace("&", "§").replace("%colorcode%", colorcode);
            String msg1 = (Main.getInstance().getMessageConfig().getString("lang.actionbarTeam").replace("&", "§").replace("%colorcode%", colorcode).replace("%gamename%", msg0));
            Main.getTimer().information = msg1;
            player.closeInventory();
            player.sendTitle(colorcode + "Teamphase", "§7Wähle nun dein Team!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            player.getInventory().clear();
            if (player.isOp()) {
                player.getInventory().setItem(5, new ItemBuilder(Material.SCUTE).setDisplayname("§8» " + colorcode + "Runde starten").build());
                player.getInventory().setItem(3, new ItemBuilder(Material.RED_BED).setDisplayname("§8» " + colorcode + "Teamauswahl").build());
            } else {
                player.getInventory().setItem(4, new ItemBuilder(Material.RED_BED).setDisplayname("§8» " + colorcode + "Teamauswahl").build());
            }
        });
    }

    public void startIngameState() {
        this.gameState = GameState.INGAME;
        this.itemManager.start();
        Bukkit.getOnlinePlayers().forEach(player -> {
            final World w = Bukkit.getWorld("world");
            Location loc = new Location(Bukkit.getWorld("world"), 0, w.getHighestBlockYAt(0, 0), 0, 0, 0);
            player.teleport(loc);
            player.closeInventory();
            player.getInventory().clear();
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
            return;
        });
        Main.getTimer().paused = false;
        for (final UUID uuid : this.getTeamManager().getPlayersInTeam(Team.SPECTATOR)) {
            if (Bukkit.getPlayer(uuid) != null) {
                if (Bukkit.getPlayer(uuid).isOp()) {
                    Bukkit.getPlayer(uuid).setGameMode(GameMode.SPECTATOR);
                } else {
                    if (Main.getInstance().getGeneralConfig().getBoolean("general.removeTeamlessPlayersOnStart")) {
                        Bukkit.getPlayer(uuid).setGameMode(GameMode.SPECTATOR);
                        String msg0 = Main.getInstance().getMessageConfig().getString("lang.noTeamOnStart").replace("&", "§");
                        Bukkit.getPlayer(uuid).kickPlayer(msg0);
                    } else {
                        Bukkit.getPlayer(uuid).setGameMode(GameMode.SPECTATOR);
                    }
                }
            }
        }
    }

    public void winEvent(final Team team) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle(colorcode + "Bingo Gewinner", "§7Team " + team.getTeamid() + " hat die Runde gewonnen!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1.0f, 1.0f);
            BoardManager.endScoreboard(team);
            player.sendMessage("");
            player.sendMessage(prefix + colorcode + "§lBingo §8» " + colorcode + "§lGewinner");
            player.sendMessage("");
            player.sendMessage(prefix + "§8| §7Das " + colorcode + "Team Nummer " + colorcode + team.getTeamid() + " §7hat diese Runde " + colorcode + "Bingo §7gewonnen!");
            player.sendMessage(prefix + "§8| §7Sie haben dafür " + colorcode + TimeProvider.Integer(Main.getTimer().time) + " §7Zeit §7benötigt!");
            player.sendMessage(prefix + "§8| §7Falls du ein " + colorcode + "Admin §7bist, gib " + colorcode + "/reset §7ein, um eine neue Map zu laden!");
            player.sendMessage("");
            return;
        });
        String msg0 = Main.getInstance().getGeneralConfig().getString("general.gamename").replace("&", "§").replace("%colorcode%", colorcode);
        String msg1 = (Main.getInstance().getMessageConfig().getString("lang.actionbarEnd").replace("&", "§").replace("%winTeam%", String.valueOf(team.getTeamid())).replace("%colorcode%", colorcode).replace("%gamename%", msg0));
        Main.getTimer().paused = true;
        Main.getTimer().information = msg1;
    }

    public SetupManager getBingosettings() {
        return this.gameSettings;
    }

    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public BoardManager getBoardManager() {
        return this.boardManager;
    }

    public enum GameState {
        LOBBY,
        TEAMJOIN,
        INGAME
    }
}
