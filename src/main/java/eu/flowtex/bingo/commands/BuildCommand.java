package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
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
        Player player = (Player) commandSender;
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (this.main.getBuildMode().contains(player.getUniqueId())) {
                    this.main.getBuildMode().remove(player.getUniqueId());
                    player.setGameMode(GameMode.SURVIVAL);
                    String msg0 = Main.getInstance().getConfig().getString("lang.buildModeQuit");
                    commandSender.sendMessage(msg0);
                } else if (!this.main.getBuildMode().contains(player.getUniqueId())) {
                    this.main.getBuildMode().add(player.getUniqueId());
                    player.setGameMode(GameMode.CREATIVE);
                    String msg1 = Main.getInstance().getConfig().getString("lang.buildModeJoin");
                    player.sendMessage(msg1);
                }
            } else {
                String msg2 = Main.getInstance().getConfig().getString("lang.noPermission");
                player.sendMessage(msg2);
            }
        }
        return false;
    }
}
