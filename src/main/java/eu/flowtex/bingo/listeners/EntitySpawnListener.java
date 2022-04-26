package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
    public EntitySpawnListener(Main main) {
    }

    @EventHandler
    public void onSpawn(final EntitySpawnEvent e) {
        if (Main.getTimer().isPaused() && e.getEntityType() != EntityType.PLAYER) {
            e.setCancelled(true);
        }
    }
}
