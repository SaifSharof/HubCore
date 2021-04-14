package com.esdevelopment.hubcore.features;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        ItemStack server_selector = new ItemBuilder(Material.getMaterial(HubCore.get().getConfig().getString("ITEM.SERVER_SELECTOR.MATERIAL")))
                .setName(CC.translate(HubCore.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")))
                .create();
        player.getInventory().setItem(HubCore.get().getConfig().getInt("ITEM.SERVER_SELECTOR.SLOT"), server_selector);

        for(int i = 0; i < 100; i++) { player.sendMessage(""); }
        HubCore.get().getConfig().getStringList("WELCOME_MESSAGE").forEach(string -> player.sendMessage(CC.translate(string
                .replace("%bullet_point%", "•"))));
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
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("hub.command.place")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission("hub.command.break")) {
            event.setCancelled(true);
        }
    }
}

