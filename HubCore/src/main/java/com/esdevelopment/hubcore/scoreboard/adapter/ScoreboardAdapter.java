package com.esdevelopment.hubcore.scoreboard.adapter;


import com.esdevelopment.hubcore.HubCore;
import com.esdevelopment.hubcore.scoreboard.*;
import com.esdevelopment.hubcore.util.CC;
import com.esdevelopment.hubcore.util.PlayerCountThread;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return CC.translate(HubCore.get().getConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        return PlaceholderAPI.setPlaceholders(player, HubCore.get().getConfig().getStringList("SCOREBOARD.LINES"));
    }
}
