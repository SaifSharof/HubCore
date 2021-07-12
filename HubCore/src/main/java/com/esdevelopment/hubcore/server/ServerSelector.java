package com.esdevelopment.hubcore.server;


import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ServerSelector implements Listener {

    private final String bullet_point = "•", right_arrow = "»", left_arrow = "«";
    FileConfiguration config = HubCore.get().getConfig();
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getItem() == null) {
            event.setCancelled(true);

        } else {

            if (CC.translate(config.getString("ITEM.SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getItem().getItemMeta().getDisplayName())) {
                Inventory inventory = Bukkit.createInventory(null, config.getInt("SERVER_SELECTOR.SIZE"), CC.translate(config.getString("SERVER_SELECTOR.NAME")));
                player.openInventory(inventory);
                IntStream.range(0, config.getInt("SERVER_SELECTOR.SIZE")).forEach(i -> inventory.setItem(i, new ItemBuilder(Material.getMaterial(
                        config.getString("GLASS_PANE.MATERIAL")), config
                        .getInt("GLASS_PANE.AMOUNT"), (short) config.getInt("GLASS_PANE.VALUE"))
                        .setName(config.getString("GLASS_PANE.NAME"))
                        .create()));

                List<String> server_lore = new ArrayList<>();
                config.getStringList("SERVER_SELECTOR.SERVERS.1.LORE").forEach(string -> server_lore.add(CC.translate(string
                        .replace("%bullet_point%", bullet_point))
                        .replace("%right_arrow%", right_arrow)
                        .replace("%left_arrow%", left_arrow)));
                ItemStack destination = new ItemBuilder(Material.getMaterial(config.getString("SERVER_SELECTOR.SERVERS.1.MATERIAL")))
                        .setName(CC.translate(config.getString("SERVER_SELECTOR.SERVERS.1.NAME")))
                        .setLore(server_lore)
                        .create();
                inventory.setItem(config.getInt("SERVER_SELECTOR.SERVERS.1.SLOT"), destination);

                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory() != null && CC.translate(config.getString("SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getInventory().getName())) {
            if (CC.translate(config.getString("SERVER_SELECTOR.SERVERS.1.NAME")).equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                BungeeUtil.sendToServer(player, config.getString("SERVER_SELECTOR.SERVERS.1.SERVER"));

                if (CC.translate(config.getString("SERVER_SELECTOR.SERVERS.1.NAME")).equalsIgnoreCase(String.valueOf(event.getCurrentItem().getItemMeta().getDisplayName() == null))) event.setCancelled(true);
            }
        }
    }
}
