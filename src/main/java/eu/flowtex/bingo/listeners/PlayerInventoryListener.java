package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryListener implements Listener {
    private Main main;
    private String colorcode = Main.getInstance().getConfig().getString("general.colorcode");

    @EventHandler
    public void onInventoryItemMove(final InventoryClickEvent e) {
        if (Main.getTimer().isPaused()) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§8» " + colorcode + "Bingo Items")) {
            e.setCancelled(true);
        }
    }
}
