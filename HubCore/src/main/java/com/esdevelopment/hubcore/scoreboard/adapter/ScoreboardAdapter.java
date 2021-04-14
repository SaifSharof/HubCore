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
        final List<String> lines = new ArrayList<>();

        lines.add(CC.translate("&7&m-------------------"));
        lines.add(CC.translate("&cOnline: &f"));
        lines.add(CC.translate("&f" + PlayerCountThread.PLAYER_COUNT));
        lines.add("");
        lines.add(CC.translate(HubCore.get().getConfig().getString("SCOREBOARD.MIDDLE")));
        lines.add(CC.translate(""));
        lines.add(CC.translate(""));
        lines.add(CC.translate(HubCore.get().getConfig().getString("SCOREBOARD.FOOTER")));
        lines.add(CC.translate("&7&m-------------------"));
        return lines;
    }
}
