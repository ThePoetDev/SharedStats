package me.thepoetdev;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler extends JavaPlugin {
    public static void operateCommands(String[] args, FileConfiguration config, CommandSender sender) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reset")) {
                Main.globalHealth = Main.globalMaxHealth;
                Main.globalHungerLevel = Main.globalMaxHunger;
                if (Bukkit.getServer().isHardcore())
                    Bukkit.getOnlinePlayers().forEach(Players -> Players.setGameMode(GameMode.SURVIVAL));
                MiscMechanics.publicMessage("All global stats have been restored.", true, (Player)sender);
                if (config.getConfigurationSection("settings") == null)
                    config.createSection("settings");
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("maxhealth")) {
                    Main.globalMaxHealth = Integer.valueOf(args[2]).intValue();
                    Main.globalHealth = Main.globalMaxHealth;
                    Bukkit.getOnlinePlayers().forEach(player -> MiscMechanics.setMaxHealth(player));
                    MiscMechanics.publicMessage("Max Health set to: " + ChatColor.GOLD + args[2], true,
                            (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("maxhunger")) {
                    Main.globalMaxHunger = Integer.valueOf(args[2]).intValue();
                    Main.globalHungerLevel = Main.globalMaxHunger;
                    MiscMechanics.publicMessage("Max Hunger set to: " + ChatColor.GOLD + args[2], true,
                            (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("damagerate")) {
                    Main.damagerate = Double.valueOf(args[2]).doubleValue();
                    MiscMechanics.publicMessage("Damage Rate set to: " + ChatColor.GOLD + args[2], true,
                            (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("hungerrate")) {
                    Main.hungerrate = Integer.valueOf(args[2]).intValue();
                    MiscMechanics.publicMessage("Hunger Rate set to: " + ChatColor.GOLD + args[2], true,
                            (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("healthregenrate")) {
                    Main.healthregenrate = Double.valueOf(args[2]).doubleValue();
                    MiscMechanics.publicMessage("Health Regeneration Rate set to: " + ChatColor.GOLD + args[2], true,
                            (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("hunger")) {
                    Main.globalHungerLevel = Integer.valueOf(args[2]).intValue();
                    MiscMechanics.publicMessage("Hunger set to: " + ChatColor.GOLD + args[2], true, (Player)sender);
                    if (config.getConfigurationSection("settings") == null)
                        config.createSection("settings");
                } else if (args[1].equalsIgnoreCase("health")) {
                    Main.globalHealth = Integer.valueOf(args[2]).intValue();
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if (Integer.valueOf(args[2]).intValue() <= Main.globalMaxHealth) {
                            GlobalStats.applyGlobalHealth(player);
                            MiscMechanics.publicMessage("Health set to: " + ChatColor.GOLD + args[2], true, (Player)sender);
                            if (config.getConfigurationSection("settings") == null)
                                config.createSection("settings");
                        } else {
                            player.setHealth(Main.globalMaxHealth);
                            MiscMechanics.publicMessage("Health set to: " + ChatColor.GOLD + Main.globalMaxHealth, true, (Player)sender);
                            if (config.getConfigurationSection("settings") == null)
                                config.createSection("settings");
                        }
                    });
                } else if (args[1].equalsIgnoreCase("sharedarmordamage")) {
                    if (args[2].equalsIgnoreCase("true")) {
                        Main.sharedArmorDamage = true;
                        MiscMechanics.publicMessage(
                                ChatColor.GOLD + "Shared Armor Damage" + ChatColor.WHITE + " has been " +
                                        ChatColor.GREEN + "ENABLED" + ChatColor.WHITE + "!",
                                true, (Player)sender);
                        if (config.getConfigurationSection("settings") == null)
                            config.createSection("settings");
                    } else if (args[2].equalsIgnoreCase("false")) {
                        Main.sharedArmorDamage = false;
                        MiscMechanics.publicMessage(
                                ChatColor.GOLD + "Shared Armor Damage" + ChatColor.WHITE + " has been " +
                                        ChatColor.RED + "DISABLED" + ChatColor.WHITE + "!",
                                true, (Player)sender);
                        if (config.getConfigurationSection("settings") == null)
                            config.createSection("settings");
                    }
                }
            } else if (args[0].equalsIgnoreCase("check")) {
                if (args[1].equalsIgnoreCase("experience")) {
                    if (Bukkit.getPlayer(args[2]) != null) {
                        Player player = Bukkit.getPlayer(args[2]);
                        MiscMechanics.publicMessage(
                                ChatColor.GOLD + player.getDisplayName() + ChatColor.WHITE +
                                        "'s experience is set to: " + ChatColor.GOLD + player.getExpToLevel(),
                                false, (Player)sender);
                    } else {
                        MiscMechanics.publicMessage(ChatColor.RED + "Player was not found.", false, (Player)sender);
                    }
                } else if (args[1].equalsIgnoreCase("maxhealth")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Max Health" + ChatColor.WHITE + " is set to: " +
                            ChatColor.GOLD + Main.globalMaxHealth, false, (Player)sender);
                } else if (args[1].equalsIgnoreCase("maxhunger")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Max Hunger" + ChatColor.WHITE + " is set to: " +
                            ChatColor.GOLD + Main.globalMaxHunger, false, (Player)sender);
                } else if (args[1].equalsIgnoreCase("damagerate")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Damage Rate" + ChatColor.WHITE + " is set to: " +
                            ChatColor.GOLD + Main.damagerate, false, (Player)sender);
                } else if (args[1].equalsIgnoreCase("hungerrate")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Hunger Rate" + ChatColor.WHITE + " is set to: " +
                            ChatColor.GOLD + Main.hungerrate, false, (Player)sender);
                } else if (args[1].equalsIgnoreCase("healthregenrate")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Health Regeneration Rate" + ChatColor.WHITE +
                            " is set to: " + ChatColor.GOLD + Main.healthregenrate, false, (Player)sender);
                } else if (args[1].equalsIgnoreCase("sharedarmordamage")) {
                    MiscMechanics.publicMessage(ChatColor.GOLD + "Shared Armor Damage" + ChatColor.WHITE +
                            " is set to: " + ChatColor.GOLD + Main.sharedArmorDamage, false, (Player)sender);
                }
            }
        }
    }
}
