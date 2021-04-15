package com.esdevelopment.hubcore.features;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        ItemStack server_selector = new ItemBuilder(Material.getMaterial(HubCore.get().getConfig().getString("ITEM.SERVER_SELECTOR.MATERIAL")))
                .setName(CC.translate(HubCore.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")))
                .create();
        player.getInventory().setItem(HubCore.get().getConfig().getInt("ITEM.SERVER_SELECTOR.SLOT"), server_selector);

        for(int i = 0; i < 100; i++) { player.sendMessage(""); }

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            for(String list : HubCore.get().getConfig().getStringList("WELCOME_MESSAGE")){
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(list)
                        .replace("%bullet_point%", "•")));
            }
        } else {
            for(String list : HubCore.get().getConfig().getStringList("")){
                player.sendMessage(CC.translate(list).replace("%bullet_point%", "•"));
            }
            
            if(HubCore.get().getConfig().getString("Spawn.location") == null){
                Bukkit.getConsoleSender().sendMessage(CC.translate("&7[HUB CORE] &cSPAWN LOCATION NO SETED"));
            }
            
            player.teleport(LocationUtil.parseToLocation(HubCore.get().getConfig().getString("Spawn.location")));
        }

        //HubCore.get().getConfig().getStringList("WELCOME_MESSAGE").forEach(string -> player.sendMessage(CC.translate(string
        //        .replace("%bullet_point%", "•"))));
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void weatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }
    
    @EventHandler
    public void entityDamageByEntity(EntityDamageByEntityEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void entitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void playerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event) {
        if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("hub.command.place") || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
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
    public void entityExplode(EntityExplodeEvent  event){
        event.setCancelled(true);
    }
}
