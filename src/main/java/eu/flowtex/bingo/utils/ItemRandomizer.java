package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.manager.SetupManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Random;

public class ItemRandomizer {

    public static ArrayList<Material> getItems(final SetupManager.ItemDifficulty itemDifficulty, final int itemsize) {
        final ArrayList<Material> result = new ArrayList<>();

        for(int i = 0; i<(itemsize-1); i++) {
            int randomNumberInRange = getRandomNumberInRange(0, Items.getItems().size()-1);
            Items items = Items.getItems().get(randomNumberInRange);
            while(result.contains(items.material) && !items.difficulty.equals(itemDifficulty)) {
                items = Items.getItems().get(randomNumberInRange);
            }
            result.add(items.material);
        }

       /*
        SetupManager.ItemDifficulty currentDifficulty = ItemDifficulty;
        for (int i = currentDifficulty.order; i < currentDifficulty.order; i++) {
            for (int i2 = 0; i2 < (itemsize / currentDifficulty.order); i2++) {
                if (result.size() >= itemsize) return result;
                Material toAdd = Items.getItems(currentDifficulty)
                        .get(ThreadLocalRandom.current().nextInt(0, Items.getItems(currentDifficulty).size())).material;
                while (result.contains(toAdd)) {
                    toAdd = Items.getItems(currentDifficulty)
                            .get(ThreadLocalRandom.current().nextInt(0, Items.getItems(currentDifficulty).size())).material;
                }
                result.add(toAdd);
            }
            currentDifficulty = currentDifficulty.getOneLower();
        }

        while (result.size() < itemsize) {
            Material toAdd = Items.getItems(ItemDifficulty)
                    .get(ThreadLocalRandom.current().nextInt(0, Items.getItems(currentDifficulty).size())).material;
            while (result.contains(toAdd)) {
                toAdd = Items.getItems(ItemDifficulty)
                        .get(ThreadLocalRandom.current().nextInt(0, Items.getItems(currentDifficulty).size())).material;
            }
            result.add(toAdd);
        }
        return result;
        */
        return result;
    }

    /**
     * @param min minimal number
     * @param max maximal number
     * @return a number with a random number between the two given numbers
     */
    public static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min) + 1) + min;
    }

}