package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemBuilder;
import eu.flowtex.bingo.utils.ScoreboardUtils;
import eu.flowtex.bingo.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamManager {
    private HashMap<UUID, Team> teams;
    private Inventory inv;
    private Main main;

    public TeamManager() {
        this.teams = new HashMap<UUID, Team>();
        this.inv = Bukkit.createInventory((InventoryHolder) null, 27, "§8» §bTeamauswahl");
        for (int i = 0; i < 27; ++i) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayname(" ").build());
            }
        }
    }

    public Team getTeamFromPlayer(final Player player) {
        return this.teams.get(player.getUniqueId());
    }

    public void initPlayer(final Player player) {
        if (!this.teams.containsKey(player.getUniqueId())) {
            this.setTeam(player, Team.SPECTATOR);
        }
    }

    public void setTeam(final Player player, final Team team) {
        this.teams.put(player.getUniqueId(), team);
        if (team == Team.SPECTATOR) {
            ScoreboardUtils.setScore(17, "§8➥ §7SPEC ", player.getScoreboard());
        } else {
            ScoreboardUtils.setScore(17, "§8➥ §7Team " + team.getTeamid(), player.getScoreboard());
        }
        TablistManager.setTeam(player, team);
        this.updateInventory();
    }

    public ArrayList<UUID> getPlayersInTeam(final Team team) {
        final ArrayList<UUID> players = new ArrayList<UUID>();
        for (final Map.Entry<UUID, Team> uuidTeamEntry : this.teams.entrySet()) {
            if (uuidTeamEntry.getValue().getTeamid() == team.getTeamid()) {
                players.add(uuidTeamEntry.getKey());
            }
        }
        return players;
    }

    public void openTeamGUI(final Player player) {
        this.updateInventory();
        player.openInventory(this.inv);
    }

    public void updateInventory() {
        this.inv.clear();
        final Team[] values;
        final Team[] teams = values = Team.values();
        for (final Team team : values) {
            if (team != Team.SPECTATOR) {
                final ArrayList<UUID> players = this.getPlayersInTeam(team);
                final int maxplayers = Main.getBingoManager().getBingosettings().getMaxplayersinteam();
                final ItemBuilder item = new ItemBuilder(team.getMat()).setDisplayname("§bTeam §b" + team.getTeamid()).setLocalizedName(team.name());
                for (int i = 0; i < maxplayers; ++i) {
                    if (i < players.size()) {
                        item.addLore("§7- §b" + Bukkit.getOfflinePlayer((UUID) players.get(i)).getName());
                    } else {
                        item.addLore("§7- §cUnbekannt");
                    }
                }
                this.inv.addItem(new ItemStack[]{item.build()});
            }
        }
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.updateInventory();
        }
    }
    public HashMap<UUID, Team> getTeams() {
        return this.teams;
    }
}
