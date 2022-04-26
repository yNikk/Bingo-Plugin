package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class CommandPreProcessListener implements Listener {
    public CommandPreProcessListener(Main main) {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCmd(final PlayerCommandPreprocessEvent e) {
        if (Main.getInstance().getDefaultConfiguration().getConfig().getBoolean("general.blockServerReload")) {
            if ((e.getMessage().startsWith("/rl") || e.getMessage().startsWith("/reload") || e.getMessage().startsWith("/bukkit:reload") || e.getMessage().startsWith("/bukkit:rl"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.reloadCommandBlocked"));
            } else {
                e.setCancelled(false);
            }
        } else {
            e.setCancelled(false);
        }
    }
}