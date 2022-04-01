package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class CommandPreProcessListener implements Listener {
    public CommandPreProcessListener() {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCmd(final PlayerCommandPreprocessEvent e) {
        if (Main.getInstance().getCustomConfig().getBoolean("general.blockServerReload")) {
            if ((e.getMessage().startsWith("/rl") || e.getMessage().startsWith("/reload") || e.getMessage().startsWith("/bukkit:reload") || e.getMessage().startsWith("/bukkit:rl"))) {
                e.setCancelled(true);
                String msg0 = (Main.getInstance().getConfig().getString("lang.reloadCommandBlocked").replace("&", "§"));
                e.getPlayer().sendMessage(prefix + msg0);
            } else {
                e.setCancelled(false);
            }
        } else {
            e.setCancelled(false);
        }
    }
}