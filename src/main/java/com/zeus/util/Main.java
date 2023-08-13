package com.zeus.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Commands commands = new Commands();
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getCommand("rules").setExecutor(commands);
        this.getCommand("ranks").setExecutor(commands);
        this.getCommand("rares").setExecutor(commands);
        this.getCommand("broad").setExecutor(commands);
        handleSchedMsgs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void handleSchedMsgs() {
        CommandHelper cH = new CommandHelper();
        File file = new File("plugins/ZeusUtil/scheduled.yml");
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(file);
        final int[] count = {0};
        try {
            yamlConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()->{
            List<String> messages = new ArrayList<>();
            Object[] fields = yamlConfig.getConfigurationSection("Messages").getKeys(false).toArray();
            if (fields.length - 1 < count[0]) count[0] = 0;
            int currentCount = 0;
            for (Object key : fields) {
                if (currentCount == count[0]) {
                    messages = yamlConfig.getStringList("Messages." + key);
                }
                currentCount++;
            }
            if (!messages.isEmpty()) {
                for (String msg : messages) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        cH.sendCenteredMessage(target, cH.colorize(msg));
                    }
                }
            }
            count[0]++;
        }, 20*60*5L, 20*60*15L);

    }
}
