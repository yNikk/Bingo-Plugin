package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import org.bukkit.Material;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;

public enum Team {
    WHITE(Material.WHITE_BED, 1),
    ORANGE(Material.ORANGE_BED, 2),
    MAGENTA(Material.MAGENTA_BED, 3),
    LIGHT_BLUE(Material.LIGHT_BLUE_BED, 4),
    YELLOW(Material.YELLOW_BED, 5),
    LIME(Material.LIME_BED, 6),
    PINK(Material.PINK_BED, 7),
    GRAY(Material.GRAY_BED, 8),
    LIGHT_GRAY(Material.LIGHT_GRAY_BED, 9),
    CYAN(Material.CYAN_BED, 10),
    PURPLE(Material.PURPLE_BED, 11),
    DARK_BLUE(Material.BLUE_BED, 12),
    BROWN(Material.BROWN_BED, 13),
    DARK_GREEN(Material.GREEN_BED, 14),
    RED(Material.RED_BED, 15),
    BLACK(Material.BLACK_BED, 16),
    SPECTATOR(Material.BARRIER, 20);

    int teamid;
    Material mat;
    private Main main;

    private Team(final Material selectionitems, final int teamid) {
        this.teamid = teamid;
        this.mat = selectionitems;
    }

    public String getScoreboardPrefix() {
        if (this == Team.SPECTATOR) {
            return colorcode + "Spec §8|§7 §7";
        }
        return colorcode + "Team " + this.teamid + " §8|§7 §7";
    }

    public Material getMat() {
        return this.mat;
    }

    public int getTeamid() {
        return this.teamid;
    }
}
