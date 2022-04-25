package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.SetupManager;
import eu.flowtex.bingo.utils.Team;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public class InventoryClickListener implements Listener {
    private Main main;

    @EventHandler
    public void onInvClick(final InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§8» " + colorcode + "Spiel Einstellungen")) {
            e.setCancelled(true);
            final ItemStack itemStack = e.getCurrentItem();
            final Player player = (Player) e.getWhoClicked();
            if (itemStack != null) {
                final String local = itemStack.getItemMeta().getLocalizedName();
                if (local.equalsIgnoreCase("max")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
                }
                if (itemStack.getType() == Material.SCUTE) {
                    Main.getBingoManager().startTeamState();
                    return;
                }
                if (local.equalsIgnoreCase("maxplayers+")) {
                    Main.getBingoManager().getBingosettings().addMaxPlayers(player);
                } else if (local.equalsIgnoreCase("maxplayers-")) {
                    Main.getBingoManager().getBingosettings().removeMaxPlayer(player);
                } else if (local.equalsIgnoreCase("items-")) {
                    Main.getBingoManager().getBingosettings().removeItems(player);
                } else if (local.equalsIgnoreCase("items+")) {
                    Main.getBingoManager().getBingosettings().addItems(player);
                } else if (local.startsWith("dif_")) {
                    SetupManager.ItemDifficulty difficulty = null;
                    final String dif = local.replace("dif_", "");
                    if (dif.equalsIgnoreCase("PEACEFUL")) {
                    difficulty = SetupManager.ItemDifficulty.EASY;
                    } else if (dif.equalsIgnoreCase("EASY")) {
                        difficulty = SetupManager.ItemDifficulty.NORMAL;
                    } else if (dif.equalsIgnoreCase("NORMAL")) {
                        difficulty = SetupManager.ItemDifficulty.HARD;
                    } else if (dif.equalsIgnoreCase("HARD")) {
                        difficulty = SetupManager.ItemDifficulty.ULTRA;
                    }
                    Main.getBingoManager().getBingosettings().setDifficulty(player, difficulty);
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase("§8» " + colorcode + "Teamauswahl")) {
            if (e.getCurrentItem() != null) {
                final Team team = Team.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
                final Team playerteam = Main.getBingoManager().getTeamManager().getTeamFromPlayer((Player) e.getWhoClicked());
                if (team.getTeamid() == playerteam.getTeamid()) {
                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 1.0f);
                    e.getWhoClicked().closeInventory();
                    return;
                }
                if (Main.getBingoManager().getTeamManager().getPlayersInTeam(team).size() >= Main.getBingoManager().getBingosettings().getMaxplayersinteam()) {
                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0f, 1.0f);
                    e.getWhoClicked().closeInventory();
                    return;
                }
                Main.getBingoManager().getTeamManager().setTeam((Player) e.getWhoClicked(), team);
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                e.getWhoClicked().closeInventory();
            }
        } else if (e.getView().getTitle().equals("§8» " + colorcode + "Bingo Items")) {
            e.setCancelled(true);
        }
    }
}
