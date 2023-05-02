package me.thepoetdev;

import me.thepoetdev.config.CustomConfig;
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

import java.util.List;
import java.util.Map;

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
        CustomConfig.registerCustomConfig(this);
        Bukkit.getPluginManager().registerEvents(new EventHandlers(), this);
        instance = this;
        this.saveDefaultConfig();

        shareHealth = getConfig().getBoolean("Settings.shareHealth");
        shareHunger = getConfig().getBoolean("Settings.shareHunger");
        sharedArmorDamage = getConfig().getBoolean("Settings.sharedArmorDamage");
        globalMaxHealth = getConfig().getInt("Settings.globalMaxHealth");
        globalMaxHunger = getConfig().getInt("Settings.globalMaxHunger");
        globalHungerRollChance = getConfig().getInt("Settings.globalHungerRollChance");
        globalHungerLevel = getConfig().getInt("Settings.globalHungerLevel");
        globalHealth = getConfig().getInt("Settings.globalHealth");
        globalAbsorption = getConfig().getInt("Settings.globalAbsorption");
        globalExperience = getConfig().getInt("Settings.globalExperience");
        damagerate = getConfig().getDouble("Settings.damagerate");
        hungerrate = getConfig().getInt("Settings.hungerrate");


        addRecipes();
    }

    @Override
    public void onDisable() {
        getConfig().set("Settings.shareHealth", shareHealth);
        getConfig().set("Settings.shareHunger", shareHunger);
        getConfig().set("Settings.sharedArmorDamage", sharedArmorDamage);
        getConfig().set("Settings.globalMaxHealth", globalMaxHealth);
        getConfig().set("Settings.globalMaxHunger", globalMaxHunger);
        getConfig().set("Settings.globalHungerRollChance", globalHungerRollChance);
        getConfig().set("Settings.globalHungerLevel", globalHungerLevel);
        getConfig().set("Settings.globalHealth", globalHealth);
        getConfig().set("Settings.globalAbsorption", globalAbsorption);
        getConfig().set("Settings.globalExperience", globalExperience);
        getConfig().set("Settings.damagerate", damagerate);
        getConfig().set("Settings.hungerrate", hungerrate);
        saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player && label.equalsIgnoreCase("sharedstats"))
            CommandHandler.operateCommands(args, this.config, sender);
        return false;
    }

    public void addRecipes() {
        key1 = new NamespacedKey(this, "lowhealthitem");
        ShapedRecipe recipe = new ShapedRecipe(key1, Items.lowHealthItem());
        List<String> ingredients = CustomConfig.getConfig().getStringList("SmallHealthPiece.Shape");
        recipe.shape(ingredients.get(0), ingredients.get(1), ingredients.get(2));

        List<Map<?,?>> map = CustomConfig.getConfig().getMapList("SmallHealthPiece.Ingredient");
        for(int i = 0; i < map.size(); i++){
            Map<?, ?> ingredient = map.get(i);
            for (Map.Entry<?, ?> entry : ingredient.entrySet()) {
                Character key = ((String)entry.getKey()).charAt(0);
                Material material = Material.getMaterial((String)entry.getValue());
                recipe.setIngredient(key, material);
            }
        }


        Bukkit.addRecipe(recipe);

        key2 = new NamespacedKey(this, "midhealthitem");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, Items.midHealthItem());

        ingredients = CustomConfig.getConfig().getStringList("MediumHealthPiece.Shape");
        recipe2.shape(ingredients.get(0), ingredients.get(1), ingredients.get(2));

        map = CustomConfig.getConfig().getMapList("MediumHealthPiece.Ingredient");
        for(int i = 0; i < map.size(); i++){
            Map<?, ?> ingredient = map.get(i);
            for (Map.Entry<?, ?> entry : ingredient.entrySet()) {
                Character key = ((String)entry.getKey()).charAt(0);
                Material material = Material.getMaterial((String)entry.getValue());
                recipe2.setIngredient(key, material);
            }
        }

        Bukkit.addRecipe(recipe2);

        key3 = new NamespacedKey(this, "highhealthitem");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, Items.highHealthItem());
        ingredients = CustomConfig.getConfig().getStringList("StrongHealthPiece.Shape");
        recipe3.shape(ingredients.get(0), ingredients.get(1), ingredients.get(2));

        map = CustomConfig.getConfig().getMapList("StrongHealthPiece.Ingredient");
        for(int i = 0; i < map.size(); i++){
            Map<?, ?> ingredient = map.get(i);
            for (Map.Entry<?, ?> entry : ingredient.entrySet()) {
                Character key = ((String)entry.getKey()).charAt(0);
                Material material = Material.getMaterial((String)entry.getValue());
                recipe3.setIngredient(key, material);
            }
        }
        Bukkit.addRecipe(recipe3);

        key4 = new NamespacedKey(this, "ultrahealthitem");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, Items.ultraHealthItem());
        ingredients = CustomConfig.getConfig().getStringList("UltraHealthPiece.Shape");
        recipe4.shape(ingredients.get(0), ingredients.get(1), ingredients.get(2));

        map = CustomConfig.getConfig().getMapList("UltraHealthPiece.Ingredient");
        for(int i = 0; i < map.size(); i++){
            Map<?, ?> ingredient = map.get(i);
            for (Map.Entry<?, ?> entry : ingredient.entrySet()) {
                Character key = ((String)entry.getKey()).charAt(0);
                Material material = Material.getMaterial((String)entry.getValue());
                recipe4.setIngredient(key, material);
            }
        }
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