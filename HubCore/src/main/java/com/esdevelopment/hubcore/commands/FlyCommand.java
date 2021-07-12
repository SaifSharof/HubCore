package com.esdevelopment.hubcore.commands;

import com.esdevelopment.hubcore.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private final ArrayList<Player> flying = new ArrayList<>();
    private final FileConfiguration config = HubCore.get().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You cannot execute this command from console");
        }

        if (!sender.hasPermission("hubcore.fly")) {
            sender.sendMessage(ChatColor.RED + "No Permission.");
        }

        if(sender instanceof Player){
            Player player = (Player) sender;
            String onMessage = ChatColor.translateAlternateColorCodes('&', config.getString("SETTINGS.FLY_ENABLED"));
            String offMessage = ChatColor.translateAlternateColorCodes('&', config.getString("SETTINGS.FLY_DISABLED"));
            if (player.hasPermission("hubcore.fly")) {
                if (flying.contains(player)) {
                    flying.remove(player);
                    player.sendMessage(offMessage);
                    player.setAllowFlight(false);
                } else if (!flying.contains(player)) {
                    flying.add(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));
                    player.setAllowFlight(true);
                }
            }
        }

        return true;
    }
}