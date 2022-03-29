package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropListener implements Listener {
    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent e) {
        if (Main.getTimer().isPaused()) {
            e.setCancelled(true);
        }
    }
}
