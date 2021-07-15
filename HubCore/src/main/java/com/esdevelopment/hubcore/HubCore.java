package com.esdevelopment.hubcore;


import com.esdevelopment.hubcore.commands.essentials.*;
import com.esdevelopment.hubcore.commands.gamemode.*;
import com.esdevelopment.hubcore.commands.spawn.*;
import com.esdevelopment.hubcore.features.*;
import com.esdevelopment.hubcore.media.*;
import com.esdevelopment.hubcore.scoreboard.*;
import com.esdevelopment.hubcore.scoreboard.adapter.*;
import com.esdevelopment.hubcore.server.*;
import com.esdevelopment.hubcore.util.*;
import lombok.Getter;
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
        assemble.setAssembleStyle(AssembleStyle.VIPER);

        if (getConfig().getBoolean("SETTINGS.ALWAYS_SUNNY")) {
            for(World world : this.getServer().getWorlds()) {
                world.setWeatherDuration(0);
                world.setTime(3000);
            }
        }
    }


    private void setupListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ServerSelector(),
                new AlwaysDay(),
                new EnderButt(),
                new LaunchPad(),
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
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gmc").setExecutor(new CreativeMode());
        getCommand("gms").setExecutor(new SurvivalMode());
        getCommand("gmsp").setExecutor(new SpectatorMode());
        getCommand("gma").setExecutor(new AdventureMode());
        getCommand("feed").setExecutor(new FeedCommand());
    }

    private void setupBungee() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeecordListeners());
    }

    public static HubCore get() {
        return getPlugin(HubCore.class);
    }

}
