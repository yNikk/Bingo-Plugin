package eu.flowtex.bingo.config;

import static eu.flowtex.bingo.manager.SetupManager.colorcode;
import static eu.flowtex.bingo.manager.SetupManager.prefix;

public class MessageConfiguration extends AbstractConfiguration {

    /**
     * This is the constructor of the abstract configuration file.
     * It will initialize all corresponding variables.
     */
    public MessageConfiguration() {
        super("plugins/Bingo", "messages.yml");
    }

    public String getMessage(String key) {
        return this.getConfig().getString(key).replaceAll("%prefix%", prefix).replaceAll("%colorcode%", colorcode);
    }
}
