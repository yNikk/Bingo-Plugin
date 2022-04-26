package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class GamemodeCommand implements CommandExecutor {

    private String gamemode;

    public GamemodeCommand(Main main) {
    }


    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] args) {
        if (!commandSender.isOp()) {
            commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.noPermission"));
            return false;
        }

        Player player;
        if (args.length <= 2 && args.length != 0) {
            if (args.length == 1) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeHelp").replaceAll("%colorcode%", colorcode));
                    return false;
                }
                player = (Player) commandSender;

            } else if (args[1].equalsIgnoreCase("@a")) {
                player = null;
            } else {
                player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.playerNull").replaceAll("%colorcode%", colorcode));
                    return false;
                }
            }

            int mode = 0;
            try {
                mode = Integer.valueOf(args[0]);
            } catch (Exception e) {
                commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeUnvalidNumber").replaceAll("%colorcode%", colorcode));
                return false;
            }

            GameMode gm;
            switch (mode) {
                case 0:
                    gm = GameMode.SURVIVAL;
                    break;
                case 1:
                    gm = GameMode.CREATIVE;
                    break;
                case 2:
                    gm = GameMode.ADVENTURE;
                    break;
                case 3:
                    gm = GameMode.SPECTATOR;
                    break;
                default:
                    commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeUnvalidNumber").replaceAll("%colorcode%", colorcode));
                    return false;
            }

            if (gm.toString().equalsIgnoreCase("CREATIVE")) {
                this.gamemode = "Creative";
            } else if (gm.toString().equalsIgnoreCase("SURVIVAL")) {
                this.gamemode = "Survival";
            } else if (gm.toString().equalsIgnoreCase("ADVENTURE")) {
                this.gamemode = "Adventure";
            } else if (gm.toString().equalsIgnoreCase("SPECTATOR")) {
                this.gamemode = "Spectator";
            }

            if (player != null) {
                player.setGameMode(gm);

                if (args.length == 1) {
                    commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeUnvalidNumber").replaceAll("%colorcode%", colorcode).replaceAll("%gamemode%", gamemode));
                } else {
                    commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeOther").replaceAll("%colorcode%", colorcode).replaceAll("%player%", player.getName()).replaceAll("%gamemode%", gamemode));
                }
            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(gm);
                }
                commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeAll").replaceAll("%colorcode%", colorcode).replaceAll("%gamemode%", gamemode));
            }
        } else {
            commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.gamemodeHelp").replaceAll("%colorcode%", colorcode));
        }
        return false;
    }
}