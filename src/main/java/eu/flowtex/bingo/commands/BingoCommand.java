package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.utils.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;


public class BingoCommand extends AbstractCommand {

    /**
     * Creates and registers a new abstract command
     */
    public BingoCommand() {
        super("bingo");
    }

    @Override
    public boolean command(CommandSender commandSender, String[] args) {
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
            commandSender.sendMessage(prefix + colorcode + "/ping §8- §7Wiedergibt deine Latenz");
            commandSender.sendMessage(prefix + colorcode + "/gm §8- §7Shortcut für /gamemode");
            commandSender.sendMessage("");
            return false;
        } else {
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "§lBingo Plugin §8»" + colorcode + "§lHelp");
            commandSender.sendMessage("");
            commandSender.sendMessage(prefix + colorcode + "/bingo §8- §7Zeigt diese Nachricht");
            commandSender.sendMessage(prefix + colorcode + "/top §8- §7Teleportiert dich an die Oberfläche");
            commandSender.sendMessage(prefix + colorcode + "/items §8- §7Zeigt die Items, die du sammeln musst");
            commandSender.sendMessage(prefix + colorcode + "/ping §8- §7Wiedergibt deine Latenz");
            commandSender.sendMessage("");
            return false;
        }
    }
}
