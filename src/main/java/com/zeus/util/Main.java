package com.zeus.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("rules").setExecutor(new Commands());
        this.getCommand("ranks").setExecutor(new Commands());
        this.getCommand("rares").setExecutor(new Commands());
        this.getCommand("broad").setExecutor(new Commands());
        this.getCommand("key").setExecutor(new Commands());
        handleScheduledMsgs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void handleScheduledMsgs() {
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
