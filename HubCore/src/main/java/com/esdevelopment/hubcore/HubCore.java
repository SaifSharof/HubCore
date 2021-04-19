package com.esdevelopment.hubcore;

import com.esdevelopment.hubcore.commands.*;
import com.esdevelopment.hubcore.features.*;
import com.esdevelopment.hubcore.media.*;
import com.esdevelopment.hubcore.scoreboard.*;
import com.esdevelopment.hubcore.scoreboard.adapter.*;
import com.esdevelopment.hubcore.server.*;
import com.esdevelopment.hubcore.util.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

import java.util.*;

@Getter
public class HubCore extends JavaPlugin {

    private PlayerCountThread playerCountThread;

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(CC.translate("&b&m------------------"));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading up HubCore by &bSaif Sharof &fv" + getDescription().getVersion()));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading classes...."));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading Scoreboard & features...."));
        getServer().getConsoleSender().sendMessage(CC.translate("&aHubCore loaded successfully !"));
        getServer().getConsoleSender().sendMessage(CC.translate("&fSupport Link: &bdiscord.me/esdevelopment"));
        getServer().getConsoleSender().sendMessage(CC.translate("&b&m------------------"));
        this.saveDefaultConfig();
        this.setupListeners();
        this.setupBungee();
        this.setupCommands();
        playerCountThread = new PlayerCountThread();
        playerCountThread.start();

        Assemble assemble = new Assemble(this, new ScoreboardAdapter());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.LATEST);

        if (getConfig().getBoolean("SETTINGS.ALWAYS_SUNNY")) {
            for(World world : this.getServer().getWorlds()) {
                world.setWeatherDuration(0);
            }
        }
    }


    private void setupListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ServerSelector(),
                new AlwaysDay(),
                new DoubleJump()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void setupCommands() {
        getCommand("hubreload").setExecutor(new ReloadCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("ping").setExecutor(new PingCommand());

    }

    private void setupBungee() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeecordListeners());
    }

    public static HubCore get() {
        return getPlugin(HubCore.class);
    }

}
