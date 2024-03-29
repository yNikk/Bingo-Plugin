package eu.flowtex.bingo.commands;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.Team;
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

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class ItemsCommand implements CommandExecutor {

    public ItemsCommand(Main main) {
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String invoke, final String[] arguments) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        if (Main.getBingoManager().gameState == GameManager.GameState.INGAME) {
            final Player player = (Player) commandSender;
            final Team team = Main.getBingoManager().getTeamManager().getTeamFromPlayer(player);
            Inventory remainingItems = Bukkit.createInventory((InventoryHolder) null, 54, "§8» " + colorcode + "Bingo Items");
            final ArrayList<Material> teamitems = Main.getBingoManager().getItemManager().getItemsFromTeam(team.getTeamid());
            for (final Material material : Main.getBingoManager().getItemManager().getNeeded()) {
                if (teamitems != null) {
                    if (!teamitems.contains(material)) {
                        remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname("§8» " + colorcode + ItemBuilder.getItemName(material)).setLore("§aDu hast dieses Item schon gefunden!").enchant(Enchantment.OXYGEN, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS).build()});
                    } else {
                        remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname("§8» " + colorcode + ItemBuilder.getItemName(material)).setLore("§cDieses Item muss noch gefunden werden!").build()});
                    }
                } else {
                    remainingItems.addItem(new ItemStack[]{new ItemBuilder(material).setDisplayname("§8» " + colorcode + ItemBuilder.getItemName(material)).setLore("§cDieses Item muss noch gefunden werden!").build()});
                }
            }
            for (int i = 0; i < 54; ++i) {
                if (remainingItems.getItem(i) == null) {
                    remainingItems.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayname(" ").build());
                }
            }
            player.openInventory(remainingItems);
        } else {
            commandSender.sendMessage(Main.getInstance().getMessageConfiguration().getMessage("lang.commandNotReady"));
        }
        return false;
    }
}
