package com.esdevelopment.hubcore.features;


import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Ping {

    public static int getPing(Player p) {
        try {
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
