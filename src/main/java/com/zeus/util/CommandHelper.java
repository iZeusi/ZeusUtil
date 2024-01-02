package com.zeus.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHelper {

    String prefix = "";

    public CommandHelper() {
        File f = new File("plugins/ZeusUtil/config.yml");
        YamlConfiguration yamlConfig = saveConfig(f);


        prefix = yamlConfig.getString("Message Prefix");
    }

    private YamlConfiguration saveConfig(File f) {
        YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(f);
        try {
            yamlConfig.save(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return yamlConfig;
    }

    public boolean hasPermission(CommandSender p, String perm) {
        return p.isOp() || p.hasPermission("zu.admin") || p.hasPermission("zu." + perm);
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(colorize(prefix + message));
    }

    public String key(String playerName, int amount) {
        Player p = null;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.getName().equalsIgnoreCase(playerName)) {
                p = online;
            }
        }

        if (p != null) {
            File f = new File("plugins/ZeusUtil/config.yml");
            YamlConfiguration yamlConfig = saveConfig(f);

            String keyName = yamlConfig.getString("Monthly Key Name");
            String cmd = "crate key give " + p.getName() + " " + keyName + " " + amount;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            return "&aSent successfully";
        } else return "&cCould not find Player with that name. Are they online?";
    }

    public void rares(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRares &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&aFull list of Rares: &ehttps://shorturl.at/sBMP6&r");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void rules(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRules &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        chatNoPrefix(p, "&c - &fThe use of hacked clients, or \"hacks\" are not allowed.");
        chatNoPrefix(p, "&c - &fDo not spam chat with multiple messages.");
        chatNoPrefix(p, "&c - &fEnglish is the language to be used in global chat.");
        chatNoPrefix(p, "&c - &fWe do not condone racism, sexism, homophobia, or any other type or prejudice or discrimination.");
        chatNoPrefix(p, "");
        chatNoPrefix(p, "&aPlease view our full rules list at: &ehttps://shorturl.at/aILZ6&r");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void ranks(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRanks &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&eRank Name &7| &bVote Count &7| &aHomes ");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&ePeasant &7| &b0 Votes &7| &a1 Homes");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eFarmer &7| &b5 Votes &7| &a2 Homes");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eCommoner &7| &b15 Votes &7| &a3 Homes");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eSquire &7| &b45 Votes &7| &a4 Homes");
        sendCenteredMessage(p, "&7Unlocks: &aShop Plot");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eKnight &7| &b90 Votes &7| &a5 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/Craft");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eCommander &7| &b150 Votes &7| &a6 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/Condense");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eLord &7| &b250 Votes &7| &a7 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/Feed");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eBaron &7| &b500 Votes &7| &a8 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/ZE Name");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eEmperor &7| &b750 Votes &7| &a9 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/Repair Hand &7(15 Min Cooldown)");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eDeity &7| &b1000 Votes &7| &a10 Homes");
        sendCenteredMessage(p, "&7Unlocks: &a/Repair All");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void chatNoPrefix(Player p, String msg) {
        p.sendMessage(colorize(msg + "&r"));
    }

    public void chatPrefix(CommandSender p, String msg) {
        p.sendMessage(colorize(prefix + msg + "&r"));
    }

    public String colorize(String msg) {
        return translateHexColorCodes(
                "&#",
                "",
                org.bukkit.ChatColor.translateAlternateColorCodes('&', msg));
    }

    public String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            char COLOR_CHAR = ChatColor.COLOR_CHAR;
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    private final int CENTER_PX = 154;

    public void sendCenteredMessage(Player player, String message) {
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb + message);
    }
}
