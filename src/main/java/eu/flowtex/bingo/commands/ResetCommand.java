package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ResetCommand extends AbstractCommand {

    /**
     * Creates and registers a new abstract command
     */
    public ResetCommand() {
        super("reset");
    }

    @Override
    public boolean command(CommandSender commandSender, String[] args) {
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
