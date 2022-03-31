package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class BingoCommand implements CommandExecutor {
    private static Main main;

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if ((commandSender.isOp())) {
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "§lBingo Plugin §8» " + colorcode + "§lHelp");
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage(prefix + colorcode + "/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage(prefix + colorcode + "/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage(prefix + colorcode + "/reset §8- §7Setzt die Welt zurück");
            commandSender.sendMessage(prefix + colorcode + "/edit §8- §7Versetzt dich in den Bau-Modus");
            commandSender.sendMessage("");
            return false;
        } else {
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "§lBingo Plugin §8»" + colorcode + "§lHelp");
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage(prefix + colorcode + "/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage(prefix + colorcode + "/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage("");
            return false;
        }
    }
}
