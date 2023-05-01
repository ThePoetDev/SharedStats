package me.thepoetdev;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GlobalStats extends JavaPlugin {
    public static void applyGlobalHealth(Player p) {
        if (Main.globalHealth > 0.0D) {
            p.setHealth(Main.globalHealth);
        } else {
            Main.globalHealth = 1.0D;
            p.setHealth(Main.globalHealth);
        }
    }

    public static void ApplyDamage(int DamageDealt, Player player) {
        ItemStack[] armor = player.getInventory().getArmorContents();
        for (int i = 0; i < armor.length; i++) {
            ItemStack item = armor[i];
            if (armor[i] != null) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta instanceof Damageable) {
                    int itemHealth = ((Damageable)itemMeta).getDamage() + DamageDealt;
                    ((Damageable)itemMeta).setDamage(itemHealth);
                    int max = armor[i].getType().getMaxDurability();
                    boolean broken = (DamageDealt + itemHealth >= max);
                    if (broken) {
                        armor[i].setAmount(0);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
                    } else {
                        armor[i].setItemMeta(itemMeta);
                    }
                }
            }
        }
    }
}
