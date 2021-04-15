package com.esdevelopment.hubcore.commands;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import com.esdevelopment.hubcore.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be executed from console");
        }

        Player player = (Player) sender;

        if (!player.hasPermission("hubcore.setspawn")) {
            sender.sendMessage(CC.translate("&cNo Permission."));
        }
        
        Location location = player.getLocation();
        
        HubCore.get().getConfig().set("Spawn.location", LocationUtil.parseToString(location));
        player.sendMessage(CC.translate("&aSpawn location has been set."));
        player.playSound(location, Sound.PORTAL, 1.0f, 1.0f);
        
        HubCore.get().saveConfig();
        HubCore.get().reloadConfig();
        
        return false;
    }
}
