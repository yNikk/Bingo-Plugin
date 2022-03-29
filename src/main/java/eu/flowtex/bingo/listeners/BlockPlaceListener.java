package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (Main.getTimer().isPaused()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
}
