package com.esdevelopment.hubcore.commands.essentials;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.features.Ping;
import com.esdevelopment.hubcore.util.*;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

public class PingCommand implements CommandExecutor {

    FileConfiguration configuration = HubCore.get().getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cThis command cannot be executed from console"));
        }
        assert sender instanceof Player;
        final Player player = (Player) sender;

        if (!player.hasPermission("hubcore.ping")) {
            player.sendMessage(CC.translate("&cNo Permission."));
        } else if (player.hasPermission("hubcore.ping")) {
            player.sendMessage(CC.translate(configuration.getString("MESSAGES.PING_MESSAGE").replace("{ping}", String.valueOf(Ping.getPing(player)))));
        }
        return false;
    }
}
