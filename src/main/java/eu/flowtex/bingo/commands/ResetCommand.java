package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ResetCommand implements CommandExecutor {

    public ResetCommand(Main main) {
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(Main.getInstance().getMessageConfiguration().getMessage("lang.resetKickMessage"));
        }
        if (commandSender.isOp()) {
            Main.getInstance().getDefaultConfiguration().getConfig().set("general.reset", (Object) true);
            Main.getInstance().saveConfig();
            Bukkit.shutdown();
        } else {
            commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.noPermission"));
        }
        return false;
    }
}
