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
        addBaseItem(Material.SAND);
        addBaseItem(Material.CLAY);
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
        addBaseItem(Material.FURNACE);
        addBaseItem(Material.RAIL);
        addBaseItem(Material.SUGAR_CANE);
        addBaseItem(Material.CARVED_PUMPKIN);
        addBaseItem(Material.JUKEBOX);
        addBaseItem(Material.EMERALD);
        addBaseItem(Material.FISHING_ROD);
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
        addBaseItem(Material.WHITE_WOOL);
        addBaseItem(Material.OAK_STAIRS);
        addBaseItem(Material.OAK_BOAT);
        addBaseItem(Material.HAY_BLOCK);
        addBaseItem(Material.GRANITE);
        addBaseItem(Material.WHITE_WOOL);
        addBaseItem(Material.GREEN_WOOL);
        addBaseItem(Material.RED_WOOL);
        addBaseItem(Material.BLUE_WOOL);
        addBaseItem(Material.YELLOW_WOOL);
        addBaseItem(Material.PINK_WOOL);
        addBaseItem(Material.ORANGE_WOOL);
        addBaseItem(Material.LIME_WOOL);
        addBaseItem(Material.GLASS);
        addBaseItem(Material.GREEN_STAINED_GLASS);
        addBaseItem(Material.BLUE_STAINED_GLASS);
        addBaseItem(Material.LIME_STAINED_GLASS);
        addBaseItem(Material.YELLOW_STAINED_GLASS);
        addBaseItem(Material.RED_STAINED_GLASS);
        addBaseItem(Material.PURPLE_STAINED_GLASS);
        addBaseItem(Material.WHITE_CONCRETE);
        addBaseItem(Material.GREEN_CONCRETE);
        addBaseItem(Material.LIME_CONCRETE);
        addBaseItem(Material.BLUE_CONCRETE);
        addBaseItem(Material.RED_CONCRETE);
        addBaseItem(Material.YELLOW_CONCRETE);
        addBaseItem(Material.WHITE_CONCRETE_POWDER);
        addBaseItem(Material.GREEN_CONCRETE_POWDER);
        addBaseItem(Material.LIME_CONCRETE_POWDER);
        addBaseItem(Material.BLUE_CONCRETE_POWDER);
        addBaseItem(Material.RED_CONCRETE_POWDER);
        addBaseItem(Material.YELLOW_CONCRETE_POWDER);
        addBaseItem(Material.OAK_SAPLING);
        addBaseItem(Material.KELP);
        addBaseItem(Material.LADDER);
        addBaseItem(Material.OAK_FENCE);
        addBaseItem(Material.CHAIN);
        addBaseItem(Material.OAK_SIGN);
        addBaseItem(Material.WHITE_BANNER);
        addBaseItem(Material.GREEN_BANNER);
        addBaseItem(Material.RED_BANNER);
        addBaseItem(Material.LIME_BANNER);
        addBaseItem(Material.YELLOW_BANNER);
        addBaseItem(Material.BLUE_BANNER);
        addBaseItem(Material.ARMOR_STAND);
        addBaseItem(Material.WHITE_BED);
        addBaseItem(Material.RED_BED);
        addBaseItem(Material.GREEN_BED);
        addBaseItem(Material.LIME_BED);
        addBaseItem(Material.BLUE_BED);
        addBaseItem(Material.YELLOW_BED);
        addBaseItem(Material.ROSE_BUSH);
        addBaseItem(Material.WHITE_CARPET);
        addBaseItem(Material.RED_CARPET);
        addBaseItem(Material.YELLOW_CARPET);
        addBaseItem(Material.BLUE_CARPET);
        addBaseItem(Material.GREEN_CARPET);
        addBaseItem(Material.LIME_CARPET);
        addBaseItem(Material.OAK_LEAVES);
        addBaseItem(Material.GRASS);
        addBaseItem(Material.DANDELION);
        addBaseItem(Material.POPPY);
        addBaseItem(Material.RED_TULIP);
        addBaseItem(Material.ORANGE_TULIP);
        addBaseItem(Material.PINK_TULIP);
        addBaseItem(Material.WHITE_TULIP);
        addBaseItem(Material.RED_TULIP);
        addBaseItem(Material.ORANGE_TULIP);
        addBaseItem(Material.ALLIUM);
        addBaseItem(Material.BLUE_ORCHID);
        addBaseItem(Material.AZURE_BLUET);
        addBaseItem(Material.CORNFLOWER);
        addBaseItem(Material.OXEYE_DAISY);
        addBaseItem(Material.STONE_PRESSURE_PLATE);
        addBaseItem(Material.OAK_PRESSURE_PLATE);
        addBaseItem(Material.OAK_TRAPDOOR);
        addBaseItem(Material.IRON_TRAPDOOR);
        addBaseItem(Material.OAK_FENCE_GATE);
        addBaseItem(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
        addBaseItem(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        addBaseItem(Material.OAK_DOOR);
        addBaseItem(Material.IRON_DOOR);
        addBaseItem(Material.REDSTONE);
        addBaseItem(Material.HOPPER);
        addBaseItem(Material.STONE_BUTTON);
        addBaseItem(Material.OAK_BUTTON);
        addBaseItem(Material.LEVER);
        addBaseItem(Material.REDSTONE_TORCH);
        addBaseItem(Material.TRIPWIRE_HOOK);
        addBaseItem(Material.MINECART);
        addBaseItem(Material.WHEAT);
        addBaseItem(Material.WHEAT_SEEDS);
        addBaseItem(Material.BOWL);
        addBaseItem(Material.EGG);
        addBaseItem(Material.ORANGE_DYE);
        addBaseItem(Material.LAPIS_LAZULI);
        addBaseItem(Material.WHITE_DYE);
        addBaseItem(Material.ORANGE_DYE);
        addBaseItem(Material.BLUE_DYE);
        addBaseItem(Material.LIGHT_BLUE_DYE);
        addBaseItem(Material.GREEN_DYE);
        addBaseItem(Material.GRAY_DYE);
        addBaseItem(Material.LIME_DYE);
        addBaseItem(Material.RED_DYE);
        addBaseItem(Material.YELLOW_DYE);
        addBaseItem(Material.BRICK);
        addBaseItem(Material.ORANGE_TULIP);
        addBaseItem(Material.FIREWORK_STAR);
        addBaseItem(Material.RABBIT_HIDE);
        addBaseItem(Material.APPLE);
        addBaseItem(Material.COD);
        addBaseItem(Material.ROTTEN_FLESH);
        addBaseItem(Material.BEEF);
        addBaseItem(Material.GOLDEN_PICKAXE);
        addBaseItem(Material.GOLDEN_AXE);
        addBaseItem(Material.STONE_SHOVEL);
        addBaseItem(Material.WOODEN_HOE);
        addBaseItem(Material.ARROW);
        addBaseItem(Material.WOODEN_SWORD);
        addBaseItem(Material.STONE_SWORD);
        addBaseItem(Material.IRON_SWORD);
        addBaseItem(Material.GOLDEN_SWORD);
        addBaseItem(Material.CAULDRON);

        // NORMAL ITEMS

        addNormalItem(Material.QUARTZ_BLOCK);
        addNormalItem(Material.BONE_BLOCK);
        addNormalItem(Material.CARROT_ON_A_STICK);
        addNormalItem(Material.OBSERVER);
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
