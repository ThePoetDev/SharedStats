package me.thepoetdev;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageHandler extends JavaPlugin {
    public static void calcArmorDmg(EntityDamageEvent.DamageCause cause, int finalDamage, Player user) {
        if (cause == EntityDamageEvent.DamageCause.PROJECTILE || cause == EntityDamageEvent.DamageCause.DRAGON_BREATH || cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK ||
                cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
            if (!Main.sharedArmorDamage) {
                GlobalStats.ApplyDamage(finalDamage, user);
            } else if (Main.sharedArmorDamage) {
                Bukkit.getOnlinePlayers().forEach(players -> GlobalStats.ApplyDamage(finalDamage, players));
            }
    }

    public static void calcDmg(Double finalDamage) {
        if (Main.damagerate == 0.0D) {
            Main.globalHealth -= finalDamage.doubleValue();
        } else {
            Main.globalHealth -= finalDamage.doubleValue() * Main.damagerate;
        }
    }

    public static void displayDmgMsg(Player user, Double finalDmg, EntityDamageEvent.DamageCause cause) {
        Bukkit.getOnlinePlayers().forEach(Players -> Players.sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(ChatColor.YELLOW + "[+" + finalDmg + " Damage] " + ChatColor.WHITE + user.getDisplayName() + ChatColor.RED + " - From " + ChatColor.AQUA + cause.toString())));
    }

    public static void preDeathHandler(EntityDamageEvent.DamageCause cause) {
        UndyingHandler.totemCarrierDetection();
        if (UndyingHandler.officialTotemCarrier != null) {
            UndyingHandler.officialTotemCarrier.getInventory()
                    .setItem(UndyingHandler.totemSlot(UndyingHandler.officialTotemCarrier), null);
            PlayerHandler.refreshPlayers(true, true);
            if (cause == EntityDamageEvent.DamageCause.VOID)
                PlayerHandler.respawnPlayers();
            UndyingHandler.totemEventSeq();
        } else {
            PlayerHandler.killPlayers();
        }
        UndyingHandler.officialTotemCarrier = null;
    }
}
