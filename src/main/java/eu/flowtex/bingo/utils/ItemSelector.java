package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.manager.SetupManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Random;

public class ItemSelector {
    private static ArrayList<Material> base;
    private static ArrayList<Material> normal;
    private static ArrayList<Material> hard;

    public static void addBaseItem(final Material material) {
        ItemSelector.base.add(material);
    }

    public static void removeBaseItem(final Material material) {
        if (ItemSelector.base.contains(material)) {
            ItemSelector.base.remove(material);
        }
    }

    public static void addBaseItem(final String material) {
        for (final Material value : Material.values()) {
            if (value.name().contains(material)) {
                ItemSelector.base.add(value);
            }
        }
    }

    public static void addNormalItem(final Material material) {
        ItemSelector.normal.add(material);
    }

    public static void removeNormalItem(final Material material) {
        if (ItemSelector.normal.contains(material)) {
            ItemSelector.normal.remove(material);
        }
    }

    public static void addNormalItem(final String material) {
        for (final Material value : Material.values()) {
            if (value.name().contains(material)) {
                ItemSelector.normal.add(value);
            }
        }
    }

    public static ArrayList<Material> getBase() {
        return ItemSelector.base;
    }

    public static void addHardItem(final Material material) {
        ItemSelector.hard.add(material);
    }

    public static void removeHardItem(final Material material) {
        if (ItemSelector.hard.contains(material)) {
            ItemSelector.hard.remove(material);
        }
    }

    public static void addHardItem(final String material) {
        for (final Material value : Material.values()) {
            if (value.name().contains(material)) {
                ItemSelector.hard.add(value);
            }
        }
    }

    public static ArrayList<Material> getHard() {
        return ItemSelector.hard;
    }

    public static ArrayList<Material> getNormal() {
        return ItemSelector.normal;
    }

    public static ArrayList<Material> getItems(final SetupManager.BingoDifficulty bingoDifficulty, final int itemsize) {
        final ArrayList<Material> items = new ArrayList<Material>();
        if (bingoDifficulty == SetupManager.BingoDifficulty.EASY) {
            for (int i = 0; i < itemsize; ++i) {
                Material material;
                for (material = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size())); items.contains(material); material = ItemSelector.base.get(new Random().nextInt(ItemSelector.base.size()))) {
                }
                items.add(material);
            }
        } else if (bingoDifficulty == SetupManager.BingoDifficulty.NORMAL) {
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
        } else if (bingoDifficulty == SetupManager.BingoDifficulty.HARD) {
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
        }
        for (final Material item : items) {
            if (!item.isItem()) {
                return getItems(bingoDifficulty, itemsize);
            }
        }
        return items;
    }

    static {
        ItemSelector.base = new ArrayList<Material>();
        ItemSelector.normal = new ArrayList<Material>();
        ItemSelector.hard = new ArrayList<Material>();
        addBaseItem("IRON");
        addBaseItem(Material.STONE);
        addBaseItem(Material.ANDESITE);
        addBaseItem(Material.DIORITE);
        addBaseItem(Material.POLISHED_ANDESITE);
        addBaseItem(Material.POLISHED_DIORITE);
        addNormalItem(Material.GRASS_BLOCK);
        addBaseItem(Material.ANDESITE);
        addBaseItem(Material.COARSE_DIRT);
        addHardItem(Material.PODZOL);
        addNormalItem(Material.CRIMSON_NYLIUM);
        addNormalItem(Material.WARPED_NYLIUM);
        addBaseItem(Material.COBBLESTONE);
        addBaseItem(Material.OAK_PLANKS);
        addBaseItem(Material.SPRUCE_PLANKS);
        addBaseItem(Material.BIRCH_PLANKS);
        addBaseItem(Material.JUNGLE_PLANKS);
        addBaseItem(Material.ACACIA_PLANKS);
        addBaseItem(Material.DARK_OAK_PLANKS);
        addBaseItem(Material.CRIMSON_PLANKS);
        addBaseItem(Material.WARPED_PLANKS);
        addNormalItem(Material.RED_SAND);
        addHardItem(Material.SPONGE);
        addBaseItem(Material.IRON_BLOCK);
        addNormalItem(Material.JUKEBOX);
        addNormalItem(Material.MOSSY_COBBLESTONE);
        addNormalItem(Material.BOOKSHELF);
        addNormalItem(Material.JACK_O_LANTERN);
        addBaseItem(Material.ANDESITE);
        addNormalItem(Material.CRYING_OBSIDIAN);
        addNormalItem(Material.MELON);
        addNormalItem(Material.SEA_LANTERN);
        addHardItem(Material.SEA_PICKLE);
        addNormalItem(Material.BONE_BLOCK);
        addNormalItem(Material.BAMBOO);
        addNormalItem(Material.TNT);
        addNormalItem(Material.ENCHANTING_TABLE);
        addHardItem(Material.END_CRYSTAL);
        addHardItem(Material.FARMLAND);
        addNormalItem(Material.BELL);
        addNormalItem(Material.NOTE_BLOCK);
        addBaseItem(Material.REDSTONE_BLOCK);
        addNormalItem(Material.ANVIL);
        addNormalItem(Material.ENDER_CHEST);
        addBaseItem(Material.CARROT_ON_A_STICK);
        addNormalItem(Material.ENDER_EYE);
        addNormalItem(Material.WARPED_FUNGUS_ON_A_STICK);
        addHardItem(Material.RABBIT_FOOT);
        addBaseItem("REDSTONE");
        removeBaseItem(Material.REDSTONE_ORE);
        addBaseItem("GOLD");
        removeBaseItem(Material.ENCHANTED_GOLDEN_APPLE);
        addBaseItem("FURNACE");
        addBaseItem("RAIL");
        addBaseItem(Material.SUGAR_CANE);
        addBaseItem(Material.CARVED_PUMPKIN);
        addBaseItem(Material.JUKEBOX);
        addBaseItem("EMERALD");
        removeBaseItem(Material.DIAMOND_HORSE_ARMOR);
        removeBaseItem(Material.DIAMOND_BLOCK);
        removeBaseItem(Material.DIAMOND);
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
        addBaseItem("BOOK");
        addBaseItem("PAPER");
        addBaseItem(Material.LECTERN);
        addNormalItem(Material.NETHERRACK);
        addNormalItem("SOUL");
        addNormalItem("DIAMOND");
        addNormalItem("WART");
        removeNormalItem(Material.SOUL_FIRE);
        removeNormalItem(Material.SOUL_WALL_TORCH);
        addNormalItem(Material.CRYING_OBSIDIAN);
        addNormalItem(Material.NAME_TAG);
        removeNormalItem(Material.DIAMOND_HORSE_ARMOR);
        removeNormalItem(Material.DIAMOND_ORE);
        addNormalItem(Material.COBWEB);
        addNormalItem(Material.SUGAR_CANE);
        addNormalItem("GLOWSTONE");
        addHardItem(Material.NETHERITE_HOE);
        addHardItem(Material.NETHERITE_INGOT);
        addHardItem(Material.NETHERITE_SCRAP);
        addHardItem(Material.OBSIDIAN);
        addHardItem(Material.ANCIENT_DEBRIS);
        addHardItem(Material.ENDER_PEARL);
        addHardItem("BLAZE");
        removeHardItem(Material.BLAZE_SPAWN_EGG);
    }
}
