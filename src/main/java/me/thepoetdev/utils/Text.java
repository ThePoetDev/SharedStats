package me.thepoetdev.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Text {

    private static final char SECTION_CHAR = '\u00A7'; // §
    private static final char AMPERSAND_CHAR = '&';

    public static String colorize(String s) {
        return s == null ? null : translateAlternateColorCodes(AMPERSAND_CHAR, SECTION_CHAR, s);
    }

    public static List<String> colorizeList(List<String> list) {
        if (list == null) return null;
        List<String> newList = new ArrayList<>();
        for (String value : list) {
            newList.add(translateAlternateColorCodes(AMPERSAND_CHAR, SECTION_CHAR, value));
        }
        return newList;
    }

    public static String decolorize(String s) {
        return s == null ? null : translateAlternateColorCodes(SECTION_CHAR, AMPERSAND_CHAR, s);
    }

    public static String removeColor(String text) {
        return ChatColor.stripColor(text);
    }

    public static List<String> replaceStrings(List<String> list, String... replacements) {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid Replacements");
        }
        List<String> editedStrings = new ArrayList<>();
        for (String value : list) {
            editedStrings.add(replaceStrings(value, replacements));
        }
        return editedStrings;
    }

    public static String replaceStrings(String string, String... replacements) {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid Replacements");
        }
        for (int i = 0; i < replacements.length; i += 2) {
            String key = replacements[i];
            String value = replacements[i + 1];
            if (value == null) value = "";
            string = string.replace(key, value);
        }
        return string;
    }

    private static String translateAlternateColorCodes(@SuppressWarnings("SameParameterValue") char from, @SuppressWarnings("SameParameterValue") char to, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == from && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = to;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }
}
