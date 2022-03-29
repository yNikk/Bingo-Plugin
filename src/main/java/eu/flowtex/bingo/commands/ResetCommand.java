package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand implements CommandExecutor {
    private static Main main;

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            String msg = Main.getInstance().getConfig().getString("lang.resetKickMessage");
            player.kickPlayer(msg);
        }
        if (commandSender.isOp()) {
            Main.getInstance().getConfig().set("general.reset", (Object) true);
            Main.getInstance().saveConfig();
            Bukkit.shutdown();
        } else {
            String msg = Main.getInstance().getConfig().getString("lang.noPermission");
            commandSender.sendMessage(msg);
        }
        return false;
    }
}
