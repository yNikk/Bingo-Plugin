package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.ScoreboardUtils;
import eu.flowtex.bingo.utils.Team;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location locSP = new Location(Bukkit.getWorld("spawn"), 0, 25, 0, 0, 0);
        //p.teleport(locSP);
        ScoreboardUtils.setCurrentScoreboard(e.getPlayer(), "§f        §8[§bFate/Bingo§8]        §f");
        Main.getBingoManager().getTeamManager().initPlayer(e.getPlayer());
        e.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + e.getPlayer().getName());
        //e.getPlayer().setPlayerListHeader("§lBINGO");
        if (Main.getBingoManager().gameState == GameManager.GameState.LOBBY) {
            if (e.getPlayer().isOp()) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(4, new ItemBuilder(Material.REPEATING_COMMAND_BLOCK).setDisplayname("§bSpiel Einstellungen").build());
            } else {
                e.getPlayer().getInventory().clear();
            }
        } else if (Main.getBingoManager().gameState == GameManager.GameState.TEAMJOIN) {
            e.getPlayer().getInventory().clear();
            if (e.getPlayer().isOp()) {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(8, new ItemBuilder(Material.SCUTE).setDisplayname("§aRunde starten").build());
                e.getPlayer().getInventory().setItem(3, new ItemBuilder(Material.WHITE_BED).setDisplayname("§bTeamauswahl").build());
            } else {
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(5, new ItemBuilder(Material.WHITE_BED).setDisplayname("§bTeamauswahl").build());
            }
        } else if (Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
            if (!e.getPlayer().isOp()) {
                String msg = Main.getInstance().getConfig().getString("lang.noMember");
                e.getPlayer().kickPlayer(msg);
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
                Main.getBingoManager().getItemManager().updateScoreboard(t);
            }
            if (Main.getBingoManager().getTeamManager().getTeamFromPlayer(e.getPlayer()) == Team.SPECTATOR && Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
                e.getPlayer().sendMessage("§7Du bist nun Spectator!");
            }
        }
    }
}