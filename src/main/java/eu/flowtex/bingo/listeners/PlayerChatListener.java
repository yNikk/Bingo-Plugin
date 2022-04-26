package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class PlayerChatListener implements Listener {
    public PlayerChatListener(Main main) {
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        e.setFormat(colorcode + e.getPlayer().getName() + " " + ChatColor.DARK_GRAY + "»" + " " + ChatColor.GRAY + e.getMessage());
    }
}
