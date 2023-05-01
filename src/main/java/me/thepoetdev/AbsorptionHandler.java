package me.thepoetdev;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbsorptionHandler extends JavaPlugin {

    public static int highAbsDuration;

    public static int highAbsAmplifier;

    public static double highAbsAmount;

    static PotionEffect Abs = null;

    public static void absorptionHandler(Double damage) {
        if (damage.doubleValue() < highAbsAmount) {
            highAbsAmount -= damage.doubleValue();
            Bukkit.getOnlinePlayers().forEach(player -> {
                PotionEffect s = new PotionEffect(PotionEffectType.ABSORPTION, highAbsDuration, highAbsAmplifier, true, true, true);
                if (player.hasPotionEffect(PotionEffectType.ABSORPTION))
                    player.removePotionEffect(PotionEffectType.ABSORPTION);
                player.addPotionEffect(s);
                player.setAbsorptionAmount(highAbsAmount);
            });
        } else {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setAbsorptionAmount(0.0D);
                player.removePotionEffect(PotionEffectType.ABSORPTION);
            });
            highAbsAmount = 0.0D;
            highAbsAmplifier = 0;
            highAbsDuration = 0;
            DamageHandler.calcDmg(Double.valueOf(damage.doubleValue() - highAbsAmount));
        }
    }

    public static boolean checkAbsorption() {
        Main.hasAbsorption = false;
        Abs = null;
        highAbsDuration = 0;
        highAbsAmount = 0.0D;
        highAbsAmplifier = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getActivePotionEffects();
        }
        return Main.hasAbsorption;
    }
}
