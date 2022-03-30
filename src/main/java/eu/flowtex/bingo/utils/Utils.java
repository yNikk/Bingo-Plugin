package eu.flowtex.bingo.utils;

import eu.flowtex.bingo.Main;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;

public class Utils {
    private static Main main;
    private static String colorcode = main.getConfig().getString("lang.noPermission");

    public static String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1) {
            seconds = duration;
        }
        if (hours <= 9) {
            string = colorcode + String.valueOf(string) + "0" + hours + "§7:" + colorcode;
        } else {
            string = colorcode + String.valueOf(string) + hours + "§7:" + colorcode;
        }
        if (minutes <= 9) {
            string = colorcode + String.valueOf(string) + "0" + minutes + "§7:" + colorcode;
        } else {
            string = colorcode + String.valueOf(string) + minutes + "§7:" + colorcode;
        }
        if (seconds <= 9) {
            string = colorcode + String.valueOf(string) + "0" + seconds + colorcode;
        } else {
            string = colorcode + String.valueOf(string) + seconds + colorcode;
        }
        return string;
    }

    public static String getItemName(final Material material) {
        return WordUtils.capitalize(material.name().replace("_", " ").toLowerCase());
    }
}
