package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.manager.SetupManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Random;

public class ItemRandomizer {
    public static ArrayList<Material> base;
    public static ArrayList<Material> normal;
    public static ArrayList<Material> hard;
    public static ArrayList<Material> ultra;

    public static void addBaseItem(final Material material) {
        ItemRandomizer.base.add(material);
    }
    public static void addNormalItem(final Material material) {
        ItemRandomizer.normal.add(material);
    }
    public static void addHardItem(final Material material) {
        ItemRandomizer.hard.add(material);
    }
    public static void addUltraItem(final Material material) {
        ItemRandomizer.ultra.add(material);
    }
    public static ArrayList<Material> getBase() { return ItemRandomizer.base; }
    public static ArrayList<Material> getNormal() { return ItemRandomizer.normal; }
    public static ArrayList<Material> getHard() { return ItemRandomizer.hard; }
    public static ArrayList<Material> getUltra() { return ItemRandomizer.ultra; }

    public static ArrayList<Material> getItems(final SetupManager.ItemDifficulty ItemDifficulty, final int itemsize) {
        final ArrayList<Material> items = new ArrayList<Material>();
        if (ItemDifficulty == SetupManager.ItemDifficulty.EASY) {
            for (int i = 0; i < itemsize; ++i) {
                Material material;
                for (material = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size())); items.contains(material); material = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size()))) {
                }
                items.add(material);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.NORMAL) {
            final int baseitems = itemsize / 3 * 2;
            final int normalitems = itemsize - baseitems;
            for (int j = 0; j < baseitems; ++j) {
                Material material2;
                for (material2 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size())); items.contains(material2); material2 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size()))) {
                }
                items.add(material2);
            }
            for (int j = 0; j < normalitems; ++j) {
                Material material2;
                for (material2 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size())); items.contains(material2); material2 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size()))) {
                }
                items.add(material2);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.HARD) {
            final int itemsizes = itemsize / 3;
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size())); items.contains(material3); material3 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size())); items.contains(material3); material3 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.hard.get(new Random().nextInt(ItemRandomizer.hard.size())); items.contains(material3); material3 = ItemRandomizer.hard.get(new Random().nextInt(ItemRandomizer.hard.size()))) {
                }
                items.add(material3);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.ULTRA) {
            final int itemsizes = itemsize / 3;
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size())); items.contains(material3); material3 = ItemRandomizer.base.get(new Random().nextInt(ItemRandomizer.base.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size())); items.contains(material3); material3 = ItemRandomizer.normal.get(new Random().nextInt(ItemRandomizer.normal.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.hard.get(new Random().nextInt(ItemRandomizer.hard.size())); items.contains(material3); material3 = ItemRandomizer.hard.get(new Random().nextInt(ItemRandomizer.hard.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemRandomizer.ultra.get(new Random().nextInt(ItemRandomizer.hard.size())); items.contains(material3); material3 = ItemRandomizer.ultra.get(new Random().nextInt(ItemRandomizer.ultra.size()))) {
                }
                items.add(material3);
            }
        }
        for (final Material item : items) {
            if (!item.isItem()) {
                return getItems(ItemDifficulty, itemsize);
            }
        }
        return items;
    }

    static {
        ItemRandomizer.base = new ArrayList<Material>();
        ItemRandomizer.normal = new ArrayList<Material>();
        ItemRandomizer.hard = new ArrayList<Material>();
        ItemRandomizer.ultra = new ArrayList<Material>();

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
