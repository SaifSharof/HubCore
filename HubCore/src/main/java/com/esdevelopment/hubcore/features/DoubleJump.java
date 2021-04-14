package com.esdevelopment.hubcore.features;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump implements Listener {

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().normalize().multiply(2).setY(1));
        player.playSound(player.getLocation(), Sound.EXPLODE, 1, 10);
    }

    @EventHandler
    public void onPlayerGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }
}