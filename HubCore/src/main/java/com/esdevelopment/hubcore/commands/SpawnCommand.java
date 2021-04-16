package com.esdevelopment.hubcore.commands;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import com.esdevelopment.hubcore.util.LocationUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be executed from console");
        }

        Player player = (Player) sender;

        if (!player.hasPermission("hubcore.spawn")) {
            sender.sendMessage(CC.translate("&cNo Permission."));
        }
        
        if(LocationUtil.parseToLocation(HubCore.get().getConfig().getString("Spawn.location")) == null){
            player.sendMessage(CC.translate("&cThere's no spawn set."));
        }
        
        player.teleport(LocationUtil.parseToLocation(HubCore.get().getConfig().getString("Spawn.location")));
        player.sendMessage(CC.translate(HubCore.get().getConfig().getString("Spawn.send.message")));
        player.playSound(player.getLocation(),
                Sound.valueOf(HubCore.get().getConfig().getString("Spawn.send.sound")),
                1.0f,
                1.0f);
        
        return false;
    }
}
