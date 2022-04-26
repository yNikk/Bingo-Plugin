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
    private ArrayList<UUID> buildMode;

    public BuildCommand(Main main) {
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (commandSender.isOp()) {
                if (Main.getInstance().getBuildMode().contains(player.getUniqueId())) {
                    Main.getInstance().getBuildMode().remove(player.getUniqueId());
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.buildModeQuit"));
                } else if (!Main.getInstance().getBuildMode().contains(player.getUniqueId())) {
                    Main.getInstance().getBuildMode().add(player.getUniqueId());
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.buildModeJoin"));
                }
            } else {
                player.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.noPermission"));
            }
        }
        return false;
    }
}
