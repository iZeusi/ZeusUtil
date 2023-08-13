package com.zeus.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    CommandHelper cH;

    public ChatListener() {
        cH = new CommandHelper();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage() + "&r";
        char ch = msg.charAt(0);
        if (Character.valueOf(ch).equals('@') && cH.hasPermission(p, "chat.admin")) {
            msg = msg.substring(1);
            //Admin Chat
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (cH.hasPermission(target, "chat.admin")) {
                    target.sendMessage(cH.colorize("&7[&eE&6M&7-&cADMIN&7]&r " + p.getDisplayName() + "&r&7:&r " + msg));
                }
            }
            e.setCancelled(true);

        } else if (Character.valueOf(ch).equals('#') && cH.hasPermission(p, "chat.staff")) {
            msg = msg.substring(1);
            //Staff Chat
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (cH.hasPermission(target, "chat.staff")) {
                    target.sendMessage(cH.colorize("&7[&eE&6M&7-&bSTAFF&7]&r " + p.getDisplayName() + "&r&7:&r " + msg));
                }
            }
            e.setCancelled(true);
        }
    }
}
