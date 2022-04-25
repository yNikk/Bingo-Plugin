package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.PlayerManager;
import eu.flowtex.bingo.utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class PingCommand extends AbstractCommand {

    /**
     * Creates and registers a new abstract command
     */
    public PingCommand() {
        super("ping");
    }

    @Override
    public boolean command(CommandSender commandSender, String[] args) {
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
