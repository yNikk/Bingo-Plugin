package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if (!commandSender.isOp()) {
            String msg = Main.getInstance().getConfig().getString("lang.noPermission");
            commandSender.sendMessage(msg);
            return false;
        }

        Player player;
        if (args.length <= 2 && args.length != 0) {
            if (args.length == 1) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage("§7/gamemode §b[Player/@a]");
                    return false;
                }
                player = (Player) commandSender;

            } else if (args[1].equalsIgnoreCase("@a")) {
                player = null;
            } else {
                player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    commandSender.sendMessage("§bSpieler " + args[1] + "§7 nicht gefunden.");
                    return false;
                }
            }

            int mode = 0;
            try {
                mode = Integer.valueOf(args[0]);
            } catch (Exception e) {
                commandSender.sendMessage("§7Bitte gib einen validen Spielmodus an!");
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
                    commandSender.sendMessage("§7Bitte gib einen validen Spielmodus an!");
                    return false;
            }

            if (player != null) {
                player.setGameMode(gm);

                if (args.length == 1) {
                    commandSender.sendMessage("§7Du befindest dich nun im Gamemode " + "§b" + gm.toString() + "§7!");
                } else {
                    commandSender.sendMessage("§7" + player.getName() + " ist nun im Gamemode " + "§b" + "" + gm.toString() + "§7!");
                }
            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(gm);
                }
                commandSender.sendMessage("§7Jeder Spieler befindet sich nun im Gamemode " + "§b" + gm.toString() + "§7!");
            }

        } else
            commandSender.sendMessage("§7/gamemode §b<Mode> [Player]");

        return false;
    }
}