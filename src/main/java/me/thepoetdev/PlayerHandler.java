package me.thepoetdev;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerHandler extends JavaPlugin {
    public static Server server = Bukkit.getServer();

    public static void respawnPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(((World)server.getWorlds().get(0)).getSpawnLocation()));
    }

    public static void syncPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            GlobalStats.applyGlobalHealth(player);
            player.setFoodLevel(Main.globalHungerLevel);
        });
    }

    public static void killPlayers() {
        if(Main.globalMaxHealth > 2)
            Main.globalMaxHealth -= 2;
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setHealth(0.0D);
            MiscMechanics.setMaxHealth(player);
            Main.globalHealth = Main.globalMaxHealth;
            Main.globalHungerLevel = Main.globalMaxHunger;
            player.setFoodLevel(Main.globalHungerLevel);
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
        });
    }

    public static void refreshPlayers(boolean partial, boolean totem) {
        if (!partial) {
            Main.globalHealth = Main.globalMaxHealth;
            Main.globalHungerLevel = Main.globalMaxHunger;
        } else {
            Main.globalHealth = Main.globalMaxHealth * 0.07D;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            GlobalStats.applyGlobalHealth(player);
            player.setFoodLevel(Main.globalHungerLevel);
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            if (totem) {
                PotionEffect s = new PotionEffect(PotionEffectType.ABSORPTION, 200, 1, true, true, true);
                AbsorptionHandler.checkAbsorption();
                AbsorptionHandler.absorptionHandler(Double.valueOf(0.0D));
                player.addPotionEffect(s);
            }
        });
    }
}

