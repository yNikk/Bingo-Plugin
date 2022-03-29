package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.Team;
import eu.flowtex.bingo.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ItemsCommand implements CommandExecutor {

    private static Main main;

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if (Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
            final Player player = (Player) commandSender;
            final Team team = Main.getBingoManager().getTeamManager().getTeamFromPlayer(player);
            Inventory remainingItems = Bukkit.createInventory((InventoryHolder) null, 27, "§bBingo Items");
            //if (Main.getBingoManager().getBingosettings().getItems() > 9) {
            //    if (Main.getBingoManager().getBingosettings().getItems() < 18) {
            //        remainingItems = Bukkit.createInventory((InventoryHolder) null, 18, "Bingo Items");
            //    } else {
            //        remainingItems = Bukkit.createInventory((InventoryHolder) null, 27, "Bingo Items");
            //    }
            //}
            final ArrayList<Material> teamitems = Main.getBingoManager().getItemManager().getItemsFromTeam(team.getTeamid());
            for (final Material material : Main.getBingoManager().getItemManager().getNeeded()) {
                if (teamitems != null) {
                    if (!teamitems.contains(material)) {
                        remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname(Utils.getItemName(material)).setLore("§aDu hast dieses Item schon gefunden!").enchant(Enchantment.OXYGEN, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).build()});
                    } else {
                        remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname(Utils.getItemName(material)).setLore("§cDieses Item muss noch gefunden werden!").build()});
                    }
                } else {
                    remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname(Utils.getItemName(material)).setLore("§cDieses Item muss noch gefunden werden!").build()});
                }
            }
            player.openInventory(remainingItems);
        } else {
            String msg = Main.getInstance().getConfig().getString("lang.commandNotReady");
            commandSender.sendMessage(msg);
        }
        return false;
    }
}
