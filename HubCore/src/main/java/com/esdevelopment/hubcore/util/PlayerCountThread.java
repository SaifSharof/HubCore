package com.esdevelopment.hubcore.util;

import com.esdevelopment.hubcore.HubCore;
import lombok.Setter;
import org.bukkit.Bukkit;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerCountThread extends Thread {

    @Setter
    public static int PLAYER_COUNT = 1;

    public PlayerCountThread() {
        setName("veracity-count");
    }

    @Override
    public void run() {
        while (true) {
            pingBungee();
            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pingBungee() {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("PlayerCount");
            out.writeUTF("ALL");
            Bukkit.getServer().sendPluginMessage(HubCore.getProvidingPlugin(HubCore.class), "BungeeCord", b.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}