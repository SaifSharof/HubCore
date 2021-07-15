package com.esdevelopment.hubcore.commands.essentials;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cThis command cannot be executed by Console."));
        }

        if (!sender.hasPermission("hubcore.feed")) {
            sender.sendMessage(CC.translate("&cNo Permission."));
        }

        assert sender instanceof Player;
        final Player player = (Player) sender;

        if (player.hasPermission("hubcore.feed")) {
            player.setFoodLevel(20);
            player.sendMessage(CC.translate(HubCore.get().getConfig().getString("MESSAGES.FOOD-LEVEL")));
        }


        return false;
    }
}
