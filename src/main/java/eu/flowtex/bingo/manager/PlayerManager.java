package eu.flowtex.bingo.manager;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static int getPing(Player p) {
        CraftPlayer ping_craftplayer = (CraftPlayer) p;
        EntityPlayer ping_entityplayer = ping_craftplayer.getHandle();
        return ping_entityplayer.ping;
    }
}
