package com.esdevelopment.hubcore.media;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be executed from console");
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("hubcore.media")) {
            player.sendMessage(CC.translate("&cNo Permission"));
        }

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            player.sendMessage(CC.translate(HubCore.get().getConfig().getString("MEDIA_LINKS.DISCORD").replace("%bullet_point%", "•")));
        } else {
            for(String list : HubCore.get().getConfig().getStringList("")){
                player.sendMessage(CC.translate(list).replace("%bullet_point%", "•"));
            }
        }
        return true;
    }
}