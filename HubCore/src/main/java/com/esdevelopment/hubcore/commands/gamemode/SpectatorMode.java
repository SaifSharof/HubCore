package com.esdevelopment.hubcore.commands.gamemode;

import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.util.CC;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectatorMode implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command cannot be executed from console");
        }
        if (!sender.hasPermission("hubcore.gmsp")) {
            sender.sendMessage(CC.translate("&cNo Permission."));
        }

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (player.hasPermission("hubcore.adventure")) {

                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(CC.translate(HubCore.get().getConfig().getString("MESSAGES.SPECTATOR")));
            }
        }



        return false;
    }
}
