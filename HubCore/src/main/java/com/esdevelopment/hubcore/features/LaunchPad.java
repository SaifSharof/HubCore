package com.esdevelopment.hubcore.features;

import com.esdevelopment.hubcore.HubCore;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchPad implements Listener {

    @EventHandler
    public void onLaunchEvent(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getLocation().getBlock().getType() == Material.valueOf(HubCore.get().getConfig().getString("LAUNCH_PAD.MATERIAL"))) {
            Vector vectah = player.getLocation().getDirection().multiply(2.0).setY(1.0);
            player.setVelocity(vectah);

            player.playSound(player.getLocation(), Sound.valueOf(HubCore.get().getConfig().getString("LAUNCH_PAD.SOUND")), 2.0f, 2.0f);
        }
    }
}
