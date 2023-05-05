package me.thepoetdev.utils;

import me.thepoetdev.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class Items extends JavaPlugin {
    /** @noinspection ConstantConditions*/
    public static ItemStack lowHealthItem(){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Small Health Potion"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 1-5."));
        meta.setLore(lore);
        meta.setColor(Color.AQUA);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "1");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack midHealthItem(){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Mid Health Potion"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 6-10."));
        meta.setLore(lore);
        meta.setColor(Color.LIME);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "2");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack highHealthItem(){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Strong Health Potion"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 11-15."));
        meta.setLore(lore);
        meta.setColor(Color.ORANGE);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "3");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack ultraHealthItem(){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Ultra Health Potion"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 16-20."));
        meta.setLore(lore);
        meta.setColor(Color.RED);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "4");
        item.setItemMeta(meta);

        return item;
    }
}
