package com.esdevelopment.hubcore.features;

import com.esdevelopment.hubcore.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;

public class EnderButt implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().getItemMeta() == null) { return; }
                if (e.getItem().getItemMeta().getDisplayName() == null) { return; }
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',HubCore.get().getConfig().getString("ITEM.ENDER_BUTT.NAME")))) {
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.5F));

                    e.setCancelled(true);
                    e.setUseItemInHand(Event.Result.DENY);

                    e.getPlayer().updateInventory();

                    try {
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(HubCore.get().getConfig().getString("ITEM.ENDER_BUTT.SOUND")), 1.0f, 1.0f);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

}