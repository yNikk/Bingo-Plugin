package eu.flowtex.bingo.utils;

import com.google.common.collect.Lists;
import eu.flowtex.bingo.manager.SetupManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Items {

    /*
    // PRESET: MATERIAL(SetupManager.ItemDifficulty.DIFFICULTY, Material.MATERIAL),

    // BASE ITEMS

    IRON_INGOT(SetupManager.ItemDifficulty.EASY, Material.IRON_INGOT),
    SAND(SetupManager.ItemDifficulty.EASY, Material.SAND),
    CLAY(SetupManager.ItemDifficulty.EASY, Material.CLAY),

    // NORMAL ITEMS

    QUARTZ_BLOCK(SetupManager.ItemDifficulty.NORMAL, Material.QUARTZ_BLOCK),
    BONE_BLOCK(SetupManager.ItemDifficulty.NORMAL, Material.BONE_BLOCK),
    CARROT_ON_A_STICK(SetupManager.ItemDifficulty.NORMAL, Material.CARROT_ON_A_STICK),
    OBSERVER(SetupManager.ItemDifficulty.NORMAL, Material.OBSERVER),
    BAMBOO(SetupManager.ItemDifficulty.NORMAL, Material.BAMBOO),
    TNT(SetupManager.ItemDifficulty.NORMAL, Material.TNT),
    JUKEBOX(SetupManager.ItemDifficulty.NORMAL, Material.JUKEBOX),
    MOSSY_COBBLESTONE(SetupManager.ItemDifficulty.NORMAL, Material.MOSSY_COBBLESTONE),
    BOOKSHELF(SetupManager.ItemDifficulty.NORMAL, Material.BOOKSHELF),
    JACK_O_LANTERN(SetupManager.ItemDifficulty.NORMAL, Material.JACK_O_LANTERN),
    ENCHANTING_TABLE(SetupManager.ItemDifficulty.NORMAL, Material.ENCHANTING_TABLE),
    CRYING_OBSIDIAN(SetupManager.ItemDifficulty.NORMAL, Material.CRYING_OBSIDIAN),
    MELON(SetupManager.ItemDifficulty.NORMAL, Material.MELON),
    SEA_LANTERN(SetupManager.ItemDifficulty.NORMAL, Material.SEA_LANTERN),
    BELL(SetupManager.ItemDifficulty.NORMAL, Material.BELL),
    NOTE_BLOCK(SetupManager.ItemDifficulty.NORMAL, Material.NOTE_BLOCK),
    ANVIL(SetupManager.ItemDifficulty.NORMAL, Material.ANVIL),
    ENDER_PEARL(SetupManager.ItemDifficulty.NORMAL, Material.ENDER_PEARL),
    WARPED_FUNGUS_ON_A_STICK(SetupManager.ItemDifficulty.NORMAL, Material.WARPED_FUNGUS_ON_A_STICK),
    NETHERRACK(SetupManager.ItemDifficulty.NORMAL, Material.NETHERRACK),
    SOUL_FIRE(SetupManager.ItemDifficulty.NORMAL, Material.SOUL_FIRE),
    DIAMOND(SetupManager.ItemDifficulty.NORMAL, Material.DIAMOND),
    NETHER_WART(SetupManager.ItemDifficulty.NORMAL, Material.NETHER_WART),
    BLAZE_ROD(SetupManager.ItemDifficulty.NORMAL, Material.BLAZE_ROD),
    SOUL_TORCH(SetupManager.ItemDifficulty.NORMAL, Material.SOUL_TORCH),
    NAME_TAG(SetupManager.ItemDifficulty.NORMAL, Material.NAME_TAG),
    COBWEB(SetupManager.ItemDifficulty.NORMAL, Material.COBWEB),
    SUGAR(SetupManager.ItemDifficulty.NORMAL, Material.SUGAR_CANE),
    GLOWSTONE(SetupManager.ItemDifficulty.NORMAL, Material.GLOWSTONE),

    // HARD ITEMS

    SEA_PICKLE(SetupManager.ItemDifficulty.HARD, Material.SEA_PICKLE),
    ENDER_CHEST(SetupManager.ItemDifficulty.HARD, Material.ENDER_CHEST),
    END_CRYSTAL(SetupManager.ItemDifficulty.HARD, Material.END_CRYSTAL),
    FARMLAND(SetupManager.ItemDifficulty.HARD, Material.FARMLAND),
    RABBIT_FOOD(SetupManager.ItemDifficulty.HARD, Material.RABBIT_FOOT),
    DIAMOND_BLOCK(SetupManager.ItemDifficulty.HARD, Material.DIAMOND_BLOCK),
    DIAMOND_HORSE_ARMOR(SetupManager.ItemDifficulty.HARD, Material.DIAMOND_HORSE_ARMOR),
    NETHERITE_HOE(SetupManager.ItemDifficulty.HARD, Material.NETHERITE_HOE),
    NETHERITE_INGOT(SetupManager.ItemDifficulty.HARD, Material.NETHERITE_INGOT),
    NETHERITE_SCRAP(SetupManager.ItemDifficulty.HARD, Material.NETHERITE_SCRAP),
    OBSIDIAN(SetupManager.ItemDifficulty.HARD, Material.OBSIDIAN),
    ANCIENT_DEBRIS(SetupManager.ItemDifficulty.HARD, Material.ANCIENT_DEBRIS),
    ENDER_EYE(SetupManager.ItemDifficulty.HARD, Material.ENDER_EYE),
    RED_SAND(SetupManager.ItemDifficulty.HARD, Material.RED_SAND),
    SPONGE(SetupManager.ItemDifficulty.HARD, Material.SPONGE),

    // ULTRA ITEMS

    TOTEM_OF_UNDYING(SetupManager.ItemDifficulty.ULTRA, Material.TOTEM_OF_UNDYING),
    ELYTRA(SetupManager.ItemDifficulty.ULTRA, Material.ELYTRA),
    ENCHANTED_GOLDEN_APPLE(SetupManager.ItemDifficulty.ULTRA, Material.ENCHANTED_GOLDEN_APPLE);


     */
    // LOGIC

    private static final List<Items> items = Lists.newArrayList();
    public SetupManager.ItemDifficulty difficulty;
    public Material material;

    public Items(SetupManager.ItemDifficulty difficulty, Material material) {
        this.difficulty = difficulty;
        this.material = material;
    }

    public static List<Items> getItems(SetupManager.ItemDifficulty difficulty) {
        List<Items> result = new ArrayList<>();
        for (Items value : items) {
            if (value.difficulty == difficulty) result.add(value);
        }
        return result;
    }

    public static List<Items> getItems() {
        return items;
    }
}