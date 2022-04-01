package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class GamemodeCommand implements CommandExecutor {
    private static Main main;
    private String gamemode;

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if (!commandSender.isOp()) {
            String msg = Main.getInstance().getConfig().getString("lang.noPermission").replace("&", "§");
            commandSender.sendMessage(prefix + msg);
            return false;
        }

        Player player;
        if (args.length <= 2 && args.length != 0) {
            if (args.length == 1) {
                if (!(commandSender instanceof Player)) {
                    String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeHelp").replace("%colorcode%", colorcode).replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
                    return false;
                }
                player = (Player) commandSender;

            } else if (args[1].equalsIgnoreCase("@a")) {
                player = null;
            } else {
                player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    String msg0 = Main.getInstance().getConfig().getString("lang.gamemodePlayerNull").replace("%colorcode%", colorcode).replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
                    return false;
                }
            }

            int mode = 0;
            try {
                mode = Integer.valueOf(args[0]);
            } catch (Exception e) {
                String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeUnvalidNumber").replace("%colorcode%", colorcode).replace("&", "§");
                commandSender.sendMessage(prefix + msg0);
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
                    String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeUnvalidNumber").replace("%colorcode%", colorcode).replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
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
                    String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeSelf").replace("%colorcode%", colorcode).replace("%gamemode%", gamemode).replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
                } else {
                    String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeOther").replace("%colorcode%", colorcode).replace("%gamemode%", gamemode).replace("%player%", player.getName()).replace("&", "§");
                    commandSender.sendMessage(prefix + msg0);
                }
            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(gm);
                }
                String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeAll").replace("%colorcode%", colorcode).replace("%gamemode%", gamemode).replace("&", "§");
                commandSender.sendMessage(prefix + msg0);
            }
        } else {
            String msg0 = Main.getInstance().getConfig().getString("lang.gamemodeHelp").replace("%colorcode%", colorcode).replace("&", "§");
            commandSender.sendMessage(prefix + msg0);
        }
        return false;
    }
}