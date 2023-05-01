package me.thepoetdev;

import me.thepoetdev.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {
    private static Main instance;
    public static boolean shareHealth;
    public static boolean shareHunger;
    public static boolean sharedArmorDamage;
    public static boolean hasAbsorption;
    public static double globalMaxHealth;
    public static double globalHealth;
    public static double globalAbsorption;
    public static double healthregenrate;
    public static double damagerate;
    public static int globalMaxHunger;
    public static int globalHungerLevel;
    public static int globalHungerRollChance;
    public static int globalExperience;
    public static int hungerrate;

    private NamespacedKey key1;
    private NamespacedKey key2;
    private NamespacedKey key3;
    private NamespacedKey key4;

    FileConfiguration config = getConfig();

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventHandlers(), this);
        instance = this;
        if (this.config.getConfigurationSection("settings") == null) {
            this.config.createSection("settings");
            MiscMechanics.setDefaults();
            saveSettings();
        } else {
            ConfigurationSection block = this.config.getConfigurationSection("settings");
            shareHealth = block.getBoolean("shareHealth");
            shareHunger = block.getBoolean("shareHunger");
            sharedArmorDamage = block.getBoolean("sharedArmorDamage");
            globalMaxHealth = block.getInt("globalMaxHealth");
            globalMaxHunger = block.getInt("globalMaxHunger");
            globalHungerRollChance = block.getInt("globalHungerRollChance");
            globalHungerLevel = block.getInt("globalHungerLevel");
            globalHealth = block.getInt("globalHealth");
            globalAbsorption = block.getInt("globalAbsorption");
            globalExperience = block.getInt("globalExperience");
            damagerate = block.getDouble("damagerate");
            hungerrate = block.getInt("damagerate");
        }

        addRecipes();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void saveSettings() {
        ConfigurationSection block = this.config.getConfigurationSection("settings");
        block.set("shareHealth", Boolean.valueOf(shareHealth));
        block.set("shareHunger", Boolean.valueOf(shareHunger));
        block.set("globalMaxHealth", Double.valueOf(globalMaxHealth));
        block.set("globalMaxHunger", Integer.valueOf(globalMaxHunger));
        block.set("globalHungerRollChance", Integer.valueOf(globalHungerRollChance));
        block.set("sharedArmorDamage", Boolean.valueOf(sharedArmorDamage));
        block.set("damagerate", Double.valueOf(damagerate));
        block.set("hungerrate", Integer.valueOf(hungerrate));
        block.set("healthregenrate", Double.valueOf(healthregenrate));
        block.set("globalHealth", Double.valueOf(globalHealth));
        block.set("globalAbsorption", Double.valueOf(globalAbsorption));
        block.set("globalExperience", Integer.valueOf(globalExperience));
        block.set("globalHungerLevel", Integer.valueOf(globalHungerLevel));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player && label.equalsIgnoreCase("sharedstats"))
            CommandHandler.operateCommands(args, this.config, sender);
        return false;
    }

    public void addRecipes() {
        key1 = new NamespacedKey(this, "lowhealthitem");
        ShapedRecipe recipe = new ShapedRecipe(key1, Items.lowHealthItem());
        recipe.shape("IDI", "DED", "IDI");

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('E', Material.EMERALD);

        key2 = new NamespacedKey(this, "midhealthitem");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, Items.midHealthItem());
        recipe2.shape("IDD", "DED", "IDI");

        recipe2.setIngredient('D', Material.DIAMOND);
        recipe2.setIngredient('I', Material.IRON_BLOCK);
        recipe2.setIngredient('E', Material.EMERALD);

        key3 = new NamespacedKey(this, "highhealthitem");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, Items.highHealthItem());
        recipe3.shape("IDI", "DDD", "IDE");

        recipe3.setIngredient('D', Material.DIAMOND);
        recipe3.setIngredient('I', Material.IRON_BLOCK);
        recipe3.setIngredient('E', Material.EMERALD);

        key4 = new NamespacedKey(this, "ultrahealthitem");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, Items.ultraHealthItem());
        recipe4.shape("IDI", "DED", "IDD");

        recipe4.setIngredient('D', Material.DIAMOND);
        recipe4.setIngredient('I', Material.IRON_BLOCK);
        recipe4.setIngredient('E', Material.EMERALD);

        Bukkit.addRecipe(recipe);
        Bukkit.addRecipe(recipe2);
        Bukkit.addRecipe(recipe3);
        Bukkit.addRecipe(recipe4);
    }

    public NamespacedKey getKey1() {
        return key1;
    }

    public NamespacedKey getKey2() {
        return key2;
    }

    public NamespacedKey getKey3() {
        return key3;
    }

    public NamespacedKey getKey4() {
        return key4;
    }

    public static Main getInstance() {
        return instance;
    }
}