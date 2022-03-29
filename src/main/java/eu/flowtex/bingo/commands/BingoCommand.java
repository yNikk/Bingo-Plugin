package eu.flowtex.bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BingoCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if ((commandSender.isOp())) {
            commandSender.sendMessage("");
            commandSender.sendMessage("§b§lBingo Plugin §8» §b§lHelp");
            commandSender.sendMessage("§b/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage("§b/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage("§b/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage("§b/reset §8- §7Setzt die Welt zurück");
            commandSender.sendMessage("");
            return false;
        } else {
            commandSender.sendMessage("");
            commandSender.sendMessage("§b§lBingo Plugin §8» §b§lHelp");
            commandSender.sendMessage("§b/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage("§b/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage("§b/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage("");
            return false;
        }
    }
}
