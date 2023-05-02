package me.thepoetdev;

import me.thepoetdev.config.CustomConfig;
import me.thepoetdev.utils.Text;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class EventHandlers implements Listener {

    @EventHandler
    public static void onEntityDamage(EntityDamageEvent event) {
        if (Main.shareHealth && event.getEntity() instanceof Player) {
            Player user = (Player) event.getEntity();
            if (Main.globalHealth > 0.0D) {
                if (!AbsorptionHandler.checkAbsorption()) {
                    if (event.getDamage() < Main.globalHealth) {
                        DamageHandler.calcDmg(Double.valueOf(event.getFinalDamage()));
                        PlayerHandler.syncPlayers();
                    } else if (event.getDamage() >= Main.globalHealth) {
                        event.setCancelled(true);
                        DamageHandler.preDeathHandler(event.getCause());
                    }
                } else {
                    AbsorptionHandler.absorptionHandler(Double.valueOf(event.getDamage()));
                    event.setCancelled(true);
                }
                DamageHandler.calcArmorDmg(event.getCause(), (int) event.getFinalDamage(), user);
                DamageHandler.displayDmgMsg(user, Double.valueOf(event.getFinalDamage()), event.getCause());
                event.setDamage(0.0D);
            } else {
                MiscMechanics.setDefaults();
            }
        }
    }

    @EventHandler
    public static void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (Main.shareHealth &&
                event.getEntity() instanceof Player) {
            event.setCancelled(true);
            if (Main.globalHealth + event.getAmount() > Main.globalMaxHealth) {
                Main.globalHealth = Main.globalMaxHealth;
            } else if (Main.globalHealth + event.getAmount() <= Main.globalMaxHealth) {
                Main.globalHealth += event.getAmount();
            }
            PlayerHandler.syncPlayers();
        }
    }

    @EventHandler
    public static void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (Main.shareHunger &&
                event.getEntity() instanceof Player) {
            event.setCancelled(true);
            if (event.getFoodLevel() != Main.globalHungerLevel)
                if (Main.globalHungerLevel > event.getFoodLevel()) {
                    if (!MiscMechanics.roll(Main.globalHungerRollChance))
                        Main.globalHungerLevel = event.getFoodLevel() - Main.hungerrate;
                } else if (Main.globalHungerLevel < event.getFoodLevel()) {
                    Main.globalHungerLevel = event.getFoodLevel();
                }
            PlayerHandler.syncPlayers();
        }
    }

    @EventHandler
    public static void onConsumeFood(PlayerItemConsumeEvent e){
        if(Main.shareHunger){
            if(e.getItem().getType() == Material.ROTTEN_FLESH){
                e.getPlayer().getInventory().getItem(e.getHand()).setAmount(e.getPlayer().getInventory().getItem(e.getHand()).getAmount() - 1);
                Random rand = new Random();
                Main.globalHungerLevel += 4;
                PlayerHandler.syncPlayers();

                if(rand.nextInt() > 20){
                    Bukkit.getOnlinePlayers().forEach(players -> {
                        players.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 15*20, 0));
                    });
                }

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        if (Main.shareHealth) {
            MiscMechanics.setMaxHealth(event.getPlayer());
            GlobalStats.applyGlobalHealth(event.getPlayer());
        }
        if (Main.shareHunger)
            event.getPlayer().setFoodLevel(Main.globalHungerLevel);

    }

    @EventHandler
    public static void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!Bukkit.getServer().isHardcore()) {
            Main.globalHealth = Main.globalMaxHealth;
            Main.globalHungerLevel = Main.globalMaxHunger;
        }

    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        e.getPlayer().discoverRecipe(Main.getInstance().getKey1());
        e.getPlayer().discoverRecipe(Main.getInstance().getKey2());
        e.getPlayer().discoverRecipe(Main.getInstance().getKey3());
        e.getPlayer().discoverRecipe(Main.getInstance().getKey4());
    }

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        if (e.getAction().isRightClick()) {
            ItemStack item = e.getItem();
            Plugin plugin = Bukkit.getPluginManager().getPlugin("SharedStats");
            PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
            if (data.has(new NamespacedKey(plugin, "healthitem"))) {
                if (data.get(new NamespacedKey(plugin, "healthitem"), PersistentDataType.STRING).equals("1")) {
                    e.setCancelled(true);
                    if (Main.globalMaxHealth >= 10) {
                        e.getPlayer().sendMessage(Text.colorize("&4You cannot use it anymore."));
                    } else {
                        Main.globalMaxHealth += 2;
                        e.getPlayer().sendMessage(Text.colorize("&6You successfully used health piece."));
                        item.setAmount(item.getAmount() - 1);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            MiscMechanics.setMaxHealth(player);
                            if (!player.equals(e.getPlayer()))
                                player.sendMessage(Text.colorize("&f" + e.getPlayer().getName() + " &6Used health piece to survive us! Thanks to it."));
                        });
                    }

                } else if (data.get(new NamespacedKey(plugin, "healthitem"), PersistentDataType.STRING).equals("2")) {
                    e.setCancelled(true);
                    if (Main.globalMaxHealth >= 20) {
                        e.getPlayer().sendMessage(Text.colorize("&4You cannot use it anymore."));
                    } else {
                        Main.globalMaxHealth += 2;
                        e.getPlayer().sendMessage(Text.colorize("&6You successfully used health piece."));
                        item.setAmount(item.getAmount() - 1);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            MiscMechanics.setMaxHealth(player);
                            if (!player.equals(e.getPlayer()))
                                player.sendMessage(Text.colorize("&f" + e.getPlayer().getName() + " &6Used health piece to survive us! Thanks to it."));
                        });
                    }
                } else if (data.get(new NamespacedKey(plugin, "healthitem"), PersistentDataType.STRING).equals("3")) {
                    e.setCancelled(true);
                    if (Main.globalMaxHealth >= 30) {
                        e.getPlayer().sendMessage(Text.colorize("&4You cannot use it anymore."));
                    } else {
                        Main.globalMaxHealth += 2;
                        e.getPlayer().sendMessage(Text.colorize("&6You successfully used health piece."));
                        item.setAmount(item.getAmount() - 1);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            MiscMechanics.setMaxHealth(player);
                            if (!player.equals(e.getPlayer()))
                                player.sendMessage(Text.colorize("&f" + e.getPlayer().getName() + " &6Used health piece to survive us! Thanks to it."));
                        });
                    }
                } else if (data.get(new NamespacedKey(plugin, "healthitem"), PersistentDataType.STRING).equals("4")) {
                    e.setCancelled(true);
                    if (Main.globalMaxHealth >= 40) {
                        e.getPlayer().sendMessage(Text.colorize("&4You cannot use it anymore."));
                    } else {
                        Main.globalMaxHealth += 2;
                        e.getPlayer().sendMessage(Text.colorize("&6You successfully used health piece."));
                        item.setAmount(item.getAmount() - 1);
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            MiscMechanics.setMaxHealth(player);
                            if (!player.equals(e.getPlayer()))
                                player.sendMessage(Text.colorize("&f" + e.getPlayer().getName() + " &6Used health piece to survive us! Thanks to it."));
                        });
                    }
                }
            }
        }
    }
}
