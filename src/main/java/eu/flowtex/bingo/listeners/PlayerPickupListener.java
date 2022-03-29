package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerPickupListener implements Listener {
    @EventHandler
    public void onPickup(final EntityPickupItemEvent e) {
        if (Main.getTimer().isPaused()) {
            e.setCancelled(true);
        }
    }
}
