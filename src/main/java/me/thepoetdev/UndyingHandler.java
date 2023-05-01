package me.thepoetdev;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class UndyingHandler extends JavaPlugin {
    public static Player officialTotemCarrier;

    public static int totemSlot(Player p) {
        int slot = 0;
        if (p.getInventory() != null)
            if (p.getInventory().contains(Material.TOTEM_OF_UNDYING)) {
                slot = p.getInventory().first(Material.TOTEM_OF_UNDYING);
            } else if (p.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
                slot = 40;
            }
        return slot;
    }

    public static void totemCarrierDetection() {
        officialTotemCarrier = null;
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getInventory().contains(Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING)
                officialTotemCarrier = player;
        });
    }

    public static void totemEventSeq() {
        MiscMechanics.publicMessage(String.valueOf(officialTotemCarrier.getDisplayName()) + "'s" + ChatColor.RED + "Totem of Undying " +
                ChatColor.WHITE + "has negated the effects of " + ChatColor.DARK_RED + "death", true, null);
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 100.0F, 25.0F);
            player.spawnParticle(Particle.TOTEM, player.getLocation(), 75);
        });
    }
}