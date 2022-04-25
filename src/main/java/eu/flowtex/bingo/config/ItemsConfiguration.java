package eu.flowtex.bingo.config;

import eu.flowtex.bingo.manager.SetupManager;
import eu.flowtex.bingo.utils.Items;
import org.bukkit.Material;

public class ItemsConfiguration extends AbstractConfiguration {

    /**
     * This is the constructor of the abstract configuration file.
     * It will initialize all corresponding variables.
     */
    public ItemsConfiguration() {
        super("plugins/Bingo", "items.yml");
    }

    public void read() {
        this.getConfig().getStringList("items").forEach(itemString -> {
            String[] split  = itemString.split(":");
            SetupManager.ItemDifficulty itemDifficulty = SetupManager.ItemDifficulty.valueOf(split[0]);
            Material material = Material.valueOf(split[1]);
            Items.getItems().add(new Items(itemDifficulty, material));
        });
    }

}
