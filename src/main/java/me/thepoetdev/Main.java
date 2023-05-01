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