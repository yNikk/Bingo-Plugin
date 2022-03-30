package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BingoCommand implements CommandExecutor {
    private static Main main;
    private String colorcode = Main.getInstance().getConfig().getString("general.colorcode");

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if ((commandSender.isOp())) {
            commandSender.sendMessage("");
            commandSender.sendMessage(colorcode + "§lBingo Plugin §8» " + colorcode + "§lHelp");
            commandSender.sendMessage("");
            commandSender.sendMessage(colorcode + "/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage(colorcode + "/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage(colorcode + "/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage(colorcode + "/reset §8- §7Setzt die Welt zurück");
            commandSender.sendMessage(colorcode + "/edit §8- §7Versetzt dich in den Bau-Modus");
            commandSender.sendMessage("");
            return false;
        } else {
            commandSender.sendMessage("");
            commandSender.sendMessage(colorcode + "§lBingo Plugin §8»" + colorcode + "§lHelp");
            commandSender.sendMessage("");
            commandSender.sendMessage(colorcode + "/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage(colorcode + "/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage(colorcode + "/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage("");
            return false;
        }
    }
}
