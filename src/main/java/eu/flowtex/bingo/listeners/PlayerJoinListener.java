package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.BoardManager;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class PlayerJoinListener implements Listener {
    private Main main;

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location locSP = new Location(Bukkit.getWorld("spawn"), 0, 25, 0, 0, 0);
        //p.teleport(locSP);
        String msg0 = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getDefaultConfiguration().getConfig().getString("general.gamename"));
        String msg00 = Main.getInstance().getMessageConfiguration().getMessage("lang.joinMessage").replaceAll("%player%", e.getPlayer().getDisplayName());
        String empty = "§f     §f";
        BoardManager.setCurrentScoreboard(e.getPlayer(), empty + "§7[" + colorcode + msg0 + "§7]" + empty);
        e.setJoinMessage(msg00);
        Main.getBingoManager().getTeamManager().initPlayer(e.getPlayer());
        //e.getPlayer().setPlayerListHeader("§lBINGO");
        if (Main.getBingoManager().gameState == GameManager.GameState.LOBBY) {
            if (e.getPlayer().isOp()) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(4, new ItemBuilder(Material.BLAZE_ROD).setDisplayname("§8» " + colorcode + "Spiel Einstellungen").build());
            } else {
                e.getPlayer().getInventory().clear();
            }
        } else if (Main.getBingoManager().gameState == GameManager.GameState.TEAMJOIN) {
            e.getPlayer().getInventory().clear();
            if (e.getPlayer().isOp()) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(6, new ItemBuilder(Material.SCUTE).setDisplayname("§8» " + colorcode + "Runde starten").build());
                e.getPlayer().getInventory().setItem(3, new ItemBuilder(Material.RED_BED).setDisplayname("§8» " + colorcode + "Teamauswahl").build());
            } else {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(5, new ItemBuilder(Material.RED_BED).setDisplayname("§8» " + colorcode + "Teamauswahl").build());
            }
        } else if (Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
            if (!e.getPlayer().isOp()) {
                if (Main.getInstance().getDefaultConfiguration().getConfig().getBoolean("general.removeTeamlessPlayersOnStart")) {
                    e.getPlayer().kickPlayer(Main.getInstance().getMessageConfiguration().getMessage("lang.noMember"));
                }
            }
        }
        if (Main.getInstance().reset()) {
            final World w = Bukkit.getWorld("world");
            final int spawnx = (int) w.getSpawnLocation().getX();
            final int spawnz = (int) w.getSpawnLocation().getZ();
            if (Main.getBingoManager().gameState != GameManager.GameState.INGAME) {
                final int y = w.getHighestBlockYAt(spawnx, spawnz);
                //e.getPlayer().teleport(new Location(w, (double) (spawnx + 5), (double) (y - 1), (double) (spawnz + 5)));
            }
            final Team t = Main.getBingoManager().getTeamManager().getTeamFromPlayer(e.getPlayer());
            if (t != Team.SPECTATOR) {
                Main.getBingoManager().getBoardManager().gameScoreboard(t);
            }
        }
    }
}