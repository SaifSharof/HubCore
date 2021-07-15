package com.esdevelopment.hubcore.commands.essentials;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be executed from console");
        }

        final Player player = (Player) sender;

        if (!player.hasPermission("hubcore.reload")) {
            sender.sendMessage(CC.translate("&cNo Permission."));
        }

        if (player.hasPermission("hubcore.reload")) {
            HubCore.get().reloadConfig();
            player.sendMessage(CC.translate("&aHubCore reloaded successfully !"));
        }

        return false;
    }
}
