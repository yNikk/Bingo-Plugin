package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemRandomizer;
import eu.flowtex.bingo.utils.ScoreboardUtils;
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
    public HashMap<Integer, ArrayList<Material>> items;

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
            this.updateScoreboard(value);
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
                                    ItemManager.this.updateScoreboard(value);
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

    public ArrayList<Material> getItemsFromTeam(final int team) {
        return this.items.get(team);
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
                p[0].sendTitle("§b" + Utils.getItemName(material), "§7Neues Item gefunden");
                p[0].playSound(p[0].getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            }
            return;
        });
        Bukkit.broadcastMessage("§7» §8[§b" + itemsnow + "§8/§b" + Main.getBingoManager().getBingosettings().getItems() + "§8] §b" + who.getName() + " §8(§bTeam " + team.getTeamid() + "§8) " + "§7hat §b" + Utils.getItemName(material) + " §7gefunden!");
    }

    public void updateScoreboard(final Team team) {
        if (team == Team.SPECTATOR) {
            return;
        }
        final ArrayList<Material> teamitems = this.getItemsFromTeam(team.getTeamid());
        for (final UUID uuid : Main.getBingoManager().getTeamManager().getPlayersInTeam(team)) {
            if (Bukkit.getPlayer(uuid) != null) {
                final Player player = Bukkit.getPlayer(uuid);
                final int online = Bukkit.getOnlinePlayers().size();
                final int placement = this.getPlace(team);
                final int teamid = team.getTeamid();
                final int remainingScore = teamitems.size();
                ScoreboardUtils.setScore(21, "§bPlatzierung", player.getScoreboard());
                ScoreboardUtils.setScore(20, "§8➥ §7" + placement + "§7. Platz", player.getScoreboard());
                ScoreboardUtils.setScore(19, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(18, "§bTeam", player.getScoreboard());
                ScoreboardUtils.setScore(17, "§8➥ §7" + teamid + "§7. Team", player.getScoreboard());
                ScoreboardUtils.setScore(16, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(15, "§bVerbleibend", player.getScoreboard());
                ScoreboardUtils.setScore(14, "§8➥ §7" + remainingScore + " Items", player.getScoreboard());
                ScoreboardUtils.setScore(13, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(10, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(9, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(8, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(7, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(6, "§b", player.getScoreboard());
                ScoreboardUtils.setScore(5, "§b", player.getScoreboard());
                if (teamitems.size() >= 5) {
                    int x = 10;
                    for (final Material teamitem : teamitems) {
                        ScoreboardUtils.setScore(x, "§8- §7" + Utils.getItemName(teamitem), player.getScoreboard());
                        if (--x <= 6) {
                            break;
                        }
                        final int noch = teamitems.size() - 4;
                        ScoreboardUtils.setScore(5, "§8- §7" + noch + " §7weitere Items...", player.getScoreboard());
                    }
                }
                else {
                    int x = 10;
                    for (final Material teamitem : teamitems) {
                        ScoreboardUtils.setScore(x, "§8- §7" + Utils.getItemName(teamitem), player.getScoreboard());
                        --x;
                    }
                }
            }
        }
    }

    public int getPlace(final Team team) {
        final int itemsnow = Main.getBingoManager().getBingosettings().getItems() - this.getItemsFromTeam(team.getTeamid()).size();
        int place = 1;
        for (final Map.Entry<Integer, ArrayList<Material>> teamArrayListEntry : this.items.entrySet()) {
            final int itemsfromotherteam = Main.getBingoManager().getBingosettings().getItems() - this.getItemsFromTeam(teamArrayListEntry.getKey()).size();
            if (itemsfromotherteam > itemsnow) {
                ++place;
            }
        }
        return place;
    }
}
