package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {
    public PlayerFoodListener(Main main) {
    }

    @EventHandler
    public void onFoodChange(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}
