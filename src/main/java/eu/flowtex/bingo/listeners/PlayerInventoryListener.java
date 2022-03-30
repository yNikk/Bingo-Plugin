package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryListener implements Listener {
    @EventHandler
    public void onInventoryItemMove(final InventoryClickEvent e) {
        if (Main.getTimer().isPaused()) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§bBingo Items")) {
            e.setCancelled(true);
        }
    }
}
