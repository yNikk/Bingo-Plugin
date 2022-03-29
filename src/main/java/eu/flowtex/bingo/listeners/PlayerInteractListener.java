package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bSpiel Einstellungen") && Main.getBingoManager().gameState == GameManager.GameState.LOBBY) {
            Main.getBingoManager().getBingosettings().openSettingsInventory(e.getPlayer());
            e.setCancelled(true);
        } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTeamauswahl") && Main.getBingoManager().gameState == GameManager.GameState.TEAMJOIN) {
            Main.getBingoManager().getTeamManager().openTeamGUI(e.getPlayer());
            e.setCancelled(true);
        } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aRunde starten") && Main.getBingoManager().gameState == GameManager.GameState.TEAMJOIN) {
            Main.getBingoManager().startIngameState();
            e.setCancelled(true);
        }
    }
}