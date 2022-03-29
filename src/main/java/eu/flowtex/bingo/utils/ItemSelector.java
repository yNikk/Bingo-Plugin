package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.manager.SetupManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Random;

public class ItemSelector {
    public static ArrayList<Material> base;
    public static ArrayList<Material> normal;
    public static ArrayList<Material> hard;
    public static ArrayList<Material> ultra;

    public static void addBaseItem(final Material material) {
        ItemSelector.base.add(material);
    }
    public static void addNormalItem(final Material material) {
        ItemSelector.normal.add(material);
    }
    public static void addHardItem(final Material material) {
        ItemSelector.hard.add(material);
    }
    public static void addUltraItem(final Material material) {
        ItemSelector.ultra.add(material);
    }
    public static ArrayList<Material> getBase() { return ItemSelector.base; }
    public static ArrayList<Material> getNormal() { return ItemSelector.normal; }
    public static ArrayList<Material> getHard() { return ItemSelector.hard; }
    public static ArrayList<Material> getUltra() { return ItemSelector.ultra; }

    public static ArrayList<Material> getItems(final SetupManager.ItemDifficulty ItemDifficulty, final int itemsize) {
        final ArrayList<Material> items = new ArrayList<Material>();
        if (ItemDifficulty == SetupManager.ItemDifficulty.EASY) {
            for (int i = 0; i < itemsize; ++i) {
                Material material;
                for (material = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size())); items.contains(material); material = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size()))) {
                }
                items.add(material);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.NORMAL) {
            final int baseitems = itemsize / 3 * 2;
            final int normalitems = itemsize - baseitems;
            for (int j = 0; j < baseitems; ++j) {
                Material material2;
                for (material2 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size())); items.contains(material2); material2 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size()))) {
                }
                items.add(material2);
            }
            for (int j = 0; j < normalitems; ++j) {
                Material material2;
                for (material2 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size())); items.contains(material2); material2 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size()))) {
                }
                items.add(material2);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.HARD) {
            final int itemsizes = itemsize / 3;
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size())); items.contains(material3); material3 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size())); items.contains(material3); material3 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.hard.get(new Random().nextInt(ItemSelector.hard.size())); items.contains(material3); material3 = ItemSelector.hard.get(new Random().nextInt(ItemSelector.hard.size()))) {
                }
                items.add(material3);
            }
        } else if (ItemDifficulty == SetupManager.ItemDifficulty.ULTRA) {
            final int itemsizes = itemsize / 3;
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size())); items.contains(material3); material3 = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size())); items.contains(material3); material3 = ItemSelector.normal.get(new Random().nextInt(ItemSelector.normal.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.hard.get(new Random().nextInt(ItemSelector.hard.size())); items.contains(material3); material3 = ItemSelector.hard.get(new Random().nextInt(ItemSelector.hard.size()))) {
                }
                items.add(material3);
            }
            for (int k = 0; k < itemsizes; ++k) {
                Material material3;
                for (material3 = ItemSelector.ultra.get(new Random().nextInt(ItemSelector.hard.size())); items.contains(material3); material3 = ItemSelector.ultra.get(new Random().nextInt(ItemSelector.ultra.size()))) {
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
}
