package me.thepoetdev;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class MiscMechanics extends JavaPlugin {
    public static void publicMessage(String str, boolean global, Player player) {
        if (global) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "SharedStats: " + ChatColor.WHITE + str);
        } else {
            player.sendMessage(ChatColor.YELLOW + "SharedStats: " + ChatColor.WHITE + str);
        }
    }

    public static void setMaxHealth(Player p) {
        AttributeInstance healthAttribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(Main.globalMaxHealth);
    }

    public static boolean roll(int odds) {
        Random random = new Random();
        int chance = random.nextInt(odds + 1);
        return (chance == odds);
    }

    public static void setDefaults() {
        Main.shareHealth = true;
        Main.shareHunger = true;
        Main.globalMaxHealth = 40.0D;
        Main.globalHealth = 40.0D;
        Main.globalAbsorption = 0.0D;
        Main.globalExperience = 0;
        Main.globalMaxHunger = 24;
        Main.globalHungerLevel = 24;
        Main.globalHungerRollChance = 1;
        Main.sharedArmorDamage = true;
        Main.healthregenrate = 0.0D;
        Main.hungerrate = 0;
        Main.damagerate = 0.0D;
    }
}
