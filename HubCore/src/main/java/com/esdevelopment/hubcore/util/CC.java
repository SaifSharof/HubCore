package com.esdevelopment.hubcore.util;

import org.bukkit.ChatColor;

public class CC {

    public static String translate (String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}