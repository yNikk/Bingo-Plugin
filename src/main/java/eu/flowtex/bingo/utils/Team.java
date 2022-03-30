package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import org.bukkit.*;

public enum Team
{
    WHITE(Material.WHITE_BED, 1),
    RED(Material.RED_BED, 2), 
    BLUE(Material.BLUE_BED, 3), 
    YELLOW(Material.YELLOW_BED, 4), 
    GREEN(Material.GREEN_BED, 5), 
    CYAN(Material.CYAN_BED, 6), 
    BLACK(Material.BLACK_BED, 7), 
    BROWN(Material.BROWN_BED, 8), 
    SPECTATOR(Material.BEDROCK, 9);
    
    int teamid;
    Material mat;
    private Main main;
    private String colorcode = Main.getInstance().getConfig().getString("general.colorcode");
    
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
