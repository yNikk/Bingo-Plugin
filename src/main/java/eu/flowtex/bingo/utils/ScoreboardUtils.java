package eu.flowtex.bingo.utils;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;
import org.bukkit.scoreboard.Team;

public class ScoreboardUtils
{
    private static HashMap<Integer, String> defaults;
    
    public static void setCurrentScoreboard(final Player p, final String p2) {
        final Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        s.registerNewObjective("m", "dummy", p2);
        s.getObjective("m").setDisplaySlot(DisplaySlot.SIDEBAR);
        for (final Map.Entry<Integer, String> integerStringEntry : ScoreboardUtils.defaults.entrySet()) {
            setScore(integerStringEntry.getKey(), integerStringEntry.getValue(), s);
        }
        p.setScoreboard(s);
    }
    
    public static void insert(final Integer s, final String s2) {
        ScoreboardUtils.defaults.put(s, s2);
        Bukkit.getOnlinePlayers().forEach(player -> setScore(s, s2, player.getScoreboard()));
    }
    
    public static void setScore(final int i, final String s2, final Scoreboard s) {
        if (s.getTeam("t" + i) == null) {
            final Team t = s.registerNewTeam("t" + i);
            t.addEntry(getChatColor(i) + "" + getChatColor(i));
            s.getObjective("m").getScore(getChatColor(i) + "" + getChatColor(i)).setScore(i);
        }
        s.getTeam("t" + i).setPrefix(s2);
    }
    
    private static ChatColor getChatColor(final int s) {
        int i = 0;
        for (final ChatColor value : ChatColor.values()) {
            if (i == s) {
                return value;
            }
            ++i;
        }
        return ChatColor.GOLD;
    }
    
    static {
        ScoreboardUtils.defaults = new HashMap<Integer, String>();
    }
}
