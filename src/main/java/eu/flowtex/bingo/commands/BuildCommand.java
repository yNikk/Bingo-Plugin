package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class BuildCommand implements CommandExecutor {
    private Main main;
    private ArrayList<UUID> buildMode;

    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        Player player = (Player) commandSender;
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (this.main.getBuildMode().contains(player.getUniqueId())) {
                    this.main.getBuildMode().remove(player.getUniqueId());
                    player.setGameMode(GameMode.SURVIVAL);
                    String msg0 = Main.getInstance().getConfig().getString("lang.buildModeQuit").replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
                } else if (!this.main.getBuildMode().contains(player.getUniqueId())) {
                    this.main.getBuildMode().add(player.getUniqueId());
                    player.setGameMode(GameMode.CREATIVE);
                    String msg1 = Main.getInstance().getConfig().getString("lang.buildModeJoin").replace("&", "§");
                    player.sendMessage(prefix + msg1);
                }
            } else {
                String msg2 = Main.getInstance().getConfig().getString("lang.noPermission").replace("&", "§");
                player.sendMessage(prefix + msg2);
            }
        }
        return false;
    }
}
