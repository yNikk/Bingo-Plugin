package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    public PingCommand(Main main) {
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.playerNull"));
                return true;
            }
            p.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.playerNull").replaceAll("%player%", target.getName()).replaceAll("%targetPing%", String.valueOf(PlayerManager.getPing(target))));
        } else {
            p.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.pingSelf").replace("%ping%", String.valueOf(PlayerManager.getPing(p))));

        }
        return false;
    }
}
