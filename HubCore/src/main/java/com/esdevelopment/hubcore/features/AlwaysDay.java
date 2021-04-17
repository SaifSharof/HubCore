package com.esdevelopment.hubcore.features;

import org.bukkit.event.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AlwaysDay implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onWeatherChangeEvent(WeatherChangeEvent event)
    {
        event.setCancelled(true);

        event.getWorld().setWeatherDuration(0);
    }

}