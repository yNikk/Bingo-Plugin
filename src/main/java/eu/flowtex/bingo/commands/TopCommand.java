package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

    public TopCommand(Main main) {
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if (Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
            final Player player = (Player) commandSender;
            if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                if (player.getWorld().getName().equals("world")) {
                    final int x = (int) player.getLocation().getX();
                    final int z = (int) player.getLocation().getZ();
                    final int y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);
                    player.teleport(new Location(Bukkit.getWorld("world"), (double) x, (double) (y + 2), (double) z));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                } else {
                    final int x = (int) Bukkit.getWorld("world").getSpawnLocation().getX();
                    final int z = (int) Bukkit.getWorld("world").getSpawnLocation().getZ();
                    final int y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);
                    player.teleport(new Location(Bukkit.getWorld("world"), (double) x, (double) (y + 1), (double) z));
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                }
            } else {
                commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.topNotReady"));
            }
        } else {
            commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.commandNotReady"));
        }
        return false;
    }
}
