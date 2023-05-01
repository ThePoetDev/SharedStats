package me.thepoetdev.utils;

import me.thepoetdev.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Items extends JavaPlugin {
    /** @noinspection ConstantConditions*/
    public static ItemStack lowHealthItem(){
        ItemStack item = new ItemStack(Material.LIGHT_BLUE_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Small Health Piece"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 1-5."));
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "1");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack midHealthItem(){
        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Mid Health Piece"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 6-10."));
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "2");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack highHealthItem(){
        ItemStack item = new ItemStack(Material.ORANGE_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Strong Health Piece"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 11-15."));
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "3");
        item.setItemMeta(meta);

        return item;
    }

    /** @noinspection ConstantConditions*/
    public static ItemStack ultraHealthItem(){
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.colorize("&4Ultra Health Piece"));
        List<String> lore = Collections.singletonList(Text.colorize("&6That item gives you power, when your max health 16-20."));
        meta.setLore(lore);
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("SharedStats"), "healthitem");
        data.set(key, PersistentDataType.STRING, "4");
        item.setItemMeta(meta);

        return item;
    }
}
