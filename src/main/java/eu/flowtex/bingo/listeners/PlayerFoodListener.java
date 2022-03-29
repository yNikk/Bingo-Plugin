package eu.flowtex.bingo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {
    @EventHandler
    public void onFoodChange(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
