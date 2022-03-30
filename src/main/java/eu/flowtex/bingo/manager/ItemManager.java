package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemRandomizer;
import eu.flowtex.bingo.utils.Team;
import eu.flowtex.bingo.utils.Utils;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import java.util.*;

public class ItemManager
{
    public ArrayList<Material> needed;
    public static HashMap<Integer, ArrayList<Material>> items;
    private static Main main;
    private String colorcode = Main.getInstance().getConfig().getString("general.colorcode");

    public ItemManager() {
        this.needed = new ArrayList<Material>();
        this.items = new HashMap<Integer, ArrayList<Material>>();
    }

    public void start() {
        ArrayList<Material> temp;
        for (temp = ItemRandomizer.getItems(Main.getBingoManager().getBingosettings().getDifficulty(), Main.getBingoManager().getBingosettings().getItems()); temp.size() != Main.getBingoManager().getBingosettings().getItems(); temp = ItemRandomizer.getItems(Main.getBingoManager().getBingosettings().getDifficulty(), Main.getBingoManager().getBingosettings().getItems())) {}
        Collections.shuffle(temp);
        this.needed = temp;
        for (final Team value : Team.values()) {
            if (value != Team.SPECTATOR) {
                this.items.put(value.getTeamid(), (ArrayList<Material>)this.needed.clone());
            }
            BoardManager.refreshScoreboard(value);
        }
        this.run();
    }

    public HashMap<Integer, ArrayList<Material>> getItems() {
        return this.items;
    }

    private void run() {
        final BukkitRunnable runnable = new BukkitRunnable() {
            public void run() {
                for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    final Team playerteam = Main.getBingoManager().getTeamManager().getTeamFromPlayer(onlinePlayer);
                    for (final ItemStack content : onlinePlayer.getInventory().getContents()) {
                        if (content != null) {
                            final ArrayList<Material> itemsfromteam = ItemManager.this.getItemsFromTeam(playerteam.getTeamid());
                            if (itemsfromteam.contains(content.getType())) {
                                itemsfromteam.remove(content.getType());
                                ItemManager.this.findItem(playerteam, content.getType(), onlinePlayer);
                                for (final Team value : Team.values()) {
                                    BoardManager.refreshScoreboard(value);
                                }
                                if (itemsfromteam.size() == 0) {
                                    Main.getBingoManager().winEvent(playerteam);
                                }
                            }
                        }
                    }
                }
            }
        };
        runnable.runTaskTimer((Plugin)Main.getInstance(), 0L, 10L);
    }

    public ArrayList<Material> getNeeded() {
        return this.needed;
    }

    public static ArrayList<Material> getItemsFromTeam(final int team) {
        return items.get(team);
    }

    public void setItems(final Integer team, final ArrayList<Material> materials) {
        this.items.put(team, materials);
    }

    public void findItem(final Team team, final Material material, final Player who) {
        final int itemsnow = Main.getBingoManager().getBingosettings().getItems() - this.getItemsFromTeam(team.getTeamid()).size();
        final Player[] p = new Player[1];
        Main.getBingoManager().getTeamManager().getPlayersInTeam(team).forEach(uuid -> {
            if (Bukkit.getPlayer(uuid) != null) {
                p[0] = Bukkit.getPlayer(uuid);
                p[0].sendTitle(colorcode + Utils.getItemName(material), "§7Neues Item gefunden");
                p[0].playSound(p[0].getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            }
            return;
        });
        Bukkit.broadcastMessage("§7» §8[" + colorcode + itemsnow + "§8/" + colorcode + Main.getBingoManager().getBingosettings().getItems() + "§8] " + colorcode + who.getName() + " §8(" + colorcode + "Team " + team.getTeamid() + "§8) " + "§7hat " + colorcode + Utils.getItemName(material) + " §7gefunden!");
    }

    public int getPlace(final Team team) {
        final int itemsnow = Main.getBingoManager().getBingosettings().getItems() - getItemsFromTeam(team.getTeamid()).size();
        int place = 1;
        for (final Map.Entry<Integer, ArrayList<Material>> teamArrayListEntry : items.entrySet()) {
            final int itemsfromotherteam = Main.getBingoManager().getBingosettings().getItems() - getItemsFromTeam(teamArrayListEntry.getKey()).size();
            if (itemsfromotherteam > itemsnow) {
                ++place;
            }
        }
        return place;
    }
}
