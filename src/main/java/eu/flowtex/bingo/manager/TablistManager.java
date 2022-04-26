package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.utils.Team;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TablistManager {
    private static Scoreboard s;

    public static void setTeam(final Player p, final Team team) {
        final String teamname = team.getTeamid() + p.getUniqueId().toString().substring(1, 6);
        for (final ScoreboardTeam teams : TablistManager.s.getTeams()) {
            if (teams.getPlayerNameSet().contains(p.getName())) {
                teams.getPlayerNameSet().remove(p.getName());
            }
        }
        if (TablistManager.s.getTeam(teamname) != null) {
            TablistManager.s.removeTeam(TablistManager.s.getTeam(teamname));
        }
        createTeam(team, teamname);
        TablistManager.s.getTeam(teamname).getPlayerNameSet().add(p.getName());
        sendPackets();
    }

    public static void sendPackets() {
        for (final ScoreboardTeam team : TablistManager.s.getTeams()) {
            final PacketPlayOutScoreboardTeam packetPlayOutScoreboardTeam = new PacketPlayOutScoreboardTeam(team, 1);
            final PacketPlayOutScoreboardTeam packetPlayOutScoreboardTeam2 = new PacketPlayOutScoreboardTeam(team, 0);
            final Packet packet;
            final Packet packet2;
            Bukkit.getOnlinePlayers().forEach(player -> {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardTeam);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutScoreboardTeam2);
            });
        }
    }

    private static void createTeam(final Team g, final String teamname) {
        TablistManager.s.createTeam(teamname);
        TablistManager.s.getTeam(teamname).setPrefix((IChatBaseComponent) new ChatComponentText(g.getScoreboardPrefix()));
    }

    static {
        TablistManager.s = new Scoreboard();
    }
}
