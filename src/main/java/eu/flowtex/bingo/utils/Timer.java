package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class Timer {
    public int time;
    public boolean paused;
    public String information;
    private Main main;

    public Timer() {
        String msg0 = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getDefaultConfiguration().getConfig().getString("general.gamename").replace("%colorcode%", colorcode));
        String msg1 = (Main.getInstance().getMessageConfig().getString("lang.actionbarLobby").replace("&", "§").replace("%colorcode%", colorcode).replace("%gamename%", msg0));
        this.information = msg1;
        this.time = 0;
        this.paused = true;
        final BukkitRunnable runnable = new BukkitRunnable() {
            public void run() {
                if (Timer.this.isPaused()) {
                    Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(Timer.this.information)));
                } else {
                    Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent("§8» " + TimeProvider.Integer(Timer.this.time))));
                    final Timer this$0 = Timer.this;
                    ++this$0.time;
                }
            }
        };
        runnable.runTaskTimer((Plugin) Main.getInstance(), 0L, 20L);
    }

    public boolean isPaused() {
        return this.paused;
    }
    public void setInformation(final String information) {
        this.information = information;
    }
}
