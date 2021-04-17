package com.esdevelopment.hubcore.features;

import com.esdevelopment.hubcore.*;
import com.esdevelopment.hubcore.util.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.*;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    FileConfiguration config = HubCore.get().getConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        if(LocationUtil.parseToLocation(config.getString("Spawn.location")) != null){
            player.teleport(LocationUtil.parseToLocation(config.getString("Spawn.location")));

        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cThere's no spawn set."));
        }

        ItemStack server_selector = new ItemBuilder(Material.getMaterial(config.getString("ITEM.SERVER_SELECTOR.MATERIAL")))
                .setName(CC.translate(HubCore.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")))
                .create();
        player.getInventory().setItem(HubCore.get().getConfig().getInt("ITEM.SERVER_SELECTOR.SLOT"), server_selector);

        for(int i = 0; i < 100; i++) { player.sendMessage(""); }

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            for(String list : config.getStringList("WELCOME_MESSAGE")){
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(list)
                        .replace("%bullet_point%", "•")));
            }
        } else {
            for(String list : config.getStringList("WELCOME_MESSAGE")){
                player.sendMessage(CC.translate(list).replace("%bullet_point%", "•"));
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("hub.command.place") || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("hub.command.break") || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void bucketFill(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketFillEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);
    }

    @EventHandler
    public void playerFish(PlayerFishEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void entityExplode(EntityExplodeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getLocation().getBlockY() < 0) {

            if(LocationUtil.parseToLocation(config.getString("Spawn.location")) != null){
                player.teleport(LocationUtil.parseToLocation(config.getString("Spawn.location")));

            } else {
                Bukkit.getConsoleSender().sendMessage(CC.translate("&cThere's no spawn set."));
            }

            if(config.getBoolean("Spawn.void.enable")){
                player.teleport(LocationUtil.parseToLocation(config.getString("Spawn.location")));
                player.sendMessage(CC.translate(config.getString("Spawn.send.message")));
            }
        }
    }
}
