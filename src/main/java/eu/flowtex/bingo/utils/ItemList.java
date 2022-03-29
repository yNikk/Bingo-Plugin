package eu.flowtex.bingo.utils;

import org.bukkit.Material;

import java.util.ArrayList;

import static eu.flowtex.bingo.utils.ItemSelector.*;

public class ItemList {
    private static ArrayList<Material> base;
    private static ArrayList<Material> normal;
    private static ArrayList<Material> hard;
    private static ArrayList<Material> ultra;

    static {
        ItemSelector.base = new ArrayList<Material>();
        ItemSelector.normal = new ArrayList<Material>();
        ItemSelector.hard = new ArrayList<Material>();
        ItemSelector.ultra = new ArrayList<Material>();

        // BASE ITEMS

        addBaseItem(Material.IRON_INGOT);
        addBaseItem(Material.ANDESITE);
        addBaseItem(Material.DIORITE);
        addBaseItem(Material.POLISHED_ANDESITE);
        addBaseItem(Material.POLISHED_DIORITE);
        addBaseItem(Material.ANDESITE);
        addBaseItem(Material.COARSE_DIRT);
        addHardItem(Material.PODZOL);
        addBaseItem(Material.COBBLESTONE);
        addBaseItem(Material.IRON_BLOCK);
        addBaseItem(Material.ANDESITE);
        addBaseItem(Material.REDSTONE_BLOCK);
        addBaseItem(Material.CARROT_ON_A_STICK);
        addBaseItem(Material.FURNACE);
        addBaseItem(Material.RAIL);
        addBaseItem(Material.SUGAR_CANE);
        addBaseItem(Material.CARVED_PUMPKIN);
        addBaseItem(Material.JUKEBOX);
        addBaseItem(Material.EMERALD);
        addBaseItem(Material.FISHING_ROD);
        addBaseItem(Material.OBSERVER);
        addBaseItem(Material.BOW);
        addBaseItem(Material.FEATHER);
        addBaseItem(Material.COMPASS);
        addBaseItem(Material.CLOCK);
        addBaseItem(Material.SHIELD);
        addBaseItem(Material.SHEARS);
        addBaseItem(Material.WATER_BUCKET);
        addBaseItem(Material.MILK_BUCKET);
        addBaseItem(Material.LAVA_BUCKET);
        addBaseItem(Material.BUCKET);
        addBaseItem(Material.SUGAR_CANE);
        addBaseItem(Material.FLINT_AND_STEEL);
        addBaseItem(Material.BOOK);
        addBaseItem(Material.PAPER);
        addBaseItem(Material.LECTERN);
        addBaseItem(Material.REDSTONE);
        addBaseItem(Material.GOLD_INGOT);

        // NORMAL ITEMS

        addNormalItem(Material.BONE_BLOCK);
        addNormalItem(Material.BAMBOO);
        addNormalItem(Material.TNT);
        addNormalItem(Material.JUKEBOX);
        addNormalItem(Material.MOSSY_COBBLESTONE);
        addNormalItem(Material.BOOKSHELF);
        addNormalItem(Material.JACK_O_LANTERN);
        addNormalItem(Material.ENCHANTING_TABLE);
        addNormalItem(Material.CRYING_OBSIDIAN);
        addNormalItem(Material.MELON);
        addNormalItem(Material.SEA_LANTERN);
        addNormalItem(Material.BELL);
        addNormalItem(Material.NOTE_BLOCK);
        addNormalItem(Material.ANVIL);
        addNormalItem(Material.ENDER_PEARL);
        addNormalItem(Material.WARPED_FUNGUS_ON_A_STICK);
        addNormalItem(Material.NETHERRACK);
        addNormalItem(Material.SOUL_FIRE);
        addNormalItem(Material.DIAMOND);
        addNormalItem(Material.NETHER_WART);
        addNormalItem(Material.BLAZE_ROD);
        addNormalItem(Material.SOUL_TORCH);
        addNormalItem(Material.CRYING_OBSIDIAN);
        addNormalItem(Material.NAME_TAG);
        addNormalItem(Material.COBWEB);
        addNormalItem(Material.SUGAR_CANE);
        addNormalItem(Material.GLOWSTONE);

        // HARD ITEMS

        addHardItem(Material.SEA_PICKLE);
        addHardItem(Material.ENDER_CHEST);
        addHardItem(Material.END_CRYSTAL);
        addHardItem(Material.FARMLAND);
        addHardItem(Material.RABBIT_FOOT);
        addHardItem(Material.DIAMOND_BLOCK);
        addHardItem(Material.DIAMOND_HORSE_ARMOR);
        addHardItem(Material.NETHERITE_HOE);
        addHardItem(Material.NETHERITE_INGOT);
        addHardItem(Material.NETHERITE_SCRAP);
        addHardItem(Material.OBSIDIAN);
        addHardItem(Material.ANCIENT_DEBRIS);
        addHardItem(Material.ENDER_EYE);
        addHardItem(Material.RED_SAND);
        addHardItem(Material.SPONGE);

        // ULTRA ITEMS

        addUltraItem(Material.TOTEM_OF_UNDYING);
        addUltraItem(Material.ELYTRA);
        addUltraItem(Material.ENCHANTED_GOLDEN_APPLE);
    }
}
