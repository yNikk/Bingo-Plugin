package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.Team;
import eu.flowtex.bingo.utils.Utils;
import org.bukkit.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class GameManager {
    public GameState gameState;
    private SetupManager gameSettings;
    private TeamManager teamManager;
    private ItemManager itemManager;
    private Main main;

    public GameManager() {
        this.gameState = gameState.LOBBY;
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
        r.runTaskLater((Plugin) Main.getInstance(), 20L);
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public void startTeamState() {
        this.gameState = GameState.TEAMJOIN;
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.closeInventory();
            Main.getTimer().information = "§bFate/Bingo §8» §7Teamphase";
            player.sendTitle("§bTeamphase", "§7Wähle nun dein Team!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            player.getInventory().clear();
            if (player.isOp()) {
                player.getInventory().setItem(5, new ItemBuilder(Material.SCUTE).setDisplayname("§aRunde starten").build());
                player.getInventory().setItem(3, new ItemBuilder(Material.WHITE_BED).setDisplayname("§bTeamauswahl").build());
            } else {
                player.getInventory().setItem(4, new ItemBuilder(Material.WHITE_BED).setDisplayname("§bTeamauswahl").build());
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
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
            player.getInventory().clear();
            //player.sendMessage("§aDie Runde startet jetzt, versuche alle Items zu bekommen!");
            return;
        });
        Main.getTimer().paused = false;
        for (final UUID uuid : this.getTeamManager().getPlayersInTeam(Team.SPECTATOR)) {
            if (Bukkit.getPlayer(uuid) != null) {
                if (Bukkit.getPlayer(uuid).isOp()) {
                    Bukkit.getPlayer(uuid).setGameMode(GameMode.SPECTATOR);
                } else {
                    Bukkit.getPlayer(uuid).setGameMode(GameMode.SPECTATOR);
                    String msg = Main.getInstance().getConfig().getString("lang.noTeamOnStart");
                    Bukkit.getPlayer(uuid).kickPlayer(msg);
                }
            }
        }
    }

    public void winEvent(final Team team) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle("§aTeam " + team.getTeamid(), "hat Bingo gewonnen");
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
            player.sendMessage("");
            player.sendMessage("§lTeam " + team.getTeamid() + " hat Bingo gewonnen!");
            player.sendMessage("§lZeit ben\u00f6tigt §8- §7" + Utils.shortInteger(Main.getTimer().time));
            player.sendMessage("");
            return;
        });
        Main.getTimer().paused = true;
        Main.getTimer().information = "§bFate/Bingo §8» §bTeam" + team.getTeamid() + " §7hat gewonnen!";
    }

    public SetupManager getBingosettings() {
        return this.gameSettings;
    }

    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public enum GameState {
        LOBBY,
        TEAMJOIN,
        INGAME;
    }
}
