package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    public int time;
    public boolean paused;
    public String information;

    public Timer() {
        this.information = "§bFate/Bingo §8» §7Lobbyphase";
        this.time = 0;
        this.paused = true;
        final BukkitRunnable runnable = new BukkitRunnable() {
            public void run() {
                if (Timer.this.isPaused()) {
                    Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(Timer.this.information)));
                } else {
                    Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(Utils.shortInteger(Timer.this.time))));
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