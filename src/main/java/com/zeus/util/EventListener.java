package com.zeus.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        String joinText = ChatColor.translateAlternateColorCodes(
                '&',
                "&7[ &a+ &7] &b%essentials_nickname%"
        );

        joinText = PlaceholderAPI.setPlaceholders(e.getPlayer(), joinText);
        Bukkit.broadcastMessage(joinText);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
        String quitText = ChatColor.translateAlternateColorCodes(
                '&',
                "&7[ &c- &7] &b%essentials_nickname%"
        );

        quitText = PlaceholderAPI.setPlaceholders(e.getPlayer(), quitText);
        Bukkit.broadcastMessage(quitText);
    }
}
