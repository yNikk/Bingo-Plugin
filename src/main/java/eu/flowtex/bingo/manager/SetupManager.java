package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SetupManager {
    private int MAX_PLAYERS;
    private int maxplayersinteam;
    private int items;
    public ItemDifficulty difficulty;
    private Main main;

    public SetupManager(final int maxplayersinteam, final int items, final ItemDifficulty difficulty) {
        this.MAX_PLAYERS = 8;
        this.maxplayersinteam = maxplayersinteam;
        this.items = items;
        this.difficulty = difficulty;
    }

    public void openSettingsInventory(final Player player) {
        final Inventory settings = Bukkit.createInventory((InventoryHolder) null, 27, "§bBingo §8» §7Settings");
        for (int i = 0; i < 27; ++i) {
            settings.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayname(" ").build());
        }
        settings.setItem(12, new ItemBuilder(Material.WHITE_BED).setDisplayname("§8» §bTeamgrösse").setLore("§7Aktueller Wert" + " §8x " + "§7" + this.maxplayersinteam).build());
        settings.setItem(10, new ItemBuilder(Material.BEACON).setDisplayname("§8» §bAnzahl der Items").setLore("§7Aktueller Wert" + " §8x " + "§7" + this.items).build());
        settings.setItem(14, new ItemBuilder(Material.ANVIL).setDisplayname("§8» §bSchwierigkeit").setLore("§7Aktueller Wert" + " §8x " + "§7" + this.difficulty.name()).build());
        settings.setItem(16, new ItemBuilder(Material.SCUTE).setDisplayname("§8» §bAuswahl Bestätigen").setLore("§7Teamphase starten").build());
        this.addButtons(settings);
        player.openInventory(settings);
        player.updateInventory();
    }

    public void addButtons(final Inventory inv) {
        if (this.maxplayersinteam < this.MAX_PLAYERS) {
            this.addButton("§aWert erhöhen (+1)", 3, inv, "maxplayers+");
        } else {
            this.addButton("§4Maximaler Wert erreicht", 3, inv, "max");
        }
        if (this.maxplayersinteam >= 2) {
            this.addButton("§cWert verringern (-1)", 21, inv, "maxplayers-");
        } else {
            this.addButton("§4Minimaler Wert erreicht", 21, inv, "max");
        }
        if (this.items != 3) {
            this.addButton("§cWert verringern (-3)", 19, inv, "items-");
        } else {
            this.addButton("§4Minimaler Wert erreicht", 19, inv, "max");
        }
        if (this.items != 45) {
            this.addButton("§aWert erhöhen (+3)", 1, inv, "items+");
        } else {
            this.addButton("§4Maximaler Wert erreicht", 1, inv, "max");
        }
        if (this.items != 3) {
            this.addButton("§cWert verringern (-3)", 19, inv, "items-");
        } else {
            this.addButton("§4Minimaler Wert erreicht", 19, inv, "max");
        }
        if (this.items != 45) {
            this.addButton("§aWert erhöhen (+3)", 1, inv, "items+");
        } else {
            this.addButton("§4Maximaler Wert erreicht", 1, inv, "max");
        }

        if (this.difficulty == ItemDifficulty.EASY) {
            this.addButton("§cNormal", 5, inv, "dif_" + Difficulty.NORMAL.name());
            this.addButton("§4Minimaler Wert erreicht", 23, inv, "max");
        } else if (this.difficulty == ItemDifficulty.NORMAL) {
            this.addButton("§cSchwer", 5, inv, "dif_" + Difficulty.HARD.name());
            this.addButton("§aEinfach", 23, inv, "dif_" + Difficulty.EASY.name());
        } else if (this.difficulty == ItemDifficulty.HARD) {
            this.addButton("§cUltra", 5, inv, "dif_" + Difficulty.HARD.name());
            this.addButton("§aNormal", 23, inv, "dif_" + Difficulty.NORMAL.name());
        } else if (this.difficulty == ItemDifficulty.ULTRA) {
            this.addButton("§4Maximaler Wert erreicht", 5, inv, "max");
            this.addButton("§aSchwer", 23, inv, "dif_" + Difficulty.NORMAL.name());
        }
    }

    public void addButton(final String name, final int slot, final Inventory inv, final String local) {
        if (local == "max") {
            inv.setItem(slot, new ItemBuilder(Material.BARRIER).setDisplayname(name).setLocalizedName(local).build());
        } else {
            inv.setItem(slot, new ItemBuilder(Material.STONE_BUTTON).setDisplayname(name).setLocalizedName(local).build());
        }
    }

    public void addMaxPlayers(final Player player) {
        ++this.maxplayersinteam;
        this.openSettingsInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
    }

    public void removeMaxPlayer(final Player player) {
        --this.maxplayersinteam;
        this.openSettingsInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
    }

    public void addItems(final Player player) {
        this.items += 3;
        this.openSettingsInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
    }

    public void removeItems(final Player player) {
        this.items -= 3;
        this.openSettingsInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
    }

    public void setDifficulty(final Player player, final ItemDifficulty bingoDifficulty) {
        this.difficulty = bingoDifficulty;
        this.openSettingsInventory(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
    }

    public void setItems(final int items) {
        this.items = items;
    }

    public int getMaxplayersinteam() {
        return this.maxplayersinteam;
    }

    public int getItems() {
        return this.items;
    }

    public ItemDifficulty getDifficulty() {
        return this.difficulty;
    }

    public enum ItemDifficulty {
        EASY,
        NORMAL,
        HARD,
        ULTRA;
    }

    public enum BingoSettingsType {
        TEAMS,
        ITEMS,
        DIFFICULTY;
    }
}
