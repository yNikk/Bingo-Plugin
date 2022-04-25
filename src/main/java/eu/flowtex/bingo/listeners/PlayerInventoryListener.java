package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class PlayerInventoryListener implements Listener {
    private Main main;

    @EventHandler
    public void onInventoryItemMove(final InventoryClickEvent e) {
        if (Main.getTimer().isPaused()) {
            if (Main.getInstance().getBuildMode().contains(e.getWhoClicked().getUniqueId())) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase("§8» " + colorcode + "Bingo Items")) {
            e.setCancelled(true);
        }
    }
}
