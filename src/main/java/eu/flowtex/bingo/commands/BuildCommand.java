package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BuildCommand implements CommandExecutor {
    private Main main;
    private ArrayList<UUID> buildMode;

    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (this.main.getBuildMode().contains(((Player) commandSender).getUniqueId())) {
                    this.main.getBuildMode().remove(((Player) commandSender).getUniqueId());
                    ((Player) commandSender).setGameMode(GameMode.SURVIVAL);
                    String msg0 = Main.getInstance().getConfig().getString("lang.buildModeQuit");
                    commandSender.sendMessage(msg0);
                } else if (!this.main.getBuildMode().contains(((Player) commandSender).getUniqueId())) {
                    this.main.getBuildMode().add(((Player) commandSender).getUniqueId());
                    ((Player) commandSender).setGameMode(GameMode.CREATIVE);
                    String msg1 = Main.getInstance().getConfig().getString("lang.buildModeJoin");
                    commandSender.sendMessage(msg1);
                }
            } else {
                String msg2 = Main.getInstance().getConfig().getString("lang.noPermission");
                commandSender.sendMessage(msg2);
            }
        }
        return false;
    }
}
