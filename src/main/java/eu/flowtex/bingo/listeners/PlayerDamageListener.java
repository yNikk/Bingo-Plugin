package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {
    public PlayerDamageListener(Main main) {
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageEvent e) {
        if (Main.getTimer().isPaused() && e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
