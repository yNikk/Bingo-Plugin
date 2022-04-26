package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerPvPListener implements Listener {
    public PlayerPvPListener(Main main) {
    }

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
