package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.AbstractCommand;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class BuildCommand extends AbstractCommand {

    private ArrayList<UUID> buildMode;

    /**
     * Creates and registers a new abstract command
     */
    public BuildCommand() {
        super("build", "edit");
    }

    @Override
    public boolean command(CommandSender commandSender, String[] args) {
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
