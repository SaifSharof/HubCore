package com.esdevelopment.hubcore.util;

import com.esdevelopment.hubcore.HubCore;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import org.bukkit.entity.Player;

public class BungeeUtil {

    public static void sendToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(HubCore.get(), "BungeeCord", out.toByteArray());
    }
}