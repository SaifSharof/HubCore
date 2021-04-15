package com.esdevelopment.hubcore.scoreboard.adapter;


import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.features.Ping;
import com.esdevelopment.hubcore.scoreboard.*;
import com.esdevelopment.hubcore.util.CC;
import com.esdevelopment.hubcore.util.PlayerCountThread;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;

public class ScoreboardAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return CC.translate(HubCore.get().getConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> list = new ArrayList<String>();

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            for(String lines : HubCore.get().getConfig().getStringList("SCOREBOARD.LINES").stream()

                    .map(a -> a.replace("{online}", String.valueOf(PlayerCountThread.PLAYER_COUNT)))
                    .map(a -> a.replace("{ping}", String.valueOf(Ping.getPing(player))))

                    .collect(Collectors.toList())) {

                list.add(PlaceholderAPI.setPlaceholders(player, lines));
            }
        } else {

            list.addAll(HubCore.get().getConfig().getStringList("SCOREBOARD.LINES").stream()

                    .map(a -> a.replace("{online}", String.valueOf(PlayerCountThread.PLAYER_COUNT)))
                    .map(a -> a.replace("{ping}", String.valueOf(Ping.getPing(player))))

                    .collect(Collectors.toList()));

        }

        return list;
    }
}
