package com.esdevelopment.hubcore;

import com.esdevelopment.hubcore.commands.ReloadCommand;
import com.esdevelopment.hubcore.commands.SetSpawnCommand;
import com.esdevelopment.hubcore.commands.SpawnCommand;
import com.esdevelopment.hubcore.features.*;
import com.esdevelopment.hubcore.media.DiscordCommand;
import com.esdevelopment.hubcore.media.StoreCommand;
import com.esdevelopment.hubcore.media.WebsiteCommand;
import com.esdevelopment.hubcore.scoreboard.*;
import com.esdevelopment.hubcore.scoreboard.adapter.*;
import com.esdevelopment.hubcore.server.*;
import com.esdevelopment.hubcore.util.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class HubCore extends JavaPlugin {

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
        saveDefaultConfig();
        this.setupListeners();
        this.setupBungee();
        this.setupCommands();
        playerCountThread = new PlayerCountThread();
        playerCountThread.start();

        Assemble assemble = new Assemble(this, new ScoreboardAdapter());
        assemble.setup();
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.VIPER);
    }

    private void setupListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ServerSelector(),
                new DoubleJump()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    public void setupCommands() {
        getCommand("hubreload").setExecutor(new ReloadCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());

    }

    private void setupBungee() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeecordListeners());
    }

    public static HubCore get() {
        return getPlugin(HubCore.class);
    }
}
