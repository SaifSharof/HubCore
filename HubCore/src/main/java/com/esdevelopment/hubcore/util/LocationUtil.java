package com.esdevelopment.hubcore.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

    //Translate a Location to a String
    public static String parseToString(Location location) {
        return location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch() + "," +
                location.getWorld().getName();
    }

    //Translate a String to a Location
    public static Location parseToLocation(String string) {
        if (string == null) return null;
        String[] data = string.split(",");
        try {
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            double z = Double.parseDouble(data[2]);
            float pitch = Float.valueOf(data[4]);
            float yaw = Float.valueOf(data[3]);
            org.bukkit.World world = Bukkit.getWorld(data[5]);
            Location location = new Location(world, x, y, z, yaw, pitch);
            //location.setPitch((float) pitch);
            //location.setYaw((float) yaw);
            return location;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}